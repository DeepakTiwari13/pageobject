package com.generic.project.util;

import java.io.InputStream;
import java.util.Map;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class Configuration {

	private static Logger logger = Logger.getLogger(Configuration.class);

	private static Map<String, String> props = null;

	private static final transient String ROOT_ENV_KEY = (System.getProperty("test.environment")) == null
			|| (System.getProperty("test.environment").isEmpty()) ? "test" : (System.getProperty("test.environment"));

	private static Configuration instance = null;

	private Configuration() {
		synchronized (Configuration.class) {
			logger.info("Loading environment "+ROOT_ENV_KEY);
			props = loadConfig().get(ROOT_ENV_KEY);
		}
	}

	public static Configuration getInstance() {
		if (instance == null) {
			try {
				instance = new Configuration();
			} catch (Exception e) {
				logger.info(e.fillInStackTrace());
				e.fillInStackTrace();
			}

		}
		return instance;
	}

	public String getProperty(String prop) {
		return props.get(prop);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> loadConfig() {
		InputStream is = Configuration.class.getClassLoader().getResourceAsStream("config.yaml");
		return (Map<String, Map<String, String>>) new Yaml().load(is);

	}
}
