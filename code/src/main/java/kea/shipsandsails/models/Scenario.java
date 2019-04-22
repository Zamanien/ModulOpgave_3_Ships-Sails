package kea.shipsandsails.models;

public class Scenario {

  private String name;     // Name of the scenario, those shown at startup, to choose from
  private String player1;  // The two nationalities facing off in this scenario
  private String player2;
  private int map_width;   // The dimensions of the map (number of hexes)
  private int map_height;

  // public Scenario() {}; // Apparently Spring outright requires the empty, default constructor, at least for models.
    
  // public Scenario(String name, String player1, String player2, int map_width, int map_height) {
  //   this.name = name;
  //   this.player1 = player1;
  //   this.player2 = player2;
  //   this.map_width = map_width;
  //   this.map_height = map_height;
  // }

  public String getName() {
    return name;
  }
 
  public String getPlayer1() {
    return player1;
  }

  public String getPlayer2() {
    return player2;
  }

  public int getMapWidth() {
    return map_width;
  }

  public int getMapHeight() {
    return map_height;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPlayer1(String player1) {
    this.player1 = player1;
  }

  public void setPlayer2(String player2) {
    this.player2 = player2;
  }

  public void setMapWidth(int mapWidth) {
    this.map_width = mapWidth;
  }

  public void setMapHeight(int mapHeight) {
    this.map_height = mapHeight;
  }
}
