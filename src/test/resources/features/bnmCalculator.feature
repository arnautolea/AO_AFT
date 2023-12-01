Feature: BNM practice

  @BNM @API
  Scenario: Calculating Total Paid Amount in lei based on some currency

    Given CSV file containing dates "src/test/resources/BNMdates.csv"
    When Retrieve the XML response for each date with currency name "Euro"
    Then Calculate the total paid amount for all dates with a salary of 1500.00

