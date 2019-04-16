package kea.shipsandsails.services;

import kea.shipsandsails.models.Order;
import kea.shipsandsails.models.Ship;

import java.util.List;

public interface IAttack {
    public void resolveAttackOwn(List<Ship> ships, List<Order> orders); //takes ship and their orders and fire - resolve damage
    public void evaluateVictoryConditions(List<Ship> ships, List<Order> orders); //max turns, no ships moving, ships eliminated
}
