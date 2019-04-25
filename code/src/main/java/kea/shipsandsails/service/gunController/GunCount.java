package kea.shipsandsails.service.gunController;


import kea.shipsandsails.models.Ship;

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
            currentAmountOfGuns = currentAmountofSailors;
            return currentAmountOfGuns;
        }

        return currentAmountOfGuns;
    }
}
