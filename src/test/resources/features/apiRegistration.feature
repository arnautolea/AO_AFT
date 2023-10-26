Feature: Register and login functionality

  Background: reset the test context
    Given test context is reset

  @RegistrationAPI @API @Smoke
  Scenario Outline: Check Registration flow with API POST get Status Code 302

    Given Valid endpoint with payload to create user <firstName> <lastName> <password>
    When Request is sent to the server
    Then The response status code should be 302

    Examples:
      | firstName   | lastName    | password   |
      | Unknown     | Neuvillette | Qwerty123! |
      | Wriothesley | Lord        | 123!!QWE   |


  @LoginAPI @API @Smoke
  Scenario: Check login flow with API POST get Status Code 302

    Given Valid endpoint with payload to log in user corey.toy@gmail.com csbt11or8a7d!1Qw
    When Request is sent to the server
    Then The response status code should be 302
