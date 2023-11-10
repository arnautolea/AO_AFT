Feature: BNM practice

  @BNM @API
  Scenario: Calculating Total Paid Amount in lei with some EURO Salary

    Given I have a CSV file containing dates "src/test/resources/BNMdates.csv"
    When I retrieve the XML response for each date
    Then I calculate the total paid amount for all dates with a salary of 2000.00 EURO

