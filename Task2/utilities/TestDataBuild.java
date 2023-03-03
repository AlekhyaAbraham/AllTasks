package org.utilities;

import java.util.Objects;

public class TestDataBuild {

    /*
     * In this class we are building the request body with format.
     * 1) If the body has the small amount of data then we are not going to create the whole pojo class to set the data.
     * 2) If the body has more amount of data then we are going to use pojo classes to set the request parameters.
     * */

    public static String payload_for_create_user(String name, String job) {
        return "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}";
    }

    public static String payload_for_update_user(String name, String job) {
        return "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}";
    }

    public static String payload_for_login(String email, String password) {
        return "{\n" +
                "    \"email\": \""+email+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}";
    }
}
