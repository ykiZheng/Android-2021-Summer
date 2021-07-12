package com.example.beat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn1 = findViewById(R.id.btn1);
        final EditText et1 = findViewById(R.id.editText);
        final TextView tv1 = findViewById(R.id.tv1);

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                if(et1.getText().toString().equals("he")) {
                    tv1.setText("福报将至，我从今开始躺平，至死方休。");
                    Log.d("MainActivity","Nice 呵.");
                }
                else
                {
                    Log.d("MainActivity","Wrong answer");
                }
            }
            }
        );

        final TextView tv2 = findViewById(R.id.tv2);
        final Switch sw1 = findViewById(R.id.sw1);
        final RadioGroup rg = findViewById(R.id.rg);
        final RadioButton rbtn1 = findViewById(R.id.rbtn1);
        final RadioButton rbtn2 = findViewById(R.id.rbtn2);
        final RadioButton rbtn3 = findViewById(R.id.rbtn3);
        final TextView tv3 = findViewById(R.id.tv3);
        sw1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(sw1.isChecked())
                {
                    btn1.setTextColor(Color.BLUE);
                    sw1.setTextColor(Color.parseColor("#009688"));  //不晓得为什么不能使用xml中的color
                    et1.setTextColor(Color.parseColor("#78771b"));
                    et1.setHintTextColor(Color.parseColor("#78771b"));
                    tv1.setTextColor(Color.parseColor("#f7002c"));
                    tv2.setText("我蓝了");
                    tv2.setTextColor(Color.BLUE);
                    rbtn1.setTextColor(Color.parseColor("#009688"));
                    rbtn2.setTextColor(Color.parseColor("#009688"));
                    rbtn3.setTextColor(Color.parseColor("#009688"));
                    tv3.setTextColor(Color.parseColor("#009688"));
                    Log.d("MainActivity","Change Blue");
                }
                else
                {
                    btn1.setTextColor(Color.BLACK);
                    sw1.setTextColor(Color.BLACK);
                    et1.setTextColor(Color.BLACK);
                    et1.setHintTextColor(Color.BLACK);
                    tv1.setTextColor(Color.BLACK);
                    tv2.setText("我黑了");
                    tv2.setTextColor(Color.BLACK);
                    rbtn1.setTextColor(Color.BLACK);
                    rbtn2.setTextColor(Color.BLACK);
                    rbtn3.setTextColor(Color.BLACK);
                    tv3.setTextColor(Color.BLACK);
                    Log.d("MainActivity","Change Black");
                }
            }
       }
        );



    }
}
