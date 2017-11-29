package edu.edx.yuri.twiterclient.images.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.edx.yuri.twiterclient.images.ImagesPresenter;
import edu.edx.yuri.twiterclient.images.ui.ImagesFragment;
import edu.edx.yuri.twiterclient.lib.di.LibsModule;

/**
 * Created by yuri_ on 27/11/2017.
 */
@Singleton
@Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
    /*Here we define what's the target for the injection, so we can
    either define an inject method with the target as a parameter, in this case it's
    the fragment, the images fragment, or we could define an explicit way to call the
    injection (ImagesPresenter getPresenter();//no parameters, so both work), nesse
    momento vamos deixar esses dois.*/
}
