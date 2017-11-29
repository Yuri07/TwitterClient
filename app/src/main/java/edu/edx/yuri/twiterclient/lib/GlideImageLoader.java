package edu.edx.yuri.twiterclient.lib;

import android.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import edu.edx.yuri.twiterclient.lib.base.ImageLoader;

/**
 * Created by yuri_ on 23/11/2017.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager){//usando fragment pq trabalharemos com tabs//RequestManager glideRequestManager){
        this.glideRequestManager = glideRequestManager;//Glide.with(fragment);//glideRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        glideRequestManager
                .load(URL)
                .into(imageView);
    }
}
