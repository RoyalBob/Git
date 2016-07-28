using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Web.Script.Serialization;
using System.IO;

namespace JSON
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        /**
         * 
         * 简单json数据
         **/
        public struct ToJsonMy
        {
            public string result { get; set; }  //属性的名字，必须与json格式字符串中的"key"值一样。
            public string res_info { get; set; }
            public string queryorder_info { get; set; }
        }

        /**
         * 
         * 简单json数据解析函数
         **/
        public static void JsonMy()
        {
            StreamReader sr = new StreamReader("E:\\json.txt", Encoding.Default);
            String json = sr.ReadLine();//Jsonstr函数读取json数据的文本txt          　　　　　　 
            JavaScriptSerializer js = new JavaScriptSerializer();   //实例化一个能够序列化数据的类
            ToJsonMy list = js.Deserialize<ToJsonMy>(json);    //将json数据转化为对象类型并赋值给list
            string result = list.result;
            string res_info = list.res_info;
            string queryorder_info = list.queryorder_info;
            Console.WriteLine("result:" + result);
            Console.WriteLine("res_info:" + res_info);
            Console.WriteLine("queryorder_info:" + queryorder_info);
            sr.Close();
        }

        /**
         * 包含对象的json数据
         * */
        public struct ToJsonMy2
        {
            public string result { get; set; }
            public string res_info { get; set; }
            public queryorder_info2 queryorder_info;
        }
        public struct queryorder_info2
        {
            public string order_num { get; set; }
            public string orderdetail { get; set; }
        };

        /**
         * 包含对象的json数据解析函数
         * */
        public static void JsonMy2()
        {
            StreamReader sr2 = new StreamReader("E:\\json2.txt", Encoding.Default);
            String json2 = sr2.ReadLine();
            JavaScriptSerializer js2 = new JavaScriptSerializer();   //实例化一个能够序列化数据的类
            ToJsonMy2 list2 = js2.Deserialize<ToJsonMy2>(json2);    //将json数据转化为对象类型并赋值给list
            string result = list2.result;
            string res_info = list2.res_info;
            string order_num = list2.queryorder_info.order_num;
            string orderdetail = list2.queryorder_info.orderdetail;
            Console.WriteLine("result:" + result);
            Console.WriteLine("res_info:" + res_info);
            Console.WriteLine("order_num:" + order_num);
            Console.WriteLine("orderdetail:" + orderdetail);
            sr2.Close();
        }


        /**
         * 包含对象、数组的json数据
         * */
        public struct ToJsonMy3
        {
            public string result { get; set; }
            public string res_info { get; set; }
            public queryorder_info3 queryorder_info;
        }
        public struct queryorder_info3
        {
            public string order_num { get; set; }
            public List<orderdetail3> orderdetail;//数组处理       
        };
        public struct orderdetail3
        {
            public string CFTUin { get; set; }
            public string CancelDeadline { get; set; }
            public string CheckInDate { get; set; }
            public string CheckOutDate { get; set; }
            public string CityID { get; set; }
            public string CurrencyCode { get; set; }
            public string HotelID { get; set; }
            public string HotelName { get; set; }
            public string ListID { get; set; }
            public string PayAmt { get; set; }
            public string PayType { get; set; }
            public string RommsCnt { get; set; }
            public string SPTransID { get; set; }
            public string State { get; set; }
        };

        /**
         * 包含对象、数组的json数据解析函数
         * */
        public static void JsonMy3()
        {
            StreamReader sr3 = new StreamReader("E:\\json3.txt", Encoding.Default);
            String json3 = sr3.ReadLine();
            JavaScriptSerializer js3 = new JavaScriptSerializer();   //实例化一个能够序列化数据的类
            ToJsonMy3 list3 = js3.Deserialize<ToJsonMy3>(json3);    //将json数据转化为对象类型并赋值给list
            string result = list3.result;
            string res_info = list3.res_info;
            string order_num = list3.queryorder_info.order_num;
            List<orderdetail3> orderdetail = list3.queryorder_info.orderdetail;
            string CFTUin = orderdetail[0].CFTUin;
            string HotelName = orderdetail[0].HotelName;
            string ListID = orderdetail[1].ListID;
            string State = orderdetail[1].State;
            Console.WriteLine("result:" + result);
            Console.WriteLine("res_info:" + res_info);
            Console.WriteLine("order_num:" + order_num);
            Console.WriteLine("CFTUin:" + CFTUin);
            Console.WriteLine("HotelName:" + HotelName);
            Console.WriteLine("ListID:" + ListID);
            Console.WriteLine("State:" + State);
            sr3.Close();
        }

        private void 简单JSON_Click(object sender, EventArgs e)
        {
            JsonMy();
        }

        private void 包含对象的JSON_Click(object sender, EventArgs e)
        {
            JsonMy2();
        }

        private void 包含对象数组的JSON_Click(object sender, EventArgs e)
        {
            JsonMy3();
        }
    }
}
