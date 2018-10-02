package com.efdalincesu.blog.RestApi;

import com.efdalincesu.blog.Models.Article;
import com.efdalincesu.blog.Models.ArticleDetails;
import com.efdalincesu.blog.Models.Author;
import com.efdalincesu.blog.Models.Category;
import com.efdalincesu.blog.Models.Comment;
import com.efdalincesu.blog.Models.Result;
import com.efdalincesu.blog.Models.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static final ManagerAll ourInstance = new ManagerAll();

    private ManagerAll() {
    }

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<List<Article>> getArticle() {

        Call<List<Article>> call = getRestApi().IGetArticle();

        return call;
    }

    public Call<List<ArticleDetails>> getArticleDetails(String articleId) {

        Call<List<ArticleDetails>> call = getRestApi().IGetArticleDetails(articleId);

        return call;
    }

    public Call<Author> getAuthorDetails(String yazarId) {

        Call<Author> call = getRestApi().IgetAuthorDetails(yazarId);

        return call;
    }

    public Call<List<Comment>> getCommentDetails(String yaziId) {

        Call<List<Comment>> call = getRestApi().IGetCommentDetails(yaziId);

        return call;
    }

    public Call<List<Category>> getCategory() {

        Call<List<Category>> call = getRestApi().IGetCategory();

        return call;
    }


    public Call<List<Article>> getCategoryDetails(String kategoriId){

        Call<List<Article>> call=getRestApi().IgetCategoryDetails(kategoriId);

        return call;
    }

    public Call<Result> register(String kadi,String mail,String sifre,String resim){

        Call<Result> call=getRestApi().IRegister(kadi, mail, sifre, resim);

        return call;
    }

    public Call<User> login(String kadi, String sifre){

        Call<User> call=getRestApi().ILogin(kadi, sifre);

        return call;
    }

    public Call<Result> editProfile(String id,String kadi,String mail,String resim,String sifre){

        Call<Result> call=getRestApi().IEditProfile(id,kadi, mail, resim, sifre);

        return call;
    }

    public Call<Result> begeniDurumu(String yaziId,String kullaniciId){

        Call<Result> call=getRestApi().IbegeniDurum(yaziId, kullaniciId);

        return call;
    }

    public Call<Result> begen(String yaziId,String kullaniciId,boolean durum){

        Call<Result> call=getRestApi().Ibegen(yaziId, kullaniciId,durum);

        return call;
    }

    public Call<Result> shareComment(String yaziId,String kullaniciId,String yorum){

        Call<Result> call=getRestApi().IshareComment(yaziId, kullaniciId,yorum);

        return call;
    }
}
