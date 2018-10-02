package com.efdalincesu.blog.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efdalincesu.blog.Models.Category;
import com.efdalincesu.blog.R;
import com.efdalincesu.blog.View.CategoryDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryListviewAdapter extends BaseAdapter implements Filterable {

    View rootView;
    Context context;
    List<Category> categoryList;
    List<Category> filteredList;

    public CategoryListviewAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        this.filteredList = categoryList;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(categoryList.get(i).getKategoriId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.category_row_layout, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Category category = filteredList.get(i);
        rootView = view;

        Picasso.get().load(category.getKategoriResmi()).into(viewHolder.imageView);
        viewHolder.kategoriAdi.setText(category.getKategoriAdi());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CategoryDetailsActivity.class);
                intent.putExtra("kategoriId", category.getKategoriId());
                intent.putExtra("kategoriAdi", category.getKategoriAdi());
                rootView.getContext().startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String search = charSequence.toString();

                if (search.isEmpty()) {
                    filteredList = categoryList;
                } else {

                    ArrayList<Category> tempFilteredList = new ArrayList<>();

                    for (Category category : categoryList) {
                        if (category.getKategoriAdi().contains(search)) {
                            tempFilteredList.add(category);
                        }
                    }

                    filteredList = tempFilteredList;

                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Category>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder {

        LinearLayout linearLayout;
        ImageView imageView;
        TextView kategoriAdi;

        ViewHolder(View view) {
            linearLayout = view.findViewById(R.id.linearLayout);
            imageView = view.findViewById(R.id.imageView);
            kategoriAdi = view.findViewById(R.id.kategoriAdi);

        }
    }
}
