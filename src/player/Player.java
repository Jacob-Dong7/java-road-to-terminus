package player;
import java.util.*;

import core.Character;
import core.GameContext;
import items.Healing;
import items.Weapon;

public class Player extends Character {
    private final Scanner scnr = new Scanner(System.in);
    private final Random random = new Random();
    private int input;

    public void getStatus(GameContext gc) {
        System.out.println("==================================================");
        System.out.println("STATUS");
        System.out.println("==================================================");
        System.out.println("Name: " + name);
        System.out.println("Strength: " + gc.stats.strength);
        System.out.println("Charisma: " + gc.stats.charisma);
        System.out.println("Stealth: " + gc.stats.stealth);
        System.out.println();
        System.out.println("Health: " + health);
        System.out.println("Money: " + gc.inventory.getMoney());
        System.out.println("Ammunition: " + gc.inventory.getAmmo());
         System.out.println("==================================================");
    
    }
    public void killed() {
        System.out.println(
            "==================================================\n" +
            "You collapse, strength leaving your body.\n" +
            "\n" +
            "Breath slows.\n" + 
            "Thoughts scatter.\n" +
            "\n" +
            "The world grows distant and quiet.\n" +
            "\n" + 
            name + " is gone.\n" +
            "The ash endures.\n" +
            "YOU HAVE DIED\n" +
            "GAME OVER\n" +
             "==================================================" 
        );
        System.exit(0);
    }

    public void create(Stats playerStat) {
        System.out.println("\nCHARACTER CREATION");
        System.out.println("--------------------------------------------------");
        System.out.println("Please enter your name: ");

        //default health weapon and its damage 
        health = 100;
        currWeapon = Weapon.KNIFE;
        damage = 20;


        //name creation and validation
        while (true) {
            name = scnr.nextLine().trim();

            if (name.isBlank()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Warning: No names detected. Please try again:");
                System.out.println("--------------------------------------------------");
                continue;
            } else if (name.length() > 20) {
                System.out.println("--------------------------------------------------");
                System.out.println("Warning: Your name is too long. Please ensure your name is less than 20 characters long");
                System.out.println("Please try again:");
                System.out.println("--------------------------------------------------");
                continue;
            } else if (name == "-1") {
                System.out.println("--------------------------------------------------");
                System.out.println("Exiting...");
                System.out.println("--------------------------------------------------");
                System.exit(0);
            } else {
                break;
            }

        }

    

        //character stats creation 
        playerStat.statAssign();
        displayStat(playerStat);

        
    }

    public void displayStat(Stats playerStat) {
        System.out.println("==================================================");
        System.out.println(
            "Your Name: " + name + "\n" +
            "Your Stats: \n" +
            "Strength: " + playerStat.strength + "\n" +
            "Charisma: " + playerStat.charisma + "\n" +
            "Stealth: " + playerStat.stealth
        );
        System.out.println("==================================================");
    }
  
