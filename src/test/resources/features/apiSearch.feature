Feature: Search functionality

  Background: reset the test context
    Given test context is reset

  @Search @API @Smoke
  Scenario: Check get valid search endpoint result

    Given Get main URL
    When GET request to "/catalogsearch/result/?q=Tops"
    Then The response status code is 200

  @SearchNegative @API @Smoke
  Scenario: Check get invalid search endpoint result

    Given Get main URL
    When GET request to "/catalogsearch/result123/?q=Tops"
    Then The response status code is 404
