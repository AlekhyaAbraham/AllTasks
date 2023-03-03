Feature: Verify the basic Operations.

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
