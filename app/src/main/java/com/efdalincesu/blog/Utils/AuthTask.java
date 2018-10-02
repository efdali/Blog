package com.efdalincesu.blog.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.efdalincesu.blog.Models.User;

public class AuthTask {


    private static String SHARED_NAME = "Cookie";
    private static String IS_LOGIN = "isLogin";
    private static String UYE_ID = "uyeId";
    private static String UYE_NICK = "uyeNick";
    private static String UYE_MAIL = "uyeMail";
    private static String UYE_RESIM = "uyeResim";
    static Context context;
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    private static boolean isLogin;
    private static User user;


    public AuthTask(boolean isLogin, User user, Context context) {
        this.isLogin = isLogin;
        this.user = user;
        this.context = context;
    }

    public static boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login,User user) {
        isLogin = login;
        setUser(user);
        commit();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        if (user!=null) {
            AuthTask.user = user;
        }else{
            AuthTask.user.set();
        }
    }

    public void commit() {


        editor.putBoolean(IS_LOGIN, isLogin);
        editor.putString(UYE_ID, user.getUyeId());
        editor.putString(UYE_NICK, user.getUyeNick());
        editor.putString(UYE_MAIL, user.getUyeMail());
        editor.putString(UYE_RESIM, user.getUyeResim());

        editor.commit();

    }

    public static AuthTask init(Context context) {

        preferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

        isLogin = preferences.getBoolean(IS_LOGIN, false);
        user=new User();
        user.setUyeId(preferences.getString(UYE_ID, "-1"));
        user.setUyeNick(preferences.getString(UYE_NICK, "-1"));
        user.setUyeMail(preferences.getString(UYE_MAIL, "-1"));
        user.setUyeResim(preferences.getString(UYE_RESIM, "-1"));

        return new AuthTask(isLogin, user, context);

    }
}
