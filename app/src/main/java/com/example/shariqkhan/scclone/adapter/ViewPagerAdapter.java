package com.example.shariqkhan.scclone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shariqkhan.scclone.fragments.CameraFragment;
import com.example.shariqkhan.scclone.fragments.ChatFragment;
import com.example.shariqkhan.scclone.fragments.StoryFramgent;

/**
 * Created by ShariqKhan on 1/12/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ChatFragment chatFragment= new ChatFragment();
                return chatFragment;
            case 1:
                CameraFragment cameraFragment = new CameraFragment();
                return cameraFragment;
            case 2:
                StoryFramgent storyFramgent = new StoryFramgent();
                return storyFramgent;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position)
//        {
//            case 1:
//                return ""
//        }
//    }
}
