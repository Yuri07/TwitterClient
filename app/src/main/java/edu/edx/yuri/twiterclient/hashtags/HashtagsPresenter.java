package edu.edx.yuri.twiterclient.hashtags;

import edu.edx.yuri.twiterclient.hashtags.events.HashtagsEvent;

/**
 * Created by yuri_ on 30/11/2017.
 */

public interface HashtagsPresenter {

    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);

}
