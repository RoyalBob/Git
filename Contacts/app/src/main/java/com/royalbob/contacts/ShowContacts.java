package com.royalbob.contacts;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by RoyalBob on 2016/7/19.
 */
public class ShowContacts extends AppCompatActivity{

    private static String path="/sdcard/myHead/";//sd路径
    private String headpath = null, phoneNumber = null, mCustomRingtone = null;
    private ImageView imageView_show;
    private String mName =null;
    private Button btn_mcall, btn_mmsg;
    private TextView tv_Tel, tv_Address, tv_QCellCore;

    String urlString = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?";
    String param = "";
    String str = "";

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            String text = (String) msg.obj;
            text = text.substring(19);
            try {
                JSONObject jsonObject =new JSONObject(text);
                str="归属地: "+jsonObject.getString("province") + " " +  jsonObject.getString("catName");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tv_QCellCore.setText(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_show);
        findViewById();
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mName = bundle.getString("Name");

        getSupportActionBar().setTitle(mName);
        getSupportActionBar().setSubtitle("通讯录");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Sqlite.initDatabase(this);
        Cursor cursor = Sqlite.mDb.rawQuery("select * from contacts where 姓名='"+ mName + "'", null);
        while(cursor.moveToNext())
        {
            String tel = null;
            tel = cursor.getString(1);
            tel = tel.replace(" ","");
            if(tel.substring(0,3).equals("+86"))
                param = "tel=" + tel.substring(3);
            else
                param = "tel=" + tel;
            tv_Tel.setText(cursor.getString(1));
            tv_Address.setText(cursor.getString(2));
            phoneNumber = cursor.getString(1);
            headpath = cursor.getString(3);
        }

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString); //URL对象
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接
                    conn.setRequestMethod("POST"); //使用post请求
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);

                    conn.setDoInput(true); //向连接中写入数据，允许输入流，即允许下载
                    conn.setDoOutput(true); //向连接中读取数据，允许输出流，即允许上传
                    conn.setUseCaches(false); //禁止缓存
                    conn.setInstanceFollowRedirects(true);//自动执行HTTP重定向
//                            conn.setRequestProperty("Content-Type", "application/soap+xml");//设置内容类型
                    conn.setRequestProperty("Charset", "UTF-8");

                    // 建立输出流，并写入数据
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(param.getBytes("UTF-8"));

                    conn.connect();
                    //outputStream.close();
                    // 判断是否响应成功
                    if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {

                        InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"gb2312");
                        BufferedReader responseReader = new BufferedReader(isr);
                        String resultData = "";
                        String readLine = null;
                        while ((readLine = responseReader.readLine()) != null) {
                            resultData += readLine ;
                        }

                        Message msg = new Message();
                        msg.obj = resultData;
                        handler.sendMessage(msg);
                        responseReader.close();
                    }

                    conn.disconnect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Bitmap bt = BitmapFactory.decodeFile(path + headpath);//从Sd中找头像，转换成Bitmap
        if(bt!=null){
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            imageView_show.setImageDrawable(drawable);
        }

        btn_mcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(intent);
            }
        });

        btn_mmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri smsToUri = Uri.parse("smsto://" + phoneNumber);
                Intent mIntent = new Intent( android.content.Intent.ACTION_SENDTO, smsToUri );
                startActivity( mIntent );
            }
        });
    }

    private void findViewById(){
        imageView_show = (ImageView)findViewById(R.id.imageView_show);
        btn_mcall = (Button)findViewById(R.id.btn_mcall);
        btn_mmsg = (Button)findViewById(R.id.btn_mmsg);
        tv_Tel = (TextView)findViewById(R.id.tvTel);
        tv_Address = (TextView)findViewById(R.id.tvAddress);
        tv_QCellCore = (TextView)findViewById(R.id.tvQCellCore);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        else if (item.getItemId() == R.id.action_edit) {
            finish();
            Intent intent = new Intent(ShowContacts.this, EditContacts.class);
            intent.putExtra("Name", mName);
            intent.putExtra("HeadPath", headpath);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.action_del) {
            new AlertDialog.Builder(this).setTitle("Warning!")
                    .setMessage("确认删除此联系人吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Sqlite.mDb.delete("contacts","姓名='" + mName + "'", null);
                            finish();
                        }
                    }).setNegativeButton("返回", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //finish();
                }
            }).show();
//            Sqlite.mDb.delete("contacts","姓名='" + mName + "'", null);
//            finish();
            return true;
        }else if (item.getItemId() == R.id.action_aloneRingtong){
            Intent intent=new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);

            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI, true);
            //类型为来电ringtong
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
            //当设置完成之后返回到当前的activity
            startActivityForResult(intent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!=RESULT_OK){
            return;
        }
        switch(requestCode) {
            case 0:
                Uri pickedUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                if (pickedUri == null || RingtoneManager.isDefault(pickedUri)) {
                    mCustomRingtone = null;
                } else {
                    mCustomRingtone = pickedUri.toString();
                }

                ContentValues values = new ContentValues();
                values.put(ContactsContract.Contacts.CUSTOM_RINGTONE, pickedUri.toString());
                getApplicationContext().getContentResolver().update(ContactsContract.Contacts.CONTENT_URI, values, ContactsContract.Contacts.DISPLAY_NAME + "='" + mName + "'", null);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
