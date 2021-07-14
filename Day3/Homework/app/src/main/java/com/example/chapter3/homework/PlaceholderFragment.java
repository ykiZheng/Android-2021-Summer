package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import java.util.LinkedList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    private  String TAG = "PlaceholderFragment";
    private List<Friend> mData = null;
    private Context mContext;
    private MyAdapter mAdapter = null;

    private ListView listView;
    private LottieAnimationView loadView;

    private static final String ARG_SECTION = "section";

    public PlaceholderFragment() {

    }

    public static PlaceholderFragment newInstance(String section){
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION,section);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder,container,false);
        mContext = view.getContext();
        listView = (ListView)view.findViewById(R.id.list_view);
        mData = new LinkedList<Friend>();
        mData.add(new Friend("大娃","力大无穷",R.drawable.huluwa1));
        mData.add(new Friend("二娃","千里眼顺风耳",R.drawable.huluwa2));
        mData.add(new Friend("三娃","铜头铁臂",R.drawable.huluwa3));
        mData.add(new Friend("火娃","喷火、吸火、霹雳",R.drawable.huluwa4));
        mData.add(new Friend("水娃","吸水、吐水",R.drawable.huluwa5));
        mData.add(new Friend("六娃","隐身术",R.drawable.huluwa6));
        mData.add(new Friend("七娃","宝葫芦",R.drawable.huluwa7));
        mData.add(new Friend("爷爷","园艺小能手",R.drawable.yeye));
        mData.add(new Friend("小绿","hello",R.mipmap.ic_launcher));
        mData.add(new Friend("小红","hello",R.drawable.huluwa1));
        mData.add(new Friend("小黄","hello",R.drawable.huluwa7));
        mData.add(new Friend("小蓝","hello",R.drawable.huluwa4));
        mData.add(new Friend("小紫","hello",R.drawable.yeye));
        mAdapter = new MyAdapter((LinkedList<Friend>)mData,mContext);
        listView.setAdapter(mAdapter);
        listView.setAlpha(0f);

        loadView = (LottieAnimationView) view.findViewById(R.id.load_view);
        loadView.playAnimation();

        Log.i(TAG,"创建控件");
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                // loadView 淡出
                AnimatorSet animatorSet = new AnimatorSet();

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(loadView, "alpha", 1f, 0f);
                animator1.setDuration(500);

                // ListView 淡入
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(listView, "alpha", 0f, 1f);
                animator2.setDuration(500);

                animatorSet.playTogether(animator1, animator2);
                animatorSet.start();
                Log.i(TAG,"fadein & fadeout");



            }
        }, 5000);
    }
}
