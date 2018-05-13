package com.fullerton.project.blissfulmoms.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullerton.project.blissfulmoms.R;
import com.fullerton.project.blissfulmoms.models.Articles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saranya A on 5/7/2017.
 */

public class ArticleFragment extends Fragment {

    private RecyclerView rv;
    private List<Articles> articles;
    private CardView articleCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.article_fragment, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rvArticle);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        articleCardView = (CardView) view.findViewById(R.id.cvArticle);

        try {
            initializeData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initializeAdapter();

        return view;

    }

    private void initializeData() throws IOException, JSONException {
        articles = new ArrayList<Articles>();
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.articles);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("articles");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Articles article = new Articles();
                article.setArticleDesc(jo_inside.getString("description"));
                article.setArticleURL(jo_inside.getString("url"));
                article.setArticleImageURL(jo_inside.getString("image"));
                articles.add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initializeAdapter() {
        ArticleAdapter adp = new ArticleAdapter(articles, getActivity(), new RecyclerViewClickListener() {
            public static final String TAG = "BLAH";

            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, articles.get(position).getArticleImageURL());
                Log.d(TAG, articles.get(position).getArticleURL());
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articles.get(position).getArticleURL()));
                startActivity(browserIntent);
            }
        });
        rv.setAdapter(adp);
    }

}
