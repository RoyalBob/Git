using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using System.Text.RegularExpressions;  //正则表达式  

namespace WebBrowser
{
    public partial class Tencent : Form
    {
        public Tencent()
        {
            InitializeComponent();
        }

        private void Tencent_Load(object sender, EventArgs e)
        {
            webBrowser_Tencent.Navigate("http://roll.news.qq.com/");         //显示网页  
            webBrowser_Tencent.ScriptErrorsSuppressed = true;            //屏蔽脚本错误信息
        }

        String clickLink = "";
        private void listBox1_MouseClick(object sender, MouseEventArgs e)
        {
            int index = listBox1.IndexFromPoint(e.X, e.Y);
            listBox1.SelectedIndex = index;
            if (listBox1.SelectedIndex != -1)
            {
                clickLink = listBox1.SelectedItem.ToString();
                webBrowser_Tencent.Navigate(clickLink);
            }
        }

        private void btn_获取新闻链接_Click(object sender, EventArgs e)
        {
            HtmlElement html = webBrowser_Tencent.Document.Body;      //定义HTML元素  
            string str = html.OuterHtml;                       //获取当前元素的HTML代码  
            MatchCollection matches;                           //定义正则表达式匹配集合  

            try
            {
                listBox1.Items.Clear();
                //正则表达式获取<a href></a>内容url  
                matches = Regex.Matches(str, "<a href=\"([^\\#\"]*?).htm\" target=_blank (.*?)>(.*?)</a>", RegexOptions.IgnoreCase);
                foreach (Match match in matches)
                {
                    string url = match.Value.ToString();
                    MatchCollection matches2;
                    matches2 = Regex.Matches(url, "href=\"([^\"]*?)\"", RegexOptions.IgnoreCase);
                    foreach (Match match2 in matches2)
                        listBox1.Items.Add(match2.Value.ToString().Substring(5).Replace("\"", ""));
                    //listBox1.Items.Add(match.Value.ToString());
                }
                //webBrowser1.Refresh();
            }
            catch (Exception msg) { MessageBox.Show(msg.Message); }   //异常处理  
        }

        private void btn_返回_Click(object sender, EventArgs e)
        {
            clickLink = "";
            webBrowser_Tencent.GoBack();
        }

        private void webBrowser_Tencent_NewWindow(object sender, CancelEventArgs e)
        {
            e.Cancel = true;
        }

        private void webBrowser_Tencent_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {

        }
    }
}
