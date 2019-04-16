package kea.shipsandsails.services;

import kea.shipsandsails.models.Direction;
import kea.shipsandsails.models.Weather;

import java.util.Random;

public class SWeather implements IWeather {

    public SWeather() {
    }

    public void newWeather() {
        Direction[] weatherArray = {Direction.N, Direction.NE, Direction.SE, Direction.S, Direction.SW, Direction.NW};
        Weather currentWeather = new Weather();
        Direction newWeather;

        if (currentWeather.getWindDirection() == null) {
            newWeather = weatherArray[new Random().nextInt(6)];
        } else {
            int index = currentWeather.getWindDirection().ordinal();
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
            if (!(index >= 0 && index <= 5)) {
                index = Math.abs(6 - Math.abs(index));
            }
            newWeather = weatherArray[index];
        }
        currentWeather.setWindDirection(newWeather);
    }

}