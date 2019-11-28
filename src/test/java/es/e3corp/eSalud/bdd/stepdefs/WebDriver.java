package es.e3corp.eSalud.bdd.stepdefs;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriver {
	
	public static ChromeDriver webDriver = webDriver();

	public static ChromeDriver webDriver() {
		System.setProperty("webdriver.chrome.driver", "Recursos//chromedriver.exe");
		final ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		return new ChromeDriver();
	}

}
