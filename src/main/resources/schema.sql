CREATE TABLE Client
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    age      VARCHAR(50) NOT NULL,
    group_id VARCHAR(50) NOT NULL,
    phone    VARCHAR(50),
    date     DATE
);