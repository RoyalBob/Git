package com.royalbob.tcpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    boolean isConnect=true;//连接还是断开
    Button ConnectButton;//定义连接按钮
    Button SendButton;//定义发送按钮
    EditText IPEditText;//定义ip输入框
    EditText PortText;//定义端口输入框
    EditText MsgEditText;//定义信息输出框
    TextView ReceiveTextView;//定义信息输入框
    Socket socket = null;//定义socket
    private OutputStream outputStream=null;//定义输出流
    private InputStream inputStream=null;//定义输入流
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectButton = (Button) findViewById(R.id.Connect_Bt);//获得连接按钮对象
        SendButton = (Button) findViewById(R.id.Send_Bt);//获得发送按钮对象
        IPEditText = (EditText) findViewById(R.id.ip_ET);//获得ip文本框对象
        PortText = (EditText) findViewById(R.id.Port_ET);//获得端口文本框按钮对象
        MsgEditText = (EditText) findViewById(R.id.Send_ET);//获得发送消息文本框对象
        ReceiveTextView = (TextView) findViewById(R.id.Receive_TV);//获得接收消息文本框对象
    }

    public void Connect_onClick(View v) {
        //标志位 = true表示连接
        if (isConnect == true) {
            isConnect = false;//置为false
            ConnectButton.setText("断开");//按钮上显示--断开
            //启动连接线程
            Connect_Thread connect_Thread = new Connect_Thread();
            connect_Thread.start();
        } else {
            //标志位 = false表示退出连接
            isConnect = true;//置为true
            ConnectButton.setText("连接");//按钮上显示连接
            try {
                socket.close();//关闭连接
                socket=null;
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    public void Send_onClick(View v) {
        try {
            //获取输出流
            outputStream = socket.getOutputStream();
            //发送数据
            outputStream.write(MsgEditText.getText().toString().getBytes());
            //outputStream.write("0".getBytes());
        } catch (Exception e) {e.printStackTrace();}
    }
    //连接线程
    class Connect_Thread extends Thread {
        public void run() {
            try {
                if (socket == null) {
                    //用InetAddress方法获取ip地址
                    InetAddress ipAddress = InetAddress.getByName(IPEditText.getText().toString());
                    int port =Integer.valueOf(PortText.getText().toString());//获取端口号
                    socket = new Socket(ipAddress, port);//创建连接地址和端口-------------------这样就好多了
                    //在创建完连接后启动接收线程
                    Receive_Thread receive_Thread = new Receive_Thread();
                    receive_Thread.start();
                }

            } catch (Exception e) {e.printStackTrace();}
        }
    }
    //接收线程
    class Receive_Thread extends Thread {
        public void run() {
            try {
                while (true) {
                    final byte[] buffer = new byte[1024];//创建接收缓冲区
                    inputStream = socket.getInputStream();
                    final int len = inputStream.read(buffer);//数据读出来，并且返回数据的长度
                    runOnUiThread(new Runnable()//不允许其他线程直接操作组件，用提供的此方法可以
                    {
                        public void run() {
                            ReceiveTextView.setText(new String(buffer,0,len));
                        }
                    });
                }
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}