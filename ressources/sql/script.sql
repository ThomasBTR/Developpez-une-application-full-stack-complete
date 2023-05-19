# CREATE DATABASE mdd;

USE mdd;

CREATE TABLE `themes` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255),
  `description` VARCHAR(255)
);

CREATE TABLE `users` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(255),
  `email` VARCHAR(255),
  `password` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


INSERT INTO users (username, email, password, created_at, updated_at)
VALUES ('Admin', 'yoga@studio.com', '$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq', null, null);
