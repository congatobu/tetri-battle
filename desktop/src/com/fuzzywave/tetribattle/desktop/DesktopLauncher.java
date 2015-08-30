package com.fuzzywave.tetribattle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fuzzywave.tetribattle.TetriBattle;

public class DesktopLauncher {
    public static void main(String[] arg) {

        DesktopAnalytics desktopAnalytics = new DesktopAnalytics();
        DesktopLogger desktopLogger = new DesktopLogger("TetriBattleDesktop");

        boolean runTexturePacker = true;
        if (runTexturePacker) {

            /*
            DesktopUtils.packTexture("/home/default/workspace/tetri-battle/assets/game/ldpi/",
                                     "/home/default/workspace/tetri-battle/android/assets/game/240x320",
                                     "game");
            DesktopUtils.packTexture("/home/default/workspace/tetri-battle/assets/game/mdpi/",
                                     "/home/default/workspace/tetri-battle/android/assets/game/320x480",
                                     "game");
            DesktopUtils.packTexture("/home/default/workspace/tetri-battle/assets/game/hdpi/",
                                     "/home/default/workspace/tetri-battle/android/assets/game/480x800",
                                     "game");
            DesktopUtils.packTexture("/home/default/workspace/tetri-battle/assets/game/xhdpi/",
                                     "/home/default/workspace/tetri-battle/android/assets/game/720x1280",
                                     "game");
            DesktopUtils.packTexture("/home/default/workspace/tetri-battle/assets/game/xxhdpi/",
                                     "/home/default/workspace/tetri-battle/android/assets/game/1080x1920",
                                     "game");
            */

            DesktopUtils.packTexture("/home/yigiter/Workspace/tetri-battle/assets/game/",
                                     "/home/yigiter/Workspace/tetri-battle/android/assets/game",
                                     "game");
        }

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 800;
        new LwjglApplication(new TetriBattle(desktopLogger, desktopAnalytics), config);
    }
}
