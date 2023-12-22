package com.is.customer_;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Dev1 on 20.11.2018.
 */
public class ApplicationPropertiesConfigurationManager {
    private Map<String, String> properties;

    private static Logger logger = Logger.getLogger(ApplicationPropertiesConfigurationManager.class);

    public ApplicationPropertiesConfigurationManager() {
        init();
    }

    private void init() {
        properties = new HashMap<String, String>();

        String path = System.getenv("CATALINA_HOME") + "\\webapps\\bf\\application.properties";
        //String path = "D:\\workspace\\bf_ipak\\WebContent\\application.properties";
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            properties.load(inputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public String get(String key){
        return this.properties.get(key);
    }
}
