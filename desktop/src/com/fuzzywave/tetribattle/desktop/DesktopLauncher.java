package com.fuzzywave.tetribattle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fuzzywave.tetribattle.TetriBattle;

public class DesktopLauncher {
    public static void main(String[] arg) {

        DesktopAnalytics desktopAnalytics = new DesktopAnalytics();
        DesktopLogger desktopLogger = new DesktopLogger("TetriBattleDesktop");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 800;
        new LwjglApplication(new TetriBattle(desktopLogger, desktopAnalytics), config);
    }
}
