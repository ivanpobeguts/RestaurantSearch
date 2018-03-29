DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users_restaurants;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role)
);

CREATE TABLE restaurants (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR,
  registered   TIMESTAMP NOT NULL,
  menu TEXT      NOT NULL
--   voices_count    INT       NOT NULL
);

CREATE TABLE users_restaurants
(
  user_id INTEGER NOT NULL,
  rest_id    INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
