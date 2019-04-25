package kea.shipsandsails.services;

import kea.shipsandsails.models.Weather;

import java.util.Random;

public class WeatherService implements IWeather {

    public WeatherService() {
    }

    public void newWeather(Weather weather) {
        int newWeather;
        if (weather.getFirstWeather()) {
            newWeather = new Random().nextInt(6);
            weather.setFirstWeather(false);
        } else {
            int oldWeather = weather.getWindDirection();
            switch(new Random().nextInt(12)+1) {
                case 1:
                    oldWeather -= 2;
                    break;
                case 2:
                case 3:
                    oldWeather -= 1;
                    break;
                case 10:
                case 11:
                    oldWeather += 1;
                    break;
                case 12:
                    oldWeather += 2;
                    break;
                default:
                    break;
            }
            if (!(oldWeather >= 0 && oldWeather < 6)) {
                oldWeather = Math.abs(6 - Math.abs(oldWeather));
            }
            newWeather = oldWeather;
        }
        weather.setWindDirection(newWeather);
    }

}