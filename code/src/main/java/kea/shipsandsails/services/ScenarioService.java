package kea.shipsandsails.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kea.shipsandsails.repositories.ScenarioRepo;
import kea.shipsandsails.models.Scenario;

// import java.util.Collection;
// import java.util.Map;

@Service
public class ScenarioService {

  // TODO: Should these be stored in the repo, in the service, the controller or a third place? Controller needs it for UI and Movement and Attack. Model could also be the place, but for something that isn't objects?
  // Map<String, String> shipTypes;
  // Map<String, String> scenario;

  @Autowired
  ScenarioRepo ss;

  public Scenario fetchScenario(String scenario) {
    return ss.fetchScenario(scenario);
  }
  
}
