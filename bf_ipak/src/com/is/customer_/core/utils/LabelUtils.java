package com.is.customer_.core.utils;

import com.is.utils.CheckNull;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class LabelUtils {
	private static Properties properties = null;

	public static Properties getProperties(String path) {
		if (properties == null) {
			properties = new Properties();
			InputStream fileInputStream = null;
			Reader reader = null;
			try {
				fileInputStream = new FileInputStream(path);
				reader = new InputStreamReader(fileInputStream,"UTF-8");
				properties.load(reader);
				reader.close();
			} catch (Exception e) {
				CheckNull.getPstr(e);
			}
			finally {
				IOUtils.closeQuietly(fileInputStream);
				IOUtils.closeQuietly(reader);
			}
		}
		return properties;
	}
}
