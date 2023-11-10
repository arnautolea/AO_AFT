@API
Feature: Add User functionality, post/get contact list

  Background: reset the test context and login
    And User is logged in


  @PostAddUser
  Scenario: Check Add User flow with API POST

    Given the API endpoint is "/users"
    And add user details:
      | firstName | lastName | password   |
      | Tom       | Kenny    | Qwerty123! |
    When Post request is sent to the server
    Then The response status code is 201


  @PostContactDetails
  Scenario: Sending a POST request with JSON data

    Given the API endpoint is "/contacts"
    When I send a POST request with the following JSON data and Authorization header: "ContactDetails.json"
    Then the response status code should be 201

  @GetContactList
  Scenario:  Verify user able to list out contact based on id

    Given the API endpoint is "/contacts/652ea007fde4e500139b9c67"
    When Get Contact List details
    Then The response status code is 200