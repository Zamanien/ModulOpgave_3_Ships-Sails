DROP DATABASE IF EXISTS modulopgave3;
CREATE DATABASE modulopgave3;
USE modulopgave3;

/* The default CHARACTER SET should be utf8mb4 so it doesn't have to be specified for each table */

CREATE TABLE scenario (
  nationality INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE ship_types (
  id INT PRIMARY KEY,
  number_of_sailors INT NOT NULL UNIQUE,
  number_of_rows_of_guns INT NOT NULL UNIQUE,
  hull_quality INT NOT NULL UNIQUE,
  sail_quality INT NOT NULL UNIQUE,
  sailors_start INT NOT NULL,
  speed INT NOT NULL UNIQUE
);

-- Describes the concrete ships in a scenario
CREATE TABLE ships (
  name VARCHAR(60) PRIMARY KEY AUTO_INCREMENT,
  type INT PRIMARY KEY AUTO_INCREMENT,
  position_x INT NOT NULL UNIQUE,
  position_y INT NOT NULL UNIQUE,
  sailors INT NOT NULL UNIQUE,
  direction CHAR(2) NOT NULL UNIQUE,

  FOREIGN KEY (direction) REFERENCES direction(direction)
);

/* CREATE TABLE order ( */
/*   ship_id INT PRIMARY KEY, */
/*   turn_number INT NOT NULL,     -- which turn does the order belong to */
/*   turn INT,           -- sharp left, left, straight, right, sharp right         - Enum? */
/*   sail_usage INT,      -- sail percentage 0 25 50 75 100 (depending on MAX_SPEED */
/*   fire BOOLEAN,       -- shoot? only possible if not loading */
/*   load BOOLEAN,       -- order load */
/*   ammunition_type INT, -- 0=canonball, 1=chainball, 2=grapeshot */
/*   target INT          -- not possible to shoot straight forward or backward     - Enum? */
/* ); */

/* CREATE TABLE weather ( */
/*   int wind_direction, -- ENUM N, NE, SE, S, SW, NW */
/*   windSpeed INT           -- 0 - 5 */

/*   FOREIGN KEY (wind_direction) REFERENCES kommune(kommune_id), */
/* ); */

CREATE TABLE direction (
  direction TINYINT PRIMARY KEY
);
