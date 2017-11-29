package edu.edx.yuri.twiterclient.images.di;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.edx.yuri.twiterclient.api.CustomTwitterApiClient;
import edu.edx.yuri.twiterclient.entities.Image;
import edu.edx.yuri.twiterclient.images.ImagesInteractor;
import edu.edx.yuri.twiterclient.images.ImagesInteractorImpl;
import edu.edx.yuri.twiterclient.images.ImagesPresenter;
import edu.edx.yuri.twiterclient.images.ImagesPresenterImpl;
import edu.edx.yuri.twiterclient.images.ImagesRepository;
import edu.edx.yuri.twiterclient.images.ImagesRepositoryImpl;
import edu.edx.yuri.twiterclient.images.ui.ImagesView;
import edu.edx.yuri.twiterclient.images.ui.adapters.ImagesAdapter;
import edu.edx.yuri.twiterclient.images.ui.adapters.OnItemClickListener;
import edu.edx.yuri.twiterclient.lib.base.EventBus;
import edu.edx.yuri.twiterclient.lib.base.ImageLoader;

/**
 * Created by yuri_ on 27/11/2017.
 * Remember we´re using a dependency injector call Dagger 2,
 we already defined the API for the  injections  on the
 component, and now on the module, we're  going to write the code to provide all
 these dependencies,
 */
@Module
public class ImagesModule {
    private ImagesView imagesView;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView imagesView, OnItemClickListener clickListener) {
        this.imagesView = imagesView;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemsList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesView imagesView, ImagesInteractor interactor){
        return new ImagesPresenterImpl(eventBus, imagesView, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView(){
        return this.imagesView;
    }
    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitterSession(){
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
        /*assuming that since it´s already active, this
        is not going to be null*/
    }

}
