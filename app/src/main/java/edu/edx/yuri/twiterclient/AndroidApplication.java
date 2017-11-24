package edu.edx.yuri.twiterclient;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

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
}
