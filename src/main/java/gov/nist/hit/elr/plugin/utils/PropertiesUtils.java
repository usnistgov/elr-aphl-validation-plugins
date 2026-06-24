package gov.nist.hit.elr.plugin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for loading and managing application properties. Uses the
 * singleton pattern to ensure only one instance exists. Maintains backward
 * compatibility with existing code.
 */
public class PropertiesUtils {

	private static final Logger logger = LogManager.getLogger(PropertiesUtils.class);

	private Properties properties;
	private static final String propFileName = "elr-plugins-config.properties";
	// Legacy name retained for backward compatibility — "plugings" was a typo in the original filename.
	// Callers still packaging the old name will still be found via the fallback in propertiesUtilsLegacy().
	private static final String propFileNameLegacy = "elr-plugings-config.properties";

	private static volatile PropertiesUtils single_instance = null;

	private PropertiesUtils() throws IOException {

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
			properties = new Properties();

			if (inputStream == null) {
				logger.warn("WARN: Property file '" + propFileName
						+ "' not found in classpath. Try to load legacy properties file.");
				propertiesUtilsLegacy();
			} else {
				properties.load(inputStream);
				logger.info("INFO: Loaded properties from " + propFileName);
			}
		} catch (IOException e) {
			logger.error("ERROR: Failed to load properties file '" + propFileName + "': " + e.getMessage());
			// Initialize with empty properties to avoid NullPointerException
			properties = new Properties();
		}
	}

	private void propertiesUtilsLegacy() {

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileNameLegacy)) {
			properties = new Properties();

			if (inputStream == null) {
				logger.warn("WARN: Property file '" + propFileNameLegacy
						+ "' not found in classpath. Using empty properties.");
			} else {
				properties.load(inputStream);
				logger.info("INFO: Loaded properties from " + propFileNameLegacy);
			}
		} catch (IOException e) {
			logger.error(
					"ERROR: Failed to load legacy properties file '" + propFileNameLegacy + "': " + e.getMessage());
			// Initialize with empty properties to avoid NullPointerException
			properties = new Properties();
		}
	}

	/**
	 * Gets the singleton instance of PropertiesUtils. Thread-safe implementation
	 * using double-checked locking.
	 *
	 * @return the PropertiesUtils instance
	 */
	public static PropertiesUtils getInstance() {
		if (single_instance == null) {
			synchronized (PropertiesUtils.class) {
				if (single_instance == null) {
					try {
						single_instance = new PropertiesUtils();
					} catch (IOException e) {
						throw new ExceptionInInitializerError(e);
					}
				}
			}
		}
		return single_instance;
	}

	/**
	 * Gets the loaded properties object. Maintained for backward compatibility with
	 * existing code.
	 *
	 * @return the properties object
	 * @throws IOException if there was an error loading the properties
	 */
	public static Properties getPropertiesStatic() throws IOException {
		return getInstance().getProperties();
	}

	/**
	 * Gets an unmodifiable view of the loaded properties.
	 *
	 * @return unmodifiable properties object
	 */
	public Properties getProperties() {
		return (Properties) Collections.unmodifiableMap(properties);
	}

	/**
	 * Gets a property value by key.
	 *
	 * @param key the property key
	 * @return the property value, or null if not found
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * Gets a property value by key with a default value.
	 *
	 * @param key          the property key
	 * @param defaultValue the default value to return if key is not found
	 * @return the property value, or the default value if not found
	 */
	public String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
}