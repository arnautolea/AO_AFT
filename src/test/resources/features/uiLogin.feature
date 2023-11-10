@UI
Feature: User Login Flow

  Background: Use get on login page
    Given User is on the Home page
    And User click on Sign In
    And Customer Login page is displayed
    And User fills password: csbt11or8a7d!1Qw

  @LoginNegative
  Scenario: Check that user is not able to login with not registered credentials

    And User fills email: some@gmail.com
    When User click on Sing In Button
    Then Error message that sign-in was incorrect is displayed
    And User is still on "Customer Login" page

  @Login
  Scenario: Validate login with registered user

    And User fills email: corey.toy@gmail.com
    When User click on Sing In Button
    And User click on dropdown
    And User click on My Account option
    Then User is logged in with Contact Information First Name: "Matha" Last Name: "Davis" Email: "corey.toy@gmail.com"




