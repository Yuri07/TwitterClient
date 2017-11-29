package edu.edx.yuri.twiterclient.images;

/**
 * Created by yuri_ on 26/11/2017.
 */

public class ImagesInteractorImpl implements ImagesInteractor {

    private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
