@API
Feature: Register and sign in functionality

  Background: Get on main page
    Given Get main URL

  @RegistrationAPI
  Scenario Outline: Check Registration flow with API POST get Status Code 302

    When Get endpoint and create payload to "create" user with firstName: "<firstName>" lastName: "<lastName>" password: "<password>"
    And POST payload
    Then The response status code is 302

    Examples:
      | firstName   | lastName    | password   |
      | NoName      | Neuvillette | Qwerty123! |
      | Wriothesley | Lord        | 123!!QWE   |


  @LoginAPI
  Scenario: Check login flow with API POST get Status Code 302

    When Get endpoint and create payload to "signIn" user with password: "csbt11or8a7d!1Qw" email: "corey.toy@gmail.com"
    And POST payload
    Then The response status code is 302
