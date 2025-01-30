INSERT INTO intweet.users (id, username, password, created_at, admin) VALUES (1, 'test', '$2y$10$NMQyUj0ZC5/y.hFSE/HlMuoq2oe9opnnRARTOEOzrfmnbGtj3dJ8m', '2024-12-06 09:43:41', 1);
INSERT INTO intweet.users (id, username, password, created_at, admin) VALUES (2, 'jb', '$2y$10$NMQyUj0ZC5/y.hFSE/HlMuoq2oe9opnnRARTOEOzrfmnbGtj3dJ8m', '2024-12-06 09:43:41', 0);

INSERT INTO intweet.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (1, 'Test1', 'Lorem ipsum el dolot ...', 1, 'test', '2024-12-06 09:43:07', null, null);
INSERT INTO intweet.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (2, 'Test1', 'Lorem ipsum el dolot ...', 2, 'test', '2024-12-06 09:43:07', null, null);
INSERT INTO intweet.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (3, 'Test1', 'Lorem ipsum el dolot ...', 2, 'test', '2024-12-06 09:43:07', null, null);
INSERT INTO intweet.posts (id, title, body, user_id, status, created_at, image, parent) VALUES (4, 'Test1', 'Lorem ipsum el dolot ...', 2, 'test', '2024-12-06 09:43:07', null, null);

INSERT INTO intweet.likes (id, post_id, user_id) VALUES (1, 1, 1);

DELIMITER //

CREATE TRIGGER before_insert_posts
    BEFORE INSERT ON posts
    FOR EACH ROW
BEGIN
    IF CHAR_LENGTH(NEW.body) > 100 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'DB Trigger : Le nombre de charactères du post ne doit pas dépasser 100 caractères';
    END IF;
END;
//

DELIMITER ;
DELIMITER //

CREATE TRIGGER before_update_posts
    BEFORE UPDATE ON posts
    FOR EACH ROW
BEGIN
    IF CHAR_LENGTH(NEW.body) > 100 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'DB Trigger : Le nombre de charactères du post ne doit pas dépasser 100 caractères';
    END IF;
END;
//

DELIMITER ;
