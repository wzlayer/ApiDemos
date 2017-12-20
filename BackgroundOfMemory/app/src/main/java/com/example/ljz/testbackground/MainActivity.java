package com.example.ljz.testbackground;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View view = findViewById(R.id.bg);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            view.setBackgroundDrawable(getDrawable(R.drawable.bg3));
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                load(view);
            }
        }).start();
    }

    private void load(final View view) {
        try {
            final GlideDrawable drawable = Glide.with(this)
                    .load(R.drawable.bg3)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundDrawable(drawable);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
