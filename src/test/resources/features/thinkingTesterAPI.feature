Feature: Add User functionality, post/get contact list

  Background: reset the test context and login
    Given test context is reset
    And User is logged in


  @PostAddUser @API @Smoke
  Scenario: Check Add User flow with API POST

    Given Valid endpoint with payload to add user Some Name Qwerty123!
    When Post request is sent to the server
    Then The response status code is 201


  @PostContactDetails @API @Smoke
  Scenario: Sending a POST request with JSON data

    Given the API endpoint is "/contacts"
    When I send a POST request with the following JSON data and Authorization header: "ContactDetails.json"
    Then the response status code should be 201

  @GetContactList @API @Smoke
  Scenario:  Verify user able to list out contact based on id

    Given the API endpoint is "/contacts/652ea007fde4e500139b9c67"
    When Get Contact List details
    Then The response status code is 200