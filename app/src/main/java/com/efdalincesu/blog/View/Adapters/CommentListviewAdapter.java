package com.efdalincesu.blog.View.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.efdalincesu.blog.Models.Comment;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.RestApi.BaseUrl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommentListviewAdapter extends BaseAdapter {

    Context context;
    List<Comment> comments;

    public CommentListviewAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder=null;
        if (view==null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.comment_row_layout, viewGroup, false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }



        Picasso.get().load(BaseUrl.URL+comments.get(i).getKullanici().getUyeResim()).into(viewHolder.imageView);
        viewHolder.kullaniciAdi.setText(comments.get(i).getKullanici().getUyeNick());
        viewHolder.yorum.setText(comments.get(i).getYorumIcerik());
        viewHolder.tarih.setText(comments.get(i).getYorumTarih());


        return view;
    }

    class ViewHolder{


        TextView kullaniciAdi,yorum,tarih;
        ImageView imageView;

        public ViewHolder(View view){

             kullaniciAdi=view.findViewById(R.id.kullaniciAdi);
             yorum=view.findViewById(R.id.yorum);
             tarih=view.findViewById(R.id.tarih);
             imageView=view.findViewById(R.id.imageView);

        }

    }
}
