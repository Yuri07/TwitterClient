package edu.edx.yuri.twiterclient.hashtags.ui.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.edx.yuri.twiterclient.R;
import edu.edx.yuri.twiterclient.entities.Hashtag;

/**
 * Created by yuri_ on 30/11/2017.
 */

public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder> {

    private List<Hashtag> dataset;
    private OnHashtagClickListener clickListener;

    public HashtagsAdapter(List<Hashtag> dataset, OnHashtagClickListener clickListener) {
        this.dataset = dataset;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hastags, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag tweet = dataset.get(position);
        holder.setOnClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.setItems(tweet.getHashtags());
    }

    public void setItems(List<Hashtag> newItems) {
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTweet)
        TextView txtTweet;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        private View view;
        private HashtagsListAdapter adapter;
        private ArrayList<String> items;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;

            items = new ArrayList<String>();
            adapter = new HashtagsListAdapter(items);

            GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }

        public void setItems(List<String> newItems){
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }

        public void setOnClickListener(final Hashtag hashtag, final OnHashtagClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(hashtag);//Na classe HashtagsFragment esse metodo é
                    // implementado chamando uma activity para mostrar o tweet pelo metodo
                    // Hashtag.getTweetUrl().
                }
            });
        }

    }


}
