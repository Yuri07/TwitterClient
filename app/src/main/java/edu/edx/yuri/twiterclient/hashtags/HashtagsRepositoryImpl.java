package edu.edx.yuri.twiterclient.hashtags;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.edx.yuri.twiterclient.api.CustomTwitterApiClient;
import edu.edx.yuri.twiterclient.entities.Hashtag;
import edu.edx.yuri.twiterclient.hashtags.events.HashtagsEvent;
import edu.edx.yuri.twiterclient.lib.base.EventBus;
import retrofit2.Call;

/**
 * Created by yuri_ on 30/11/2017.
 */

public class HashtagsRepositoryImpl implements HashtagsRepository {

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public HashtagsRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getHashtags() {

        Call<List<Tweet>> c = client.getTimelineService().homeTimeline(TWEET_COUNT,
                true, true,
                true, true);

        c.enqueue(new Callback<List<Tweet>>() {

            @Override
            public void success(Result<List<Tweet>> result) {
                List<Hashtag> items = new ArrayList<Hashtag>();

                for (Tweet tweet : result.data){
                    if (containsHashtags(tweet)) {
                        Hashtag tweetModel = new Hashtag();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);
                        tweetModel.setTweetText(tweet.text);

                        List<String> hashtags = new ArrayList<String>();
                        for (HashtagEntity hashtag : tweet.entities.hashtags) {
                            hashtags.add(hashtag.text);
                        }
                        tweetModel.setHashtags(hashtags);

                        items.add(tweetModel);
                    }
                }

                Collections.sort(items, new Comparator<Hashtag>(){

                    @Override
                    public int compare(Hashtag hashtag, Hashtag t1) {
                        return t1.getFavoriteCount()-hashtag.getFavoriteCount();
                    }
                });
                post(items);
            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getLocalizedMessage());
            }
        });

        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        //post("Teste pos");
    }

    private boolean containsHashtags(Tweet tweet){
        return tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void post(List<Hashtag> items) {
        post(items, null);
    }

    private void post(String error) {
        post(null, error);
    }

    private void post(List<Hashtag> items, String error) {
        HashtagsEvent event = new HashtagsEvent();
        event.setError(error);
        event.setHashtags(items);
        eventBus.post(event);
    }

}
