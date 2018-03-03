package edu.edx.yuri.twiterclient.hashtags.di;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.edx.yuri.twiterclient.api.CustomTwitterApiClient;
import edu.edx.yuri.twiterclient.entities.Hashtag;
import edu.edx.yuri.twiterclient.hashtags.HashtagsInteractor;
import edu.edx.yuri.twiterclient.hashtags.HashtagsInteractorImpl;
import edu.edx.yuri.twiterclient.hashtags.HashtagsPresenter;
import edu.edx.yuri.twiterclient.hashtags.HashtagsPresenterImpl;
import edu.edx.yuri.twiterclient.hashtags.HashtagsRepository;
import edu.edx.yuri.twiterclient.hashtags.HashtagsRepositoryImpl;
import edu.edx.yuri.twiterclient.hashtags.ui.HashtagsView;
import edu.edx.yuri.twiterclient.hashtags.ui.adapters.HashtagsAdapter;
import edu.edx.yuri.twiterclient.hashtags.ui.adapters.OnHashtagClickListener;
import edu.edx.yuri.twiterclient.images.ui.adapters.OnItemClickListener;
import edu.edx.yuri.twiterclient.lib.base.EventBus;

/**
 * Created by yuri_ on 30/11/2017.
 */
@Module
public class HashtagsModule {

    private HashtagsView hashtagsView;
    private OnHashtagClickListener onHashtagClickListener;

    public HashtagsModule(HashtagsView hashtagsView, OnHashtagClickListener onHashtagClickListener) {
        this.hashtagsView = hashtagsView;
        this.onHashtagClickListener = onHashtagClickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> dataset, OnHashtagClickListener clickListener) {
        return new HashtagsAdapter(dataset, clickListener);
    }

    @Provides
    @Singleton
    OnHashtagClickListener providesOnHashtagClickListener() {
        return this.onHashtagClickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemsList() {
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(EventBus eventBus, HashtagsView view,
                                                HashtagsInteractor interactor) {
        return new HashtagsPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return this.hashtagsView;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsRepositoryImpl(eventBus, client);
    }

    /*@Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session) {//
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitterSession() {//
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }*/

}
