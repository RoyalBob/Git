package com.royalbob.contacts;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RoyalBob on 2016/7/20.
 */
public class UserAdapter extends BaseAdapter {

    private Context context;
    private String searchInfo;
    private ArrayList<UserInfo> arrayList_UserInfo;

    public UserAdapter(Context context, ArrayList<UserInfo> arrayList_UserInfo) {
        arrayList_UserInfo.clear();
        this.context = context;
        Sqlite.mDb = SQLiteDatabase.openDatabase(context.getFilesDir()+"/contacts", null, SQLiteDatabase.OPEN_READWRITE);
        this.arrayList_UserInfo = Sqlite.loadUserInfo(arrayList_UserInfo);
    }

    public UserAdapter(Context context, ArrayList<UserInfo> arrayList_UserInfo, String searchInfo) {
        arrayList_UserInfo.clear();
        this.context = context;
        this.searchInfo = searchInfo;
        Sqlite.mDb = SQLiteDatabase.openDatabase(context.getFilesDir()+"/contacts", null, SQLiteDatabase.OPEN_READWRITE);
        this.arrayList_UserInfo = Sqlite.loadSearchInfo(arrayList_UserInfo, searchInfo);
    }

    @Override
    public int getCount() {
        return arrayList_UserInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList_UserInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_mainitem, null);
            holder.imageView_mainitem = (ImageView) convertView.findViewById(R.id.imageView_mainitem);
            holder.textView_mainitem_Name = (TextView) convertView.findViewById(R.id.textView_mainitem_Name);
            holder.textView_mainitem_Tel = (TextView) convertView.findViewById(R.id.textView_mainitem_Tel);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        UserInfo userinfoItem= arrayList_UserInfo.get(position);
        Bitmap bt = BitmapFactory.decodeFile("/sdcard/myHead/"+ arrayList_UserInfo.get(position).getmHead());//从Sd中找头像，转换成Bitmap
        if(bt!=null){
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            holder.imageView_mainitem.setImageDrawable(drawable);
        }else{
            holder.imageView_mainitem.setImageResource(R.drawable.am);
        }

        String name = userinfoItem.getmName();
        if(searchInfo!=null){
            if (name != null && name.contains(searchInfo)) {

                int index = name.indexOf(searchInfo);

                int len = searchInfo.length();

                Spanned temp = Html.fromHtml(name.substring(0, index)
                        + "<b><u><font color=#99cc33>"
                        + name.substring(index, index + len) + "</font></u></b>"
                        + name.substring(index + len, name.length()));

                holder.textView_mainitem_Name.setText(temp);
            } else {
                holder.textView_mainitem_Name.setText(userinfoItem.getmName());
            }

            String tel = userinfoItem.getmTel();
            if (tel != null && tel.contains(searchInfo)) {

                int index = tel.indexOf(searchInfo);

                int len = searchInfo.length();

                Spanned temp = Html.fromHtml(tel.substring(0, index)
                        + "<b><u><font color=#99cc33>"
                        + tel.substring(index, index + len) + "</font></u></b>"
                        + tel.substring(index + len, tel.length()));

                holder.textView_mainitem_Tel.setText(temp);
            } else {
                holder.textView_mainitem_Tel.setText(userinfoItem.getmTel());
            }
        }else{
            holder.textView_mainitem_Name.setText(userinfoItem.getmName());
            holder.textView_mainitem_Tel.setText(userinfoItem.getmTel());
        }


        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView_mainitem;
        TextView textView_mainitem_Name;
        TextView textView_mainitem_Tel;
    }
}