package org.zhwj184.telnet.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.zhwj184.telnet.Constants;

public class ConfigurationManager {

	private static ConfigurationManager instance = new ConfigurationManager();

	private static Properties properties;
	private final Logger logger = Logger.getLogger(ConfigurationManager.class
			.getName());
	private static boolean FORCE_LOAD_PROPERTIES = Boolean.valueOf(
			System.getProperty(Constants.FORCE_LOAD)).booleanValue();
	

	public static ConfigurationManager getInstance() {
		return instance;
	}

	/**
	 * Maximum number of threads getter.
	 * 
	 * @return
	 */
	public int getMaxThreads() {
		if (FORCE_LOAD_PROPERTIES || properties == null) {
			loadProperties();
		}
		return Integer.valueOf(properties.getProperty(Constants.MAX_THREADS,
				Constants.DEFAULT_MAX_THREADS));

	}

	/**
	 * Port number getter.
	 * 
	 * @return
	 */
	public int getPort() {
		if (FORCE_LOAD_PROPERTIES || properties == null) {
			loadProperties();
		}
		return Integer.valueOf(properties.getProperty(Constants.PORT_NUMBER,
				Constants.DEFAULT_PORT_NUM));
	}

	/**
	 * Load properties only once
	 */
	private void loadProperties() {

		properties = new Properties();
		final String configPath = System
				.getProperty(Constants.CONFIG_FILE_ENV_PROPERTY);
		if (configPath != null) {
			try {
				logger.info("Loading property file from external: "
						+ configPath);
				properties.load(new FileInputStream(configPath));
			} catch (IOException e) {
				logger.warning("Failed to load property file from: "
						+ configPath + ". Looking  in classpath.");
				loadFromClassPath();

			}
		} else {
			loadFromClassPath();
		}
	}

	/**
	 * Loads property from class path.
	 */
	private void loadFromClassPath() {
		try {
			InputStream in = ClassLoader
					.getSystemResourceAsStream("server.properties");
			if (in != null) {
				logger.info("Loading property file from class path");
				properties.load(in);
			}

		} catch (IOException e) {
			logger.warning("Failed to load property file from the class path. Default configurations will be used");
		}
	}

}
