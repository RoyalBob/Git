package com.royalbob.contacts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listview;
    private EditText et_search;
    private String searchInfo = "";
    private UserAdapter userAdapter, userAdapter2;
    private ArrayList<UserInfo> arrayList_UserInfo = new ArrayList<UserInfo>();
    private Activity activity;
    private Handler handler;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        findViewbyId();
        Sqlite.initDatabase(this);
        initView();
        initPermision();
        setAdapter();
        activity = this;
    }

    private void findViewbyId(){
        listview = (ListView) findViewById(R.id.listView1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        et_search = (EditText) findViewById(R.id.et_search);
    }

    private void initView() {
        toolbar.setTitle("通讯录");//设置主标题
        toolbar.setSubtitle("联系人");//设置子标题
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditContacts.class);
                startActivity(intent);
            }
        });
    }

    private void initPermision() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_CONTACTS},1210);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_CONTACTS},1211);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE},1211);
    }

    private void setAdapter() {
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchInfo = String.valueOf(charSequence);
                userAdapter2 = new UserAdapter(getApplicationContext(), arrayList_UserInfo, searchInfo);
                listview.setAdapter(userAdapter2);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        userAdapter = new UserAdapter(this, arrayList_UserInfo);
        listview.setAdapter(userAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ShowContacts.class);
                intent.putExtra("Name",arrayList_UserInfo.get(i).getmName().toString());
                startActivity(intent);
            }
        });

//        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //Snackbar.make(getWindow().getDecorView(), "Long Click!", Snackbar.LENGTH_LONG).show();
//                AlertDialog alertDialog = new AlertDialog.Builder(activity).setSingleChoiceItems(new String[]{"删除联系人","22222"}, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        switch (i){
//                            case 0:
//                                Snackbar.make(getWindow().getDecorView(), "111", Snackbar.LENGTH_LONG).show();
//
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                }).show();
//                return true;
//            }
//        });
    }

    @Override
    protected void onResume() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        et_search.setVisibility(View.GONE);
        et_search.setText("");
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent=new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI, true);
            //类型为来电ringtong
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
            //设置显示的题目
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置来电的铃声");
            //当设置完成之后返回到当前的activity
            startActivityForResult(intent, 0);
            return true;
        }else if (id == R.id.action_search) {
            Snackbar.make(getWindow().getDecorView(), "Please Input ...", Snackbar.LENGTH_SHORT).show();
            getSupportActionBar().setDisplayShowHomeEnabled(true);//左上角图标显示
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左上角返回图标是否显示
            et_search.setVisibility(View.VISIBLE);
            return true;
        }else if (id == android.R.id.home) {
            et_search.setVisibility(View.GONE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
            et_search.setText("");
            return true;
        }else if (id == R.id.action_add){
            Intent intent = new Intent(MainActivity.this, EditContacts.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_importContacts){
            progressDialog = ProgressDialog.show(MainActivity.this, "提示", "正在导入...", true, false);
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.obj.equals("1")){
                        userAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                        Snackbar.make(getWindow().getDecorView(), "导入完成!", Snackbar.LENGTH_LONG).show();
                        setAdapter();

                    }

                    super.handleMessage(msg);
                }
            };

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ImportContacts importContacts = new ImportContacts();
                    importContacts.ImportContacts(activity);

                    Message msg = new Message();
                    msg.obj = "1";
                    handler.sendMessage(msg);
                }
            });
            thread.start();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!=RESULT_OK){
            return;
        }
        switch(requestCode){
            case 0:
                try {
                    //得到我们选择的铃声
                    Uri pickedUri=data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    //将我们选择的铃声选择成默认
                    if(pickedUri!=null){
                        RingtoneManager.setActualDefaultRingtoneUri(MainActivity.this, RingtoneManager.TYPE_RINGTONE, pickedUri);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
