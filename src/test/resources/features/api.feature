Feature: Search functionality
  Background: reset the test context
    Given test context is reset

  @Search @API @Smoke
  Scenario: Check search result

    Given Get main URL
    When Send a GET request to "/catalogsearch/result/?q=Tops"
    Then The response status code should be 200

  @SearchNegative @API @Smoke
  Scenario: Check search result with invalid get request

    Given Get main URL
    When Send a GET request to "/catalogsearch/result123/?q=Tops"
    Then The response status code should be 404
