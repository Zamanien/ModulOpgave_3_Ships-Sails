package kea.shipsandsails.service.gunController;

import java.util.Scanner;

public class GunType
{


    public static int checkWeaponType() {
        final int cannonBall = 1;
        final int chainShot = 2;
        final int grapeShot = 3;

        Scanner skan = new Scanner(System.in);
        int weaponType = 0;
        int chosenWeapon = skan.nextInt();

        if(chosenWeapon == 1) {
            weaponType = cannonBall;
            System.out.println("Cannonball is chosen");
            return weaponType;
        }
        if (chosenWeapon == 2) {
            weaponType = chainShot;
            System.out.println("Chain is chosen");
            return weaponType;
        }
        if (chosenWeapon == 3)
        {
                weaponType = grapeShot;
                System.out.println("Grape Shot is chosen");
                return weaponType;
        }
        return weaponType;
    }

        public static void pauseForWeaponChoice() {
            System.out.println("Choose your weapon");
            System.out.println("1. Cannonball");
            System.out.println("2. Chain");
            System.out.println("3. Grape shot");
            checkWeaponType();


        }


}




