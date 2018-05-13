package com.fullerton.project.blissfulmoms.fragments;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fullerton.project.blissfulmoms.R;
import com.fullerton.project.blissfulmoms.models.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Saranya A on 5/7/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    List<Articles> articles;
    Context mContext;
    RecyclerViewClickListener listener;

    ArticleAdapter(List<Articles> articles, Context context, RecyclerViewClickListener listener) {
        this.articles = articles;
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_items, parent, false);
        final ArticleViewHolder avh = new ArticleViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, avh.getAdapterPosition());
            }
        });
        return avh;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        Context context = holder.articlePhoto.getContext();

        Picasso.with(context)
                .load(articles.get(position).getArticleImageURL())
                .into(holder.articlePhoto);
        holder.articleDesc.setText(articles.get(position).getArticleDesc());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView articlePhoto;
        TextView articleDesc;

        ArticleViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvArticle);
            articleDesc = (TextView) itemView.findViewById(R.id.textArticleDetails);
            articlePhoto = (ImageView) itemView.findViewById(R.id.imageViewArticles);
        }
    }

}
