package kea.shipsandsails.services;

import kea.shipsandsails.models.Order;
import kea.shipsandsails.models.Ship;
import kea.shipsandsails.models.Weather;

import java.util.List;

public class MoveService implements IMove {

    //move ships and resolve collisions
    public void movement(List<Ship> ships, List<Order> orders, Weather weather) {
    }

    // alt movement i indeværende tur ofres mod at sætte maks sejl (eller så mange som lageret samt sailors tillader).
    public void rigNewSails(int maxSails, int sailors) {
        setActiveSails();
        setRemainingMove(0);
    }

    // udregner andelen af aktive sails, f.eks. 10%, 25%, osv.
    public double sailQuality(int sails, int sailors) {
        double sailQuality = 100.0;
        return sailQuality;
    }

    // antal gange et skib kan rotere i indeværende tur.
    public turningsRemaining(int shipID) {
    }

    // collission.
    public collissionDamage(int shipID) {
        double collissionDam = ship1.getHullStrength - shipID.getHullStrength * 0.3;
        if (collissionDam <= 0) {
            remove ship;
        } else {
            ship1.setHullStrength = ship1.getHullStrength - collissionDam;
            remainingMove(0);
        }
    }
}



/*
alle skibe rykker 1 felt samtidig, der skal løbende tjekkes for kollision

brættet skal extendes i alle retninger med ét ekstra felt der ikke vises for spilleren.
f.eks. 12 x 12 som går fra 0 to 11 gange 0 to 11 hvor der kun kan rykkes på 1-10 felterne.
derved undgår move metoderne underlige edge cases som varierer fra hex til hex.

et skib der rykker til 0 eller 11 i ovenstående tilfælde vil blive ødelagt.

UI skal ændres en smule. det er ikke muligt at rotere i samme felt, man kan kun gå til et af de tre felter foran skibet.

forslag til UI, omkredsen af feltet bag et skib kan være enten grønt eller rødt, grønt hvis det har mere move tilbage.



kan der rykkes til en af tre hexes foran skibet?:
moveRemaining();
turningsRemaining();
collision i ønsket hex.

moveRemaining();
    tidligere move i samme tur.
    wind direction samt egen direction.
    sailQuality (aktive sails, som også afhænger af sailors)
    max speed (afhænger af skibstype)
speed remaining (tidligere move i samme tur)
number of turns pr. turn (afhænger af skibstype)
max speed change (afhænger af skibstype)
*/