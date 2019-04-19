package kea.shipsandsails.services;

import kea.shipsandsails.models.Direction;
import kea.shipsandsails.models.Weather;

import java.util.Random;

public class WeatherService implements IWeather {

    public WeatherService() {
    }

    public void newWeather(Weather weather) {
        Direction[] weatherArray = {Direction.N, Direction.NE, Direction.SE, Direction.S, Direction.SW, Direction.NW};
        Direction newWeather;

        if (weather.getWindDirection() == null) {
            newWeather = weatherArray[new Random().nextInt(6)];
        } else {
            int index = weather.getWindDirection().ordinal();
            switch(new Random().nextInt(12)+1) {
                case 1:
                    index -= 2;
                    break;
                case 2:
                case 3:
                    index -= 1;
                    break;
                case 10:
                case 11:
                    index += 1;
                    break;
                case 12:
                    index += 2;
                    break;
                default:
                    break;
            }
            if (!(index >= 0 && index < weatherArray.length)) {
                index = Math.abs(weatherArray.length - Math.abs(index));
            }
            newWeather = weatherArray[index];
        }
        weather.setWindDirection(newWeather);
    }

}