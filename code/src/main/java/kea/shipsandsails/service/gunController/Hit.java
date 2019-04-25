package kea.shipsandsails.service.gunController;



import kea.shipsandsails.models.Order;

import java.util.*;


public class Hit
{
    //Declare variables
    private Random r = new Random();
    private int rollHit = r.nextInt(10) + 1;
    private double firingValueNumbers = 0.0;


    public double getFiringValueNumbers() {
        return firingValueNumbers;
    }

    public void setFiringValueNumbers(double firingValueNumbers) {
        this.firingValueNumbers = firingValueNumbers;
    }

    public void whatIsMyWeapon() {


             targetIsHit();
        //Make method that skips the entire targetIsHit() Method if load is true
    }


   public double targetIsHit() {

       //Create methods that checks if the target is hit.
       //Wait for communication responsible class to verify
       //It´s currently not working
        for (int rollOnce = 0; rollOnce < 7; rollOnce++) {
            if(tile == 10%) {
                if (rollHit == 1) {
                    System.out.println("Gratz you scored a hit!");
                    System.out.println("Your roll was:" + rollOnce);
                    firingValueNumbers = 0.1;
                }
            }
            if (tile == 40%) {
                if (rollHit <= 4) {
                    System.out.println("Congratulations! You score a hit!");
                    System.out.println("Your roll was:" + rollHit);
                    int rollCritical = r.nextInt(20) + 1;
                    firingValueNumbers = 0.4;
                    {
                        if (rollCritical <= 2) {
                            System.out.println("Congratulation you scored a critical hit!");
                            System.out.println("Your roll was: " + rollCritical);
                            return rollCritical;
                        }
                    }
                }
            }
            else {
                System.out.println("You missed :(");
                System.out.println("Your roll was:" + rollHit);
            }
       return firingValueNumbers;
       }
        return firingValueNumbers;
}
}



