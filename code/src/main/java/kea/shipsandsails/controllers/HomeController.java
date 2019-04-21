package kea.shipsandsails.controllers;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model; // For the model!
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import kea.shipsandsails.services.ScenarioService;

@Controller
public class HomeController {

  @Autowired 
  ScenarioService sceser; // Had a NullPointerException before where I actually created a "new ScenarioService", rather than not creating it and using Autowired.

  // The main menu
  @GetMapping("/")
  public String mainmenu() {
    // Just a test
    System.out.println( sceser.fetchScenario("X Marks the Spot").getName() );
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
