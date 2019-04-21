package kea.shipsandsails.services;

import kea.shipsandsails.repositories.ShipRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

  @Autowired
  ShipRepo sr;

  public List fetchScenarioShips(String scenario) {
    return sr.fetchScenarioShips(scenario);
  }
  
}
