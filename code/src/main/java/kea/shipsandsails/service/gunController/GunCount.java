package kea.shipsandsails.service.gunController;

import dk.kea.shipgame.Model.Ship;

public class GunCount
{
    public static int amountOfGuns()
    {
        Ship amount = new Ship();

        int sailorsAvailable = amount.getSailors();
        int gunsAvailable = amount.getShipId();

        //Gets the ship´s amount of guns and considers it firing broadside
        int currentAmountOfGuns = gunsAvailable / 2;
        int currentAmountofSailors = sailorsAvailable / 3;

        if(currentAmountofSailors >= 3 && currentAmountofSailors <= currentAmountOfGuns)
        {
            return currentAmountOfGuns;
        }

        return currentAmountOfGuns;
    }
}
