package edu.edx.yuri.twiterclient.images;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.edx.yuri.twiterclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {


    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_images_fragment);
        return textView;
    }

}
