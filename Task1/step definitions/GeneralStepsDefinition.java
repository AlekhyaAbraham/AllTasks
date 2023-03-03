package org.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.utilities.TestDataBuild;
import org.utilities.Utils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GeneralStepsDefinition extends Utils {

    // Declare the Request specification variable for further utilize in this class.
    private RequestSpecification request_specification;

    // Declare object to Access the method who build the payload for test.
    TestDataBuild testDataBuild = new TestDataBuild();
    String log_file_name_for_log_test_case_logs = "BasicOperations.txt";

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer response_code) {
        // Assert the api response code.
        assertEquals((Object) response_code, response.getStatusCode());
    }


    @When("Call {string} with {string} method with parameters")
    public void callWithMethodWithParameters(String APIURI, String API_method, DataTable tableData) {
        // First convert data table to the String map.
        Map<String, String> map = tableData.asMap(String.class, String.class);

        // Variable that store the request body.
        String request_body = null;
        String resource_name = null;

        // Generate request body based on the APIURI.
        if (APIURI.equalsIgnoreCase("Login")) {
            request_body = TestDataBuild.payload_for_login(map.get("email"), map.get("password"));
            resource_name = "login";
        }


        // Make the Request specification from the built request specification.
        request_specification = given()

                // Bind the built configuration with request.
                .spec(generateAndGetRequestSpecificationForAstpp(log_file_name_for_log_test_case_logs))

                // Here pass the CreateCustomerRequestBody class object because we have prepared our request parameters using the pojo class.
                .body(request_body);

        // Calling the request and store the response for further verification.
        response = hit_https_request_and_return_response(resource_name, API_method, request_specification);


    }

    @When("Call {string} with {string} method.")
    public void callWithMethod(String APIURI, String API_method) {
        // Variable that store the request body.
        String request_body = null;
        String resource_name = null;




        // Calling the request and store the response for further verification.
        response = hit_https_request_and_return_response(resource_name, API_method, request_specification);

    }


}
