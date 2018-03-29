DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM users_restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@yandex.ru', 'password'),
  ('User2', 'user2@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name, registered, menu) VALUES
  ('Ресторан1', '2015-05-30 10:00:00', 'Меню1'),
  ('Ресторан2', '2015-05-30 11:00:00', 'Меню2'),
  ('Ресторан3', '2015-05-30 12:00:00', 'Меню3'),
  ('Ресторан4', '2015-05-30 15:00:00', 'Меню4');

INSERT INTO users_restaurants (user_id, rest_id) VALUES
  (100000, 100003),
  (100000, 100004),
  (100001, 100005),
  (100001, 100006),
  (100001, 100004);
