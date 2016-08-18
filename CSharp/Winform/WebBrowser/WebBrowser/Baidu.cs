using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

//新添加命名空间
using System.Net;
using System.IO;
using System.Text.RegularExpressions;  //正则表达式  
using System.Timers;

namespace WebBrowser
{
    public partial class Baidu : Form
    {
        public Baidu()
        {
            InitializeComponent();
        }

        private void Baidu_Load(object sender, EventArgs e)
        {
            webBrowser1.Navigate("http://www.baidu.com");         //显示网页  
            webBrowser1.ScriptErrorsSuppressed = true;            //屏蔽脚本错误信息
        }

        //页面加载完成后执行此函数
        private void webBrowser_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            //定义html元素 通过ID获取控件值 (用户名 密码 登录按钮)
            HtmlElementCollection links = this.webBrowser1.Document.Links;
            foreach (HtmlElement link in links)
            {
                if (link.GetAttribute("OuterHtml") == "<a name=\"tj_login\" class=\"lb\" onclick=\"return false;\" href=\"https://passport.baidu.com/v2/?login&amp;tpl=mn&amp;u=http%3A%2F%2Fwww.baidu.com%2F\">登录</a>")
                {
                    link.InvokeMember("click"); //激发链接的点击事件
                    break;
                }
            }
            //定义html元素 通过Id获取控件值
            HtmlElement tbUserid = webBrowser1.Document.GetElementById("TANGRAM__PSP_8__userName");
            HtmlElement tbPasswd = webBrowser1.Document.GetElementById("TANGRAM__PSP_8__password");
            HtmlElement btnSubmit = webBrowser1.Document.GetElementById("TANGRAM__PSP_8__submit");

            //三个元素其一为空返回 加载后才执行赋值 否则会出现为null值的崩溃错误
            if (tbUserid == null || tbPasswd == null || btnSubmit == null)  { return; }

            //设置元素value属性值 (用户名 密码值)
            tbUserid.SetAttribute("value", "15088860868");
            tbPasswd.SetAttribute("value", "Xiaocaluo121");
            
            //执行元素的方法：如click submit
            btnSubmit.InvokeMember("click");
            
        }

        private void btn_获取新闻链接_Click(object sender, EventArgs e)
        {
            Baidu_Load(sender, e);
            timer1.Interval = 2000;
            timer1.Start();
            HtmlElement html = webBrowser1.Document.Body;      //定义HTML元素  
            string str = html.OuterHtml;                       //获取当前元素的HTML代码  
            MatchCollection matches;                           //定义正则表达式匹配集合  

            try
            {
                //正则表达式获取<a href></a>内容url  
                matches = Regex.Matches(str, "<a title=\"(.*?)\" class=\"s-yahei\" href=\"([^\"]*?)\" (.*?)>(.*?)</a>", RegexOptions.IgnoreCase);
                foreach (Match match in matches)
                {
                    string url = match.Value.ToString();
                    MatchCollection matches2;
                    matches2 = Regex.Matches(url, "data-link=\"([^\"]*?)\"", RegexOptions.IgnoreCase);
                    foreach (Match match2 in matches2)
                        listBox1.Items.Add(match2.Value.ToString().Substring(10).Replace("\"", ""));
                }
                webBrowser1.Refresh();
            }
            catch (Exception msg) { MessageBox.Show(msg.Message); }   //异常处理  
        }

        String clickLink = "";
        private void listBox1_MouseClick(object sender, MouseEventArgs e)
        {
            timer1.Stop();
            int index = listBox1.IndexFromPoint(e.X, e.Y);
            listBox1.SelectedIndex = index;
            if (listBox1.SelectedIndex != -1)
            {
                clickLink = listBox1.SelectedItem.ToString();
                webBrowser1.Navigate(clickLink);
            }
        }

        private void btn_停止获取新闻链接_Click(object sender, EventArgs e)
        {
            timer1.Stop();
        }

        private void btn_返回_Click(object sender, EventArgs e)
        {
            timer1.Stop();
            clickLink = "";
            webBrowser1.GoBack();
        }

        private void btn_首页_Click(object sender, EventArgs e)
        {
            timer1.Stop();
            listBox1.Items.Clear();
            Baidu_Load(sender, e);
        }

        private void btn_发布微博_Click(object sender, EventArgs e)
        {
            Weibo weibo = new Weibo();
            weibo.Show();
        }

        private void btn_发布当前链接到微博_Click(object sender, EventArgs e)
        {
            if (!clickLink.Equals(""))
            {
                linkToPost();
                clickLink = "";
            }
            else
                MessageBox.Show("空白链接,未发送!");
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            btn_获取新闻链接_Click(sender, e);
        }

        private void linkToPost()
        {
            //准备用户验证数据
            string username = "1508886....";
            string password = ".....";
            string usernamePassword = username + ":" + password;

            //准备调用的URL及需要POST的数据
            string url = "https://api.weibo.com/2/statuses/update.json";
            string news_title = "Link";
            string t_news = string.Format("{0}:{1}", news_title, clickLink);
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

            //获取服务端的响应内容
            WebResponse wr = httpRequest.GetResponse();
            Stream receiveStream = wr.GetResponseStream();
            StreamReader reader = new StreamReader(receiveStream, Encoding.UTF8);
            MessageBox.Show("发布链接成功！\n" + clickLink + "\n以下是服务器响应返回的信息:\n" + reader.ReadToEnd());
            
        }

        private void btn_JavaScriptTest_Click(object sender, EventArgs e)
        {
            DirectoryInfo dir = Directory.GetParent(AppDomain.CurrentDomain.BaseDirectory);
            string path = dir.Parent.Parent.FullName + "/JavaScript1.js";
            string str2 = File.ReadAllText(path);
            string fun = string.Format(@"sayHello('{0}')", textBox1.Text.Trim());
            string result = ExecuteScript(fun, str2);
            MessageBox.Show(result);
        }

        /// <summary>
        /// 执行JS
        /// </summary>
        /// <param name="sExpression">参数体</param>
        /// <param name="sCode">JavaScript代码的字符串</param>
        /// <returns></returns>
        private string ExecuteScript(string sExpression, string sCode)
        {
            MSScriptControl.ScriptControl scriptControl = new MSScriptControl.ScriptControl();
            scriptControl.UseSafeSubset = true;
            scriptControl.Language = "JScript";
            scriptControl.AddCode(sCode);
            try
            {
                string str = scriptControl.Eval(sExpression).ToString();
                return str;
            }
            catch (Exception ex)
            {
                string str = ex.Message;
            }
            return null;
        }

        private void webBrowser1_NewWindow(object sender, CancelEventArgs e)
        {
            e.Cancel = true;
        }

        private void btn_腾讯_Click(object sender, EventArgs e)
        {
            Tencent tencent = new Tencent();
            tencent.Show();
        }
    }
}
