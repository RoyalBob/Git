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
        int typeid1 = 0;
        if (type1.Text != "")
        {
            string str1 = "select typeid from type where typename like '%" + type1.Text + "%'";
            MySqlCommand cmd1 = new MySqlCommand(str1, conn);
            MySqlDataReader dr1 = cmd1.ExecuteReader();
            if (dr1.Read())
            {
                typeid1 = dr1.GetInt32(0);
            }
            dr1.Close();
        }
            
        /*type2*/
        int typeid2 = 0;
        if (type2.Text != "")
        {
            string str2 = "select typeid from type where typename like '%" + type2.Text + "%'";
            MySqlCommand cmd2 = new MySqlCommand(str2, conn);
            MySqlDataReader dr2 = cmd2.ExecuteReader();
            if (dr2.Read())
            {
                typeid2 = dr2.GetInt32(0);
            }
            dr2.Close();
        }
            
        conn.Close();

        if (type1.Text == "" && type2.Text == "") {
            SqlDataSource1.SelectCommand = "SELECT * FROM files ORDER BY publishdate DESC";
        }
        else {
            SqlDataSource1.SelectCommand = "SELECT * FROM files WHERE typeid=" + typeid1 + " OR typeid=" + typeid2 + " ORDER BY publishdate DESC ";
        }
        
    }

    protected void 奥运_Click(object sender, EventArgs e)
    {
        if (type1.Text != 奥运.Text && type2.Text != 奥运.Text)
        {
            if (type1.Text == "")
            {
                type1.Text = 奥运.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 奥运.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void 国际_Click(object sender, EventArgs e)
    {
        if (type1.Text != 国际.Text&& type2.Text != 国际.Text) {
            if (type1.Text == "") {
                type1.Text = 国际.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            } else {
                type2.Text = 国际.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void 社会_Click(object sender, EventArgs e)
    {
        if (type1.Text != 社会.Text && type2.Text != 社会.Text) {
            if (type1.Text == "")
            {
                type1.Text = 社会.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 社会.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void 时政_Click(object sender, EventArgs e)
    {
        if (type1.Text != 时政.Text && type2.Text != 时政.Text)
        {
            if (type1.Text == "")
            {
                type1.Text = 时政.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 时政.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void 各地_Click(object sender, EventArgs e)
    {
        if (type1.Text != 各地.Text && type2.Text != 各地.Text)
        {
            if (type1.Text == "")
            {
                type1.Text = 各地.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 各地.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void 港澳台_Click(object sender, EventArgs e)
    {
        if (type1.Text != 港澳台.Text && type2.Text != 港澳台.Text)
        {
            if (type1.Text == "")
            {
                type1.Text = 港澳台.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 港澳台.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void 经济_Click(object sender, EventArgs e)
    {
        if (type1.Text != 经济.Text && type2.Text != 经济.Text)
        {
            if (type1.Text == "")
            {
                type1.Text = 经济.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 经济.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }


    protected void 军事_Click(object sender, EventArgs e)
    {
        if (type1.Text != 军事.Text && type2.Text != 军事.Text)
        {
            if (type1.Text == "")
            {
                type1.Text = 军事.Text;
                type1.Visible = true;
                type1_b.Visible = true;
            }
            else
            {
                type2.Text = 军事.Text;
                type2.Visible = true;
                type2_b.Visible = true;
            }
        }
    }

    protected void quan_Click(object sender, EventArgs e)
    {
        type1.Text = "";
        type2.Text = "";
        type1.Visible = false;
        type1_b.Visible = false;
        type2.Visible = false;
        type2_b.Visible = false;
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
}