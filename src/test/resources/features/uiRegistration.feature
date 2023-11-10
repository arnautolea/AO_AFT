Feature: User Registration Flow

  @Registration @UI
  Scenario: Registration of a new User

    Given User is on the Home page
    And User click on Create An Account link
    And user fill the registration form with valid data
    When User clicks on Create an Account Button
    Then User redirected on Account Page, 'My Account' inscription is displayed on the screen
    And User is logged in with Contact Information
