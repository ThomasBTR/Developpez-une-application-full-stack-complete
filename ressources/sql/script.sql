# CREATE DATABASE mdd;

USE mdd;

CREATE TABLE `themes`
(
    `id`    INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(255),
    `description` VARCHAR(255)
);

CREATE TABLE `users`
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT,
    `username`   VARCHAR(255),
    `email`      VARCHAR(255),
    `password`   VARCHAR(255),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `users_themes`
(
    `user_id`  INT,
    `theme_id` INT,
    PRIMARY KEY (`user_id`, `theme_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`)
);

INSERT INTO users (username, email, password, created_at, updated_at)
#  password: test!1234
VALUES ('Admin', 'admin@mdd.com', '$2a$10$OrLIi.J1M2Y19h.QfIp3.e3BW7LxRuunsNHFQm.gnp60hwo.5fCci', null, null);


INSERT INTO themes (title, description)
VALUES ('Java', 'This is the first theme'),
       ('Angular', 'This is the second theme'),
       ('Javascript', 'This is the third theme'),
       ('C#', 'This is the fourth theme'),
       ('Python', 'This is the fifth theme');

INSERT INTO users_themes (user_id, theme_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5);