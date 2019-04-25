package kea.shipsandsails.services;

import kea.shipsandsails.models.Order;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.models.ShipType;
import kea.shipsandsails.services.IAttack;
import kea.shipsandsails.services.GunType;
import java.util.List;
import java.util.Scanner;

public class GunController implements IAttack
{


    @Override
    public void resolveAttackOwn(List<Ship> ships, List<Order> orders) {

    }

    @Override
    public void evaluateVictoryConditions(List<Ship> ships, List<Order> orders) {

    }
    /*public void checkAmmunition()
    {
        GunType myAmmunition = new GunType();
        myAmmunition.pauseForWeaponChoice();
        Scanner scan = new Scanner(System.in);
        String pause = scan.nextLine();
    }
    public static void fireInDirectionOf()
    {
        Target direction = new Target();
       direction.shootDirection();
    }*/

    public static double[] checkCurrentDamage(Ship ship, ShipType[] shiptypes)
    {
        GunType myAmmunition = new GunType();
        myAmmunition.pauseForWeaponChoice();
        Scanner scan = new Scanner(System.in);
        String pause = scan.nextLine();
        //GunCount fireIsPossible = new GunCount();
        int i;
        for (i = 0; i<shiptypes.length; i++)
        {
            if(shiptypes[i].getName() == ship.getType())
            {
                break;
            }
        }
        shiptypes[i].getGunsPerSide();
        int amountOfGuns = shiptypes[i].getGunsPerSide();
        int amountOfSailors = ship.getSailors();
        Target direction = new Target();
        direction.shootDirection();
        Order waitForTurn = new Order();
        double[] remainingHealth = new double[3];


        if(waitForTurn.isFire()== false)
        {
            waitForTurn.setLoad(true);
            if(waitForTurn.isLoad() == true)
            {
                System.out.println("Loading is active");
                waitForTurn.setFire(true);
            }
        }
        else
        {
            Hit weHitTheTarget = new Hit();
            int guns;
            amountOfGuns = amountOfGuns / 2;
            amountOfSailors = amountOfSailors / 3;
            if(amountOfSailors> 3 && amountOfSailors <=amountOfGuns)
            {
                guns = amountOfSailors;
            }
            else
            {
                guns = amountOfGuns;
            }
            double firingValue = weHitTheTarget.getFiringValueNumbers() * guns;
            if(myAmmunition.checkWeaponType() == 1)
            {
                remainingHealth[0] = ship.getHull_health() - firingValue;
            }
            if (myAmmunition.checkWeaponType() == 2)
            {
                remainingHealth[1] = ship.getSailHealth()- firingValue;

            }
            if(myAmmunition.checkWeaponType() == 3)
            {
                remainingHealth[2] = ship.getSailors() - firingValue;

            }

        }
        return remainingHealth;
    }




}
