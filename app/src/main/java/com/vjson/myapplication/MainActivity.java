package com.vjson.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;

import ai.medialab.medialabads.MediaLabAdView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mContainer;
    private MediaLabAdView mMediaLabAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = findViewById(R.id.container);

        int widthPx = getResources().getDimensionPixelSize(R.dimen.ad_width);
        int heightPx = getResources().getDimensionPixelSize(R.dimen.ad_height);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(widthPx, heightPx);
        mMediaLabAdView = new MediaLabAdView(this);
        mMediaLabAdView.setLayoutParams(layoutParams);
        mMediaLabAdView.initialize(this, "114106652", MediaLabAdView.AdSize.BANNER, "http://ana-base.whisper.sh/ana/index.html", Color.BLACK, 15, new MediaLabAdView.AdLoadListener() {
            @Override
            public void onLoadFinished(boolean b) {

                Log.d("MainActivity", "MediaLabAdView load success = " + b);
            }
        });
        mContainer.addView(mMediaLabAdView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        MobclickAgent.onPause(this);

        if (mMediaLabAdView != null) {
            mMediaLabAdView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);

        if (mMediaLabAdView != null) {
            mMediaLabAdView.resume(true);
            mMediaLabAdView.loadAd(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMediaLabAdView != null) {
            mMediaLabAdView.destroy();
        }
    }
}
