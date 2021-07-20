package com.example.meiaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.URL;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
public class ImageActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    String mockUrl = "https://p8.itc.cn/images01/20210414/4f017835b63447978d323ba7b6760b87.jpeg";
    String mockErrorUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/400px-Android_logo_2019_%28stacked%29.svg.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        init();
    }
    private void init(){
        findViewById(R.id.btnFailure).setOnClickListener(this);
        findViewById(R.id.btnSuccess).setOnClickListener(this);
        imageView = findViewById(R.id.img);
    }

    private void changeImg(String url){
        Glide.with(this).load(url)
                .placeholder(R.drawable.rectangle)
                .apply(new RequestOptions().centerCrop())
                .transition(withCrossFade())
                .into(imageView);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSuccess:
                changeImg(mockUrl);
                break;
            case R.id.btnFailure:
                changeImg(mockErrorUrl);
                break;
        }

    }
}
