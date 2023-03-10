
# Task2

2.Automation Testing of web API.
Use below mock api’s link from google and automate for all the CRUD operations as well as some negative scenarios.
Url: https://reqres.in/  


## Tech Stack

IDE- IntelliJ IDEA
Maven - build automation tool 
maven - 3.9.0

Programming Language - Java
Java version: 19.0.2

Framework and its versions
Rest Assured  - 5.0.1
Cucumber  - 7.3.4
Juint     - 4.13.2



## API Reference

baseURL=https://reqres.in/

   createUser("/api/users/"),
    updateUser("/api/users/2"),
    deleteUser("/api/users/2"),
    login("/api/login"),
    listUser("/api/users?page=2");
#### Get all items

Feature: Verify the basic CRUD Operations.

  Scenario: Verify Login api with valid credentials
    When Call "Login" with "POST" method with parameters
      | email    | eve.holt@reqres.in |
      | password | cityslicka         |
    Then the response status code should be 200

  Scenario: Verify Login api with Invalid credentials
    When Call "Login" with "POST" method with parameters
      | email    | asdfsadf@reqres.in |
      | password | cityslicka         |
    Then the response status code should be 400

  Scenario: Verify Create user api
    When Call "CreateUser" with "POST" method with parameters
      | name | Alekhya |
      | job  | Associate Engineer 2 |
    Then the response status code should be 201
    And "name" in the response body should be "Alekhya".
    And "job" in the response body should be "Associate Engineer 2".

  Scenario: Verify List user api
    When Call "ListUser" with "POST" method.
    Then the response status code should be 201

  Scenario: Verify Update user api
    When Call "CreateUser" with "POST" method with parameters
      | name | Alekhya Abraham |
      | job  | Associate Engineer ii   |
    Then the response status code should be 201
    And "name" in the response body should be "Alekhya Abraham".
    And "job" in the response body should be "Associate Engineer ii".

  Scenario: Verify Delete user api
    When Call "DeleteUser" with "POST" method.
    Then the response status code should be 201

To publish the cucumber report  https://reports.cucumber.io