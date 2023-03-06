package org.cucumber.Options;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                /* Here we have to give the features file path.
                If we have to run the single or specific feature file then we can give the name of feature file.
                Or if we want to run the entire feature file package then we have to give the path until the package
                level*/
                features = {"src/test/java/org/features/BasicCRUDOperations.feature"},

                /* Then we have to give the Package name in which the steps definitions are available for the feature
                file. And both TestRunner class and Steps definition should have exact in same package.*/
                glue = "org/step_definitions"

                /*
                * Using this plugin we are generating the results of the test in the json file format to the given path.
                * This file will become input for the reporting plugin in pom.xml file.
                * */
                ,plugin = "json:target/jsonReports/cucumber-report.json",

                /*
                * To publish the cucumber report at  https://reports.cucumber.io we need this parameter value as true
                * */
                publish = true

                /* Tags will run only those test cases from the feature files which has tag matched with defined tag here.*/
//                ,tags = "@AddPlace or @DeletePlace"
        )
public class TestRunner {


}
