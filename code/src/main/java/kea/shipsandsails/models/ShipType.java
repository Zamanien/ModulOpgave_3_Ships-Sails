package kea.shipsandsails.models;

public class ShipType {

  private String name;
  private int sailors;
  private int rows_of_guns;
  private int hull_condition;
  private int sails_up;
  private int sails_storage;
  private int max_speed;
  private int max_speed_change;
  private int max_rotate;

    
  public ShipType(int sailors, int rows_of_guns, int hull_condition, int sails_up, int sails_storage, int max_speed, int max_speed_change, int max_rotate) {
    this.sailors = sailors;
    this.rows_of_guns = rows_of_guns;
    this.hull_condition = hull_condition;
    this.sails_up = sails_up;
    this.sails_storage = sails_storage;
    this.max_speed = max_speed;
    this.max_speed_change = max_speed_change;
    this.max_rotate = max_rotate;
  }

  public String getName() {
    return name;
  }

  public int getSailors() {
    return sailors;
  }

  public int getRowsOfGuns() {
    return rows_of_guns;
  }

  public int getHullCondition() {
    return hull_condition;
  }

  public int getSailsUp() {
    return sails_up;
  }

  public int getSailsStorage() {
    return sails_storage;
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

  public void setRowsOfGuns(int rowsOfGuns) {
    this.rows_of_guns = rowsOfGuns;
  }

  public void setHullCondition(int hullCondition) {
    this.hull_condition = hullCondition;
  }

  public void setSailsUp(int sailsUp) {
    this.sails_up = sails_up;
  }

  public void setSailsStorage(int sailsStorage) {
    this.sails_storage = sailsStorage;
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
