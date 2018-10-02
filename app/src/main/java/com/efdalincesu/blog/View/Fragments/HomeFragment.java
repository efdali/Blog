package com.efdalincesu.blog.View.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Article;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.View.Adapters.ArticleRecyclerViewAdapter;
import com.efdalincesu.blog.View.ArticleActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    List<Article> articles;
    RecyclerView recyclerView;
    ImageView imageView;
    RelativeLayout relativeLayout;
    TextView baslik, tarih, yorumSayisi;
    SwipeRefreshLayout refreshLayout;
    View view;
    ProgressBar progressBar;
    ArticleRecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        articles = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        imageView = view.findViewById(R.id.imageView);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        baslik = view.findViewById(R.id.baslik);
        tarih = view.findViewById(R.id.tarih);
        yorumSayisi = view.findViewById(R.id.yorumSayisi);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        progressBar=view.findViewById(R.id.progressBar);

        sendRequest();

        return view;
    }

    @Override
    public void onRefresh() {
        sendRequest();
        Toast.makeText(getContext(), "Akış Yenilendi", Toast.LENGTH_SHORT).show();
        refreshLayout.setRefreshing(false);
    }

    public void sendRequest() {

        relativeLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Call<List<Article>> call = ManagerAll.getInstance().getArticle();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                articles = response.body();
                if (articles.size() > 0) {
                    final Article article = articles.get(0);
                    Picasso.get().load(article.getYaziResim()).into(imageView);
                    baslik.setText(article.getYaziBaslik());
                    tarih.setText(article.getYaziTarih());
                    yorumSayisi.setText(article.getYorumSayisi() + "");
                    relativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(view.getContext(), ArticleActivity.class);
                            intent.putExtra("articleId", article.getYaziId());
                            intent.putExtra("yorumSayisi", article.getYorumSayisi());
                            startActivity(intent);

                        }
                    });
                    articles.remove(0);
                    adapter = new ArticleRecyclerViewAdapter(view.getContext(), articles);
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(),"Birşey Yok",Toast.LENGTH_LONG).show();
                }

                relativeLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_search_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.arama);
        SearchManager manager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=null;

        if (searchItem!=null)
            searchView= (SearchView) searchItem.getActionView();


        if (searchView!=null)
            searchView.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);
    }
}


