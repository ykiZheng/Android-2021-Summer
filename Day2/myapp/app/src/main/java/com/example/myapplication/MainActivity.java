package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Main Activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Main Activity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Main Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Main Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Main Activity onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra("userId", 123456);
                startActivity(intent);
                break;
            case R.id.btn3:
                Intent baiduIntent = new Intent(Intent.ACTION_VIEW);
                baiduIntent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(baiduIntent);
                break;
            case R.id.btn4:
                Intent diaIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
                //diaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(diaIntent);
                break;
            case R.id.btn5:
                Intent douIntent = new Intent(MainActivity.this, douyin.class);
                startActivity(douIntent);
                break;
        }
    }
}
