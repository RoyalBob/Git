using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using MySql.Data.MySqlClient;

public partial class type : System.Web.UI.Page
{
    MySqlConnection conn = new MySqlConnection("Server=qdm166048328.my3w.com; Database=qdm166048328_db; User ID=qdm166048328; Password=mfj1003mfj");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Page.IsPostBack == false) {
            type1.Text = "";
            type1.Visible = false;
            type1_b.Visible = false;
            type2.Visible = false;
            type2_b.Visible = false;
        }
    }

    protected void Page_PreRender(object sender, EventArgs e) {

        conn.Open();
        /*type1*/
        int typeid1=0;
        string str1 = "select typeid from type where typename='" + type1.Text + "'";
        MySqlCommand cmd1 = new MySqlCommand(str1, conn);
        MySqlDataReader dr1 = cmd1.ExecuteReader();
        if (dr1.Read())
        {
            typeid1 = dr1.GetInt32(0); 
        }
        dr1.Close();
        /*type2*/
        int typeid2=0;
        string str2 = "select typeid from type where typename='" + type2.Text + "'";
        MySqlCommand cmd2 = new MySqlCommand(str2, conn);
        MySqlDataReader dr2 = cmd2.ExecuteReader();
        if (dr2.Read())
        {
            typeid2 = dr2.GetInt32(0);
        }
        dr2.Close();
       
        if (type1.Text == ""& type2.Text == "") {
            SqlDataSource1.SelectCommand = "select * from files";
        }
        else {
            SqlDataSource1.SelectCommand = "select * from files where typeid=" + typeid1 + " or typeid=" + typeid2 + " ";
        }
        
       
    }
    protected void type1_b_Click(object sender, EventArgs e)
    {
        type1.Text = "";
        type1.Visible = false;
        type1_b.Visible = false;
    }

    protected void type2_b_Click(object sender, EventArgs e)
    {
        type2.Text = "";
        type2.Visible = false;
        type2_b.Visible = false;
    }

    protected void 社会新闻_Click(object sender, EventArgs e)
    {
        if (type1.Text != 社会新闻.Text&& type2.Text != 社会新闻.Text) {
            if (type1.Text == "") {
                type1.Text = 社会新闻.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            } else {
                type2.Text = 社会新闻.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void 时政新闻_Click(object sender, EventArgs e)
    {
        if (type1.Text != 时政新闻.Text && type2.Text != 时政新闻.Text) {
            if (type1.Text == "")
            {
                type1.Text = 时政新闻.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 时政新闻.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }



    //protected void IT_Click(object sender, EventArgs e)
    //{
    //    if (type1.Text != IT.Text && type2.Text != IT.Text)
    //    {
    //        if (type1.Text == "")
    //        {
    //            type1.Text = IT.Text;
    //            type1.Visible = true;
    //            type1_b.Visible = true;
    //        }
    //        else
    //        {
    //            type2.Text = IT.Text;
    //            type2.Visible = true;
    //            type2_b.Visible = true;
    //        }
    //    }
        
    //}

    //protected void Markdown_Click(object sender, EventArgs e)
    //{
    //    if (type1.Text != Markdown.Text && type2.Text != Markdown.Text)
    //    {
    //        if (type1.Text == "")
    //        {
    //            type1.Text = Markdown.Text;
    //            type1.Visible = true;
    //            type1_b.Visible = true;
    //        }
    //        else
    //        {
    //            type2.Text = Markdown.Text;
    //            type2.Visible = true;
    //            type2_b.Visible = true;
    //        }
    //    }
    //}

    //protected void java_Click(object sender, EventArgs e)
    //{
    //    if (type1.Text != java.Text && type2.Text != java.Text)
    //    {
    //        if (type1.Text == "")
    //        {
    //            type1.Text = java.Text;
    //            type1.Visible = true;
    //            type1_b.Visible = true;
    //        }
    //        else
    //        {
    //            type2.Text = java.Text;
    //            type2.Visible = true;
    //            type2_b.Visible = true;
    //        }
    //    }
    //}

    protected void quan_Click(object sender, EventArgs e)
    {
        type1.Text = "";
        type2.Text = "";
        type1.Visible = false;
        type1_b.Visible = false;
        type2.Visible = false;
        type2_b.Visible = false;
    }
}