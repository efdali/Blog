package com.efdalincesu.blog.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.efdalincesu.blog.Models.Article;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.View.ArticleActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder> implements Filterable{


    Context context;
    List<Article> articleList;
    List<Article> filteredList;

    public ArticleRecyclerViewAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articleList =articles;
        this.filteredList=articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_home_row_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        if (filteredList.size()!=0) {
            final Article article = filteredList.get(position);
            Picasso.get().load(article.getYaziResim()).into(holder.imageView);
            holder.baslik.setText(article.getYaziBaslik());
            holder.icerik.setText(article.getYaziIcerik());
            holder.tarih.setText(article.getYaziTarih());
            if (article.getYorumSayisi() != null) {
                holder.yorumSayisi.setText(article.getYorumSayisi() + "");
            } else {
                holder.yorumSayisi.setVisibility(View.GONE);
            }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ArticleActivity.class);
                    intent.putExtra("articleId", article.getYaziId());
                    intent.putExtra("yorumSayisi", article.getYorumSayisi());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString=charSequence.toString().toLowerCase();

                if (searchString.isEmpty()){
                    filteredList=articleList;
                }else{

                    ArrayList<Article> articles=new ArrayList<>();

                    for (Article article : articleList){
                        if (article.getYaziBaslik().toLowerCase().contains(searchString)){
                            articles.add(article);
                        }
                    }


                    filteredList=articles;

                }

                FilterResults results=new FilterResults();
                results.values=filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList= (List<Article>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView baslik, icerik, tarih, yorumSayisi;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            baslik = itemView.findViewById(R.id.baslik);
            icerik = itemView.findViewById(R.id.icerik);
            tarih = itemView.findViewById(R.id.tarih);
            yorumSayisi = itemView.findViewById(R.id.yorumSayisi);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
