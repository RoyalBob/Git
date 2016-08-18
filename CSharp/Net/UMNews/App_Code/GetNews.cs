using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Text.RegularExpressions;  //正则表达式  
using System.IO;
using System.Net;
using System.Text;
using MySql.Data.MySqlClient;

/// <summary>
/// getNews 的摘要说明
/// </summary>
public class GetNews
{
    public GetNews()
    {
        //
        // TODO: 在此处添加构造函数逻辑
        //
    }

    public static void getTencentNews(string url, MySqlConnection conn)
    {
        string filename = "", publishdate = "", typename = "", sql = "";
        string[] picimg = new string[200];
        string[] pictxt = new string[200];
        int picimgPos = 0, pictxtPos = 0;

        HttpWebRequest httpRequest = (HttpWebRequest)WebRequest.Create(url);
        HttpWebResponse httpResponse = (HttpWebResponse)httpRequest.GetResponse();
        Stream receiveStream = httpResponse.GetResponseStream();
        StreamReader streamReader = new StreamReader(receiveStream, Encoding.Default);
        string htmlstr = streamReader.ReadToEnd();  //htmlstr是新闻标题页源代码
        streamReader.Close();
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
                    sql = "insert into files(filename,typename,typeid,filecontent,publisher,publishdate,link) values('" + filename + "',";
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
                    catch (WebException ex)
                    {
                        httpResponse2 = (HttpWebResponse)ex.Response;
                    }
                    Stream receiveStream2 = httpResponse2.GetResponseStream();
                    StreamReader streamReader2 = new StreamReader(receiveStream2, Encoding.Default);
                    string htmlstr2 = streamReader2.ReadToEnd();    //htmlstr2是新闻详情页的源代码
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
                            typename = match10.Value.ToString().Substring(1).Replace("</a>", "");
                        }
                    }

                    for (int i = 0; i < (picimgPos > pictxtPos ? pictxtPos : picimgPos); i++)
                        filecontent = filecontent + picimg[i] + pictxt[i];
                    MatchCollection matches4 = Regex.Matches(htmlstr2, "<P style=\"TEXT-INDENT: 2em\">(.*?)</P>", RegexOptions.IgnoreCase);
                    foreach (Match match4 in matches4)
                    {
                        filecontent = filecontent + match4.Value.ToString().Replace("'", "\\'");
                    }
                    conn.Open();
                    MySqlDataReader reader_select_typeid = new MySqlCommand("select typeid from type where typename='" + typename + "'", conn).ExecuteReader();
                    int typeid = 99;
                    while (reader_select_typeid.Read())
                    {
                        if (reader_select_typeid.HasRows)
                            typeid = reader_select_typeid.GetInt32(0);
                        else
                            new MySqlCommand("insert into type(typename) values('" + typename + "')", conn).ExecuteNonQuery();
                    }
                    reader_select_typeid.Close();
                    MySqlDataReader reader_typeid = new MySqlCommand("select typeid from type where typename='" + typename + "'", conn).ExecuteReader();
                    if (reader_typeid.Read())
                        typeid = reader_typeid.GetInt32(0);
                    reader_typeid.Close();
                    sql += "'" + typename + "'," + typeid + ",'" + filecontent + "','UM','" + publishdate + "','" + newshtml + "')";

                    MySqlDataReader reader_filename = new MySqlCommand("select filename from files where filename='" + filename + "'", conn).ExecuteReader();
                    if (reader_filename.HasRows || filecontent.Equals("") || filecontent.Length < 300)
                    {
                        reader_filename.Close();
                    }
                    else
                    {
                        reader_filename.Close();
                        new MySqlCommand(sql, conn).ExecuteNonQuery();
                    }
                    conn.Close();
                }
            }
        }
        catch (Exception msg) { }
    }

    public static void getAoyunNews(string url, MySqlConnection conn)
    {
        string filename = "", publishdate = "", sql = "";
        string[] picimg = new string[200];
        int picimgPos = 0;

        HttpWebRequest httpRequest = (HttpWebRequest)WebRequest.Create(url);
        HttpWebResponse httpResponse = (HttpWebResponse)httpRequest.GetResponse();
        Stream receiveStream = httpResponse.GetResponseStream();
        StreamReader streamReader = new StreamReader(receiveStream, Encoding.Default);
        string htmlstr = streamReader.ReadToEnd();  //htmlstr是新闻标题页源代码
        streamReader.Close();
        try
        {
            //正则表达式获取<a href></a>内容url
            MatchCollection matches = Regex.Matches(htmlstr, "<a target=\"_blank\" href=\"(.*?).htm\"(.*?)>(.*?)</a>", RegexOptions.IgnoreCase);
            foreach (Match match in matches)
            {
                //matches2 获取 matches 中的新闻标题
                MatchCollection matches2 = Regex.Matches(match.Value.ToString(), "(>)(.*?)(</a>)", RegexOptions.IgnoreCase);
                foreach (Match match2 in matches2)
                {
                    filename = match2.Value.ToString().Substring(1).Replace("</a>", "");
                    sql = "insert into files(filename,typename,typeid,filecontent,publisher,publishdate,link) values('" + filename + "',";
                }

                //matches3 获取 matches 中的新闻网址
                MatchCollection matches3 = Regex.Matches(match.Value.ToString(), "href=\"([^\"]*?)\"", RegexOptions.IgnoreCase);
                foreach (Match match3 in matches3)
                {
                    string filecontent = "";
                    picimgPos = 0;
                    string newshtml = match3.Value.ToString().Substring(5).Replace("\"", "");   //newshtml 是新闻网址
                    HttpWebRequest httpRequest2 = (HttpWebRequest)WebRequest.Create(newshtml);
                    HttpWebResponse httpResponse2;
                    try
                    {
                        httpResponse2 = (HttpWebResponse)httpRequest2.GetResponse();
                    }
                    catch (WebException ex)
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

                    //matches7 获取新闻发布时间
                    MatchCollection matches7 = Regex.Matches(htmlstr2, "<span class=\"a_time\">(.*?)</span>", RegexOptions.IgnoreCase);
                    foreach (Match match7 in matches7)
                    {
                        MatchCollection matches8 = Regex.Matches(match7.Value.ToString(), ">(.*?)</span>", RegexOptions.IgnoreCase);
                        foreach (Match match8 in matches8)
                        {
                            publishdate = match8.Value.ToString().Substring(1, 10).Replace("</span>", "");
                        }
                    }

                    for (int i = 0; i < picimgPos; i++)
                        filecontent = filecontent + picimg[i];
                    //matches4 获取新闻正文内容
                    MatchCollection matches4 = Regex.Matches(htmlstr2, "<[pP] style=\"TEXT-INDENT: 2em\">(.*?)</[pP]>", RegexOptions.IgnoreCase);
                    foreach (Match match4 in matches4)
                    {
                        filecontent = filecontent + match4.Value.ToString().Replace("'", "\\'");
                    }
                    sql += "'" + "奥运体育" + "'," + 9 + ",'" + filecontent + "','UM','" + publishdate + "','" + newshtml + "')";

                    conn.Open();
                    MySqlDataReader reader_filename = new MySqlCommand("select filename from files where filename='" + filename + "'", conn).ExecuteReader();
                    if (reader_filename.HasRows || filecontent.Equals("") || filecontent.Length < 80)
                    {
                        reader_filename.Close();
                    }
                    else
                    {
                        reader_filename.Close();
                        new MySqlCommand(sql, conn).ExecuteNonQuery();
                    }
                    conn.Close();
                }
            }
        }
        catch (Exception msg) { conn.Close(); }
    }

    public static void getFenghuangNews(string url, MySqlConnection conn)
    {
        string filename = "", publishdate = "", sql = "";
        string[] picimg = new string[200];
        int picimgPos = 0;

        HttpWebRequest httpRequest = (HttpWebRequest)WebRequest.Create(url);
        HttpWebResponse httpResponse = (HttpWebResponse)httpRequest.GetResponse();
        Stream receiveStream = httpResponse.GetResponseStream();
        StreamReader streamReader = new StreamReader(receiveStream, Encoding.UTF8);
        string htmlstr = streamReader.ReadToEnd();  //htmlstr是新闻标题页源代码
        streamReader.Close();
        try
        {
            //正则表达式获取<a href></a>内容url
            MatchCollection matches = Regex.Matches(htmlstr, "[<][h][2][>]<a href=\"(.*?)\" target=\"_blank\" title=\"(.*?)\">(.*?)</a>", RegexOptions.IgnoreCase);
            foreach (Match match in matches)
            {
                //matches2 获取 matches 中的新闻标题
                MatchCollection matches2 = Regex.Matches(match.Value.ToString(), ">[^<](.*?)</a>", RegexOptions.IgnoreCase);
                foreach (Match match2 in matches2)
                {
                    filename = match2.Value.ToString().Substring(1).Replace("</a>", "");
                    sql = "insert into files(filename,typename,typeid,filecontent,publisher,publishdate,link) values('" + filename + "',";
                }

                //matches3 获取 matches 中的新闻网址
                MatchCollection matches3 = Regex.Matches(match.Value.ToString(), "href=\"([^\"]*?)\"", RegexOptions.IgnoreCase);
                foreach (Match match3 in matches3)
                {
                    string filecontent = "";
                    picimgPos = 0;
                    string newshtml = match3.Value.ToString().Substring(5).Replace("\"", "");   //newshtml 是新闻网址
                    HttpWebRequest httpRequest2 = (HttpWebRequest)WebRequest.Create(newshtml);
                    HttpWebResponse httpResponse2;
                    try
                    {
                        httpResponse2 = (HttpWebResponse)httpRequest2.GetResponse();
                    }
                    catch (WebException ex)
                    {
                        httpResponse2 = (HttpWebResponse)ex.Response;
                    }
                    Stream receiveStream2 = httpResponse2.GetResponseStream();
                    StreamReader streamReader2 = new StreamReader(receiveStream2, Encoding.UTF8);
                    string htmlstr2 = streamReader2.ReadToEnd();    //htmlstr是新闻详情页源代码
                    streamReader2.Close();

                    //matches5 获取新闻内的图片
                    MatchCollection matches5 = Regex.Matches(htmlstr2, "<img src=\"(.*?)\" width=\"(.*?)\" height=\"(.*?)\" alt=\"(.*?)\" />", RegexOptions.IgnoreCase);
                    foreach (Match match5 in matches5)
                    {
                        picimg[picimgPos] = match5.Value.ToString();
                        picimgPos++;
                    }

                    //matches7 获取新闻发布时间
                    MatchCollection matches7 = Regex.Matches(htmlstr2, "<span itemprop=\"datePublished\" class=\"ss01\">(.*?)</span>", RegexOptions.IgnoreCase);
                    foreach (Match match7 in matches7)
                    {
                        MatchCollection matches8 = Regex.Matches(match7.Value.ToString(), ">(.*?)</span>", RegexOptions.IgnoreCase);
                        foreach (Match match8 in matches8)
                            publishdate = match8.Value.ToString().Substring(1, 10).Replace("</span>", "").Replace("年","-").Replace("月","-");
                    }

                    for (int i = 0; i < picimgPos; i++)
                        filecontent = filecontent + picimg[i];
                    //matches4 获取新闻正文内容
                    MatchCollection matches4 = Regex.Matches(htmlstr2, "<div id=\"main_content\" class=\"js_selection_area\">(.*?)</div>", RegexOptions.Singleline);
                    foreach (Match match4 in matches4)
                    {
                        MatchCollection matches_newscontent = Regex.Matches(match4.Value.ToString(), "<[pP]>(.*?)</[pP]>", RegexOptions.Singleline);
                        foreach (Match match_newscontent in matches_newscontent)
                            filecontent = filecontent + match_newscontent.Value.ToString().Replace("'", "\\'");
                    }
                        
                    sql += "'" + "奥运体育" + "'," + 9 + ",'" + filecontent + "','UM','" + publishdate + "','" + newshtml + "')";

                    conn.Open();
                    MySqlDataReader reader_filename = new MySqlCommand("select filename from files where filename='" + filename + "'", conn).ExecuteReader();
                    if (reader_filename.HasRows || filecontent.Equals("") || filecontent.Length < 80)
                    {
                        reader_filename.Close();
                    }
                    else
                    {
                        reader_filename.Close();
                        new MySqlCommand(sql, conn).ExecuteNonQuery();
                    }
                    conn.Close();
                }
            }
        }
        catch (Exception msg)
        {
            Console.Write("FenghuangNews,Error:" + msg.ToString());
            Console.WriteLine();
            if (conn != null)
                conn.Close();
        }
    }
}