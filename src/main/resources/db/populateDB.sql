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
  ('ROLE_USER', 1000),
  ('ROLE_ADMIN', 1001);
-- starts from 100
INSERT INTO restaurant (NAME) VALUES
  ('MacDonalds'),
  ('KFC'),
  ('BurgerKing');

INSERT INTO menus (RESTAURANT_ID, DISH_NAME, PRICE, DATE_TIME) VALUES
  (100, 'burger', 17900, '2017-12-03'),
  (100, 'fri', 7500, '2017-12-03'),
  (100, 'naggets', 10500, '2017-12-03'),
  (101, 'burger', 24000, '2017-12-03'),
  (101, 'milk', 7900, '2017-12-03');

INSERT INTO votes (USER_ID, RESTAURANT_ID, DATE_TIME) VALUES
  (1000, 100, '2017-12-03 10:00:00'),
  (1001, 101, '2017-12-03 10:00:00');