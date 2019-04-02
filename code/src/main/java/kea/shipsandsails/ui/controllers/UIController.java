package kea.shipsandsails.ui.controllers;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model; // For the model!
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

  @GetMapping("/")
  public String game() {
    return "index";
  }
  
}
