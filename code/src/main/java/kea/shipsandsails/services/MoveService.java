package kea.shipsandsails.services;

import kea.shipsandsails.controllers.HomeController;
import kea.shipsandsails.models.Direction;
import kea.shipsandsails.models.Order;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.models.Weather;

import java.util.List;

public class MoveService implements IMove {

    // move ships and resolve collisions
    public void movement(List<Ship> ships, List<Order> orders, Weather weather) {
    }

    // move selected ship to new position and direction
    public Ship moveShip(Ship ship, int rotateDirection) {
        newRotation(ship, rotateDirection);
        newPosition(ship);
        onBoard(ship);
        return ship;
    }

    // rotate selected ship
    private void newRotation(Ship ship, int rotateDirection) {
        int index = ship.getDirection().ordinal() + rotateDirection;
        Direction[] directionArray = {Direction.N, Direction.NE, Direction.SE, Direction.S, Direction.SW, Direction.NW};
        if (!(index >= 0 && index < directionArray.length)) {
            index = Math.abs(directionArray.length - Math.abs(index));
        }
        ship.setDirection(directionArray[index]);
    }

    // move selected ship
    private void newPosition(Ship ship) {
        switch (ship.getDirection()) {
            case N:
                ship.getCoordinate().setX(ship.getCoordinate().getX() - 1);
                break;
            case NE:
                ship.getCoordinate().setX(ship.getCoordinate().getX() - 1);
                ship.getCoordinate().setY(ship.getCoordinate().getY() + 1);
                break;
            case SE:
                ship.getCoordinate().setY(ship.getCoordinate().getY() + 1);
                break;
            case S:
                ship.getCoordinate().setX(ship.getCoordinate().getX() + 1);
                break;
            case SW:
                ship.getCoordinate().setY(ship.getCoordinate().getY() - 1);
                break;
            case NW:
                ship.getCoordinate().setX(ship.getCoordinate().getX() - 1);
                ship.getCoordinate().setY(ship.getCoordinate().getY() - 1);
                break;
        }
    }

    // selected ship still on board
    private void onBoard(Ship ship) {
        if (!((ship.getCoordinate().getX() >= 0 && ship.getCoordinate().getX() < HomeController.scenario.getMapWidth()) &&
                (ship.getCoordinate().getY() >= 0 && ship.getCoordinate().getY() < HomeController.scenario.getMapHeight()))) {
            ship = null;
        }
    }
}



/*
kan der rykkes til en af tre hexes foran skibet?:
moveRemaining();
turningsRemaining();
collision i ønsket hex.

moveRemaining();
    tidligere move i samme tur.
    wind direction samt egen direction.
    sailQuality (aktive sails, som også afhænger af sailors)
    max speed (afhænger af skibstype)
speed remaining (tidligere move i samme tur)
number of turns pr. turn (afhænger af skibstype)
max speed change (afhænger af skibstype)
*/