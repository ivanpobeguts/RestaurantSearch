DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM vote_history;
DELETE FROM menu;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants (name) VALUES
  ('Restaurant1'),
  ('Restaurant2'),
  ('Restaurant3'),
  ('Restaurant4');

INSERT INTO users (name, email, password, rest_id) VALUES
  ('User1', 'user1@yandex.ru', '{noop}password1', 100000),
  ('User2', 'user2@yandex.ru', '{noop}password2', 100001),
  ('Admin', 'admin@gmail.com', '{noop}admin', null);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100004),
  ('ROLE_USER', 100005),
  ('ROLE_ADMIN', 100006);

INSERT INTO menu (rest_id, menu) VALUES
  (100000, '{"name":"chicken wings","value":25},{"name":"fries","value":15},{"name":"apple juice", "value":10}'),
  (100001, '{"name":"pork","value":30},{"name":"fries","value":15},{"name":"orange juice", "value":15}'),
  (100002, '{"name":"pizza carbonara","value":30},{"name":"black tea", "value":7}'),
  (100003, '{"name":"salmon","value":25},{"name":"rice","value":15},{"name":"white wine", "value":14}'),
  (100001, '{"name":"scramble","value":15},{"name":"pancake","value":10},{"name":"cappuccino", "value":10}');

INSERT INTO vote_history (user_id, rest_id) VALUES
  (100004, 100000),
  (100005, 100001);

