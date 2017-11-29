package edu.edx.yuri.twiterclient.images;

import org.greenrobot.eventbus.Subscribe;

import edu.edx.yuri.twiterclient.images.events.ImagesEvent;
import edu.edx.yuri.twiterclient.images.ui.ImagesView;
import edu.edx.yuri.twiterclient.lib.base.EventBus;

/**
 * Created by yuri_ on 26/11/2017.
 */

public class ImagesPresenterImpl implements ImagesPresenter {

    private EventBus eventBus;
    private ImagesView imagesView;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(EventBus eventBus, ImagesView imagesView, ImagesInteractor interactor) {
        this.eventBus = eventBus;
        this.imagesView = imagesView;
        this.interactor = interactor;
        /*eventually I'm going to work with
            an injection, but at this at the
            moment let's focus on what we have here*/
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
        imagesView= null;
    }

    @Override
    public void getImagesTweets() {
        if(imagesView!=null){
            imagesView.hideImages();
            imagesView.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        String errorMsg = event.getError();
        if(imagesView!=null){
            imagesView.showImages();
            imagesView.hideProgress();
            if(errorMsg!=null){
                imagesView.onError(errorMsg);
            }else{
                imagesView.setContent(event.getImages());
            }
        }

    }
}
