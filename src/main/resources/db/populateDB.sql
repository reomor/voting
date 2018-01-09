-- https://stackoverflow.com/questions/4990410/how-can-i-wipe-data-from-my-hsqldb-after-every-test
TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK;

DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM MENUS;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;

-- starts from 1000
INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (user_id, role) VALUES
  (1000, 'ROLE_USER'),
  (1001, 'ROLE_USER'),
  (1001, 'ROLE_ADMIN');

-- starts from 100
INSERT INTO restaurant (NAME) VALUES
  ('MacDonalds'),
  ('KFC'),
  ('BurgerKing');

INSERT INTO menus (RESTAURANT_ID, DATE_TIME) VALUES
  (100, '2017-12-03'),
  (101, '2017-12-03'),
  (100, '2017-12-04'),
  (101, '2017-12-15');

INSERT INTO dishes (MENU_ID, DISH_NAME, PRICE) VALUES
  (0, 'burger', 17900),
  (0, 'fri', 7500),
  (0, 'naggets', 10500),
  (1, 'burger', 24000),
  (1, 'milk', 7900),
  (2, 'coffee', 9000),
  (3, 'steak', 25000);

INSERT INTO votes (USER_ID, RESTAURANT_ID, DATE_TIME) VALUES
  (1000, 100, '2017-12-03 10:00:00'),
  (1001, 101, '2017-12-03 10:30:00');