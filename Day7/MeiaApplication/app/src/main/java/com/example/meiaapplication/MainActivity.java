package com.example.meiaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                Intent imgIntent = new Intent(this, ImageActivity.class);
                startActivity(imgIntent);
                break;
            case R.id.btn2:
                Intent videoIntent = new Intent(this,VideoActivity.class);
                startActivity(videoIntent);
                break;
        }
    }
}
