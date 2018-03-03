package edu.edx.yuri.twiterclient.hashtags;

import org.greenrobot.eventbus.Subscribe;

import edu.edx.yuri.twiterclient.hashtags.events.HashtagsEvent;
import edu.edx.yuri.twiterclient.hashtags.ui.HashtagsView;
import edu.edx.yuri.twiterclient.lib.base.EventBus;

/**
 * Created by yuri_ on 30/11/2017.
 */

public class HashtagsPresenterImpl implements HashtagsPresenter {

    private HashtagsView hashtagsView;
    private EventBus eventBus;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(EventBus eventBus, HashtagsView view, HashtagsInteractor interactor) {
        this.eventBus = eventBus;
        this.hashtagsView = view;
        this.interactor = interactor;

    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        hashtagsView = null;
    }

    @Override
    public void getHashtagTweets() {
        if (hashtagsView != null) {
            hashtagsView.hideItems();
            hashtagsView.showProgress();
        }
        //hashtagsView.onError("Teste pre !");
        interactor.execute();
    }



    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event){
        String errorMsg = event.getError();

        //hashtagsView.onError(errorMsg);

        if (hashtagsView != null) {
            hashtagsView.showItems();
            hashtagsView.hideProgress();
            if (errorMsg != null) {
                hashtagsView.onError(errorMsg);
            } else {
                hashtagsView.setContent(event.getHashtags());
            }
        }
    }
}

