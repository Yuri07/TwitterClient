package edu.edx.yuri.twiterclient.entities;

/**
 * Created by yuri_ on 25/11/2017.
 */

public class Image {//Nosso POJO(Plain Old Java Object)
    private String id;
    private String imageURL;
    private String tweetText;
    private int favoriteCount;
    private final static String BASE_TWEET_URL ="https://twitter.com/null/status/";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getTweetUrl(){
        return BASE_TWEET_URL + this.id;
    }
}
