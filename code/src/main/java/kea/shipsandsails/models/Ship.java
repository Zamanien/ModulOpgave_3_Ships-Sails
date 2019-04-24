package kea.shipsandsails.models;

public class Ship {

    private int id;
    private String type;
    private String nationality;
    private String name;
    private int x;
    private int y;
    private int direction;             // ENUM, N, NE, SE, S, SW, NW
    private int speed;                 // change: speed last round +            / - calculated change
    private int hull_health;           // 0 - 100%
    private int sail_health;           // 0 - 100%
    private int sailors;               // 0 - MAX_SAILORS
    private int load;                  // load time
    private int current_ammunition_type; // 0=canonball, 1=chainball, 2=grapeshot
    private int moves_remaining;        // How many moves the ship has left. 0 = none
    private int rotations_remaining;    // How many rotations the ship has left. 0 = none

    // All the values have the same starting values for all shiptypes are set here
    public Ship() {
      this.speed = 0;
      this.hull_health = 100;
      this.sail_health = 100;
      this.load = 0;
      this.current_ammunition_type = 0;
      this.moves_remaining = 0;
      this.rotations_remaining = 0;
    } // Required by Spring, at least for models

    // public Ship(int shipId, int shipType, String nationality, String name, int x, int direction, int speed, int hull_health, int sail_health, int sailors, int load, int currentAmmunitionType) {
    //     this.shipId = shipId;
    //     this.shipType = shipType;
    //     this.nationality = nationality;
    //     this.name = name;
    //     this.coordinate = coordinate;
    //     this.direction = direction;
    //     this.speed = speed;
    //     this.hull_health = hull_health;
    //     this.sail_health = sail_health;
    //     this.sailors = sailors;
    //     this.load = load;
    //     this.currentAmmunitionType = currentAmmunitionType;
    // }

    public int getMovesRemaining() {
      return moves_remaining;
    }

    public void setMovesRemaining(int movesRemaining) {
      this.moves_remaining = movesRemaining;
    }

    public int getRotationsRemaining() {
      return rotations_remaining;
    }

    public void setRotationsRemaining(int rotationsRemaining) {
      this.rotations_remaining = rotationsRemaining;
    }

    public int getCurrentAmmunitionType() {
        return current_ammunition_type;
    }

    public void setCurrentAmmunitionType(int currentAmmunitionType) {
        this.current_ammunition_type = currentAmmunitionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
      return this.x;
    }

    public int getY() {
      return this.y;
    }

    public void setX(int x) {
      this.x = x;
    }

    public void setY(int y) {
      this.y = y;
    }

    // public Coordinate getCoordinate() {
    //     return coordinate;
    // }

    // public void setCoordinate(Coordinate coordinate) {
    //     this.coordinate = coordinate;
    // }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHull_health() {
        return hull_health;
    }

    public void setHull_health(int hull_health) {
        this.hull_health = hull_health;
    }

    public int getSail_health() {
        return sail_health;
    }

    public void setSail_health(int sail_health) {
        this.sail_health = sail_health;
    }

    public int getSailors() {
        return sailors;
    }

    public void setSailors(int sailors) {
        this.sailors = sailors;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}
