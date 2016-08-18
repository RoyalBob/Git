using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.IO;

namespace WebBrowser
{
    public partial class Weibo : Form
    {
        public Weibo()
        {
            InitializeComponent();
        }

        private void btn_发布_Click(object sender, EventArgs e)
        {
            //准备用户验证数据
            string username = "1508886....";
            string password = ".....";
            string usernamePassword = username + ":" + password;

            //准备调用的URL及需要POST的数据
            string url = "https://api.weibo.com/2/statuses/update.json";
            /**
             * 带链接的url
             */
            //string news_title = "VS2010网剧合集：讲述程序员的爱情故事";
            //int news_id = 62747;
            //string t_news = string.Format("{0}，http://news.cnblogs.com/n/{1}/", news_title, news_id);
            //string data = "source=2613388209&status=" + System.Web.HttpUtility.UrlEncode(t_news);//textBox1.Text.ToString();
            string data = "source=2613388209&status=" + textBox1.Text.ToString() + "&lat=+29.8&long=+121.6";
            //29.8277400000,121.6037230000

            //准备用于发起请求的HttpWebRequest对象
            WebRequest webRequest = WebRequest.Create(url);
            HttpWebRequest httpRequest = webRequest as HttpWebRequest;

            //准备用于用户验证的凭据
            CredentialCache myCache = new CredentialCache();
            myCache.Add(new Uri(url), "Basic", new NetworkCredential(username, password));
            httpRequest.Credentials = myCache;
            httpRequest.Headers.Add("Authorization", "Basic " +
            Convert.ToBase64String(new ASCIIEncoding().GetBytes(usernamePassword)));

            //发起POST请求
            httpRequest.Method = "POST";
            httpRequest.ContentType = "application/x-www-form-urlencoded";
            Encoding encoding = Encoding.ASCII;
            byte[] bytesToPost = encoding.GetBytes(data);
            httpRequest.ContentLength = bytesToPost.Length;
            Stream requestStream = httpRequest.GetRequestStream();
            requestStream.Write(bytesToPost, 0, bytesToPost.Length);
            requestStream.Close();

            MessageBox.Show("发布成功！");
            this.Close();

        }
    }
}
