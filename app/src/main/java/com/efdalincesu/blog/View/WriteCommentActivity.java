package com.efdalincesu.blog.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Result;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.Utils.AuthTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteCommentActivity extends AppCompatActivity {

    String yaziId, yorumSayisi;
    EditText yorum;
    Button paylas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);


        init();
    }

    private void init() {

        final Intent i = getIntent();
        yaziId = i.getStringExtra("yaziId");
        yorumSayisi = i.getStringExtra("yorumSayisi");

        getSupportActionBar().setTitle("Yorum Yaz");

        yorum = findViewById(R.id.yorum);
        paylas = findViewById(R.id.paylas);

        paylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String yorumS = yorum.getText().toString();
                if (!TextUtils.isEmpty(yorumS)) {

                    Call<Result> call = ManagerAll.getInstance().shareComment(yaziId, AuthTask.getUser().getUyeId(), yorumS);
                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            Result result = response.body();
                            if (result.getResult().equals("ok")) {
                                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                                intent.putExtra("articleId", yaziId);
                                intent.putExtra("yorumSayisi", yorumSayisi);
                                finish();
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Bir hata oluştu.Daha sonra tekrar deneyin!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }
        });


    }
}
