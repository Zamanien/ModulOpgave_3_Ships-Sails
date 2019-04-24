package kea.shipsandsails.services;

import kea.shipsandsails.controllers.HomeController;
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
        int index = ship.getDirection() + rotateDirection;
        if (!(index >= 0 && index < 6)) {
            index = Math.abs(6 - Math.abs(index));
        }
        ship.setDirection(index);
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
    }

    // selected ship still on board
    private void onBoard(Ship ship) {
        if (!((ship.getX() >= 0 && ship.getX() < HomeController.scenario.getMapWidth()) &&
                (ship.getY() >= 0 && ship.getY() < HomeController.scenario.getMapHeight()))) {
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