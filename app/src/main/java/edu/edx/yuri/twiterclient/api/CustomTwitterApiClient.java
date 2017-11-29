package edu.edx.yuri.twiterclient.api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;


/**
 * Created by yuri_ on 23/11/2017.
 */

public class CustomTwitterApiClient extends TwitterApiClient {
    public CustomTwitterApiClient(TwitterSession session){
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public TimeLineService getTimelineService() {
        return getService(TimeLineService.class);
    }

}
