package at.ac.tuwien.inso.swtesten.util;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectDirectories;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SelectDirectories("src/test/resources/sample")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "at.ac.tuwien.inso.swtesten.sample1")
public @interface Sample1 {
}
