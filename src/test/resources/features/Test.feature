Feature: Testing the login scenario against 5 login credentials

  Scenario Outline:
  Given user is able to launch URL
  When user enters "<username>" and "<Password>"
  And user clicks on login button
  Then user is able to see dashboard page
  Examples:
    |username|password|
    |abc1    |xyz1    |
    |abc2`   |xyz1    |
    |abc3    |xyz1    |
    |abc4    |xyz1    |
    |abc5    |xyz1    |


