Feature: Register and login functionality

  Background: reset the test context
    Given test context is reset

  @RegistrationAPI @API @Smoke
  Scenario Outline: Check Registration flow with API POST

    Given Valid endpoint with payload to create user <firstName> <lastName> <password>
    When Request is sent to the server
    Then The response status code should be 302

    Examples:
      | firstName | lastName | password   |
      | Vasea     | Pupkin   | Qwerty123! |


  @LoginAPI @API @Smoke
  Scenario Outline: Check login flow with API POST

    Given Valid endpoint with payload to log in user <email> <password>
    When Request is sent to the server
    Then The response status code should be 302

    Examples:
      | email               | password         |
      | corey.toy@gmail.com | csbt11or8a7d!1Qw |