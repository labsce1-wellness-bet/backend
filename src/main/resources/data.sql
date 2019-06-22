INSERT INTO WELLNESS_GROUPS
  (GROUP_ID, ADMIN_ID, GROUP_NAME, SECRET_CODE)
VALUES
  (1, 'auth0|3018490385', 'a', 'aaaa'),
  (2, 'auth0|3018490386', 'b', 'bbbb'),
  (3, 'auth0|3018490387', 'c', 'cccc'),
  (4, 'auth0|3018490388', 'd', 'dddd'),
  (5, 'auth0|3018490389', 'e', 'eeee'),
  (6, 'auth0|3018490390', 'f', 'ffff');

alter sequence hibernate_sequence restart with 10;


INSERT INTO COMPETITIONS
  (COMP_ID, BET_AMOUNT, COMPETITION_TYPE, END_DATE, MESSAGE, START_DATE, GROUP_ID)
VALUES
  (1, 100, 'Sleep', CURRENT_DATE(), 'What is up?', CURRENT_DATE(), 1),
  (2, 30, 'Sleep', CURRENT_DATE(), 'This competition is very long...', CURRENT_DATE(), 1),
  (3, 5, 'Sleep', CURRENT_DATE(), 'Group 2 competition', CURRENT_DATE(), 2),
  (4, 1, 'Sleep', CURRENT_DATE(), 'Another Group 2 competition', CURRENT_DATE(), 2),
  (5, 1, 'Sleep', CURRENT_DATE(), 'We are on fire!', CURRENT_DATE(), 2);

