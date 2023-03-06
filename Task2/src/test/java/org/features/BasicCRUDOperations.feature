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