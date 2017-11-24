package edu.edx.yuri.twiterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.edx.yuri.twiterclient.main.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.twitterLoginButton)
    TwitterLoginButton loginButton;
    @BindView(R.id.container)
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        if(TwitterCore.getInstance().getSessionManager().getActiveSession() != null){
            navigateToMainScreen();
        }

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                navigateToMainScreen();
                /*TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;*/

            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                String msgError = String.format(getString(R.string.login_error_message),
                        exception.getLocalizedMessage());
                Snackbar.make(container, msgError, Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
    /*We are going also to need a start and onActivityResult because eventually the
    Twitter SDK is going to return, the flow here is that the user is on our app,
    press the button, it changes to Twitter, either official app or a webview and
    afterwards it gets our app again and this method is called,*/
}
