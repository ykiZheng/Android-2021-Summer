package com.example.meiaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity{

    VideoView videoView;
    String mockUrl = "https://f.video.weibocdn.com/Y712UBcJlx07OiWNYNvW01041202EoxA0E010.mp4?label=mp4_720p&template=1280x720.25.0&trans_finger=1f0da16358befad33323e3a1b7f95fc9&media_id=4660055904288829&tp=8x8A3El:YTkl0eM8&us=0&ori=1&bf=2&ot=h&ps=3lckmu&uid=7QtcDN&ab=,1493-g0,1192-g0,1191-g0,1046-g2,1258-g0,3601-g3&Expires=1626769043&ssig=G1Fp%2Bkky0g&KID=unistore,video";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }
    private void init(){
        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(mockUrl));
        videoView.setMediaController(new MediaController(this));
        videoView.start();

    }


}
