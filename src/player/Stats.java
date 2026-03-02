package player;
import java.util.Scanner;

public class Stats {

    private static final Scanner scnr = new Scanner(System.in);
    int strength, charisma, stealth, points, input;

    public Stats() {
        strength = 0;
        stealth = 0;
        charisma = 0;
        points = 5;
    }

    public void statAssign() {
        System.out.println("==================================================");
        System.out.println("ASSIGN YOUR ATTRIBUTES:");
        
        //while loop created for stat creation
        while (points > 0) {
        
        //tells user how many points remain
        if (points > 1) {
            System.out.println(
                "You have " +
                points +
                " skill points remaining."
            );
        } else if (points == 1) {
            System.out.println("You have 1 skill point remaining.");
        }

        System.out.println(
            "CURRENT ATTRIBUTES:\n" +
            "--------------------------------------------------\n" +
            "Strength:   " + strength + "\n" +
            "Charisma:   " + charisma + "\n" + 
            "Stealth:    " + stealth +
            "\n--------------------------------------------------\n"
        );

        System.out.println("[1]  Strength");
        System.out.println("[2]  Charisma");
        System.out.println("[3]  Stealth");
        System.out.println("--------------------------------------------------");
        System.out.println("[4]  Reset Points");


        System.out.println("\n==================================================");

        if (scnr.hasNextInt()) {
            input = scnr.nextInt();
        } else {
            System.out.println("--------------------------------------------------");
            System.out.println("Warning: Please enter a number 1-4");
            System.out.println("--------------------------------------------------");
            scnr.next();
            continue;
        }


        //user skill selection input
        if (input == 1) {
            ++strength;
            --points;
        } else if (input == 2) {
            ++charisma;
            --points;
        } else if (input == 3) {
            ++stealth;
            --points;
        } else if (input == 4) {
            System.out.println("Your Stats Have Been Reset");
            strength = 0;
            stealth = 0; 
            charisma = 0;
            points = 5;

        } else if (input == -1) {
            System.out.println("Quitting...");
            System.exit(0);
        } else {
            System.out.println("Please Select From The Options Displayed");
            continue;
        }

        }
    }

    public int getStealth() {
        return stealth;
    }
    public int getStrength() {
        return strength;
    }
    public int getCharisma() {
        return charisma;
    }
}