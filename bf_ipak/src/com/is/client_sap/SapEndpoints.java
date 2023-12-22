package com.is.client_sap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.is.utils.CheckNull;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

import com.is.ISLogger;
import org.zkoss.zk.ui.WebApp;

public class SapEndpoints {
    private static Map<String, String> properties = Collections.synchronizedMap(new HashMap<String, String>());
    private static Map<String, Endpoint> endpoints = Collections.synchronizedMap(new HashMap<String, Endpoint>());

    static {
        init();
    }

    public synchronized static void init() {
        Properties props = new Properties();
        InputStream io = null;
        try {
            //String realPath = getRealPath("sap.properties");
            String realPath = System.getenv("CATALINA_HOME") + "\\webapps\\bf\\sap.properties";
            io = new FileInputStream(realPath);
            props.load(io);
            for (Entry<Object, Object> entry : props.entrySet()) {
                properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            ISLogger.getLogger().error(e.getStackTrace());
            e.printStackTrace();
        } finally {
            if (io != null) {
                try {
                    io.close();
                } catch (IOException e) {
                    ISLogger.getLogger().error(e.getStackTrace());
                    e.printStackTrace();
                }
            }
        }

        File file = new File(SapUtil.SAP_LOG_FOLDER);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private static String getRealPath(String s) {
        Execution execution = Executions.getCurrent();
        Desktop desktop = execution.getDesktop();
        WebApp webApp = desktop.getWebApp();
        return webApp.getRealPath(s);
    }

    public synchronized static Endpoint getEndpoint(String endpoint) {
        if (properties.size() == 0)
            init();
        return new Endpoint(
                  properties.get(endpoint)
                , properties.get(SapEnum.UN_PROP.getSapValue())
                , properties.get(SapEnum.PW_PROP.getSapValue()));
    }

    public synchronized static Map<String, String> getProperties() {
        if (properties.size() == 0)
            init();

        return properties;
    }

    public static void main(String[] args) {
        for (SapEnum entry : SapEnum.values()) {
            System.out.printf("%s - %s \n", entry.getSapValue(), endpoints.get(entry.getSapValue()));
        }
    }
}
