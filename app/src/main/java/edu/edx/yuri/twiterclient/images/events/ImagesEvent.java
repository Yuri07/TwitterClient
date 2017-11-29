package edu.edx.yuri.twiterclient.images.events;

import java.util.List;

import edu.edx.yuri.twiterclient.entities.Image;

/**
 * Created by yuri_ on 25/11/2017.
 */

public class ImagesEvent {

    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
