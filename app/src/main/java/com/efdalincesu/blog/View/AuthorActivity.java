package com.efdalincesu.blog.View;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Author;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.BaseUrl;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.View.Adapters.ArticleRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorActivity extends AppCompatActivity {

    String yazarId,yaziId,yorumSayisi;
    RecyclerView recyclerView;
    ArticleRecyclerViewAdapter adapter;
    Author author;
    ImageView imageView;
    TextView yazar,basTarih,puan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        init();
    }

    private void init() {

        Intent intent=getIntent();
        yazarId=intent.getStringExtra("yazarId");

        yaziId=intent.getStringExtra("articleId");
        yorumSayisi=intent.getStringExtra("yorumSayisi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Yazar Hakkında");
        recyclerView=findViewById(R.id.recyclerView);
        imageView=findViewById(R.id.imageView);
        yazar=findViewById(R.id.yazar);
        basTarih=findViewById(R.id.basTarih);
        puan=findViewById(R.id.puan);

        Call<Author> call=ManagerAll.getInstance().getAuthorDetails(yazarId);
        call.enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {

                author=response.body();

                Picasso.get().load(BaseUrl.URL+author.getYazarResim()).into(imageView);
                Log.d("eklendi",BaseUrl.URL+author.getYazarResim()+"okey");
                yazar.setText(author.getYazarNick());
                basTarih.setText(author.getYazBasTarih());
                puan.setText(author.getYazarPuan()+" Puan");
                adapter=new ArticleRecyclerViewAdapter(getApplicationContext(),author.getYazilar());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
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
