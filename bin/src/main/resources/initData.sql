INSERT INTO testdb.users (id, username, created_at, admin) VALUES (1, 'Liduen', '2024-12-06 09:43:41', 1);
INSERT INTO testdb.users (id, username, created_at, admin) VALUES (2, 'Okami', '2024-12-07 21:43:41', 0);

INSERT INTO testdb.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (1, 'Test1', 'Lorem ipsum el dolot ...', 1, 'test', '2024-12-06 09:43:07', null, null);
INSERT INTO testdb.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (2, 'Test2', 'Vous connaissez Star Citizen ?', 2, 'test', '2024-12-07 21:43:07', null, null);

INSERT INTO testdb.likes (id, post_id, user_id) VALUES (1, 1, 1);
INSERT INTO testdb.likes (id, post_id, user_id) VALUES (2, 1, 2);