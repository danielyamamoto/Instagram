package com.example.instagram;

import android.app.Application;

import com.example.instagram.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    public static final String REST_CONSUMER_KEY = BuildConfig.CONSUMER_KEY;       // API KEY
    public static final String REST_CONSUMER_SECRET = BuildConfig.CONSUMER_SECRET; // SECRET KEY
    public static final String URL_DATABASE = BuildConfig.URL_DATABASE;            // URL DATABASE

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(REST_CONSUMER_KEY)
                .clientKey(REST_CONSUMER_SECRET)
                .server(URL_DATABASE)
                .build());
    }
}
