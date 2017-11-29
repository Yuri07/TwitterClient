package edu.edx.yuri.twiterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.edx.yuri.twiterclient.AndroidApplication;
import edu.edx.yuri.twiterclient.R;
import edu.edx.yuri.twiterclient.entities.Image;
import edu.edx.yuri.twiterclient.images.ImagesPresenter;
import edu.edx.yuri.twiterclient.images.di.ImagesComponent;
import edu.edx.yuri.twiterclient.images.ui.adapters.ImagesAdapter;
import edu.edx.yuri.twiterclient.images.ui.adapters.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.container)
    FrameLayout container;

    @Inject
    ImagesPresenter presenter;
    @Inject
    ImagesAdapter adapter;


    public ImagesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupInjection();
        setupRecyclerView();
        presenter.getImagesTweets();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        AndroidApplication app = (AndroidApplication)getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this, this, this);

        /*I could use inject or explicitly write a method for each one
        of the elements that I´m injecting.
        //(presenter = imagesComponent.getPresenter()[sem as anotacoes @inject em presenter];)*/
        imagesComponent.inject(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
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
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));/*in this case we're going to create a new
                                                                                        intent, it's an implicit intent to launch
                                                                                        a browser, that's why I'm using action
                                                                                          view,*/
        startActivity(intent);
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        null.unbind();
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
