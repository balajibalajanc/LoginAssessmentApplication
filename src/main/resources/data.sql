--
--CREATE TABLE IF NOT EXISTS role (
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    role VARCHAR(255)
--);
--
--CREATE TABLE  IF NOT EXISTS manager (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(255),
--    teams VARCHAR(255)
--);
INSERT IGNORE into manager (id, name, teams)
VALUES
(1, 'Pallavi', 'Team A'),
(2, 'Sudheer', 'Team B'),
(3, 'Ishtiaq', 'Team C'),
(4, 'Hareni', 'Team A'),
(5, 'Leo', 'Team B'),
(6, 'Das', 'Team C'),
(7, 'Murugan', 'Team A'),
(8, 'abishek', 'Team B'),
(9, 'Harold', 'Team C'),
(10, 'Anthony', 'Team A');



INSERT IGNORE INTO role (id, role) VALUES
(0,'Admin'),
(1, 'USER'),
(2, 'Manager'),
(3, 'Developer'),
(4, 'Designer'),
(5, 'Salesperson'),
(6, 'Analyst'),
(7, 'Supervisor'),
(8, 'Support Specialist'),
(9, 'Quality Assurance'),
(10, 'Marketing Specialist');