package com.example.chapter3.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {

    private LinkedList<Friend>mData;
    private Context mContext;

    public MyAdapter(LinkedList<Friend>mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount(){
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false);
        ImageView img_icon = (ImageView)convertView.findViewById(R.id.img_head);
        TextView tv_name = (TextView)convertView.findViewById(R.id.tv_name);
        TextView tv_msg = (TextView)convertView.findViewById(R.id.tv_msg);
        img_icon.setBackgroundResource(mData.get(position).getIcon());
        tv_name.setText(mData.get(position).getName());
        tv_msg.setText(mData.get(position).getSpeak());
        return convertView;
    }
}
