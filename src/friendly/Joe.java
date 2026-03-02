package friendly;

import items.Weapon;
import java.util.Scanner;
import core.*;

public class Joe extends Trader{

    public Joe() {
        super("Joe", TraderType.WEAPON);
        populateVendor();
    }
    @Override
    public void populateVendor() {
        this.traderWeapon.add(new TraderWeapon(Weapon.REINFORCED_KNIFE, 200, 5));
        this.traderWeapon.add(new TraderWeapon(Weapon.REINFORCED_CLEAVER, 300, 5));
        this.traderWeapon.add(new TraderWeapon(Weapon.RIFLE, 700, 1));
        this.traderWeapon.add(new TraderWeapon(Weapon.ADVANCERIFLE, 1500, 1));
    }

    @Override 
    public String[] getDescription() {
        String[] desc = {
            "The reinforced door opens halfway.",
            "",
            "A man sits behind a heavy industrial workbench.",
            "",
            "Rifles lie arranged in precise rows.",
            "Magazines stacked by caliber.",
            "Parts sorted into labeled trays.",
            "",
            "The lighting is harsher here.",
            "A voice comes from inside.",
            "",
            "\"Hold.\" The man said",
            "",
            "He studies you without standing.",
            "",
            "\"Joe.\"",
            "",
            "\"You looking for weapons or trouble?\""
        };
        return desc;
    }

    @Override
    public String[] getDescriptionVisited() {
        String[] desc = {
            "Joe glances up briefly as you reapproach him.",
            "",
            "\"You again. What can I do for you?\""
        };
        return desc;
    }

    @Override
    public void trade(GameContext gc) {
        int input;
        Boolean hasWeapon = false;
        Scanner scnr = new Scanner(System.in);

        while (true) {
            hasWeapon = false;
            System.out.println("==================================================================");
            System.out.println("TRADING - " + getName().toUpperCase() + "'S WEAPONS & ARMS");
            System.out.printf("Credits: $%,d%n", gc.inventory.getMoney());
            System.out.println("==================================================================");
            System.out.println();
            System.out.printf(
                "%-4s %-25s %7s %10s %7s%n",
                "#", "Weapon", "DMG", "Price", "Stock"
            );
            System.out.println("------------------------------------------------------------------");
            for (int i = 0; i < traderWeapon.size(); ++i) {
                int itemNum = i + 1;
                System.out.printf(
                    "[%-4d] %-25s | %-9d | $%-,7d | %-9s%n",
                    itemNum,
                    traderWeapon.get(i).getWeapon().getName(),
                    traderWeapon.get(i).getWeapon().getDamage(),
                    traderWeapon.get(i).getPrice(),
                    traderWeapon.get(i).getAmount()
                );
            }
            System.out.println("------------------------------------------------------------------");
            System.out.println("[0] Return");
            System.out.println("==================================================================");

            input = scnr.nextInt();
            if (input > traderWeapon.size() || input < 0) {
                System.out.println("--------------------------------------------------");
                System.out.println("Select from avaliable actions");
                System.out.println("--------------------------------------------------");
            }

            if (input == 0) {
                return;
            }

            for (Weapon weapon : gc.inventory.getWeaponSling()) {
                if (weapon == traderWeapon.get(input - 1).getWeapon()) {
                    hasWeapon = true;
                    break;
                }
            }

            if (hasWeapon == true) {
                alreadyOwn();
                continue;            
            }

            if (traderWeapon.get(input - 1).getAmount() <= 0) {
                noStock();
                continue;
            }

            if (gc.inventory.getMoney() < traderWeapon.get(input - 1).getPrice()) {
                noMoney();
                continue;
            } else {
                gc.inventory.useMoney(traderWeapon.get(input - 1).getPrice());
                gc.inventory.findWeapon(traderWeapon.get(input - 1).getWeapon());
                traderWeapon.get(input - 1).sell();
                System.out.println("--------------------------------------------------");
                System.out.println("+ " + traderWeapon.get(input - 1).getWeapon().getName());
                System.out.println("--------------------------------------------------");
            }
        }
        
    }

}
