package com.efdalincesu.blog.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Comment;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.Utils.AuthTask;
import com.efdalincesu.blog.View.Adapters.CommentListviewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    String yaziId;
    List<Comment> comments;
    ListView listView;
    CommentListviewAdapter adapter;
    FloatingActionButton fabYorum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
    }

    private void init() {

        Intent intent=getIntent();
        yaziId=intent.getStringExtra("yaziId");
        listView=findViewById(R.id.listview);
        fabYorum=findViewById(R.id.fabYorum);
        comments=new ArrayList<>();

        Call<List<Comment>> call= ManagerAll.getInstance().getCommentDetails(yaziId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                comments=response.body();
                adapter=new CommentListviewAdapter(getApplicationContext(),comments);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        fabYorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AuthTask.isLogin()) {
                    Intent intent = new Intent(getApplicationContext(), WriteCommentActivity.class);
                    intent.putExtra("yaziId", yaziId);
                    startActivity(intent);
                }else{
                    Snackbar.make(findViewById(R.id.relative),"Giriş Yapmalısın!",Snackbar.LENGTH_LONG)
                            .setAction("Giriş Yap", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getApplicationContext(), WriteCommentActivity.class);
                                    intent.putExtra("yaziId", yaziId);
                                    startActivity(intent);
                                }
                            }).show();
                }
            }
        });

    }
}
