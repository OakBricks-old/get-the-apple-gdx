package oakbricks.opengts.desktop;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import blue.endless.jankson.api.SyntaxError;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import oakbricks.opengts.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.FileSystems;

import static oakbricks.opengts.Main.version;
import static oakbricks.opengts.desktop.Consts.userDirString;

public class DesktopLauncher {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main (String[] arg) {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(getAssets().open("filename.txt")));

			// do reading, usually loop until end of file reading
			String mLine;
			while ((mLine = reader.readLine()) != null) {
				//process line
			}
		} catch (IOException e) {
			//log the exception
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					//log the exception
				}
			}
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Get The Apple! " + version + " - OpenSource";
		config.addIcon("iconWindow.png", Files.FileType.Internal);
		config.pauseWhenBackground = true;
		config.pauseWhenMinimized = true;
		config.resizable = false;
		new LwjglApplication(new Main(), config);
		LOGGER.info("nice");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		LOGGER.warn(userDirString);
		File f = new File(userDirString, "config.json5");
		if (!f.exists() || f.isDirectory()) {

			File configFile = new File(Consts.userDirString + FileSystems.getDefault().getSeparator() + "test.json");
			Jankson jankson = Jankson.builder().build();
			String result = jankson
					.toJson("new ConfigObject()") //The first call makes a JsonObject
					.toJson(true, true, 0);     //The second turns the JsonObject into a String -
			//in this case, preserving comments and pretty-printing with newlines
			try {
				if(!configFile.exists()) configFile.createNewFile();
				FileOutputStream out = new FileOutputStream(configFile, false);

				out.write(result.getBytes());
				out.flush();
				out.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			LOGGER.info("JSON File Loaded!");
			try {
				JsonObject configObject = Jankson
						.builder()
						.build()
						.load(new File("config.json5"));

				//This will strip comments and regularize the file, but emit newlines and indents for readability
				String processed = configObject.toJson(false, true);

				//This will inject a default setting after the last listed key in the object, if it doesn't already exist.
				//Otherwise it does nothing to the comment, ordering, or value.
				configObject.putDefault("test1", new JsonPrimitive(Boolean.TRUE), "test for booleans");

			} catch (IOException ex) {
				LOGGER.error("Couldn't read the config file", ex);
				return; //or System.exit(-1) or rethrow an exception
			} catch (SyntaxError error) {
				LOGGER.error(error.getMessage());
				LOGGER.error(error.getLineMessage());
				return; //or System.exit(-1) or rethrow an exception
			}
		}
		LOGGER.warn("test");
	}

	public void saveDefaultConfig() {
	}

}
