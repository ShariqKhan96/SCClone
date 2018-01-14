package com.example.shariqkhan.scclone;

import android.animation.ArgbEvaluator;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.shariqkhan.scclone.adapter.ViewPagerAdapter;
import com.example.shariqkhan.scclone.forCustomView.CustomView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.background_view)
    View background;
    @BindView(R.id.my_viewPager)
    ViewPager pager;

//    @BindView(R.id.custom_frameLayout)
//    FrameLayout frameLayout;


    int colorBlue;
    int colorPurple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

        CustomView customView = findViewById(R.id.my_custom_tabs);
        customView.setUpWithViewPager(pager);

        pager.setCurrentItem(1);


        //    evaluator = new ArgbEvaluator();

//        mCameraImage = (ImageView) findViewById(R.id.camera_button);
//        mGalleryImage = (ImageView) findViewById(R.id.gallery_button_image);
//        mChatImage = (ImageView) findViewById(R.id.chat_image);
//        mStoryImage = (ImageView) findViewById(R.id.story_image);

        //  mIndicator = findViewById(R.id.indicator);

        colorBlue = ContextCompat.getColor(this, R.color.light_blue);
        colorPurple = ContextCompat.getColor(this, R.color.light_purple);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                switch (position) {
//                    case 0:
//                        background.setBackgroundColor(colorBlue);
//                        background.setAlpha(1 - positionOffset);
//                        break;
//                    case 1:
//                        background.setBackgroundColor(colorPurple);
//                        background.setAlpha(positionOffset);
//                        break;
//                }
                if (position == 0) {
                    background.setBackgroundColor(colorBlue);
                    background.setAlpha(1 - positionOffset);


                } else if (position == 1) {

                    background.setBackgroundColor(colorPurple);
                    background.setAlpha(positionOffset);

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}
