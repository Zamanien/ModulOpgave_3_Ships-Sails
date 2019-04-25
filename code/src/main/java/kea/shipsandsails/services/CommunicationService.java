package kea.shipsandsails.services;

import kea.shipsandsails.models.Order;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.models.Weather;

import java.util.List;

public class CommunicationService implements ICommunication {

    //returns true if server - use to determine if to send Weather
    public boolean initComm(String ip) {
      return true; // Just here temporarily so the compiler doesn't complain    
    }

    //exchange weather - if server then decide weather, if client receive weather
    public void exchangeWeather(Weather weather) {
    
    }

    //exchange orders
    public void exchangeOrders(List<Ship> ships, List<Order> orders) {
    
    }

    //receive info on enemy surviving ships
    public void exchangeAttackResolved(List<Ship> ships) {
    
    }


}
