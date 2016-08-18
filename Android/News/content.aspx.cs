using System;
using System.Web.UI;
using MySql.Data.MySqlClient;

public partial class content : Page
{
    MySqlConnection conn = new MySqlConnection("Server=qdm166048328.my3w.com; Database=qdm166048328_db; User ID=qdm166048328; Password=mfj1003mfj");
    protected void Page_Load(object sender, EventArgs e)
    {
        conn.Open();
        MySqlCommand cmd = new MySqlCommand("select * from pinglun", conn);
        MySqlDataReader reader = cmd.ExecuteReader();
        if (reader.Read())
        {
            Session["original_id"] = reader.GetInt32(0);
            Session["original_fileid"] = reader.GetString(1);
            Session["original_comment"] = reader.GetString(2);
            Session["original_publishdate"] = reader.GetDateTime(3);
            Session["original_username"] = reader.GetString(4);
            reader.Close();
        }
        else
        {
        }
        conn.Close();

        Label4.Visible = false;             //不显示评论
        DiscussGridView.Visible = false;    //不显示评论
        if (Session["username"] != null)
        {

            if ((string)Session["username"] == "admin")
            {
                GridView2.Visible = true;
                //DiscussGridView.Visible = false;    //不显示评论
            }
            else
            {
                GridView2.Visible = false;
                //DiscussGridView.Visible = true;    //不显示评论
            }
            //this.discuss.Visible = false;
            //this.submit.Style.Add("top", "0");
        }
        else
        {
            //this.discuss.Visible = true;
        }
    }

    //protected void submit_Click(object sender, EventArgs e)
    //{
    //    if (TxtSpeak.Text == "")
    //    {
    //        Response.Write("<script>alert('内容不能为空，请重新填写')</script>");
    //    }
    //    else
    //    {
    //        string username = (string)Session["username"];
    //        if (username != null)
    //        {
    //            try
    //            {
    //                conn.Open();
    //                string sql1 = "insert into pinglun(fileid,comment,publishdate,username) values('" + Request.QueryString["ID"] + "','" + TxtSpeak.Text + "','" + System.DateTime.Now.ToString() + "','" + username + "')";
    //                MySqlCommand cmd1 = new MySqlCommand(sql1, conn);
    //                cmd1.ExecuteNonQuery();
    //                Response.Write("<script>alert('评论成功')</script>");
    //                Response.Redirect("content.aspx?ID=" + Request.QueryString["ID"]);
    //            }
    //            catch
    //            {
    //                Response.Write("<script>alert('评论失败，请重新填写')</script>");
    //            }
    //            finally
    //            {
    //                conn.Close();
    //            }

    //        }
    //        else
    //        {
    //            try{
    //                string str = "匿名用户";
    //                conn.Open();
    //                string sql1 = "insert into pinglun(fileid,comment,publishdate,username) values('" + Request.QueryString["ID"] + "','" + TxtSpeak.Text + "','" + System.DateTime.Now + "','" + str + "')";
    //                MySqlCommand cmd1 = new MySqlCommand(sql1, conn);
    //                cmd1.ExecuteNonQuery();
    //                Response.Write("<script>alert('评论成功')</script>");
    //                Response.Redirect("content.aspx?ID=" + Request.QueryString["ID"]);
    //            }
    //            catch(Exception msg)
    //            {
    //                Response.Write("<script>alert('评论失败，请重新填写')</script>");
    //            }
    //            finally
    //            {
    //                conn.Close();
    //            }

    //        }
    //    }
    //}
}