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

	public static void main (String[] arg) throws IOException {
		File configFile = new File(userDirString, "config.json");
		String minWidth = null;
		String maxWidth = null;
		if (!configFile.exists() || configFile.isDirectory()) {
			//empty
		} else {
			LOGGER.info("JSON Config file loaded!");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Get The Apple! - " + version;
		config.addIcon("iconWindow.png", Files.FileType.Internal);
		config.pauseWhenBackground = true;
		config.pauseWhenMinimized = true;
		config.resizable = false;
		new LwjglApplication(new Main(), config);
		//LOGGER.debug("Working Directory: " + System.getProperty("user.dir"));
		//LOGGER.warn(userDirString);
		System.out.println("Current locale is: " + Locale.getDefault().toString().toLowerCase());

	}

}
