Feature: User Login Flow

  @LoginNegative @UI @Smoke
  Scenario: Check that user is not able to login with not registered credentials
    Given User is on the Home page
    And User click on Sign In
    And Customer Login page is displayed
    And User fills email: some@gmail.com
    And User fills password: csbt11or8a7d!1Qw
    When User click on Sing In Button
    Then Error message that sign-in was incorrect is displayed
    And User is still on "Customer Login" page

  @Login @UI @Smoke
  Scenario: Validate login with registered user
    Given User is on the Home page
    And User click on Sign In
    And Customer Login page is displayed
    And User fills email: corey.toy@gmail.com
    And User fills password: csbt11or8a7d!1Qw
    When User click on Sing In Button
    And User click on dropdown
    And User click on My Account option
    Then User is logged in with Contact Information name and email
  """
  Matha Davis
  corey.toy@gmail.com
  """


