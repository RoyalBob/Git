package com.royalbob.contacts;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by RoyalBob on 2016/7/16.
 */
public class Sqlite {
    public static SQLiteDatabase mDb = null;
    public static void initDatabase(Activity act) {
        String databaseFilename =act.getFilesDir()+"/contacts";
        File file = new File(act.getFilesDir(),"contacts");
        //判断文件是否存在，不存在就新建一个
        if(!file.exists())
        {
            FileOutputStream os = null;
            try{
                //得到数据库文件的写入流
                os = new FileOutputStream(databaseFilename);
                Log.v("aa","数据库不存在，被创建！");
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
            //得到数据库文件的数据流
            InputStream is = act.getResources().openRawResource(R.raw.contacts);
            byte[] buffer = new byte[8192];
            int count = 0;
            try{
                while((count=is.read(buffer))>0){
                    os.write(buffer, 0, count);
                    os.flush();
                }
                is.close();
                os.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        mDb=SQLiteDatabase.openDatabase(databaseFilename, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public static ArrayList<UserInfo> loadUserInfo(ArrayList<UserInfo> arrayList_UserInfo){
        Cursor cursor = Sqlite.mDb.rawQuery("select * from contacts", null);
        while(cursor.moveToNext()){
            UserInfo userInfo = new UserInfo();//放在循环里面!
            userInfo.setmName(cursor.getString(cursor.getColumnIndex("姓名")));
            userInfo.setmTel(cursor.getString(cursor.getColumnIndex("电话")));
            userInfo.setmAddress(cursor.getString(cursor.getColumnIndex("地址")));
            if(cursor.getColumnIndex("头像")!=-1)
                userInfo.setmHead(cursor.getString(cursor.getColumnIndex("头像")));
            if(cursor.getColumnIndex("铃声")!=-1)
                userInfo.setmRingtong(cursor.getString(cursor.getColumnIndex("铃声")));
            arrayList_UserInfo.add(userInfo);
        }
        return arrayList_UserInfo;
    }

    public static ArrayList<UserInfo> loadSearchInfo(ArrayList<UserInfo> arrayList_UserInfo, String searchInfo){
        Cursor cursor = Sqlite.mDb.rawQuery("select * from contacts where (姓名 like '%" + searchInfo +"%') or (电话 like '%"+ searchInfo +"%')", null);
        while(cursor.moveToNext()){
            UserInfo userInfo = new UserInfo();//放在循环里面!
            userInfo.setmName(cursor.getString(cursor.getColumnIndex("姓名")));
            userInfo.setmTel(cursor.getString(cursor.getColumnIndex("电话")));
            if(cursor.getColumnIndex("地址")!=-1)
                userInfo.setmHead(cursor.getString(cursor.getColumnIndex("地址")));
            if(cursor.getColumnIndex("头像")!=-1)
                userInfo.setmHead(cursor.getString(cursor.getColumnIndex("头像")));
            if(cursor.getColumnIndex("铃声")!=-1)
                userInfo.setmRingtong(cursor.getString(cursor.getColumnIndex("铃声")));
            arrayList_UserInfo.add(userInfo);
        }
        return arrayList_UserInfo;
    }
}
