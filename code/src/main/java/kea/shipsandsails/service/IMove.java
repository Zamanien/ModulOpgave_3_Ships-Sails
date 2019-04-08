package dk.kea.shipgame.Service;

import dk.kea.shipgame.Model.Order;
import dk.kea.shipgame.Model.Ship;
import dk.kea.shipgame.Model.Weather;

import java.util.List;

public interface IMove {
    public void movement(List<Ship> ships, List<Order> orders, Weather weather); //move ships and resolve collisions
}
