Feature: User Registration Flow

  @Registration @UI @Smoke
  Scenario: Registration of a new User

    Given User is on the Home page
    And User click on Create An Account link
    And User fills First Name
    And User fills Last Name
    And User fills Email
    And User fills password and confirmation password
    When User clicks on Create an Account Button
    Then User redirected on Account Page, 'My Account' inscription is displayed on the screen
