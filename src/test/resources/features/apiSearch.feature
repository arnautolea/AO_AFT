@API
Feature: Search functionality

  Background: Get on main page
    Given Get main URL

  @Search
  Scenario: Get valid search endpoint status code

    When GET request to "/catalogsearch/result/?q=Tops"
    Then The response status code is 200

  @SearchNegative
  Scenario: Get invalid search endpoint status code

    When GET request to "/catalogsearch/result123/?q=Tops"
    Then The response status code is 404
