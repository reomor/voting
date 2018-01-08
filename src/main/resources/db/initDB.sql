DROP TABLE votes IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE menus IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1000, INCREMENT BY 1) PRIMARY KEY,
  name             VARCHAR(255)                 NOT NULL,
  email            VARCHAR(255)                 NOT NULL,
  password         VARCHAR(255)                 NOT NULL,
  registered       TIMESTAMP DEFAULT now()      NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE         NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id           INTEGER                       NOT NULL,
  role              VARCHAR(255)                  NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
  id                INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  name              VARCHAR(255)                  NOT NULL
);

CREATE TABLE menus
(
  id                INTEGER                     IDENTITY PRIMARY KEY,
  restaurant_id     INTEGER                     NOT NULL,
  date_time         DATE                        NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id                INTEGER                     IDENTITY PRIMARY KEY,
  menu_id           INTEGER                     NOT NULL,
  dish_name         VARCHAR(255)                NOT NULL,
  price             INTEGER                     NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id               INTEGER                      IDENTITY PRIMARY KEY,
  user_id          INTEGER                      NOT NULL,
  restaurant_id    INTEGER                      NOT NULL,
  date_time        TIMESTAMP DEFAULT now()      NOT NULL,
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);