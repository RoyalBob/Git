<%@ Application Language="C#" %>
<%@ Import Namespace="MySql.Data.MySqlClient" %> 
<%@ Import Namespace="System.Text.RegularExpressions" %>  
<%@ Import Namespace="System.Net" %>  
<%@ Import Namespace="System.Text" %>  
<%@ Import Namespace="System.IO" %>

<script runat="server">
    string[] newstitle = new string[1000];
    string[] newslink = new string[1000];
    string[] newscontent = new string[1000];
    static int i = 0;
    int pos = 0;
    string mynewscontent = "";
    System.Timers.Timer timer1= new System.Timers.Timer();
    MySqlConnection conn = new MySqlConnection("Server=qdm166048328.my3w.com; Database=qdm166048328_db; User ID=qdm166048328; Password=mfj1003mfj");

    void Application_Start(object sender, EventArgs e)
    {
        // 在应用程序启动时运行的代码
        conn.Open();
        new MySqlCommand("insert into test(msg, date) values ('应用程序启动!','" +  DateTime.Now.ToString() + "')", conn).ExecuteNonQuery();

        MySqlDataReader reader = new MySqlCommand("select * from files where publishdate='" + DateTime.Today.ToString("yyyy-MM-dd") + "' ORDER BY typename='奥运体育' desc", conn).ExecuteReader();
        if(reader.HasRows)
        {
            while (reader.Read())
            {
                newstitle[pos] = reader.GetString(1);
                newscontent[pos] = reader.GetString(4);
                newslink[pos] = "http://www.babalishi.cn/content.aspx?id=" + reader.GetString(0);
                pos++;
            }
            reader.Close();
            conn.Close();
            GetNews.getAoyunNews(Url.AoyunUrl, conn);
            GetNews.getFenghuangNews(Url.FenghuangUrl, conn);
        }
        else
        {
            i = 0;
            reader.Close();
            new MySqlCommand("DELETE FROM files WHERE publishdate<CURDATE()-7", conn).ExecuteNonQuery();
            new MySqlCommand("DELETE FROM test WHERE date < date_sub(NOW(),INTERVAL 1 day)", conn).ExecuteNonQuery();
            new MySqlCommand("update sendid set sendid=" + i, conn).ExecuteNonQuery();
            conn.Close();
            GetNews.getTencentNews(Url.TencentUrl, conn);
            GetNews.getAoyunNews(Url.AoyunUrl, conn);
            GetNews.getFenghuangNews(Url.FenghuangUrl, conn);
        }

        conn.Open();
        MySqlDataReader reader3 = new MySqlCommand("select sendid from sendid", conn).ExecuteReader();
        if(reader3.Read())
            i = reader3.GetInt32(0);
        reader3.Close();
        conn.Close();

        timer1.Interval = 29 * 60 * 1000;
        if (i < pos - 1)
        {
            timer1.Enabled = true;
            timer1.Elapsed += new System.Timers.ElapsedEventHandler(run);
            timer1.Start();
        }
        else
        {
            i = 0;
            conn.Open();
            new MySqlCommand("update sendid set sendid=" + i, conn).ExecuteNonQuery();
            conn.Close();
            timer1.Enabled = false;
        }

    }

    void Application_End(object sender, EventArgs e)
    {
        //  在应用程序关闭时运行的代码
        string url = "http://www.babalishi.cn";
        HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
        HttpWebResponse rsp = (HttpWebResponse)req.GetResponse();
        string tmp = rsp.StatusDescription;

        conn.Open();
        new MySqlCommand("insert into test(msg, date) values ('应用程序关闭!重新打开状态:" + tmp + "','" +  DateTime.Now.ToString() + "')", conn).ExecuteNonQuery();
        conn.Close();
    }

    void Application_Error(object sender, EventArgs e)
    {
        // 在出现未处理的错误时运行的代码
        conn.Open();
        new MySqlCommand("insert into test(msg, date) values ('未处理的错误!" + e.ToString() + "','" +  DateTime.Now.ToString() + "')", conn).ExecuteNonQuery();
        conn.Close();
    }

    void Session_Start(object sender, EventArgs e)
    {
        // 在新会话启动时运行的代码
        conn.Open();
        new MySqlCommand("insert into test(msg, date) values ('新会话启动!','" +  DateTime.Now.ToString() + "')", conn).ExecuteNonQuery();
        conn.Close();
    }

    void Session_End(object sender, EventArgs e)
    {
        // 在会话结束时运行的代码。 
        // 注意: 只有在 Web.config 文件中的 sessionstate 模式设置为
        // InProc 时，才会引发 Session_End 事件。如果会话模式设置为 StateServer
        // 或 SQLServer，则不引发该事件。
        conn.Open();
        new MySqlCommand("insert into test(msg, date) values ('会话结束!','" +  DateTime.Now.ToString() + "')", conn).ExecuteNonQuery();
        conn.Close();
    }

    private void run(object sender, System.Timers.ElapsedEventArgs args)
    {
        string t_news = "";
        mynewscontent = "";
        GetNews.getAoyunNews(Url.AoyunUrl, conn);
        t_news = getNewscontent(i);
        PostToWeibo.RequestWeibo("3637160140", "13291921791","Xiaocaluo121", t_news, conn);
        try
        {
            i++;
            conn.Open();
            new MySqlCommand("update sendid set sendid=" + i, conn).ExecuteNonQuery();
            conn.Close();
        }
        catch(Exception msg){}
    }

    /// <summary>
    /// 获取第i条新闻的内容到t_news
    /// </summary>
    /// <param name="i">第i条新闻</param>
    private string getNewscontent(int i)
    {
        string weiboNews="";
        //matchesContent 获取新闻正文内容
        if(newscontent[i]!=null)
        {
            MatchCollection matchesContent = Regex.Matches(newscontent[i], "<P style=\"TEXT-INDENT: 2em\">(.*?)</P>", RegexOptions.IgnoreCase);
            foreach (Match matchContent in matchesContent)
            {
                MatchCollection matchesContent2 = Regex.Matches(matchContent.Value.ToString(), ">[^><](.*?)</P>", RegexOptions.IgnoreCase);
                foreach (Match matchContent2 in matchesContent2)
                {
                    string a = matchContent2.Value.ToString().Substring(1).Replace("</P>", "").Replace("<STRONG>", "").Replace("</STRONG>", "");
                    if (!a.Contains("keyword")&& !a.Contains("class=pictext")&& !a.Contains("align=center>"))
                        mynewscontent += a;
                }
            }
            weiboNews = string.Format("{0}    {1}    {2}", newstitle[i], mynewscontent.Substring(0, (mynewscontent.Length>80?80: mynewscontent.Length)) + "......", newslink[i]);
        }
        return weiboNews;
    }

</script>
