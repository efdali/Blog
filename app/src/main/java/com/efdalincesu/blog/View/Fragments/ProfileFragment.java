package com.efdalincesu.blog.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.BaseUrl;
import com.efdalincesu.blog.Utils.AuthTask;
import com.efdalincesu.blog.View.EditProfileActivity;
import com.efdalincesu.blog.View.LoginActivity;
import com.efdalincesu.blog.View.RegisterActivity;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    ImageView imageView;
    TextView baslik, altText, kayitOl, gizlilikPolitikası, oyla, projeler, info, cikis,duzenle;
    AuthTask authTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        authTask=AuthTask.init(getContext());

        imageView = rootView.findViewById(R.id.imageView);
        baslik = rootView.findViewById(R.id.baslik);
        altText = rootView.findViewById(R.id.altText);
        kayitOl = rootView.findViewById(R.id.kayitOl);
        gizlilikPolitikası = rootView.findViewById(R.id.gizlilikPolitikası);
        oyla = rootView.findViewById(R.id.oyla);
        projeler = rootView.findViewById(R.id.projeler);
        info = rootView.findViewById(R.id.info);
        cikis = rootView.findViewById(R.id.cikis);
        duzenle=rootView.findViewById(R.id.duzenle);

        isLogin(authTask.isLogin());

        duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=null;
                if (authTask.isLogin())
                    intent=new Intent(getContext(),EditProfileActivity.class);
                else
                    intent=new Intent(getContext(), LoginActivity.class);

                startActivity(intent);
            }
        });


        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authTask.setLogin(false,null);
                isLogin(authTask.isLogin());
            }
        });


        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                rootView.getContext().startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        isLogin(authTask.isLogin());

    }

    public void isLogin(boolean isLogin) {


        baslik.setText(isLogin ? authTask.getUser().getUyeNick() : "Hoşgeldiniz");
        altText.setText(isLogin ? authTask.getUser().getUyeMail() : "Kayıtlı Değilmisin?");
        if (isLogin)
            Picasso.get().load(authTask.getUser().getUyeResim()).into(imageView);
        else
            imageView.setBackgroundResource(R.drawable.anonymous_user);
        kayitOl.setVisibility(isLogin ? View.GONE : View.VISIBLE);
        cikis.setVisibility(!isLogin ? View.GONE : View.VISIBLE);
        duzenle.setText(isLogin ? "Düzenle" : "Giriş Yap");
        duzenle.setGravity(isLogin ? Gravity.RIGHT : Gravity.CENTER);


    }
}
