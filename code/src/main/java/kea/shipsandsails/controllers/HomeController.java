package kea.shipsandsails.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // For the model!
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import kea.shipsandsails.services.ScenarioService;
import kea.shipsandsails.services.ShipService;
import kea.shipsandsails.services.ShipTypeService;
import kea.shipsandsails.models.Scenario;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.models.ShipType;

import java.util.List;

@Controller
public class HomeController {

  public static List<Ship> ships; // Maybe these should be private and only passed into movement and guns, but for now it's probably easier to test things when they're public
  public static List<ShipType> shipTypes;
  public static Scenario scenario;

  // Autowired must be specified above each service, not just once above multiple of them
  @Autowired 
  ScenarioService scs; // Had a NullPointerException before where I actually created a "new ScenarioService", rather than not creating it and using Autowired.
  @Autowired 
  ShipService shs;
  @Autowired 
  ShipTypeService shtys;

  // The main menu
  @GetMapping("/")
  public String mainmenu() {
    return "index";
  }

  // If choosing server: Choose a scenario on this screen
  @GetMapping("/server")
  public String server() {
    return "server";
  }

  // If choosing client: Type in ip and/or port on this screen
  @GetMapping("/client")
  public String client() {
    return "client";
  }

  // The game itself
  @GetMapping("/game")
  public String game(Model model) {
    model.addAttribute("scenario", scenario);
    model.addAttribute("ship_types", shipTypes);
    model.addAttribute("ships", ships);

    // Mainly for testing
    scenario = scs.fetchScenario("X Marks the Spot");
    // System.out.println( scenario.getName() );

    ships = shs.fetchScenarioShips("X Marks the Spot");
    // System.out.println(ships.get(1).getNationality());

    shipTypes = shtys.fetchShipTypes();
    // System.out.println( shipTypes.get(1).getName() );

    return "game";
  }

}
