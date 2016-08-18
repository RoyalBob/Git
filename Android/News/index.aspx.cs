using System;
using System.IO;
using System.Net;
using System.Text;
using System.Web.UI;
using System.Text.RegularExpressions;  //正则表达式  
using MySql.Data.MySqlClient;

public partial class _Default : Page
{
    string[] newstitle = new string[200];
    string[] newslink = new string[200];
    string mynewscontent = "";
    string[] newscontent = new string[200];
    int i = 0;
    MySqlConnection conn = new MySqlConnection("Server=qdm166048328.my3w.com; Database=qdm166048328_db; User ID=qdm166048328; Password=mfj1003mfj");
    protected void Page_Load(object sender, EventArgs e)
    {
        Session["publishdate"] = DateTime.Today.GetDateTimeFormats('D')[0].ToString();
        //准备用于发起请求的HttpWebRequest对象
        conn.Open();
        MySqlCommand cmd = new MySqlCommand("select * from newsdate where date='" + DateTime.Today.GetDateTimeFormats('D')[0].ToString() + "'", conn);
        MySqlDataReader reader = cmd.ExecuteReader();
        if (reader.HasRows)
        {
            reader.Close();
            conn.Close();
        }
        else
        {
            reader.Close();
            conn.Close();
            if (getNews())
            {
                conn.Open();
                MySqlCommand cmd2 = new MySqlCommand("insert into newsdate(date) values ('" + DateTime.Today.GetDateTimeFormats('D')[0] + "')", conn);
                cmd2.ExecuteNonQuery();
                conn.Close();
            }
        }
        timer1.Enabled = true;
    }

    private bool getNews()
    {
        HttpWebRequest httpRequest = (HttpWebRequest)WebRequest.Create("http://news.qq.com/");
        HttpWebResponse httpResponse = (HttpWebResponse)httpRequest.GetResponse();
        Stream receiveStream = httpResponse.GetResponseStream();
        StreamReader streamReader = new StreamReader(receiveStream, Encoding.Default);
        string htmlstr = streamReader.ReadToEnd();  //htmlstr是新闻标题页源代码
        streamReader.Close();
        string filename = "", publishdate="", type="";
        string sql = "";
        string[] picimg = new string[200];
        string[] pictxt = new string[200];
        int picimgPos = 0, pictxtPos = 0;
        try
        {
            //正则表达式获取<a href></a>内容url
            MatchCollection matches = Regex.Matches(htmlstr, "<a target=\"_blank\" class=\"linkto\" href=\"(.*?).htm\"(.*?)>(.*?)</a>", RegexOptions.IgnoreCase);
            foreach (Match match in matches)
            {
                //matches2 获取 matches 中的新闻标题
                MatchCollection matches2 = Regex.Matches(match.Value.ToString(), "(>)(.*?)(</a>)", RegexOptions.IgnoreCase);
                foreach (Match match2 in matches2)
                {
                    filename = match2.Value.ToString().Substring(1).Replace("</a>", "");
                    sql = "insert into files(filename,type,typeid,filecontent,publisher,publishdate,link) values('" + filename + "',"; 
                }

                //matches3 获取 matches 中的新闻网址
                MatchCollection matches3 = Regex.Matches(match.Value.ToString(), "href=\"([^\"]*?)\"", RegexOptions.IgnoreCase);
                foreach (Match match3 in matches3)
                {
                    string filecontent = "";
                    picimgPos = 0;
                    pictxtPos = 0;
                    string newshtml = match3.Value.ToString().Substring(5).Replace("\"", "");   //newshtml 是新闻网址
                    HttpWebRequest httpRequest2 = (HttpWebRequest)WebRequest.Create(newshtml);
                    HttpWebResponse httpResponse2;
                    try
                    {
                        httpResponse2 = (HttpWebResponse)httpRequest2.GetResponse();
                    }
                    catch(WebException ex)
                    {
                        httpResponse2 = (HttpWebResponse)ex.Response;
                    }
                    Stream receiveStream2 = httpResponse2.GetResponseStream();
                    StreamReader streamReader2 = new StreamReader(receiveStream2, Encoding.Default);
                    string htmlstr2 = streamReader2.ReadToEnd();    //htmlstr是新闻详情页源代码
                    streamReader2.Close();

                    //matches5 获取新闻内的图片
                    MatchCollection matches5 = Regex.Matches(htmlstr2, "<img alt=(.*?) src=\"(.*?)\">", RegexOptions.IgnoreCase);
                    foreach (Match match5 in matches5)
                    {
                        picimg[picimgPos] = match5.Value.ToString();
                        picimgPos++;
                    }
                    //matches6 获取新闻内图片的说明
                    MatchCollection matches6 = Regex.Matches(htmlstr2, "<p class=pictext align=center>(.*?)</p>", RegexOptions.IgnoreCase);
                    foreach (Match match6 in matches6)
                    {
                        pictxt[pictxtPos] = match6.Value.ToString();
                        pictxtPos++;
                    }

                    //matches7 获取新闻发布时间
                    MatchCollection matches7 = Regex.Matches(htmlstr2, "<span class=\"article-time\">(.*?)</span>", RegexOptions.IgnoreCase);
                    foreach (Match match7 in matches7)
                    {
                        MatchCollection matches8 = Regex.Matches(match7.Value.ToString(), ">(.*?)</span>", RegexOptions.IgnoreCase);
                        foreach (Match match8 in matches8)
                        {
                            publishdate = match8.Value.ToString().Substring(1).Replace("</span>", "");
                        }
                    }

                    //matches9 获取新闻类型
                    MatchCollection matches9 = Regex.Matches(htmlstr2, "<a target=\"_blank\" accesskey=\"5\" href=\"(.*?)\" title=\"(.*?)\">(.*?)</a>", RegexOptions.IgnoreCase);
                    foreach (Match match9 in matches9)
                    {
                        MatchCollection matches10 = Regex.Matches(match9.Value.ToString(), ">(.*?)</a>", RegexOptions.IgnoreCase);
                        foreach (Match match10 in matches10)
                        {
                            type = match10.Value.ToString().Substring(1).Replace("</a>", "");
                        }
                    }


                    for (int i = 0; i < (picimgPos > pictxtPos ? pictxtPos : picimgPos); i++)
                        filecontent = filecontent + "<div style=\"text-align:center\">" + picimg[i] + pictxt[i];
                    filecontent += "</div>";
                    MatchCollection matches4 = Regex.Matches(htmlstr2, "<P style=\"TEXT-INDENT: 2em\">(.*?)</P>", RegexOptions.IgnoreCase);
                    foreach (Match match4 in matches4)
                    {
                        filecontent = filecontent + "<div style=\"text-align:left\">" + match4.Value.ToString().Replace("'", "\\'");
                    }
                    filecontent = filecontent + "</div>";
                    sql += "'" + type + "'," + "10,'" + filecontent + "','bob','" + publishdate + "','" + newshtml + "')";

                    conn.Open();
                    MySqlCommand cmd = new MySqlCommand("select filename from files where filename='" + filename + "'", conn);
                    MySqlDataReader reader1 = cmd.ExecuteReader();
                    if (reader1.HasRows || filecontent.Equals("") || filecontent.Equals("</div></div>"))//|| filecontent.Length < 50)
                    {
                        reader1.Close();
                    }
                    else
                    {
                        reader1.Close();
                        MySqlCommand cmd1 = new MySqlCommand(sql, conn);
                        cmd1.ExecuteNonQuery();
                    }
                    conn.Close();
                }
            }
        }
        catch (Exception msg)   //异常处理  
        {
            Response.Write("<script>alert(" + msg.ToString() + ")</script>");
            return false;
        }
        return true;
    }

