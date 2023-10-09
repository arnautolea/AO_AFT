package org.myatf.runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@IncludeTags({"Smoke"})
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "org.myatf.definitions")
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/Report1.json",
                "html:target/cucumber-reports/Report1.html",
        }
)
public class TestRunner {
}