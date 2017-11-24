package edu.edx.yuri.twiterclient.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.core.TwitterCore;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.edx.yuri.twiterclient.LoginActivity;
import edu.edx.yuri.twiterclient.R;
import edu.edx.yuri.twiterclient.hashtags.HashtagsFragment;
import edu.edx.yuri.twiterclient.images.ImagesFragment;
import edu.edx.yuri.twiterclient.main.ui.adpaters.MainSectionsPageAdapter;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupAtapter();
    }

    private void setupAtapter() {
        Fragment[] fragments = new Fragment[]{new ImagesFragment(), new HashtagsFragment()};
        String[] titles = new String[]{String.valueOf(R.string.main_header_images), String.valueOf(R.string.main_header_hashtags)};
        MainSectionsPageAdapter adapter = new MainSectionsPageAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_logout) {
            logout();
        }


        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        //Twitter.getLogger();
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
