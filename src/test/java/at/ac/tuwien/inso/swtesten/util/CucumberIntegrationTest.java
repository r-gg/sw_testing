package at.ac.tuwien.inso.swtesten.util;


import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME;

import io.cucumber.junit.CucumberOptions;
import net.bytebuddy.asm.Advice;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;

/**
 * Executes Cucumber .feature files as JUnit test.
 * You can either run the sample projects (@Sample1, @Sample2) or your own scenarios defined during the lab (@Lab).
 * Do not forget to change activate the correct configuration.
 */

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "false")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:cucumberReport/cucumber-report.html, json:cucumberReport/cucumber.json")
@ConfigurationParameter(key = PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME, value = "true")
@Lab //change to @Sample2 or @Lab
public class CucumberIntegrationTest {
}
