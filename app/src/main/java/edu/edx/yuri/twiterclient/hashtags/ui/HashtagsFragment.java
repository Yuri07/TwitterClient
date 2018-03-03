package edu.edx.yuri.twiterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.edx.yuri.twiterclient.AndroidApplication;
import edu.edx.yuri.twiterclient.R;
import edu.edx.yuri.twiterclient.entities.Hashtag;
import edu.edx.yuri.twiterclient.hashtags.HashtagsPresenter;
import edu.edx.yuri.twiterclient.hashtags.di.HashtagsComponent;
import edu.edx.yuri.twiterclient.hashtags.ui.adapters.HashtagsAdapter;
import edu.edx.yuri.twiterclient.hashtags.ui.adapters.OnHashtagClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagsFragment extends Fragment implements HashtagsView, OnHashtagClickListener {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    FrameLayout container;
    Unbinder unbinder;

    @Inject
    HashtagsAdapter adapter;
    @Inject
    HashtagsPresenter presenter;

    public HashtagsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();
        presenter.getHashtagTweets();

        return view;
    }

    private void setupInjection() {
        AndroidApplication app = (AndroidApplication)getActivity().getApplication();
        HashtagsComponent hashtagsComponent = app.getHashtagsComponent(this, this);
        //presenter = imagesComponent.getPresenter();
        hashtagsComponent.inject(this);
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showItems() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideItems() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        //Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void setContent(List<Hashtag> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Hashtag hashtag) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hashtag.getTweetURL()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
