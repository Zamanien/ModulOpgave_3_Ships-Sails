package kea.shipsandsails.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kea.shipsandsails.repositories.ShipTypeRepo;
import kea.shipsandsails.models.ShipType;

import java.util.Collection;
// import java.util.Map;

@Service
public class ShipTypeService {

  // TODO: Should these be stored in the repo, in the service, the controller or a third place? Controller needs it for UI and Movement and Attack. Model could also be the place, but for something that isn't objects?
  // Map<String, String> shipTypes;
  // Map<String, String> scenario;

  @Autowired
  ShipTypeRepo str;

  public Collection<ShipType> fetchShipTypes() {
    return str.fetchShipTypes();
  }

}
