package kea.shipsandsails.controllers;

import kea.shipsandsails.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import kea.shipsandsails.models.Scenario;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.models.ShipType;

import java.io.IOException;
import java.net.InetAddress;
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
    // ServerConnection sc = new ServerConnection();

    return "server";
  }

  // If choosing client: Type in ip and/or port on this screen
  @GetMapping("/client")
  public String client() {
    //ClientConnection cc = new ClientConnection(6060, "127.0.0.1");

    return "client";
  }

  // The game itself
  @GetMapping("/game")
  public String game(Model model) {

    // INIT
    scenario = scs.fetchScenario("X Marks the Spot");
    ships = shs.fetchScenarioShips("X Marks the Spot");
    shipTypes = shtys.fetchShipTypes();

    // Setting starting values specific to each ship type on the ships
    for (var ship : ships) {
      for (var shipType : shipTypes) {
        if ( ship.getType().equals( shipType.getName() ) ) {
          ship.setMovesRemaining( shipType.getMaxSpeed() );
          ship.setRotationsRemaining( shipType.getMaxRotate() );
          ship.setCurrentSailsUp( shipType.getMaxSailsUp() );
          ship.setSailsTotal( shipType.getSailsTotal() );
          ship.setSailors( shipType.getSailors() ); // TODO: Really this needs to be converted to a percentage either here or in JS, for the UI progress bar
        }
      }
    }
    // TESTING

    // System.out.println( scenario.getName() );
    // System.out.println(ships.get(1).getNationality());
    // System.out.println( shipTypes.get(1).getName() );

    // for (var ship : ships) {
    //   System.out.println(ship.getType());
    //   System.out.println(ship.getNationality());
    //   System.out.println();
    // }
  
    
    model.addAttribute("j_scenario", scenario);
    model.addAttribute("j_ship_types", shipTypes);
    model.addAttribute("j_ships", ships);

    return "game";
  }

}
