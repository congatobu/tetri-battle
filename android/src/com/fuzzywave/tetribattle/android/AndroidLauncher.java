package com.fuzzywave.tetribattle.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.fuzzywave.tetribattle.TetriBattle;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidAnalytics androidAnalytics = new AndroidAnalytics(this);
        AndroidLogger androidLogger = new AndroidLogger("TetriBattleAndroid");

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        initialize(new TetriBattle(androidLogger, androidAnalytics), config);
    }
}
