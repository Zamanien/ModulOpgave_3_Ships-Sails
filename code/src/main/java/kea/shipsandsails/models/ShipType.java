package kea.shipsandsails.models;

public class ShipType {

  // Starting values for ships that vary per type
  private String name;
  private int sailors;
  private int sailors_needed_on_guns;
  private int sailors_needed_on_sails;
  private int guns_per_side;  // (guns_per_row * rows_of_guns) / 2
  private int hull_condition;
  private int max_sails_up;
  private int sails_total;
  private int max_speed;
  private int max_speed_change;
  private int max_rotate;

  // public ShipType() {} // Required by Spring, at least for models

  // public ShipType(int sailors, int sailors_needed_on_guns, int sailors_needed_on_sails, int hull_condition, int max_sails_up, int sails_total, int max_speed, int max_speed_change, int max_rotate) {
  //   this.sailors = sailors;
  //   this.sailors_needed_on_guns = sailors_needed_on_guns;
  //   this.sailors_needed_on_sails = sailors_needed_on_sails;
  //   this.hull_condition = hull_condition;
  //   this.max_sails_up = max_sails_up;
  //   this.sails_total = sails_total; // All sails incl. or excl. max_sails_up?
  //   this.max_speed = max_speed;
  //   this.max_speed_change = max_speed_change;
  //   this.max_rotate = max_rotate;
  // }

  public String getName() {
    return name;
  }

  public int getSailors() {
    return sailors;
  }

  public int getSailorsNeededOnGuns() {
    return sailors_needed_on_guns;
  }

  public int getSailorsNeededOnSails() {
    return sailors_needed_on_sails;
  }

  public int getGunsPerSide() {
    return guns_per_side;
  }

  public void setGunsPerSide(int gunsPerSide) {
    this.guns_per_side = gunsPerSide;
  }

  public int gethullCondition() {
    return hull_condition;
  }

  public int getMaxSailsUp() {
    return max_sails_up;
  }

  public int getSailsTotal() {
    return sails_total;
  }

  public int getMaxSpeed() {
    return max_speed;
  }

  public int getMaxSpeedChange() {
    return max_speed_change;
  }

  public int getMaxRotate() {
    return max_rotate;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSailors(int sailors) {
    this.sailors = sailors;
  }

  public void setSailorsOnGuns(int sailorsNeededOnGuns) {
    this.sailors_needed_on_guns = sailorsNeededOnGuns;
  }

  public void setSailorsNeededOnSails(int sailorsNeededOnSails) {
    this.sailors_needed_on_sails = sailorsNeededOnSails;
  }

  public void setHullCondition(int hullCondition) {
    this.hull_condition = hullCondition;
  }

  public void setMaxSailsUp(int maxSailsUp) {
    this.max_sails_up = maxSailsUp;
  }

  public void setSailsTotal(int sailsTotal) {
    this.sails_total = sailsTotal;
  }

  public void setMaxSpeed(int maxSpeed) {
    this.max_speed = maxSpeed;
  }

  public void setMaxSpeedChange(int maxSpeedChange) {
    this.max_speed_change = maxSpeedChange;
  }

  public void setMaxRotate(int maxRotate) {
    this.max_rotate = maxRotate;
  }
}
