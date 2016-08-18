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

public partial class MasterPage : System.Web.UI.MasterPage
{
    MySqlConnection conn = new MySqlConnection("Server=qdm166048328.my3w.com; Database=qdm166048328_db; User ID=qdm166048328; Password=mfj1003mfj");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["username"] != null)
        {
            this.sidebarSign.Visible = false;
            this.sidebarMain.Visible = true;
            //this.sidebarMain.Style.Add("display", "block");
            this.Username.Text = (string)Session["username"];
            if (this.Username.Text == "admin")
            {
                SidebarAdmin.Visible = true;
                sidebarNavAdmin.Visible = true;
            }
            else {
                SidebarAdmin.Visible = false;
                sidebarNavAdmin.Visible = false;
            }
        }
        else {
            this.sidebarSign.Visible = true;
            this.sidebarMain.Visible = false;
            //this.sidebarMain.Style.Add("display", "none");
        }

    }  
    

    protected void btnlogin_Click(object sender, EventArgs e)
    {
        try
        {
            conn.Open();
            string sql = "select * from users where username='" + this.loginUsername.Value + "'and password='" + this.loginPassword.Value + "'";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            MySqlDataReader reader = cmd.ExecuteReader();
            if (reader.HasRows)
            {
                Session["username"] = this.loginUsername.Value;
                Response.Redirect("index.aspx");
            }
            else
            {
                Response.Write("<script>alert('用户名或密码错误，请重新填写')</script>");
                reader.Close();
            }
        }
        catch
        {
            Response.Write("<script>alert('用户名或密码错误，请重新填写')</script>");
        }
        finally
        {
            conn.Close();
        }
    }

    protected void logout_Click(object sender, EventArgs e)
    {
        Session.Clear();
        Response.Redirect("index.aspx");
    }

    protected void btnreg_Click(object sender, EventArgs e)
    {
        if (regPassword.Value != regRePassword.Value)
        {
            Response.Write("<script>alert('两次密码不相同，请重新输入')</script>");
        }
        else
        {
            try
            {
                conn.Open();
                string sql = "select * from users where username='" + this.regUsername.Value + "'";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                MySqlDataReader reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    Response.Write("<script>alert('用户名被占用，请重新输入新用户名')</script>");
                    reader.Close();
                }
                else
                {
                    reader.Close();
                    string sql1 = "insert into users values('" + this.regUsername.Value + "','" + this.regPassword.Value + "','','" + this.regEmail.Value + "','')";
                    MySqlCommand cmd1 = new MySqlCommand(sql1, conn);
                    cmd1.ExecuteNonQuery();
                    this.regUsername.Value = "";
                    this.regPassword.Value = "";
                    this.regRePassword.Value = "";
                    this.regEmail.Value = "";
                    Response.Write("<script>alert('注册成功')</script>");
                }
            }
            catch
            {
                Response.Write("<script>alert('注册失败')</script>");
            }
            finally
            {
                conn.Close();
            }
        }
    }

    protected void changePassword_Click(object sender, EventArgs e)
    {
        if (newPassword.Value == newRePassword.Value)
        {
            String username = (string)Session["username"];
            string read = "";
            conn.Open();
            string str = "select password from users where username='" + username + "'";
            MySqlCommand cmd = new MySqlCommand(str, conn);
            MySqlDataReader dr = cmd.ExecuteReader();
            if (dr.Read())
            {
                read = dr.GetString(0).ToString();
                dr.Close();
            }
            conn.Close();

            if (oldPassword.Value == read)
            {
                try
                {
                    conn.Open();
                    string str1 = "update users set password='" + newPassword.Value + "' where username='" + username + "'";
                    MySqlCommand cmd1 = new MySqlCommand(str1, conn);
                    cmd1.ExecuteNonQuery();
                    this.Response.Write("<script>alert('修改成功')</script>");
                    oldPassword.Value = "";
                    newPassword.Value = "";
                    newRePassword.Value = "";
                }
                catch
                {

                    this.Response.Write("<script>alert('修改失败')</script>");
                }
                finally
                {
                    conn.Close();
                }
            }
            else
            {
                this.Response.Write("<script>alert('原始密码填写错误')</script>");
            }
        }
        else {
            Response.Write("<script>alert('两次密码不相同，请重新输入')</script>");
        }
    }
}
