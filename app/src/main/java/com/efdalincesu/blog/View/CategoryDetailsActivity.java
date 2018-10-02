package com.efdalincesu.blog.View;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Article;
import com.efdalincesu.blog.Models.Category;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.View.Adapters.ArticleRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDetailsActivity extends AppCompatActivity {

    String kategoriId;
    String kategoriAdi;
    String yaziId;
    String yorumSayisi;
    RecyclerView recyclerView;
    ArticleRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        init();
    }

    private void init() {

        Intent intent=getIntent();
        kategoriId=intent.getStringExtra("kategoriId");
        kategoriAdi=intent.getStringExtra("kategoriAdi");
        yaziId=intent.getStringExtra("articleId");
        yorumSayisi=intent.getStringExtra("yorumSayisi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(kategoriAdi);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Call<List<Article>> call= ManagerAll.getInstance().getCategoryDetails(kategoriId);
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {

                adapter=new ArticleRecyclerViewAdapter(getApplicationContext(),response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            Intent intent=new Intent(getApplicationContext(),ArticleActivity.class);
            intent.putExtra("articleId",yaziId);
            intent.putExtra("yorumSayisi",yorumSayisi);
            NavUtils.navigateUpTo(this,intent);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
