package dk.kea.shipgame.Service;

import dk.kea.shipgame.Model.Order;
import dk.kea.shipgame.Model.Ship;
import dk.kea.shipgame.Model.Weather;

import java.util.List;

public interface ICommunication {
    public boolean initComm(String ip); //returns true if server - use to determine if to send Weather
    public void exchangeMovesAttacks(List<Ship> ships, List<Order> orders, Weather weather); //exchange orders and weather
    public void exchangeAttackResolved(List<Ship> ships, List<Order> orders); //receive info on enemy surviving ships
}
