package kea.shipsandsails.service.gunController;

import java.util.*;


public class Dice
{




    public static void whatIsMyWeapon() {
        pauseForWeaponChoice();
        checkWeaponType();
        targetIsHit();
    }

    public static void pauseForWeaponChoice() {
        System.out.println("Choose your weapon");
        System.out.println("1. Cannonball");
        System.out.println("2. Chain");
        System.out.println("3. Grape shot");
        Scanner scan = new Scanner(System.in);
        String pause = scan.nextLine();
    }

    public static void checkWeaponType() {

        Scanner skan = new Scanner(System.in);
        int weaponType = skan.nextInt();
        String waitForInput = skan.nextLine();


        switch (weaponType) {
            case 1:
                weaponType = 0;
                System.out.println("Cannonball is chosen");
                break;
            case 2:
                weaponType = 1;
                System.out.println("Chain is chosen");
                break;
            case 3:
                weaponType = 2;
                System.out.println("Grape Shot is chosen");
                break;
        }

        //Pausen virker ikke. Få den til at implementere våben og interface
        System.out.println("Where do you want to shoot?" + waitForInput);

    }

   public static int targetIsHit() {

       Random r = new Random();
       int rollHit = r.nextInt(10) + 1;
       int checkForHit = 0;

       for (int i = 0; i <3; i++) {


           if (rollHit <= 4) {
               System.out.println("Congratulations! You score a hit!");
               System.out.println("Your roll was:" + rollHit);
               int rollCritical = r.nextInt(20) + 1;
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

       }
       return rollHit;
   }


        //case 2:

        //case 3:
    }
}
}



