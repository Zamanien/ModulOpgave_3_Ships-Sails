-- If it doesn't work:
-- Try LOAD DATA LOCAL instead.  You may need to run mysql with --local-infile=1 as well, both server and client side.
-- If run WITHOUT LOCAL the path should be on the server, while WITH LOCAL it should be on the client.
-- Alternately set my.ini's secure_file_priv=""

USE modulopgave3;

-- Disable FOREIGN KEY checks before emptying tables
SET FOREIGN_KEY_CHECKS=0;

-- Empty tables
TRUNCATE TABLE ships;

SET FOREIGN_KEY_CHECKS=1;

-- The path for LOAD DATA should be for the server!
-- Your INFILE path may have to be updated!
LOAD DATA
  INFILE '/var/lib/mysql/ships.csv'
  INTO TABLE ships
  CHARACTER SET UTF8
  FIELDS TERMINATED BY ';' ENCLOSED BY '' ESCAPED BY '\\'
  IGNORE 1 LINES
  ;
