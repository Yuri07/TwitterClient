package edu.edx.yuri.twiterclient.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.edx.yuri.twiterclient.hashtags.ui.HashtagsFragment;
import edu.edx.yuri.twiterclient.lib.di.LibsModule;

/**
 * Created by yuri_ on 30/11/2017.
 */

@Singleton
@Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {

    void inject(HashtagsFragment fragment);

}