    private void linkToPost()
    {
        conn.Open();
        MySqlCommand cmd = new MySqlCommand("select * from files", conn);
        MySqlDataReader reader = cmd.ExecuteReader();
        int pos = 0;
        while(reader.Read())
        {
            newstitle[pos]=reader.GetString(1);
            newscontent[pos] = reader.GetString(4).Substring(0,200);
            newslink[pos] = "www.babalishi.cn/content.aspx?id=" + reader.GetString(0);
            pos++;
        }
        reader.Close();
        conn.Close();
        //准备用户验证数据
        string username = "15088860868";
        string password = "Xiaocaluo121";
        string usernamePassword = username + ":" + password;

        //准备调用的URL及需要POST的数据
        string url = "https://api.weibo.com/2/statuses/update.json";
        //matches2 获取 matches 中的新闻标题
        MatchCollection matchesContent = Regex.Matches(newscontent[i], "[^>]+$", RegexOptions.IgnoreCase);
        foreach (Match matchContent in matchesContent)
        {
            mynewscontent = matchContent.Value.ToString() + "......";
        }
        string t_news = string.Format("{0}: \n{1}  \n{2}", newstitle[i], newslink[i], mynewscontent);
        if (i <= pos)
            i++;
        else
            timer1.Enabled = false;
        string data = "source=2613388209&status=" + System.Web.HttpUtility.UrlEncode(t_news);

        //准备用于发起请求的HttpWebRequest对象
        HttpWebRequest httpRequest = (HttpWebRequest)WebRequest.Create(url);

        //准备用于用户验证的凭据
        CredentialCache myCache = new CredentialCache();
        myCache.Add(new Uri(url), "Basic", new NetworkCredential(username, password));
        httpRequest.Credentials = myCache;
        httpRequest.Headers.Add("Authorization", "Basic " + Convert.ToBase64String(new ASCIIEncoding().GetBytes(usernamePassword)));

        //发起POST请求
        httpRequest.Method = "POST";
        httpRequest.ContentType = "application/x-www-form-urlencoded";
        Encoding encoding = Encoding.ASCII;
        byte[] bytesToPost = encoding.GetBytes(data);
        httpRequest.ContentLength = bytesToPost.Length;
        Stream requestStream = httpRequest.GetRequestStream();
        requestStream.Write(bytesToPost, 0, bytesToPost.Length);
        requestStream.Close();

        ////获取服务端的响应内容
        //WebResponse wr = httpRequest.GetResponse();
        //Stream receiveStream = wr.GetResponseStream();
        //StreamReader reader = new StreamReader(receiveStream, Encoding.UTF8);
    }

    protected void timer1_Tick(object sender, EventArgs e)
    {
        linkToPost();
    }
}