@API
Feature: Register and sign in functionality

  Background: Get on main page
    Given Get main URL

  @RegistrationAPI
  Scenario Outline: Check Registration flow with API POST get Status Code 302

    When "create" user with firstName: "<firstName>" lastName: "<lastName>" password: "<password>"
    And Send a POST request
    Then The response status code is 302

    Examples:
      | firstName   | lastName    | password   |
      | NoName      | Neuvillette | Qwerty123! |
      | Wriothesley | Lord        | 123!!QWE   |


  @LoginAPI
  Scenario: Check login flow with API POST get Status Code 302

    When "signIn" user with password: "csbt11or8a7d!1Qw" email: "corey.toy@gmail.com"
    And Send a POST request
    Then The response status code is 302
