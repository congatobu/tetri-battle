package com.fuzzywave.tetribattle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fuzzywave.tetribattle.TetriBattleGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

        DesktopAnalytics desktopAnalytics = new DesktopAnalytics();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new TetriBattleGame(desktopAnalytics), config);
	}
}
