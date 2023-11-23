Feature: BNM practice

  @BNM @API
  Scenario: Calculating Total Paid Amount in lei with some EURO Salary

    Given CSV file containing dates "src/test/resources/BNMdates.csv"
    When Retrieve the XML response for each date
    Then Calculate the total paid amount for all dates with a salary of 2000.00 EURO

