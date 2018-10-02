package com.efdalincesu.blog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efdalincesu.blog.Models.Article;

import java.util.ArrayList;

public class FragmentPager extends Fragment {

    Article article;

    public static FragmentPager getInstance(Article article){

        FragmentPager fragmentPager=new FragmentPager();
        Bundle bundle=new Bundle();
        bundle.putSerializable("article",article);
        fragmentPager.setArguments(bundle);
        return fragmentPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article= (Article) getArguments().getSerializable("article");

        Log.d("eklendi",article.getYaziBaslik());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragmentpager,container,false);


        return v;


    }
}
