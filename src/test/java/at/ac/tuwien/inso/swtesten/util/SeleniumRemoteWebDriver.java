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

public class SeleniumRemoteWebDriver {
	private static RemoteWebDriver webdriver = null;
	private static String defaultLocale = "en"; //use english as default

	public static RemoteWebDriver getRemoteDriver() throws MalformedURLException {
		if (webdriver == null) {

			String hubUrl = "http://localhost:4444/wd/hub";
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(Browser.CHROME.browserName());
			webdriver = new RemoteWebDriver(new URL(hubUrl), capabilities);

			// Be aware that we recommend to use Chrome.
//			webdriver = createChromeWebDriver();
//			webdriver = createFireFoxWebDriver();
		}

		webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		webdriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		webdriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(50));
		return webdriver;
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

	public static void closeDriver() {
		if(webdriver == null) {
			return;
		}

		try {
			webdriver.quit();
			webdriver = null;
		} catch (UnreachableBrowserException ignored) {
		}
	}

	public static void setDefaultLocale(String defaultLocale) {
		SeleniumRemoteWebDriver.defaultLocale = defaultLocale;
	}

	public static void waitUntil(ExpectedCondition<?> condition) {
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(15), Duration.ofMillis(500));
		wait.until(condition);
	}
}
