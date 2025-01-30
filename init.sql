create table users
(
    admin      bit          not null,
    id         int auto_increment
        primary key,
    created_at datetime(6)  null,
    password   varchar(255) not null,
    username   varchar(255) not null,
    constraint UKr43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table bans
(
    id      int auto_increment
        primary key,
    user_id int not null,
    constraint FKdshp5tj95xpg7ikybc4teb68q
        foreign key (user_id) references users (id)
);

create table posts
(
    id         int auto_increment
        primary key,
    parent     int          null,
    user_id    int          not null,
    created_at datetime(6)  null,
    body       text         null,
    status     varchar(255) not null,
    title      varchar(255) not null,
    image      longblob     null,
    constraint FK2d2stn8v8uofd22ph8tgms8xe
        foreign key (parent) references posts (id),
    constraint FK5lidm6cqbc7u4xhqpxm898qme
        foreign key (user_id) references users (id)
);

create table likes
(
    id      int auto_increment
        primary key,
    post_id int not null,
    user_id int not null,
    constraint FKnvx9seeqqyy71bij291pwiwrg
        foreign key (user_id) references users (id),
    constraint FKry8tnr4x2vwemv2bb0h5hyl0x
        foreign key (post_id) references posts (id)
);

create definer = user@`%` trigger before_insert_posts
    before insert
    on posts
    for each row
BEGIN
    IF CHAR_LENGTH(NEW.body) > 100 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'DB Trigger : Le nombre de charactères du post ne doit pas dépasser 100 caractères';
    END IF;
END;

create definer = user@`%` trigger before_update_posts
    before update
    on posts
    for each row
BEGIN
    IF CHAR_LENGTH(NEW.body) > 100 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'DB Trigger : Le nombre de charactères du post ne doit pas dépasser 100 caractères';
    END IF;
END;


