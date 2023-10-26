Feature: BNM practice

  @BNM
  Scenario: Retrieve EURO value for specific dates from bnm.md

    Given I have a CSV file containing dates "src/test/resources/BNMdates.csv"
    When I retrieve the XML response for each date
    Then I calculate the total paid amount for all dates with a salary of 1.0 EURO

