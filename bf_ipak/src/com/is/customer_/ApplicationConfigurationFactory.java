package com.is.customer_;

/**
 * Created by Dev1 on 20.11.2018.
 */
public class ApplicationConfigurationFactory {
    private static ApplicationPropertiesConfigurationManager applicationPropertiesConfigurationManager;

    public static ApplicationPropertiesConfigurationManager getConfigurationManager(){
        if (applicationPropertiesConfigurationManager == null)
            applicationPropertiesConfigurationManager = new ApplicationPropertiesConfigurationManager();
        return applicationPropertiesConfigurationManager;
    }
}
