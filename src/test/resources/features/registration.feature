Feature: User Registration Positive Flow

  @Login @Smoke
  Scenario Outline: Login and logout with registered user
    Given User is on the Home page
    And User click on Sign In
    And Customer Login page is displayed
    And User fills email: <Email> of registered user
    And User fills password: <Password> of registered user
    When User click on Sing In Button
    Then User is logged in and redirected on main page
    When User click on dropdown
    And User click on Sign Out option
    Then 'You are signed out' message displayed

    Examples:
      | Email | Password |
      |corey.toy@gmail.com|csbt11or8a7d!1Qw|
    #  |rochell.crooks@yahoo.com|ygyg66o8rtz!1Qw|
    #  |joaquin.gutkowski@hotmail.com|wro3lh8vyi2lfs6!1Qw|

  @Registration @Smoke
  Scenario: Registration of a new User

    Given User is on the Home page
    When User click on Create An Account link
    And User fills firstName
    And User fills lastName
    And User fills email
    And User fills password and confirmation password
    And User clicks on Create an Account Button
    Then User redirected on Account Page, 'My Account' inscription is displayed on the screen