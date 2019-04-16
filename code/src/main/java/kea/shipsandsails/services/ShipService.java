package kea.shipsandsails.services;

import kea.shipsandsails.repositories.ShipRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShipService {

  @Autowired
  ShipRepo sr;

  public Collection fetchScenarioShips(String scenario) {
    return sr.fetchScenarioShips(scenario);
  }
  
}
