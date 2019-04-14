DROP DATABASE IF EXISTS shipsandsails;
CREATE DATABASE shipsandsails;
USE shipsandsails;

/* The default CHARACTER SET should be utf8mb4 so it doesn't have to be specified for each table */

CREATE TABLE nationalities (
  name VARCHAR(60) PRIMARY KEY
);

CREATE TABLE directions (
  direction TINYINT PRIMARY KEY,
  CHECK (direction BETWEEN 0 AND 5) -- matching the enum numbers, which are zero indexed
);

CREATE TABLE scenarios (
  name VARCHAR(60) PRIMARY KEY,  -- Name of the scenario, those shown at startup, to choose from
  player1 VARCHAR(60) NOT NULL, -- The two nationalities facing off in this scenario
  player2 VARCHAR(60) NOT NULL,
  map_width INT NOT NULL,        -- The dimensions of the map (number of hexes)
  map_height INT NOT NULL

  FOREIGN KEY (player1) REFERENCES nationality (id),
  FOREIGN KEY (player2) REFERENCES nationality (id)
);

CREATE TABLE ship_types (
  name VARCHAR(60) PRIMARY KEY,
  sailors INT NOT NULL,
  rows_of_guns INT NOT NULL,
  hull_condition INT NOT NULL,
  max_speed INT NOT NULL
);

-- Describes the specific ships in a scenario
CREATE TABLE ships (
  id INT PRIMARY KEY,
  type INT NOT NULL,             -- the ship type
  scenario VARCHAR(60) NOT NULL, -- What scenario does this ship belong to
  nationality VARCHAR(60) NOT NULL,  -- Which player controls this ship
  --name VARCHAR(60) UNIQUE,       -- Basically a custom name for the ship. Should these even be in the database, or just user configurable in java?
  position_x INT NOT NULL,       -- Starting coordinates
  position_y INT NOT NULL,
--  sailors INT NOT NULL,        -- only relevant if one wants a ship to be able to start with a limited crew
  direction TINYINT NOT NULL,    -- The direction it's facing at the start
  sails INT NOT NULL,            -- Starts at 100 for any ship

  FOREIGN KEY (scenario) REFERENCES scenario (name),
  FOREIGN KEY (type) REFERENCES ship_types (name),
  FOREIGN KEY (nationality) REFERENCES nationality (id),
  FOREIGN KEY (direction) REFERENCES direction (direction)
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
