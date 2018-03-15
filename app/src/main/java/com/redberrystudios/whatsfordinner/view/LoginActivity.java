package com.redberrystudios.whatsfordinner.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.redberrystudios.whatsfordinner.R;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String EMAIL = "email";

    public static final String PROVIDER_KEY = "PROVIDER_KEY";
    public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";

    private CallbackManager mCallbackManager;

    private GoogleSignInClient mGoogleSignInClient;

    @BindView(R.id.fb_login_btn)
    LoginButton facebookLoginButton;

    @BindView(R.id.login_google_btn)
    SignInButton googleLoginButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "Result code: " + resultCode);
        if (resultCode == RESULT_OK) {
            Bundle token = checkForLogin();
            navigateToMainScreen(token);
        } else {
            Toast.makeText(this, "Login problem!", Toast.LENGTH_SHORT).show();
        }

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mCallbackManager = CallbackManager.Factory.create();

        // Set the initial permissions to request from the user while logging in
        facebookLoginButton.setReadPermissions(Collections.singletonList(EMAIL));

        // Register a callback to respond to the user
        facebookLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, loginResult.getAccessToken().getToken());

                setResult(RESULT_OK);
            }

            @Override
            public void onCancel() {
                setResult(RESULT_CANCELED);
            }

            @Override
            public void onError(FacebookException e) {
                // Handle exception
            }
        });

        googleLoginButton.setSize(SignInButton.SIZE_WIDE);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @OnClick(R.id.login_google_btn)
    void onClickGoogleLogin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RESULT_OK);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle loginBundle = checkForLogin();
        if (loginBundle != null) {
            navigateToMainScreen(loginBundle);
        }
    }

    private void navigateToMainScreen(Bundle tokenBundle) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtras(tokenBundle);
        startActivity(mainIntent);
        finish();
    }

    private Bundle checkForLogin() {
        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(this);
        AccessToken fbAccessToken = AccessToken.getCurrentAccessToken();

        String tokenString = "";
        String providerString = "";

        if (googleAccount != null) {
            tokenString = googleAccount.getIdToken();
            providerString = "google";
        } else if (fbAccessToken != null) {
            tokenString = fbAccessToken.getToken();
            providerString = "facebook";
        }

        Bundle tokenBundle = new Bundle();
        tokenBundle.putString(PROVIDER_KEY, providerString);
        tokenBundle.putString(ACCESS_TOKEN_KEY, tokenString);

        if (tokenString != null && !tokenString.isEmpty()) {
            return tokenBundle;
        } else {
            return null;
        }
    }

}