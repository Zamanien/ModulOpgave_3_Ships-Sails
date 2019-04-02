package models;

public class Ship {

    private int shipId;
    private int shipType;
    private String nationality;
    private String name;
    private int x;
    private int y;
    private Direction direction; //ENUM, N, NE, SE, S, SW, NW
    private int speed; //change: speed last round +/- calculated change
    private int hull_health; //0 - 100
    private int sail_health; //0 - 100
    private int canons; //0 - MAX_KANONER afhængigt af, hvor mange der virker - max halvdelen skyder
    private int sailors; //0 - MAX_SAILORS

    public Ship() {
    }

    public Ship(int shipId, int shipType, String nationality, String name, int x, int y, Direction direction, int speed, int hull_health, int sail_health, int canons, int sailors) {
        this.shipId = shipId;
        this.shipType = shipType;
        this.nationality = nationality;
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.hull_health = hull_health;
        this.sail_health = sail_health;
        this.canons = canons;
        this.sailors = sailors;
    }

    public int getShipId() {
        return shipId;
    }

    public int getShipType() {
        return shipType;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() { return speed; }

    public int getHull_health() {
        return hull_health;
    }

    public int getSail_health() {
        return sail_health;
    }

    public int getCanons(){ return canons; }

    public int getSailors() {
        return sailors;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed ) { this.speed = speed; }

    public void setHull_health(int hull_health) {
        this.hull_health = hull_health;
    }

    public void setSail_health(int sail_health) {
        this.sail_health = sail_health;
    }

    public void setCanons(int canons) { this.canons = canons; }

    public void setSailors(int sailors) {
        this.sailors = sailors;
    }

}
