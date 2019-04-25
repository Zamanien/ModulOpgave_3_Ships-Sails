package kea.shipsandsails.models;

public class Weather {
    private int windDirection; //ENUM N, NE, SE, S, SW, NW bruges ikke længere.
    private int windSpeed; //0 - 5 -- windSpeed is constant and not changing. Bruges ikke.
    private boolean firstWeather = true;

    public Weather() {
    }

    public Weather(int windDirection, int windSpeed, boolean firstWeather) {
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.firstWeather = firstWeather;
    }

    public boolean getFirstWeather() {
        return firstWeather;
    }

    public void setFirstWeather(boolean firstWeather) {
        this.firstWeather = firstWeather;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }
}