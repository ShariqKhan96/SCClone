package com.example.shariqkhan.scclone.forCustomView;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.shariqkhan.scclone.R;

/**
 * Created by ShariqKhan on 1/13/2018.
 */

public class CustomView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ImageView mCameraImage;
    private ImageView mGalleryImage;
    private ImageView mStoryImage;
    private ImageView mChatImage;

    private View mIndicator;

    private int chatShiftTranslationX;

    private int mIndicatorTranslation;
    ArgbEvaluator evaluator;
    private int mCenterColor;
    private int mSideColor;

    private int mTranslationY;
    int currentPadding;

    public CustomView(@NonNull Context context) {
        this(context, null);
    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();


    }

    public void setUpWithViewPager(final ViewPager pager) {
        pager.addOnPageChangeListener(this);

        mChatImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pager.getCurrentItem() != 0)
                    pager.setCurrentItem(0);
            }
        });
        mStoryImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pager.getCurrentItem() != 2)
                    pager.setCurrentItem(2);
            }
        });
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_snap, this, true);

        mCameraImage = (ImageView) findViewById(R.id.camera_button);
        mGalleryImage = (ImageView) findViewById(R.id.gallery_button_image);
        mChatImage = (ImageView) findViewById(R.id.chat_image);
        mStoryImage = (ImageView) findViewById(R.id.story_image);

        mIndicator = findViewById(R.id.indicator);

        evaluator = new ArgbEvaluator();

        mSideColor = ContextCompat.getColor(getContext(), R.color.dark_grey);
        mCenterColor = ContextCompat.getColor(getContext(), R.color.white);


        //indicator should be moving further

        mIndicatorTranslation = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 88, getResources().getDisplayMetrics());
        mGalleryImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                chatShiftTranslationX = (int) (mGalleryImage.getX() - mChatImage.getX()) - mIndicatorTranslation;
                mGalleryImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mTranslationY = getHeight() - mGalleryImage.getBottom();
            }
        });


    }

    private void moveViews(float moveFromCenter) {
        mChatImage.setTranslationX(moveFromCenter * chatShiftTranslationX);
        mStoryImage.setTranslationX(-moveFromCenter * chatShiftTranslationX);
        // mIndicator.setTranslationX(moveFromCenter*mIndicatorTranslation);

        mIndicator.setAlpha(moveFromCenter);
        mIndicator.setScaleX(moveFromCenter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (position == 0) {

            setColor(1 - positionOffset);

            moveViews(1 - positionOffset);

            mIndicator.setTranslationX((positionOffset - 1) * mIndicatorTranslation);
            moveAndScaleCenterViewTranslation(1 - positionOffset);

        } else if (position == 1) {

            setColor(positionOffset);

            moveViews(positionOffset);
            mIndicator.setTranslationX(positionOffset * mIndicatorTranslation);
            moveAndScaleCenterViewTranslation(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setColor(float fractionFromCenter) {

        int color = (int) evaluator.evaluate(fractionFromCenter, mCenterColor, mSideColor);
        Log.e("color", String.valueOf(color));
        mChatImage.setColorFilter(color);
        mStoryImage.setColorFilter(color);
        mCameraImage.setColorFilter(color);
        mIndicator.setBackgroundColor(color);
        mGalleryImage.setAlpha(1-fractionFromCenter);
    }

    private void moveAndScaleCenterViewTranslation(float centerScale) {

        float scale = .7f + ((1-centerScale) * 0.3f);
        mCameraImage.setScaleX(scale);
        mCameraImage.setScaleY(scale);
//
        int translation = (int) (centerScale * mTranslationY);
        mCameraImage.setTranslationY(translation);
        mGalleryImage.setTranslationY(translation);

    }


}
