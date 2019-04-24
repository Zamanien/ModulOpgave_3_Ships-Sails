package kea.shipsandsails.service.gunController;
import dk.kea.shipgame.Model.Order;


import java.util.*;


public class Hit
{
    //Declar variables
    private Random r = new Random();
    private int rollHit = r.nextInt(10) + 1;
    private double firingValueNumbers = 0.0;

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    public int getRollHit() {
        return rollHit;
    }

    public void setRollHit(int rollHit) {
        this.rollHit = rollHit;
    }

    public double getFiringValueNumbers() {
        return firingValueNumbers;
    }

    public void setFiringValueNumbers(double firingValueNumbers) {
        this.firingValueNumbers = firingValueNumbers;
    }

    public void whatIsMyWeapon() {
        /*
        Order waitForTurn = new Order();*/


        //Make method that skips the entire targetIsHit() Method if load is true
        targetIsHit();
    }


   public double targetIsHit() {

       //Create methods that checks if the target is hit.
       //Wait for communication responsible class to verify
       //It´s currently not working
        for (int rollOnce = 0; rollOnce < 1; rollOnce++) {
            if (rollHit <= 4) {
                System.out.println("Congratulations! You score a hit!");
                System.out.println("Your roll was:" + rollHit);
                int rollCritical = r.nextInt(20) + 1;
                firingValueNumbers = 0.4;
                {
                    if (rollCritical <= 2) {
                        System.out.println("Congratulation you scored a critical hit!");
                        System.out.println("Your roll was: " + rollCritical);
                    }
                }
            } else {
                System.out.println("You missed :(");
                System.out.println("Your roll was:" + rollHit);
            }
       return firingValueNumbers;
       }
        return firingValueNumbers;
}
}



