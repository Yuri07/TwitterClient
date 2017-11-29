package edu.edx.yuri.twiterclient;

import android.app.Application;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import edu.edx.yuri.twiterclient.images.di.DaggerImagesComponent;
import edu.edx.yuri.twiterclient.images.di.ImagesComponent;
import edu.edx.yuri.twiterclient.images.di.ImagesModule;
import edu.edx.yuri.twiterclient.images.ui.ImagesView;
import edu.edx.yuri.twiterclient.images.ui.adapters.OnItemClickListener;
import edu.edx.yuri.twiterclient.lib.di.LibsModule;

/**
 * Created by yuri_ on 23/11/2017.
 */

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Twitter.initialize(this);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_CONSUMER_KEY,
                        BuildConfig.TWITTER_CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView imagesView, OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(imagesView, clickListener))
                .build();
    }

}
