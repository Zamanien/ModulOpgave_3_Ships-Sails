package dk.kea.shipgame.Model;

public class Weather {
    private Direction windDirection; //ENUM N, NE, SE, S, SW, NW
    private int windSpeed; //0 - 5

    public Weather() {
    }

    public Weather(Direction windDirection, int windSpeed) {
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public Direction getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Direction windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }
}
