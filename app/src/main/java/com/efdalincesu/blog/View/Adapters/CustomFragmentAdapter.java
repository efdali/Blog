package com.efdalincesu.blog.View.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.efdalincesu.blog.View.Fragments.CategoryFragment;
import com.efdalincesu.blog.View.Fragments.HomeFragment;
import com.efdalincesu.blog.View.Fragments.ProfileFragment;

public class CustomFragmentAdapter extends FragmentPagerAdapter {
    FragmentManager fm;
    public CustomFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.fm=fm;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=null;

        if (position==0){
            fragment=new HomeFragment();
        }else if(position==1){
            fragment=new CategoryFragment();
        }else if(position==2){
            fragment=new ProfileFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
