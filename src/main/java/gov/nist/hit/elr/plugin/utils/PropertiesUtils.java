package gov.nist.hit.elr.plugin.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtils {

  private InputStream inputStream;
  private Properties properties;

  private static PropertiesUtils single_instance = null; 

  private PropertiesUtils() throws IOException {

    try {
      properties = new Properties();
      String propFileName = "config.properties";

      inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

      if (inputStream != null) {
        properties.load(inputStream);
      } else {
        throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
      }

    } catch (Exception e) {
      System.out.println("Exception: " + e);
    } finally {
      inputStream.close();
    }  

  }

  public static Properties getInstance() throws IOException 
  { 
    if (single_instance == null) 
      single_instance = new PropertiesUtils(); 

    return single_instance.getProperties(); 
  } 

  private Properties getProperties() {
    return properties;
  }



}
