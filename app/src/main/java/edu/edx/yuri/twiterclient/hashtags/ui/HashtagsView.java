package edu.edx.yuri.twiterclient.hashtags.ui;

import java.util.List;

import edu.edx.yuri.twiterclient.entities.Hashtag;

/**
 * Created by yuri_ on 30/11/2017.
 */

public interface HashtagsView {
    void showItems();
    void hideItems();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);

}
