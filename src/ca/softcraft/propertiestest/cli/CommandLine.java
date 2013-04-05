package ca.softcraft.propertiestest.cli;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

public class CommandLine {
	public static void main(String[] args) {
		System.out.println("This is CommandLine:" + new Date());
		InputStream resourceAsStream = CommandLine.class.getResourceAsStream("/ca/softcraft/propertiestest/packaged.properties");
		Properties properties = new Properties();
		try {
			properties.load(resourceAsStream);
			System.out.println("The value of myproperty is: " + properties.getProperty("myproperty"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unused")
	private static void displayAnyResource(String[] args) {
		if (args.length > 0){
			System.out.println("Path: " + args[0]);
			URL url = CommandLine.class.getResource(args[0]);
			System.out.println("URL: " + url);
		}
	}
}
