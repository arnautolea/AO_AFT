Feature: Search functionality

  @Search @Smoke
  Scenario: Check search result

    Given Get main URL
    When Send a GET request to "/catalogsearch/result/?q=Tops"
    Then The response status code should be 200
    And The response body should contain "Tops"
