package kea.shipsandsails.controllers;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model; // For the model!
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
  public String game() {
    return "game";
  }

}
