DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM vote_history;
DELETE FROM menu;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants (name) VALUES
  ('Ресторан1'),
  ('Ресторан2'),
  ('Ресторан3'),
  ('Ресторан4');

INSERT INTO users (name, email, password, rest_id) VALUES
  ('User1', 'user1@yandex.ru', '{noop}password1', 100000),
  ('User2', 'user2@yandex.ru', '{noop}password2', 100001),
  ('Admin', 'admin@gmail.com', '{noop}admin', null);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100004),
  ('ROLE_USER', 100005),
  ('ROLE_ADMIN', 100006);

INSERT INTO menu (rest_id, menu) VALUES
  (100000, '{"name":"свинная отбивная","value":25},{"name":"картошка фри","value":15},{"name":"яблочный сок", "value":10}'),
  (100001, 'Меню2'),
  (100002, 'Меню3'),
  (100003, 'Меню4'),
  (100001, 'Меню5');

INSERT INTO vote_history (user_id, rest_id) VALUES
  (100004, 100000),
  (100005, 100001);

