package org.myatf.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        //path of step definition file
        glue = "org.myatf.definitions",
        plugin = {"pretty",
                "json:target/cucumber-reports/report.json",
                "html:target/cucumber-reports/report.html",
        },
        tags = ("@Smoke")
)
public class TestRunner {
}