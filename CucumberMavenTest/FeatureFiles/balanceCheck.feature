Feature: Different Balance verification 

  @BalanceCheck
  Scenario: I verify differect balances
    Given User navigate to balance test page
    When User verifies right coloumn values appears on the page
    And User verify all values greater than zero
    And User verify total balance is sum of all balance
    Then User verify balance is formatted in currency
    