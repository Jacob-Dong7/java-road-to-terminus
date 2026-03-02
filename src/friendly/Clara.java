package friendly;

import core.GameContext;
import items.*;
import java.util.Scanner;

public class Clara extends Trader {

    public Clara() {
        super("Clara", TraderType.GENERAL);
        populateVendor();
    }

    @Override
    public void populateVendor() {
        this.traderInventory.add(new TraderItem(new Ammo(65), 100));
        this.traderInventory.add(new TraderItem(new HealingItem(Healing.BANDAGE, 3), 70));
        this.traderInventory.add(new TraderItem(new HealingItem(Healing.SYRINGE, 5), 300));
    }

    @Override
    public String[] getDescription() {
        String[] desc = {
            "You move toward the kiosk.",
            "The woman behind the kiosk looks up.",
            "",
            "Up close, the stall is more organized than it first appeared.",
            "Food tins stacked by label.",
            "Rounds bundled in cloth.",
            "Antibiotics sealed tight in metal tins.",
            "",
            "A kettle simmers on the stove beside her.",
            "Her eyes assess you quickly - hands, weapon, posture.",
            "",
            "\"Welcome to New Concourse. We don't see many new faces.\"",
            "",
            "She wipes her hands on a rag.",
            "",
            "\"Name's Clara\"",
            "",
            "She leans slightly against the counter.",
            "",
            "\"You buying?\"",
            "",
            "Your stomach answers before you do.",
            "",
            "She slides a dented tin of navy beans and a bottle of purified water across the counter..",
            "",
            "\"Food and water for 10.\"",
            "",
            "You hand over what you can spare.",
            "You sit on the edge of the kiosk and eat.",
            "The food is plain. Warm enough.",
            "The water is clean.",
            "",
            "The ache in your gut dulls.",
            "",
            "You looks around as you eat",
            "You see that beyond the far wall, a sign reads: SERVICE ACCESS.",
            "A heavier section of the mall lies deeper inside.",
            "",
            "You finish the canned food quickly.",
            "\"You need anything else?\" says Clara"
    };
    
    return desc;

    }

    @Override
    public String[] getDescriptionVisited() {
        String[] desc = {
            "Clara stands behind the kiosk, counting inventory.",
            "",
            "\"Back again?\" She says as you approach",
            "",
            "\"What do you need?\""
        };
        return desc;    
    }

   @Override
    public void trade(GameContext gc) {


        while (true) {
            Scanner scnr = new Scanner(System.in);
            int input, amount = 0, price = 0, choice; 

            System.out.println("==================================================================");
            System.out.println("TRADING - " + getName().toUpperCase() + "'S GENERAL GOODS");
            System.out.printf("Credits: $%,d%n", gc.inventory.getMoney());
            System.out.println("==================================================================");
            System.out.println();

            System.out.printf(
                "%-3s %-25s %-12s %9s %8s%n",
                "#",
                "Item",
                "Effect",
                "Price",
                "Stock"
            );
            System.out.println("------------------------------------------------------------------");
            for (int i = 0; i < traderInventory.size(); ++i) {
                int itemNum = i + 1;
                if (traderInventory.get(i).getItem() instanceof HealingItem) {
                    HealingItem healingItem = (HealingItem) traderInventory.get(i).getItem();
                    
                    System.out.printf(
                        "[%d] %-25s | + %5d HP | $%,5d | STOCK %3d%n",
                        itemNum,
                        healingItem.getHeal().getName(),
                        traderInventory.get(i).getItem().getAmount(),
                        traderInventory.get(i).getPrice(),
                        traderInventory.get(i).getItem().getAmount()
                    );
                } else if (traderInventory.get(i).getItem() instanceof Ammo) {
                    System.out.printf(
                        "[%d] %-25s |   %5s    | $%,5d | STOCK %3d%n",
                        itemNum,
                        traderInventory.get(i).getItem().getName(),
                        "-",
                        traderInventory.get(i).getPrice(),
                        traderInventory.get(i).getItem().getAmount()
                    );
                }
            }
            System.out.println("------------------------------------------------------------------");
            System.out.println("[0] Return");
            System.out.println("==================================================================");

            input = scnr.nextInt();
            if (input > traderInventory.size() || input < 0) {
                System.out.println("--------------------------------------------------");
                System.out.println("Select from avaliable actions");
                System.out.println("--------------------------------------------------");
                continue;
            }

            if (input == 0) return;

            choice = input - 1;

            if (traderInventory.get(choice).getItem().getAmount() <= 0) {
                noStock();
                continue;
            }

            if (traderInventory.get(choice).getItem() instanceof Ammo) {
                System.out.println("--------------------------------------------------");
                System.out.println("Enter Quantity (Stock Left " + traderInventory.get(choice).getItem().getAmount() + "): ");
                System.out.println("--------------------------------------------------");
                input = scnr.nextInt();
                if (input < 0 || input > traderInventory.get(choice).getItem().getAmount()) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Out of stock");
                    System.out.println("--------------------------------------------------");
                    continue;
                }

                amount = input;
                price = traderInventory.get(choice).getPrice() * amount;
                 System.out.println("-23");
                if (gc.inventory.getMoney() < price) {
                    noMoney();
                    continue;
                }  else {
                    gc.inventory.useMoney(price);
                    gc.inventory.findAmmo(amount);
                    System.out.println("--------------------------------------------------");
                    System.out.println("+ " + amount + " ammo");
                    System.out.println("- $" + price);
                }
            } else if (traderInventory.get(choice).getItem() instanceof HealingItem) {
                System.out.println("--------------------------------------------------");
                System.out.println("Enter Quantity (Stock Left " + traderInventory.get(choice).getItem().getAmount() + "): ");
                System.out.println("--------------------------------------------------");
                HealingItem heal = (HealingItem) traderInventory.get(choice).getItem();
                Healing newHeal = (Healing) heal.getHeal();

                input = scnr.nextInt();
                if (input < 0 || input > heal.getAmount()) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Out of stock");
                    System.out.println("--------------------------------------------------");
                    continue;
                }


                amount = input;
                price = amount * traderInventory.get(choice).getPrice();

                if (gc.inventory.getMoney() < price) {
                    noMoney();
                    continue;
                } else {
                    Boolean hasHeal = false;
                    System.out.println("--------------------------------------------------");
                    for (HealingItem med : gc.inventory.getMedPouch()) {
                        if (med.getHeal() ==  newHeal) {
                            System.out.println("+ " + newHeal.getName()  + " x" + amount);
                            med.buyItem(amount);
                            hasHeal = true;
                            break;
                        }
                    }           
                    
                    if (hasHeal == false) {
                        System.out.println("+ " + newHeal.getName() + " x" + amount + " (New item added to Medical Pouch)");
                        gc.inventory.getMedPouch().add(new HealingItem(newHeal, amount));
                    }
                    System.out.println("- $" + price);
                    System.out.println("--------------------------------------------------");

                }

                }

                traderInventory.get(choice).sell(amount);
                
            }
        } 
}
