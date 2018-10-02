package com.efdalincesu.blog.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.efdalincesu.blog.Models.ArticleDetails;
import com.efdalincesu.blog.Models.Result;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.Utils.AuthTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    TextView baslik, icerik, tarih, yorumSayisi, yazar, kategori;
    ImageView imageView;
    TextView begen, yorum;
    String articleId = null;
    String yorumSayisiS = null;
    List<ArticleDetails> articleDetails;
    boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        init();

        Call<List<ArticleDetails>> call = ManagerAll.getInstance().getArticleDetails(articleId);
        call.enqueue(new Callback<List<ArticleDetails>>() {
            @Override
            public void onResponse(Call<List<ArticleDetails>> call, Response<List<ArticleDetails>> response) {
                articleDetails = response.body();
                final ArticleDetails article = articleDetails.get(0);
                baslik.setText(article.getYaziBaslik());
                icerik.setText(article.getYaziIcerik());
                tarih.setText(article.getYaziTarih());
                yazar.setText(article.getYazarAdi());
                kategori.setText(article.getKategoriAdi());
                begen.setText(article.getBegeniSayisi());
                yorum.setText(yorumSayisiS);
                kategori.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), CategoryDetailsActivity.class);
                        intent.putExtra("kategoriId", article.getKategoriId());
                        intent.putExtra("kategoriAdi", article.getKategoriAdi());
                        intent.putExtra("articleId", articleId);
                        intent.putExtra("yorumSayisi", yorumSayisiS);
                        startActivity(intent);
                    }
                });
                Picasso.get().load(article.getYaziResim()).into(imageView);
                yorumSayisi.setText("Oku " + yorumSayisiS + " yorumlar");

                yorumSayisi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                        intent.putExtra("yaziId", article.getYaziId());
                        startActivity(intent);
                    }
                });

                yazar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), AuthorActivity.class);
                        intent.putExtra("yazarId", article.getYazarId());
                        intent.putExtra("articleId", articleId);
                        intent.putExtra("yorumSayisi", yorumSayisiS);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ArticleDetails>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        baslik = findViewById(R.id.baslik);
        icerik = findViewById(R.id.icerik);
        tarih = findViewById(R.id.tarih);
        yorum = findViewById(R.id.yorum);
        begen = findViewById(R.id.begen);
        yorumSayisi = findViewById(R.id.yorumSayisi);
        yazar = findViewById(R.id.yazar);
        kategori = findViewById(R.id.kategori);
        imageView = findViewById(R.id.imageView);
        final Intent intent = getIntent();
        articleId = intent.getStringExtra("articleId");
        yorumSayisiS = intent.getStringExtra("yorumSayisi");
        articleDetails = new ArrayList<>();

        yorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kontrol()) {
                    Intent intent = new Intent(getApplicationContext(), WriteCommentActivity.class);
                    intent.putExtra("yaziId", articleId);
                    intent.putExtra("yorumSayisi", yorumSayisiS);
                    startActivity(intent);
                }
            }
        });

        begen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kontrol()) {
                    int sayi = Integer.valueOf(begen.getText().toString());
                    if (isLiked) {
                        isLiked = false;
                        sayi -= 1;
                    } else {
                        isLiked = true;
                        sayi += 1;
                    }

                    begen.setText(sayi + "");
                    setImageUrl(isLiked);
                    Call<Result> call = ManagerAll.getInstance().begen(articleId, AuthTask.getUser().getUyeId(), isLiked);
                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Bir hata oluştu.Daha sonra tekrar deneyiniz!", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });


        Call<Result> call = ManagerAll.getInstance().begeniDurumu(articleId, AuthTask.getUser().getUyeId());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (result.getResult().equals("ok")) {
                    isLiked = true;
                    setImageUrl(isLiked);
                } else {
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

    }


    public void setImageUrl(boolean isLiked) {
        if (isLiked)
            begen.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.article_liked, 0);
        else
            begen.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.article_like, 0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            Intent intent = new Intent(this, MainActivity.class);
            NavUtils.navigateUpTo(this, intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean kontrol() {


        if (AuthTask.getUser().getUyeId().equals("-1")) {

            Snackbar.make(findViewById(R.id.main_content), "Önce Giriş Yapmalısın!", Snackbar.LENGTH_LONG)
                    .setAction("Giriş Yap", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }).show();

            return false;
        } else {

            return true;

        }
    }
}
