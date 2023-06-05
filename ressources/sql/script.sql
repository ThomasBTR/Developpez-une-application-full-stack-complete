# CREATE DATABASE mdd;

USE mdd;

CREATE TABLE `themes`
(
    `id`    INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(255),
    `description` VARCHAR(255),
    `user_id` INT
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

ALTER TABLE `themes` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);


INSERT INTO users (username, email, password, created_at, updated_at)
#  password: test!1234
VALUES ('Admin', 'admin@mdd.com', '$2a$10$OrLIi.J1M2Y19h.QfIp3.e3BW7LxRuunsNHFQm.gnp60hwo.5fCci', null, null);


INSERT INTO themes (title, user_id, description)
VALUES ('Java', 1, 'This is the first theme'),
       ('Angular', 1, 'This is the second theme'),
       ('Javascript', 1, 'This is the third theme'),
       ('C#', 1, 'This is the fourth theme'),
       ('Python', 1, 'This is the fifth theme');