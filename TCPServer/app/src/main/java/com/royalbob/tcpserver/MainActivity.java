package com.royalbob.tcpserver;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    ServerSocket serverSocket;//创建ServerSocket对象
    Socket clicksSocket;//连接通道，创建Socket对象
    Button startButton;//发送按钮
    EditText portEditText;//端口号
    TextView receiveTextView;//接收消息框
    Button sendButton;//发送按钮
    EditText sendEditText;//发送消息框
    InputStream inputstream;//创建输入数据流
    OutputStream outputStream;//创建输出数据流
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start_button);
        portEditText = (EditText) findViewById(R.id.port_EditText);
        receiveTextView = (TextView) findViewById(R.id.receive_TV);
        sendButton = (Button) findViewById(R.id.send_button);
        sendEditText = (EditText) findViewById(R.id.message_EditText);

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //服务器监听线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int port =Integer.valueOf(portEditText.getText().toString());//获取portEditText中的端口号
                            serverSocket = new ServerSocket(port);//监听port端口，这个程序的通信端口就是port了
                        } catch (IOException e) {e.printStackTrace();}
                        while (true) {
                            try {
                                //监听连接 ，如果无连接就会处于阻塞状态，一直在这等着
                                clicksSocket = serverSocket.accept();
                                inputstream = clicksSocket.getInputStream();//
                                //启动接收线程
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        while (true) {
                                            try {
                                                final byte[] buf = new byte[1024];
                                                final int len = inputstream.read(buf);
                                                runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        receiveTextView.setText(new String(buf, 0, len));
                                                    }
                                                });
                                            } catch (Exception e) {e.printStackTrace();}
                                        }
                                    }
                                }).start();
                            } catch (IOException e) {e.printStackTrace();}
                        }
                    }
                }).start();
            }
        });
        sendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //获取输出流
                    outputStream = clicksSocket.getOutputStream();
                    //发送数据
                    outputStream.write(sendEditText.getText().toString().getBytes());
                } catch (Exception e) {e.printStackTrace();}
            }
        });
    }


}