Feature: Search functionality

  Background: reset the test context
    Given test context is reset

  @RegistrationAPI @API @Smoke
  Scenario Outline: Check Registration flow with API POST

    Given Valid endpoint with payload to create user <firstName> <lastName> <password>
    And Request is sent to the server
    Then User get on My account page

    Examples:
      | firstName | lastName | password   |
      | Vasea     | Pupkin   | Qwerty123! |


  @LoginAPI @API @Smoke
  Scenario Outline: Check login flow with API POST

    Given Valid endpoint with payload to login user <email> <password>
    And Request is sent to the server
    Then User get on My account page

    Examples:
      | email               | password         |
      | corey.toy@gmail.com | csbt11or8a7d!1Qw |