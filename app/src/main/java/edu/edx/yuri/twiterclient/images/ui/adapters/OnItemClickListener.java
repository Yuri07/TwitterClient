package edu.edx.yuri.twiterclient.images.ui.adapters;

import edu.edx.yuri.twiterclient.entities.Image;

/**
 * Created by yuri_ on 25/11/2017.
   Here we have the item, click listener is going to be a contract also
   here I defined only the item click, as you may recall the recyclerview doesn't
   handle the click as the previous list view used to do, so now we need to define
   this on another step, I'm going to implement this item click with the view
   holder.
 */

public interface OnItemClickListener {
    void onItemClick(Image image);
}
