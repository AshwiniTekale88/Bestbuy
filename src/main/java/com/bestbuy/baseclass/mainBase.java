package com.bestbuy.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class mainBase {
	public static WebDriver driver;
	
	public static Properties prop;
	public static Logger log = Logger.getLogger(mainBase.class);
	
	
	public mainBase(){
		
	    
		prop=new Properties();
		
		String basepath = System.getProperty("user.dir");
		try {
			FileInputStream fis = new FileInputStream(basepath + "\\src\\main\\resources\\config.properties");
			prop.load(fis);
			log.info("Configuration file loaded");
		} catch (FileNotFoundException e) {
			System.out.println("object repository not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("file not found");
			e.printStackTrace();
		}

	}
	public static void initBrowser() {
		String browsername = prop.getProperty("browser");
		switch (browsername) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		case "HtmlUnit":
			driver = new HtmlUnitDriver();
			break;
		default:
			System.err.println("Invalid Browser");
			break;
		}
	
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

}
}



		
		
	


