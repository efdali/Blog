package com.efdalincesu.blog.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Article;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.Utils.AuthTask;
import com.efdalincesu.blog.View.Adapters.ArticleRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    ArticleRecyclerViewAdapter adapter;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_favorite,container,false);

        recyclerView=v.findViewById(R.id.recyclerView);
        textView=v.findViewById(R.id.textView);

        sendRequest();

        return v;
    }

    private void sendRequest() {

        if (AuthTask.isLogin()) {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Call<List<Article>> call = ManagerAll.getInstance().getFavorite(AuthTask.getUser().getUyeId());
            call.enqueue(new Callback<List<Article>>() {
                @Override
                public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                    adapter=new ArticleRecyclerViewAdapter(getContext(),response.body());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Article>> call, Throwable t) {
                    Toast.makeText(getContext(),"Sunucularda Yaşanan Teknik Hatadan Dolayı Daha Sonra Tekrar Deneyiniz!",Toast.LENGTH_LONG).show();

                }
            });
        }else{
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}
