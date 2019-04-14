package dk.kea.shipgame.Model;

import java.util.List;

public class Order {

    private int shipId;
    private int turnNumber; //which turn does the order belong to
    private int turn; //sharp left, left, straight, right, sharp right - Enum?
    private int sailUsage; //sail percentage 0 25 50 75 100 (depending on MAX_SPEED
    private List<Coordinate> coordinateList; //ship coordinates from origin to destination
    private boolean fire; //shoot? only possible if not loading
    private boolean load; //order load
    private int ammunitionType; //0=canonball, 1=chainball, 2=grapeshot
    private int target; //not possible to shoot straight forward or backward - Enum?

    public Order() {
    }

    public Order(int shipId, int turnNumber, int turn, int sailUsage, List<Coordinate> coordinateList, boolean fire, boolean load, int ammunitionType, int target) {
        this.shipId = shipId;
        this.turnNumber = turnNumber;
        this.turn = turn;
        this.sailUsage = sailUsage;
        this.coordinateList = coordinateList;
        this.fire = fire;
        this.load = load;
        this.ammunitionType = ammunitionType;
        this.target = target;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public int getSailUsage() {
        return sailUsage;
    }

    public void setSailUsage(int sailUsage) {
        this.sailUsage = sailUsage;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public int getAmmunitionType() {
        return ammunitionType;
    }

    public void setAmmunitionType(int ammunitionType) {
        this.ammunitionType = ammunitionType;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
