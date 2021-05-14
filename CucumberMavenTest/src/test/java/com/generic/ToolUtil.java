/**
 * 
 */
package com.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


public class ToolUtil {

	public static Properties openPropertyFile(File file) {
		Properties propertyFile = new Properties();
		try {
			FileInputStream fis = new FileInputStream(file);
			propertyFile.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			propertyFile = null;
		}
		return propertyFile;
	}

	public static String getTimeSecondsStamp() {
		String stamp = new SimpleDateFormat("yyMMddkkmmss").format(Calendar.getInstance().getTime());
		return stamp;
	}

}
