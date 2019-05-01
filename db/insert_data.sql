USE shipsandsails;

-- Disable FOREIGN KEY checks before emptying tables
SET FOREIGN_KEY_CHECKS=0;

-- Empty tables
TRUNCATE TABLE ships;
TRUNCATE TABLE ship_types;
TRUNCATE TABLE scenarios;
TRUNCATE TABLE nationalities;

SET FOREIGN_KEY_CHECKS=1;


INSERT INTO nationalities (name) VALUES ('Aztec');
INSERT INTO nationalities (name) VALUES ('Celts');



INSERT INTO scenarios (name, player1, player2, map_width, map_height) VALUES ('X Marks the Spot' , 'Aztec'   , 'Celts'   , 6         , 5);



INSERT INTO ship_types (name , sailors , sailors_needed_on_guns , sailors_needed_on_sails , guns_per_side , hull_condition , max_sails_up , sails_total , max_speed , max_speed_change , max_rotate)
VALUES ('Brig'               , 60      , 24                     , 24                      , 4             , 8              , 25           , 4           , 30        , 2                , 1);

INSERT INTO ship_types (name , sailors , sailors_needed_on_guns , sailors_needed_on_sails , guns_per_side , hull_condition , max_sails_up , sails_total , max_speed , max_speed_change , max_rotate)
VALUES ('Ship of the Line'   , 160     , 48                     , 60                      , 16            , 60             , 10           , 60          , 5         , 2                , 2);

INSERT INTO ship_types (name , sailors , sailors_needed_on_guns , sailors_needed_on_sails , guns_per_side , hull_condition , max_sails_up , sails_total , max_speed , max_speed_change , max_rotate)
VALUES ('Man at War'         , 340     , 84                     , 144                     , 42            , 140            , 24           , 80          , 4         , 1                , 1);


-- Directions: 30 to 330 degrees, 0 degrees being straight left which is an invalid direction. 30 is north west, 90 north and so forth.
INSERT INTO ships (scenario , type               , nationality , row , col , direction)
VALUES('X Marks the Spot'   , 'Ship of the Line' , 'Aztec'     , 3   , 4   , 30);

INSERT INTO ships (scenario , type               , nationality , row , col , direction)
VALUES('X Marks the Spot'   , 'Brig'             , 'Aztec'     , 3   , 5   , 150);

INSERT INTO ships (scenario , type               , nationality , row , col , direction)
VALUES('X Marks the Spot'   , 'Man at War'       , 'Aztec'     , 2   , 2   , 330);

INSERT INTO ships (scenario , type               , nationality , row , col , direction)
VALUES('X Marks the Spot'   , 'Man at War'       , 'Celts'     , 1   , 5   , 90);

INSERT INTO ships (scenario , type               , nationality , row , col , direction)
VALUES('X Marks the Spot'   , 'Ship of the Line' , 'Celts'     , 0   , 1   , 270);
