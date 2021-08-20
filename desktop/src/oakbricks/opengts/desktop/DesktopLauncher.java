package oakbricks.opengts.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.gson.Gson;
import oakbricks.opengts.Main;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static oakbricks.opengts.Main.version;
import static oakbricks.opengts.desktop.Consts.userDirString;

public class DesktopLauncher {

	static class JsonPrimitives {
		private int screenH = 1;
		private int screenW = 2;
		private String username = "Player";
		JsonPrimitives() {
			// no-args constructor
		}
	}

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main (String[] arg) throws IOException {
		File configFile = new File(userDirString, "config.json");
		if (!configFile.exists() || configFile.isDirectory()) {
			//LOGGER.info("JSON config not loaded/does not exist! Creating a new one!");
			configFile.setWritable(true);
			configFile.setReadable(true);
			JsonPrimitives obj = new JsonPrimitives();
			Gson gson = new Gson();
			String json = gson.toJson(obj);

			System.out.println(json);

			FileUtils.writeStringToFile(configFile, gson.toJson(obj), StandardCharsets.UTF_8);

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
