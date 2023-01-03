package at.ac.tuwien.inso.swtesten.util;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class SeleniumRemoteWebDriver {
	private static ThreadLocal<RemoteWebDriver> webdriver = new ThreadLocal<>();
	private static String defaultLocale = "en"; //use english as default

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

	private static RemoteWebDriver createChromeWebDriver() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		if (StringUtils.isNoneBlank(defaultLocale)) {
			options.setExperimentalOption("prefs", ImmutableMap.builder().put("intl.accept_languages", defaultLocale).build());
		}
		return new ChromeDriver(options);
	}

	private static RemoteWebDriver createFireFoxWebDriver() {
		WebDriverManager.firefoxdriver().setup();

		FirefoxOptions options = new FirefoxOptions();
		if (StringUtils.isNoneBlank(defaultLocale)) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("intl.accept_languages", defaultLocale);
			options.setProfile(profile);
		}

		return new FirefoxDriver(options);
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

	public static void setDefaultLocale(String defaultLocale) {
		SeleniumRemoteWebDriver.defaultLocale = defaultLocale;
	}

}
