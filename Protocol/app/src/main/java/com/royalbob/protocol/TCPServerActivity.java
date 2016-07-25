package com.royalbob.protocol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerActivity extends AppCompatActivity {

    private Button btn_send, btn_connect;
    private EditText edt_msg, edt_port_tcpserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpserver);

        getSupportActionBar().setTitle("TCP Server");
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_connect = (Button)findViewById(R.id.btn_connect);
        edt_port_tcpserver = (EditText)findViewById(R.id.edt_port_tcpserver);
        edt_msg = (EditText)findViewById(R.id.edt_msg);

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TCPServer(Integer.parseInt(edt_port_tcpserver.getText().toString()));
                    }
                });
            }
        });
    }

    private void TCPServer(int port){
        try {
            ServerSocket server = new ServerSocket(port);
            //等待客户端连接
            Socket client = server.accept();
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();

            byte message[] = new byte[1024];
            int len = input.read(message);
            Log.v("TCP Server", "message from client:" + new String(message, 0, len));

            String sendString = "Server it is!";
            output.write(sendString.getBytes());
            client.close();
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_udpSend) {
            finish();
            Intent intent = new Intent(TCPServerActivity.this, UDPSendActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.action_udpReceive){
            finish();
            Intent intent = new Intent(TCPServerActivity.this, UDPReceiveActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.action_tcpClient){
            finish();
            Intent intent = new Intent(TCPServerActivity.this, TCPClientActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.action_tcpServer){
            Toast.makeText(getApplicationContext(), "请勿切换至本页面！", Toast.LENGTH_LONG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
