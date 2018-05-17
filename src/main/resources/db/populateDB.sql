DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM users_restaurants;
DELETE FROM menu;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@yandex.ru', '{noop}password1'),
  ('User2', 'user2@yandex.ru', '{noop}password2'),
  ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100002);

INSERT INTO restaurants (name) VALUES
  ('Ресторан1'),
  ('Ресторан2'),
  ('Ресторан3'),
  ('Ресторан4');

INSERT INTO menu (rest_id, menu) VALUES
  (100003, '{"name":"свинная отбивная","value":25},{"name":"картошка фри","value":15},{"name":"яблочный сок", "value":10}'),
  (100004, 'Меню2'),
  (100005, 'Меню3'),
  (100006, 'Меню4'),
  (100004, 'Меню5');

INSERT INTO users_restaurants (user_id, rest_id) VALUES
  (100000, 100003),
  (100000, 100004),
  (100001, 100005),
  (100001, 100006),
  (100001, 100004);
