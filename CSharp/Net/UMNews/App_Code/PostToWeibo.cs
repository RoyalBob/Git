using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using MySql.Data.MySqlClient;

/// <summary>
/// PostToWeibo 的摘要说明
/// </summary>
public class PostToWeibo
{
    public PostToWeibo()
    {
        //
        // TODO: 在此处添加构造函数逻辑
        //
    }

    /// <summary>
    /// 发送t_news到微博
    /// </summary>
    /// <param name="source">app key</param>
    /// <param name="username">用户名</param>
    /// <param name="password">密码</param>
    /// <param name="t_news">需要发送的微博内容</param>
    /// <param name="conn">数据库连接</param>
    public static void RequestWeibo(string source, string username, string password, string t_news, MySqlConnection conn)
    {
        string data = "source=" + source + "&status=" + HttpUtility.UrlEncode(t_news);

        //准备用户验证数据
        string usernamePassword = username + ":" + password;

        //准备调用的URL及需要POST的数据
        string url = "https://api.weibo.com/2/statuses/update.json";

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

        //获取服务端的响应内容
        try
        {
            WebResponse wr = httpRequest.GetResponse();
            Stream receiveStream = wr.GetResponseStream();
            StreamReader reader = new StreamReader(receiveStream, Encoding.UTF8);
            receiveStream.Close();
        }
        catch (Exception e)
        {
            conn.Open();
            MySqlCommand cmd2 = new MySqlCommand("insert into test(msg, date) values ('" + e.ToString() + "','" + DateTime.Now.ToString() + "')", conn);
            cmd2.ExecuteNonQuery();
            conn.Close();
        }
    }
}