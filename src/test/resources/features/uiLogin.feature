Feature: User Login/Logout Flow

  @Login @UI @Smoke
  Scenario Outline: Login and logout with registered user
    Given User is on the Home page
    And User click on Sign In
    And Customer Login page is displayed
    And User fills email: <Email>
    And User fills password: <Password>
    When User click on Sing In Button
    Then User is logged in and redirected on main page
    When User click on dropdown
    And User click on Sign Out option
    Then 'You are signed out' message displayed

    Examples:
      | Email                         | Password            |
      | corey.toy@gmail.com           | csbt11or8a7d!1Qw    |
      | rochell.crooks@yahoo.com      | ygyg66o8rtz!1Qw     |
     # | joaquin.gutkowski@hotmail.com | wro3lh8vyi2lfs6!1Qw |


  @LoginNegative @UI @Smoke
  Scenario Outline: Login attempt with not registered user
    Given User is on the Home page
    And User click on Sign In
    And Customer Login page is displayed
    And User fills email: <Email>
    And User fills password: <Password>
    When User click on Sing In Button
    Then Error message that sign-in was incorrect is displayed
    And User is still on "Customer Login" page

    Examples:
      | Email          | Password         |
      | some@gmail.com | csbt11or8a7d!1Qw |