DROP DATABASE IF EXISTS modulopgave3;
CREATE DATABASE modulopgave3;
USE modulopgave3;

/* The default CHARACTER SET should be utf8mb4 so it doesn't have to be specified for each table */

CREATE TABLE ships (
    nationality INT PRIMARY KEY AUTO_INCREMENT,
    number INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60) PRIMARY KEY AUTO_INCREMENT,
    number_of_sailors INT NOT NULL UNIQUE
    number_of_rows_of_guns INT NOT NULL UNIQUE
    hull_quality INT NOT NULL UNIQUE
    sail_quality INT NOT NULL UNIQUE
    position_x INT NOT NULL UNIQUE
    position_y INT NOT NULL UNIQUE
    position_z INT NOT NULL UNIQUE
    direction CHAR(2) NOT NULL UNIQUE
    speed INT NOT NULL UNIQUE
);
