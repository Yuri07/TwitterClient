package edu.edx.yuri.twiterclient.lib.di;



import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.edx.yuri.twiterclient.api.CustomTwitterApiClient;
import edu.edx.yuri.twiterclient.lib.GlideImageLoader;
import edu.edx.yuri.twiterclient.lib.GreenRobotEventBus;
import edu.edx.yuri.twiterclient.lib.base.EventBus;
import edu.edx.yuri.twiterclient.lib.base.ImageLoader;

/**
 * Created by yuri_ on 23/11/2017.
 */
@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session) {//
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitterSession() {//
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }

}
