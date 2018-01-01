DELETE FROM votes;
DELETE FROM menus;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;

-- starts from 1000
INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER_REGULAR', 1000),
  ('ROLE_USER_REGULAR', 1001),
  ('ROLE_ADMIN', 1001);

-- starts from 100
INSERT INTO restaurant (NAME) VALUES
  ('MacDonalds'),
  ('KFC'),
  ('BurgerKing');

INSERT INTO menus (RESTAURANT_ID, DATE_TIME) VALUES
  (100, '2017-12-03'),
  (101, '2017-12-03');

INSERT INTO dishes (MENU_ID, DISH_NAME, PRICE) VALUES
  (0, 'burger', 17900),
  (0, 'fri', 7500),
  (0, 'naggets', 10500),
  (1, 'burger', 24000),
  (1, 'milk', 7900);

INSERT INTO votes (USER_ID, RESTAURANT_ID, DATE_TIME) VALUES
  (1000, 100, '2017-12-03 10:00:00'),
  (1001, 101, '2017-12-03 10:30:00');