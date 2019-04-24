package kea.shipsandsails.services;

import kea.shipsandsails.models.*;

import java.util.List;

public class MoveService implements IMove {

    // move ships and resolve collisions
    public void movement(List<Ship> ships, List<Order> orders, Weather weather) {
    }

    // move selected ship to new position and direction
    public Ship moveShip(Ship ship, int rotateDirection, Scenario scenario) {
        newRotation(ship, rotateDirection);
        newPosition(ship);
        onBoard(ship, scenario);
        return ship;
    }

    // rotate selected ship
    private void newRotation(Ship ship, int rotateDirection) {
        int index = ship.getDirection() + rotateDirection;
        if (!(index >= 0 && index < 6)) {
            index = Math.abs(6 - Math.abs(index));
        }
        ship.setDirection(index);

        // decreasing rotations remaining by 1
        if (rotateDirection != 0) {
            ship.setRotationsRemaining(ship.getRotationsRemaining() - 1);
        }
    }

    // move selected ship
    private void newPosition(Ship ship) {
        switch (ship.getDirection()) {
            case 0:
                ship.setX(ship.getX() - 1);
                break;
            case 1:
                ship.setX(ship.getX() - 1);
                ship.setY(ship.getY() + 1);
                break;
            case 2:
                ship.setY(ship.getY() + 1);
                break;
            case 3:
                ship.setX(ship.getX() + 1);
                break;
            case 4:
                ship.setY(ship.getY() - 1);
                break;
            case 5:
                ship.setX(ship.getX() - 1);
                ship.setY(ship.getY() - 1);
                break;
        }

        // decreasing moves remaining by 1
        ship.setMovesRemaining(ship.getMovesRemaining() - 1);
    }

    // selected ship still on board
    private void onBoard(Ship ship, Scenario scenario) {
        if (!((ship.getX() >= 0 && ship.getX() < scenario.getMapWidth()) &&
                (ship.getY() >= 0 && ship.getY() < scenario.getMapHeight()))) {
            ship = null;
        }
    }

    // this method belongs with AttackService
    // calculates sail health
    public void newSailHealth(Ship ship, ShipType shipType) {
        double sailsUp = Math.min(ship.getSailors() % 6, ship.getCurrentSailsUp());
        ship.setSailHealth(sailsUp / shipType.getMaxSailsUp());
    }
}

/*
public boolean movePossible(Ship ship) {

    // hvor meget modstand giver vinden
    direction / wind;

    // kan der rykkes mere
    moveRemaining() - wind > 0;
    // hvis der drejes, tjek for antal rotations tilbage
    rotationsRemaining() > 0;

}



max move remaining:

    min af:
    max speed - sail
    previousMove + maxSpeedChange - sail

min move remaning:

    max af:
    0
    previousMove - maxSpeedChange - sail



rotationsRemaining samt movesRemaining reguleres/nulstilles af controlleren
collision tjekkes efter alle har rykkket
*/