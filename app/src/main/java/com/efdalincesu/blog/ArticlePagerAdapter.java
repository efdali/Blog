package com.efdalincesu.blog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.efdalincesu.blog.Models.Article;

import java.util.ArrayList;

public class ArticlePagerAdapter extends FragmentPagerAdapter {

    ArrayList<Article> articleList;



    public ArticlePagerAdapter(FragmentManager fm,ArrayList<Article> articles) {
        super(fm);
        this.articleList=articles;
    }

    @Override
    public Fragment getItem(int position) {

        FragmentPager.getInstance(articleList.get(position));
        return null;
    }

    @Override
    public int getCount() {
        return articleList.size();
    }
}
