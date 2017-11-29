package edu.edx.yuri.twiterclient.images.ui;


import java.util.List;

import edu.edx.yuri.twiterclient.entities.Image;

/**
 * Created by yuri_ on 25/11/2017.
 */

public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
