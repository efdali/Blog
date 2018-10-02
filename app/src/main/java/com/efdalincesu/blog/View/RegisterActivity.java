package com.efdalincesu.blog.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Result;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout usernameLayout, passwordLayout, mailLayout;
    EditText username, password, mail;
    ImageView imageView;
    Button register;
    TextView login;
    Bitmap bitmap;
    ProgressBar progressBar;
    CardView cardView;
    boolean isChangeImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


        }

        imageView = findViewById(R.id.imageView);
        username = findViewById(R.id.usernameEdittext);
        password = findViewById(R.id.passwordEdittext);
        mail = findViewById(R.id.mailEdittext);
        usernameLayout = findViewById(R.id.usernameLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        mailLayout = findViewById(R.id.mailLayout);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        cardView = findViewById(R.id.cardView);
        progressBar = findViewById(R.id.progressBar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Resim Seçiniz!"), 1);

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard();

                attemptLogin();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {


            Uri uri = data.getData();
            isChangeImage = true;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                Toast.makeText(this, "Birşeyler Eksik Gitti!", Toast.LENGTH_LONG).show();
            }

        }


    }

    public String imageToString() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();


        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    public void attemptLogin() {

        mail.setError(null);
        username.setError(null);
        password.setError(null);

        String email = mail.getText().toString().trim();
        String name = username.getText().toString().trim();
        String pass = password.getText().toString().trim();


        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(name)) {
            username.setError("Zorunlu Alan");
            focusView = username;
            cancel = true;
        } else if (TextUtils.isEmpty(email)) {
            mail.setError("Zorunlu Alan");
            focusView = mail;
            cancel = true;
        } else if (!validateEmail(email)) {
            mail.setError("Yanlış mail");
            focusView = mail;
            cancel = true;
        } else if (TextUtils.isEmpty(pass) || validatePassword(pass)) {
            passwordLayout.setError("Zorunlu Alan(En az 6 karakter olmalıdır!)");
            focusView = password;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            cardView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            Call<Result> call = null;

            if (isChangeImage) {

                call = ManagerAll.getInstance().register(name, email, pass, imageToString());

            } else {
                call = ManagerAll.getInstance().register(name, email, pass, String.valueOf(-1));
            }


            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    String result = response.body().getResult();
                    if (result.equals("ok")) {
                        Toast.makeText(getApplicationContext(), "Kayıt Oldunuz.Lütfen Giriş Yapınız!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Hatalı Bilgi Girdiniz!", Toast.LENGTH_LONG).show();
                    }
                    cardView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validatePassword(String password) {


        return password.trim().length() < 6;
    }

    public boolean validateEmail(String email) {

        return email.contains("@");
    }

}
