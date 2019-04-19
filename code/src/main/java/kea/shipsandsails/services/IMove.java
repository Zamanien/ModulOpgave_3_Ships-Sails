package kea.shipsandsails.services;

import kea.shipsandsails.models.Order;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.models.Weather;

import java.util.List;

public interface IMove {
    public void movement(List<Ship> ships, List<Order> orders, Weather weather); //move ships and resolve collisions
}