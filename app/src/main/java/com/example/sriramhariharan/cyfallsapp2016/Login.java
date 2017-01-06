package com.example.sriramhariharan.cyfallsapp2016;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        setTheme(android.R.style.Theme_Holo_Light_NoActionBar);

        mEmailView = (EditText) findViewById(R.id.email);

        SharedPreferences sharedPref = this.getSharedPreferences("Login", Context.MODE_PRIVATE);


        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        logo = (ImageView) findViewById(R.id.loginlogo);
        if (sharedPref.contains("Username") && sharedPref.contains("Password")) {
            String username = sharedPref.getString("Username", "");
            String password = sharedPref.getString("Password", "");
            showProgress(true);

            mAuthTask = new UserLoginTask(username, password);
            mAuthTask.execute((Void) null);

        }

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 0;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 0;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
    }


    public void logOut(View view) {
        SharedPreferences settings = Login.this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */



    public class UserLoginTask extends AsyncTask<Void, Void, String> {

        private final String muser;
        private final String mPassword;
        private String tname;

        UserLoginTask(String user, String password) {
            muser = user;
            mPassword = password;


        }




        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            SharedPreferences preferences = Login.this.getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            try {
                ClssPkg p = ClssPkg.getFromServer(muser,mPassword);
                Log.e("______THEERRROR",p.toString());
                if(!(p.toString().equals("Wrong login"))){
                    Values.PKG = p;
                    Values.courses = p.classes;
                    Values.PKGcopy = ClssPkg.parse(p.toString());

                    Log.e("HELLO",p.toString());

                    return "success";
                }
                else{
                    return "fail";
                }

            } catch (Exception e) {
                Log.e("THE ERROR",e.toString());
                return "Exception";
            }

        }


        @Override
        protected void onPostExecute(final String success) {
            showProgress(false);
            mAuthTask = null;

            if (success.equals("success")) {

                startService(new Intent(getApplicationContext(), BackgroundService.class));

                SharedPreferences sharedPref = Login.this.getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences preferences = Login.this.getSharedPreferences("Userinfo", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();


                ClssPkg p = Values.PKG;
                String stringedpkg = p.toString();
                editor.putString("Username", muser);
                editor.putString("Password", mPassword);
                editor.commit();

                SharedPreferences.Editor infoedit = preferences.edit();
                infoedit.putString("Name", tname);
                infoedit.putString("Courses",stringedpkg);
                infoedit.commit();

                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();


            } else if (success.equals("Exception")) {
                Log.e("login", "EXCEPTION"+muser+" "+mPassword);
                mPasswordView.setError("There was an error");
                mPasswordView.requestFocus();
            } else if (success.equals("fail")) {
                Log.e("login", "FAIL");
                mPasswordView.setError("Login Failed");
                mPasswordView.requestFocus();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }

    }


}

