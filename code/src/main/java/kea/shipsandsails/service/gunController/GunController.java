package kea.shipsandsails.service.gunController;

import kea.shipsandsails.models.Order;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.services.IAttack;

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

    public static double[] checkCurrentDamage(Ship ship)
    {
        GunType myAmmunition = new GunType();
        myAmmunition.pauseForWeaponChoice();
        Scanner scan = new Scanner(System.in);
        String pause = scan.nextLine();
        GunCount fireIsPossible = new GunCount();
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
            int guns = fireIsPossible.amountOfGuns();
            double firingValue = weHitTheTarget.getFiringValueNumbers() * guns;
            double damageDone = firingValue;
            if(myAmmunition.checkWeaponType() == 1)
            {
                remainingHealth[0] = ship.getHull_health() - damageDone;
            }
            if (myAmmunition.checkWeaponType() == 2)
            {
                remainingHealth[1] = ship.getSail_health() - damageDone;

            }
            if(myAmmunition.checkWeaponType() == 3)
            {
                remainingHealth[2] = ship.getSailors() - damageDone;

            }

        }
        return remainingHealth;
    }




}
