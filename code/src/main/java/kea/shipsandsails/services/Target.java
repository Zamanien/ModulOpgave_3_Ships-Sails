package kea.shipsandsails.services;

import java.util.Scanner;

public class Target
{
    public static final void shootDirection() {
        Scanner skanFoTarget = new Scanner(System.in);
        int target = skanFoTarget.nextInt();
        int targetDirection = 0;

        int west = 1;
        int east = 2;
        int northWest = 3;
        int northEast = 4;
        int southWest = 5;
        int southEast = 6;

        if (target == 1) {
            targetDirection = west;
        }
        if (target == 2) {
            targetDirection = east;
        }
        if (target == 3) {
            targetDirection = northWest;
        }
        if (target == 4) {
            targetDirection = northEast;
        }
        if (target == 5) {
            targetDirection = southWest;
        }
        if (target == 6) {
            targetDirection = southEast;
        }
    }
}
