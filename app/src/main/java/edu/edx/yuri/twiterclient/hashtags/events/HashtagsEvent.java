package edu.edx.yuri.twiterclient.hashtags.events;

import java.util.List;

import edu.edx.yuri.twiterclient.entities.Hashtag;

/**
 * Created by yuri_ on 30/11/2017.
 */

public class HashtagsEvent {

    private String error;
    private List<Hashtag> hashtags;

    public List<Hashtag> getHashtags(){
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags){

        this.hashtags = hashtags;

    }

    public String getError(){
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
