package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SnakeGame;

public class DesktopLauncher {

	public static final int FPS = 15;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 800;
		config.foregroundFPS = FPS;
		new LwjglApplication(new SnakeGame(), config);
	}
}
