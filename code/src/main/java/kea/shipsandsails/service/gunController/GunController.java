package kea.shipsandsails.service.gunController;

import dk.kea.shipgame.Model.Order;
import dk.kea.shipgame.Model.Ship;

import java.util.List;

public class GunController implements dk.kea.shipgame.Service.IAttack
{


    @Override
    public void resolveAttackOwn(List<Ship> ships, List<Order> orders) {

    }

    @Override
    public void evaluateVictoryConditions(List<Ship> ships, List<Order> orders) {

    }
    public void checkAmmunition()
    {
        GunType myAmmuntion = new GunType();
        myAmmuntion.pauseForWeaponChoice();
    }
    public static int callOnStuff()
    {
        GunCount fireIsPossible = new GunCount();
        Hit targetHit = new Hit();

        double someDamage = targetHit.targetIsHit();
        int guns = fireIsPossible.amountOfGuns();
        double firingValue = targetHit.getFiringValueNumbers() * guns;



    }




}