    public void intro() {
        String[] introText = {

            "==================================================",
            "                     DEAD SUN",
            "==================================================",
            "",
            "Year 2176.",
            "",
            "One hundred years ago, pressure built beneath the crust.",
            "Tectonic plates locked.",
            "Magma chambers swelled.",
            "",
            "When they ruptured, they ruptured everywhere.",
            "",
            "Supervolcanoes erupted across the planet.",
            "Ash entered the stratosphere.",
            "Sulfur clouds reflected sunlight back into space.",
            "",
            "Global temperature dropped six degrees in months.",
            "",
            "The sun disappeared.",
            "",
            "Scientists called it a volcanic winter.",
            "",
            "It lasted thirty years.",
            "",
            "Crops failed first.",
            "Then power.",
            "Then law.",
            "",
            "People did not die from lava.",
            "They died from hunger.",
            "",
            "And hunger makes animals of everyone.",
            "",
            "Neighbors killed neighbors for rice.",
            "Parents stole from children.",
            "Bodies lay in the streets for days.",
            "",
            "Some burned furniture for heat.",
            "Some burned houses...",
            "Some burned people...",
            "",
            "By the time the sky cleared,",
            "ninety-eight percent of humanity was gone.",
            "",
            "The sun came back wrong.",
            "",
            "A weak red blur behind smoke.",
            "",
            "A dead sun.",
            "",
            name + ", you were born long after the fall.",
            "",
            "You have never seen blue sky.",
            "You have never tasted fresh food.",
            "",
            "Only ash.",
            "Only cold.",
            "",
            "Only ruins filled with bones that used to be cities.",
            "",
            "Your home wasn't a house.",
            "It was a metro station.",
            "Line 12. Platform C.",
            "",
            "Thirty people. Scrap beds. Melted snow for water.",
            "They called it Haven.",
            "",
            "It wasn't living.",
            "But it was safe.",
            "",
            "Last night, gunshots echoed through the tunnels.",
            "",
            "Doors blown open.",
            "Flashlights in the dark.",
            "Screaming.",
            "",
            "The Black Torch raiders.",
            "",
            "They took food.",
            "They took people.",
            "They killed the rest.",
            "",
            "Your parents shoved you into a maintenance hatch.",
            "",
            "\"Run east,\" your father said.",
            "\"Geothermal station... greenhouse... food...\"",
            "",
            "You heard the shots before the door slammed shut.",
            "",
            "By morning, Haven was silent.",
            "",
            "Smoke.",
            "Blood.",
            "No survivors.",
            "",
            "You take what you can carry.",
            "Three bullets. An homemade pistol. A knife. And some bandages.",
            "",
            "The tunnel ahead is cold and black.",
            "",
            "Walk east.",
            "Find the greenhouse.",
            "",
            "Or die like the others.",
            "",
            "Press 1 to step into the ash...",
            "=================================================="
        };
        for (int i = 0; i < introText.length; ++i) {
            System.out.println(introText[i]);
        }

        while (true) {
            input = scnr.nextInt();
            if (input == -1) {
                System.out.println("Exiting...");
                System.exit(0);
            } else if (input == 1) {
                return;
            } else {
                System.out.println("Press 1 to step into the ash...");
                scnr.nextLine();
                continue;
            }
        }
    };

    public void healPrompt(Healing usedItem) {
        System.out.println("==================================================");
        System.out.println("HEALING");
        System.out.println("==================================================");
        if (usedItem == Healing.BANDAGE) {
            System.out.println("You tear open a worn bandage packet with shaking fingers.");
            System.out.println("Cloth wraps tight around the wound, soaking slowly as you pull it firm.");
            System.out.println("The bleeding eases. It's not pretty, but it will hold.");
        } else if (usedItem == Healing.MEDKIT) {
            System.out.println("You drop to one knee and snap open the medkit.");
            System.out.println("Disinfectant burns as you clean the wound, stitching and sealing with steady hands.");
            System.out.println("Your breathing steadies. Strength creeps back into your limbs.");
        } else if(usedItem == Healing.SYRINGE) {
            System.out.println("You press the syringe against your arm and inject without hesitation.");
            System.out.println("A cold rush floods your veins, numbing pain and sharpening your focus.");
            System.out.println("Your vision clears. The weakness fades — for now.");
        }
        System.out.println("You recovered " + usedItem.getHeal() + " HP");
        System.out.println("Your HP: " + health + " / 100");
    }

    @Override
    public void takeDamage(int damageTaken, Weapon weapon) {
        health -= damageTaken;
        System.out.println("(" + damageTaken + " DMG)");
    }
    @Override
    public boolean tryAttack() {
        int hit = random.nextInt(1, 101); // 1 - 100% 
        if (hit <= 90) { 
            return true;
        } else {
            System.out.println("==================================================");
            System.out.println("YOUR TURN");
            System.out.println("==================================================");
            System.out.println("Your attack misses.");
            System.out.println("==================================================");
            return false;
        }       
    }
};
    



//javac src/main.java src/character.java src/player.java
//java src/main.java


