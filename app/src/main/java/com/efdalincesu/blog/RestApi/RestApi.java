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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RestApi {

    @GET("selectArticle.php")
    Call<List<Article>> IGetArticle();

    @GET("articleDetails.php")
    Call<List<ArticleDetails>> IGetArticleDetails(@Query("yaziId") String articleId);

    @GET("authorDetails.php")
    Call<Author> IgetAuthorDetails(@Query("yazarId") String yazarId);

    @GET("commentDetails.php")
    Call<List<Comment>> IGetCommentDetails(@Query("yaziId") String yaziId);

    @GET("getCategory.php")
    Call<List<Category>> IGetCategory();

    @GET("getCategoryDetails.php")
    Call<List<Article>> IgetCategoryDetails(@Query("kategoriId") String kategoriId);

    @FormUrlEncoded
    @POST("register.php")
    Call<Result> IRegister(@Field("kadi") String kadi,@Field("mail") String mail,@Field("sifre") String sifre,@Field("resim") String resim);

    @FormUrlEncoded
    @POST("login.php")
    Call<User> ILogin(@Field("kadi") String kadi, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("edit_profile.php")
    Call<Result> IEditProfile(@Field("id") String id,@Field("kadi") String kadi,@Field("mail") String mail,@Field("resim") String resim,@Field("sifre") String sifre);

    @GET("begeniDurum.php")
    Call<Result> IbegeniDurum(@Query("yaziId") String yaziId,@Query("kullaniciId") String kullaniciId);

    @GET("articleLike.php")
    Call<Result> Ibegen(@Query("yaziId") String yaziId,@Query("kullaniciId") String kullaniciId,@Query("durum") boolean durum);

    @GET("shareComment.php")
    Call<Result> IshareComment(@Query("yaziId") String yaziId,@Query("kullaniciId") String kullaniciId,@Query("yorum") String yorum);

    @GET("getFavorite.php")
    Call<List<Article>> getFavorite(@Query("kullaniciId") String kullaniciId);
}
