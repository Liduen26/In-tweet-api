INSERT INTO intweet.users (id, username, password, created_at, admin) VALUES (1, 'Liduen', '$2y$10$.hboccbR4A8LnbhzSpPNfeQRjajM/ypzmxnCzd74KAkq95Ky8npJ6', '2024-12-06 09:43:41', 0);
INSERT INTO intweet.users (id, username, password, created_at, admin) VALUES (2, 'UserTest', '$2a$10$Kxe.q/Zt862hvGxwn1IfWuxyqgGxF0cJftH8mo87XEqzoKgWy8zyq', '2024-12-07 21:43:41', 1);

INSERT INTO intweet.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (1, 'Test1', 'Lorem ipsum el dolot ...', 1, 'test', '2024-12-06 09:43:07', null, null);
INSERT INTO intweet.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (2, 'Test2', 'Vous connaissez Star Citizen ?', 2, 'test', '2024-12-07 21:43:07', null, null);

INSERT INTO intweet.likes (id, post_id, user_id) VALUES (1, 1, 1);
INSERT INTO intweet.likes (id, post_id, user_id) VALUES (2, 1, 2);