package com.example.meiaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class OnlineActivity extends AppCompatActivity {

    VideoView videoView;
    String mockUrl = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    private void init() {
        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(mockUrl));
        videoView.setMediaController(new MediaController(this));

        videoView.start();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 加入横屏要处理的代码
            //无title
            requestWindowFeature(Window.FEATURE_NO_TITLE);
//全屏
            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN);

        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 加入竖屏要处理的代码
        }
    }

    private void setFullScreen(boolean fullScreen) {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (fullScreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }
}


