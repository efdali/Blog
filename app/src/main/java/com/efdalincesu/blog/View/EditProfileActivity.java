package com.efdalincesu.blog.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.efdalincesu.blog.Models.Result;
import com.efdalincesu.blog.Models.User;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.ManagerAll;
import com.efdalincesu.blog.Utils.AuthTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    ImageView imageView;
    EditText userNameEdittext, mailEdittext, passwordEdittext;
    TextInputLayout passwordLayout;
    boolean isChangeImage = false;
    Bitmap bitmap;
    ProgressBar progressBar;
    CardView cardView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = AuthTask.getUser();

        imageView = findViewById(R.id.imageView);
        userNameEdittext = findViewById(R.id.usernameEt);
        mailEdittext = findViewById(R.id.mailEt);
        passwordEdittext = findViewById(R.id.passwordEt);
        passwordLayout = findViewById(R.id.passwordLayout);
        cardView = findViewById(R.id.cardView);
        progressBar = findViewById(R.id.progressBar);

        Picasso.get().load(user.getUyeResim()).into(imageView);
        userNameEdittext.setText(user.getUyeNick());
        mailEdittext.setText(user.getUyeMail());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {

            isChangeImage = true;
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                Toast.makeText(this, "Birşeyler Eksik Gitti!", Toast.LENGTH_LONG).show();
            }

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == 1) {


            attemptLogin();


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuItem save_item = menu.add(0, 1, 1, "Kaydet");
        save_item.setIcon(R.drawable.menu_item_save);
        save_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        return super.onCreateOptionsMenu(menu);
    }

    public String imageToString() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();


        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    public void attemptLogin() {

        userNameEdittext.setError(null);
        mailEdittext.setError(null);
        passwordLayout.setError(null);

        String email = mailEdittext.getText().toString().trim();
        String name = userNameEdittext.getText().toString().trim();
        String pass = passwordEdittext.getText().toString().trim();


        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(name)) {
            userNameEdittext.setError("Zorunlu Alan");
            focusView = userNameEdittext;
            cancel = true;
        } else if (TextUtils.isEmpty(email)) {
            mailEdittext.setError("Zorunlu Alan");
            focusView = mailEdittext;
            cancel = true;
        } else if (!validateEmail(email)) {
            mailEdittext.setError("Yanlış mail");
            focusView = mailEdittext;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(pass) || validatePassword(pass)) {
            passwordLayout.setError("Zorunlu Alan(En az 6 karakter olmalıdır!)");
            focusView = passwordEdittext;
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

                call = ManagerAll.getInstance().editProfile(AuthTask.getUser().getUyeId(), name, email, imageToString(), pass);

            } else {
                call = ManagerAll.getInstance().editProfile(AuthTask.getUser().getUyeId(), name, email, String.valueOf(-1), pass);
            }


            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Result result = response.body();
                    if (result.getResult().equals("ok")) {
                        AuthTask.init(getApplicationContext()).setLogin(false, null);
                        Toast.makeText(getApplicationContext(), "Bilgileriniz Güncellenmiştir.Lütfen tekrar giriş yapınız!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Hatalı Bilgi Girdiniz!", Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public boolean validatePassword(String password) {


        return password.trim().length() < 6;
    }

    public boolean validateEmail(String email) {

        return email.contains("@");
    }
}
