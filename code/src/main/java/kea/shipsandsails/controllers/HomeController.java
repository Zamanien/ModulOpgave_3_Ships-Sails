package kea.shipsandsails.controllers;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model; // For the model!
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;

import kea.shipsandsails.services.ScenarioService;
import kea.shipsandsails.services.ShipService;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.services.ShipTypeService;
import java.util.Collection;

@Controller
public class HomeController {

  // Autowired must be specified above each service, not just once above all of them
  @Autowired 
  ScenarioService scs; // Had a NullPointerException before where I actually created a "new ScenarioService", rather than not creating it and using Autowired.
  @Autowired 
  ShipService shs;
  @Autowired 
  ShipTypeService shtys;

  // The main menu
  @GetMapping("/")
  public String mainmenu() {
    // Just a test
    System.out.println( scs.fetchScenario("X Marks the Spot").getName() );

    Ship ship = (Ship) shs.fetchScenarioShips("X Marks the Spot").get(1) ;
    System.out.println(ship.getNationality());

    System.out.println( shtys.fetchShipTypes().get(1).getName() );
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
  public String game() {
    return "game";
  }

}
