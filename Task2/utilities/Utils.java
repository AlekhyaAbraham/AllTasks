package org.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Properties;

public class Utils {

    /*
     * Commonly use utilities come inside this class.
     * We extend this class to the other class and use the resources.
     * */

    // Declare the variable for store the response inside it and utilize it further in this class for assertion.
    public static Response response;

    // Declare session filter variable for the store the login session for the other apis.
    public static SessionFilter sessionFilter;

    private static RequestSpecification request_specification_builder;

    /**
     * This method will generate the required request specification for the request.
     *
     * @param log_file_name_with_extension :  Need to pass the log file name in which we want log our request.
     * @return : Return specification in the form of the RequestSpecification object.
     */
    public static RequestSpecification generateAndGetRequestSpecificationForAstpp(String log_file_name_with_extension) {

        /*
         * Here we have check that request spec builder object is already initialized or not.
         * If it already initialized then do not initialized again instead of it just return it.
         * This mechanism become helpful when we run same scenario with different data set.
         * So everytime it not replace the logs to the logs file that we have created.
         * */
        if (request_specification_builder == null) {
            // Declare the variable.
            PrintStream log;

            // Make path until the log folder.
            String log_file_path =
                    System.getProperty("user.dir") + File.separator +
                            "src" + File.separator +
                            "test" + File.separator +
                            "java" + File.separator +
                            "org" + File.separator +
                            "testLogs" + File.separator +
                            log_file_name_with_extension;

            // Creating the log file.
            File f1 = new File(log_file_path);

            // We need headers params for this request. So first we are making headers params map for passing it to the request.
            HashMap<String, String> header_params = new HashMap<>();
            header_params.put("Content-Type", "application/json");

            try {

                /*
                 * By using this object we are specifying that where we want to log. Here we are making run time new file and
                 * log into it.
                 * */
                log = new PrintStream(f1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


            // Build the request specification.
            request_specification_builder = new RequestSpecBuilder()

                    // Set the Base Uri. Value of baseURL we are taking from the "config.properties" file.
                    .setBaseUri(getValueFromPropertyFile(PropertyFileKeys.BASE_URL))

                    // Set up the header params.
                    .addHeaders(header_params)

                    // Here we are giving the Filter to this request that responsible for logging of the request.
                    .addFilter(RequestLoggingFilter.logRequestTo(log))

                    // Here we are giving the Filter to this request that responsible for logging of the response.
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))

                    // Build entire request specification.
                    .build();

            // Return the built object with configuration.
            return request_specification_builder;
        }

        // Return the built object with configuration.
        return request_specification_builder;

    }

    /**
     * This method will read the properties file and give us value of the desire key.
     *
     * @param property_key : Value key that we want to get.
     * @return
     */

    // To scan any .properties file this class will help us.
    static Properties properties = new Properties();

    public static String getValueFromPropertyFile(String property_key){

        // Here we are loading the test_config.properties file to this class to access the properties declared in file.
        try {
            properties.load(Utils.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Now here we are reading the key value provided to this method.
        return properties.getProperty(property_key);
    }


    /**
     * Method will help us to get the value from the response for provided key and return it.
     *
     * @param response : Response object from which we need to extract the value.
     * @param key      : Which field value we required that key should be here.
     * @return : Will return the value of the key in String format.
     */
    public static String getValueFromResponse(Response response, String key) {

        // Lets, first convert response object to string.
        String response_as_string = response.asString();

        // Now pass the convert response object to JsonPath for extract the fields value from it.
        JsonPath response_in_json = new JsonPath(response_as_string);

        // Now get the value from the response for provided key and return it.
        return response_in_json.get(key).toString().trim();

    }

    /**
     * Method will hit the given https request with the define resource and return the response that will come
     * from the request.
     *
     * @param resource_name : API URI Resource name
     * @param request_type : Request type weather it is post,get,delete
     * @param request_specification : Request specification that is needed to place the request.
     * @return : Will return the response that will come from the request.
     */
    public static Response hit_https_request_and_return_response(String resource_name, String request_type, RequestSpecification request_specification) {

        // By using enum class variable we dynamically get the resource end point.
        APIResources end_point = APIResources.valueOf(resource_name);
        print("Resource name : " + resource_name);
        print("End point : " + end_point.getResource());

        /*
         * Here now based on the http_method value we are going to hit the request with configuration.
         * And store the response to the response object to do the further operation.
         * */

        if (request_type.equalsIgnoreCase("post")) {

            if (sessionFilter != null)
            {
                // Return the response.
                return request_specification

                        .given()

                        // Add the current session.
                        .filter(sessionFilter)

                        .when()

                        // Log the request.
                        .log().all()

                        /*
                         * Here we use the enum class to get the resource details.
                         * Making post request.
                         * */
                        .post(end_point.getResource()).then().log().all().extract().response();
            } else {
                // Return the response.
                return request_specification

                        .when()

                        // Log the request.
                        .log().all()

                        /*
                         * Here we use the enum class to get the resource details.
                         * Making post request.
                         * */
                        .post(end_point.getResource()).then().log().all().extract().response();
            }
        }
        else if (request_type.equalsIgnoreCase("get")) {

            if (sessionFilter != null)
            {
                // Return the response.
                return
                        request_specification

                                .given()

                                // Add the current session.
                                .filter(sessionFilter)

                                .when()

                                // Log the request.
                                .log().all()

                                /*
                                 * Here we use the enum class to get the resource details.
                                 * Making post request.
                                 * */
                                .get(end_point.getResource()).then().log().all().extract().response();

            } else {
                // Return the response.
                return
                        request_specification

                                .when()

                                // Log the request.
                                .log().all()

                                /*
                                 * Here we use the enum class to get the resource details.
                                 * Making post request.
                                 * */
                                .get(end_point.getResource()).then().log().all().extract().response();
            }
        }
        else if (request_type.equalsIgnoreCase("delete")) {

            if (sessionFilter!= null)
            {
                // Return the response.
                return
                        request_specification

                                .given()

                                // Add the current session.
                                .filter(sessionFilter)

                                .when()

                                // Log the request.
                                .log().all()

                                /*
                                 * Here we use the enum class to get the resource details.
                                 * Making post request.
                                 * */
                                .delete(end_point.getResource()).then().log().all().extract().response();
            } else {
                // Return the response.
                return
                        request_specification

                                .when()

                                // Log the request.
                                .log().all()

                                /*
                                 * Here we use the enum class to get the resource details.
                                 * Making post request.
                                 * */
                                .delete(end_point.getResource()).then().log().all().extract().response();
            }
        }

        return null;
    }

    /**
     * Method will print the content to the console. Reason to make this method is we have to not type entire System.ou....
     * for printing content to the console.
     * @param content : Content that we want to print.
     */
    public static void print(String content)
    {
        System.out.println(content);
    }

}
