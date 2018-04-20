DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM users_restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@yandex.ru', 'password1'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100002);

INSERT INTO restaurants (name, menu) VALUES
  ('Ресторан1', 'Меню1'),
  ('Ресторан2', 'Меню2'),
  ('Ресторан3', 'Меню3'),
  ('Ресторан4', 'Меню4');

INSERT INTO users_restaurants (user_id, rest_id) VALUES
  (100000, 100003),
  (100000, 100004),
  (100001, 100005),
  (100001, 100006),
  (100001, 100004);
