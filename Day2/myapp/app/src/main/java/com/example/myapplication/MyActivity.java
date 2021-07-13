package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.i(TAG,"Practice Activity onCreate");
        initView();
    }

    private void initView(){
        findViewById(R.id.btn1).setOnClickListener(this);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.i(TAG,"Practice Activity onStart");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.i(TAG,"Practice Activity onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"Practice Activity onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"Practice Activity onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"Practice Activity onDestroy");
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(MyActivity.this, MainActivity.class);
                intent.putExtra("userId", 123456);
                startActivity(intent);
                break;
        }
    }


}
