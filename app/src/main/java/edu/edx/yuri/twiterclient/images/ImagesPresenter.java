package edu.edx.yuri.twiterclient.images;

import edu.edx.yuri.twiterclient.images.events.ImagesEvent;

/**
 * Created by yuri_ on 25/11/2017.
 */

public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImagesTweets();
    void onEventMainThread(ImagesEvent event);
}
