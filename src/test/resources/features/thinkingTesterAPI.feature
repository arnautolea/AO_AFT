Feature: Add User functionality

  Background: reset the test context
    Given test context is reset

  @PostAddUser @API @Smoke
  Scenario Outline: Check Add User flow with API POST

    Given Valid endpoint with payload to add user <firstName> <lastName> <password>
    When Post request is sent to the server
    Then The response status code should be 201

    Examples:
      | firstName | lastName | password   |
      | Vasea     | Pupkin   | Qwerty123! |


  @PostContactDetails @API @Smoke
  Scenario: Sending a POST request with JSON data

    Given the API endpoint is "/contacts"
    When I send a POST request with the following JSON data and Authorization header
    Then the response status code should be 201

  @GetContactList @API @Smoke
  Scenario:  Verify user able to list out contact based on id

    Given the API endpoint is "/contacts/652ea007fde4e500139b9c67"
    When Get Contact List details
    Then The response status code should be 200