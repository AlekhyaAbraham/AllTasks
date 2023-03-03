
Basic Automation Framework using Rest Assured




## API Reference


  Request method:	POST

  Request URI:	https://reqres.in/api/login

  BaseURL=https://reqres.in/

  resource: /api/login
```

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


To publish the cucumber report  https://reports.cucumber.io

https://reports.cucumber.io/reports/7256f24e-6978-4c54-aca7-41bc476b6a1f