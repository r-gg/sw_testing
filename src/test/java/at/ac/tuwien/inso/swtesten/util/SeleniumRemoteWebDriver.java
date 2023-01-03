package at.ac.tuwien.inso.swtesten.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

public class SeleniumRemoteWebDriver {
	private static ThreadLocal<RemoteWebDriver> webdriver = new ThreadLocal<>();
	private static ConcurrentHashMap< String,WebDriver> drivers = new ConcurrentHashMap<String,WebDriver>();

	public static RemoteWebDriver getRemoteDriver() throws MalformedURLException {
		System.out.println("My thread id: "+ Thread.currentThread().getId());
		if (webdriver.get() == null) {

			String hubUrl = "http://localhost:4444/wd/hub";
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(Browser.CHROME.browserName());
			webdriver.set(new RemoteWebDriver(new URL(hubUrl), capabilities));
		}
		webdriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		webdriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		webdriver.get().manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
		System.out.println("get Remote driver with sessionid:" + webdriver.get().getSessionId().toString());
		return webdriver.get();
	}


	public static void closeDriver(WebDriver driver) {
		if(webdriver.get() == null) {
			return;
		}

		try {
			webdriver.get().quit();
			webdriver.set(null);
		} catch (UnreachableBrowserException ignored) {
		}
	}

}
