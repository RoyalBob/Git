using System;
using System.IO;
using System.Net;
using System.Text;
using System.Web.UI;
using System.Text.RegularExpressions;  //正则表达式  
using MySql.Data.MySqlClient;

public partial class _Default : Page
{
    MySqlConnection conn = new MySqlConnection("Server=qdm166048328.my3w.com; Database=qdm166048328_db; User ID=qdm166048328; Password=mfj1003mfj");
    protected void Page_Load(object sender, EventArgs e)
    {
        //准备用于发起请求的HttpWebRequest对象
        conn.Open();
        MySqlDataReader reader = new MySqlCommand("select * from files where publishdate='" + DateTime.Today.ToString("yyyy-MM-dd") + "'", conn).ExecuteReader();
        if (reader.HasRows)
        {
            reader.Close();
            conn.Close();
        }
        else
        {
            reader.Close();
            conn.Close();
            GetNews.getTencentNews(Url.TencentUrl, conn);
            GetNews.getAoyunNews(Url.AoyunUrl, conn);
            GetNews.getFenghuangNews(Url.FenghuangUrl, conn);
        }
    }

}