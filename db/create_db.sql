--DROP DATABASE IF EXISTS shipsandsails;
--CREATE DATABASE shipsandsails;
USE shipsandsails;

/* The default CHARACTER SET should be utf8mb4 so it doesn't have to be specified for each table */
DROP TABLE IF EXISTS ships;
DROP TABLE IF EXISTS ship_types;
DROP TABLE IF EXISTS scenarios;
DROP TABLE IF EXISTS nationalities;

CREATE TABLE nationalities (
  name VARCHAR(60) PRIMARY KEY
);

CREATE TABLE scenarios (
  name VARCHAR(60) PRIMARY KEY,  -- Name of the scenario, those shown at startup, to choose from
  player1 VARCHAR(60) NOT NULL, -- The two nationalities facing off in this scenario
  player2 VARCHAR(60) NOT NULL,
  map_width INT NOT NULL,        -- The dimensions of the map (number of hexes)
  map_height INT NOT NULL,

  FOREIGN KEY (player1) REFERENCES nationalities(name),
  FOREIGN KEY (player2) REFERENCES nationalities(name)
);

CREATE TABLE ship_types (
  name VARCHAR(60) PRIMARY KEY,
  sailors INT NOT NULL, -- number of sailors at the start of the game
  sailors_needed_on_guns INT NOT NULL,
  sailors_needed_on_sails INT NOT NULL,
  guns_per_side INT NOT NULL, -- (guns_per_row * rows_of_guns) / 2
  hull_condition INT NOT NULL, -- hull condition at the start of the game
  max_sails_up INT NOT NULL,
  sails_total INT NOT NULL, -- total number of sails at the start of the game
  max_speed INT NOT NULL,
  max_speed_change INT NOT NULL,
  max_rotate INT NOT NULL
);

-- Describes the specific ships in a scenario
CREATE TABLE ships (
  scenario VARCHAR(60) NOT NULL, -- What scenario does this ship belong to
  id INT PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(60) NOT NULL,             -- the ship type
  nationality VARCHAR(60) NOT NULL,  -- Which player controls this ship
  row INT NOT NULL,       -- Starting coordinates
  col INT NOT NULL,
  direction INT NOT NULL,    -- The direction it's facing at the start

  CHECK (direction BETWEEN 30 AND 330),
  FOREIGN KEY (scenario) REFERENCES scenarios(name),
  FOREIGN KEY (type) REFERENCES ship_types(name),
  FOREIGN KEY (nationality) REFERENCES nationalities(name)
  --  sailors INT NOT NULL,        -- only relevant if one wants a ship to be able to start with a limited crew
  -- name VARCHAR(60) UNIQUE,       -- Basically a custom name for the ship. Should these even be in the database, or just user configurable in java?
  -- sails INT NOT NULL,            -- Starts at 100 for any ship, or directly derived from ship type
);
