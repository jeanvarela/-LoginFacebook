package br.com.loginfacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton fbLoginButton;

    private ProfilePictureView profilePictureView;
    private TextView userNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();

        fbLoginButton = (LoginButton) findViewById(R.id._fb_login);
        fbLoginButton.setReadPermissions("public_profile");

        profilePictureView = (ProfilePictureView) findViewById(R.id.user_pic);
        profilePictureView.setCropped(true);
        userNameView = (TextView) findViewById(R.id.user_name);


        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
                Toast.makeText(
                        MainActivity.this,
                        "Sucesso",
                        Toast.LENGTH_LONG).show();

                updateUI();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(
                        MainActivity.this,
                        "Cancel",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(final FacebookException exception) {
                // App code
                Toast.makeText(
                        MainActivity.this,
                        "Erro",
                        Toast.LENGTH_LONG).show();
            }
        });

        new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    final Profile oldProfile,
                    final Profile currentProfile) {
                updateUI();
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI() {
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {

            profilePictureView.setProfileId(profile.getId());
            userNameView.setText(String.format("%s %s",profile.getFirstName(), profile.getLastName()));
        } else {
            profilePictureView.setProfileId(null);
            userNameView.setText(getString(R.string.welcome));
        }
    }
}
