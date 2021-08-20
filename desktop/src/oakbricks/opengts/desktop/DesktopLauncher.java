package oakbricks.opengts.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import oakbricks.opengts.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Locale;

import static oakbricks.opengts.Main.version;
import static oakbricks.opengts.desktop.Consts.userDirString;

public class DesktopLauncher {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Get The Apple! - " + version;
		config.addIcon("iconWindow.png", Files.FileType.Internal);
		config.pauseWhenBackground = true;
		config.pauseWhenMinimized = true;
		config.resizable = false;
		new LwjglApplication(new Main(), config);
		LOGGER.info("nice");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		LOGGER.warn(userDirString);
		System.out.println(Locale.getDefault());
		File f = new File(userDirString, "config.json");
		if (!f.exists() || f.isDirectory()) {
			LOGGER.info("JSON config not loaded/does not exist! Creating a new one!");
		} else {
			LOGGER.info("JSON Condig file loaded!");
		}
		LOGGER.warn("test");
	}

	public void saveDefaultConfig() {
	}

}
