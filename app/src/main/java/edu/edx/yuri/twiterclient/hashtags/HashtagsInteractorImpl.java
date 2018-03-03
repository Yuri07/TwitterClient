package edu.edx.yuri.twiterclient.hashtags;

/**
 * Created by yuri_ on 30/11/2017.
 */

public class HashtagsInteractorImpl implements HashtagsInteractor {

    private HashtagsRepository repository;

    public HashtagsInteractorImpl(HashtagsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getHashtags();
    }

}
