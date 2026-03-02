package friendly;

import java.util.*;

import core.GameContext;

public abstract class Trader {
    protected final String name;
    protected final TraderType type;
    protected ArrayList<TraderItem> traderInventory;
    protected ArrayList<TraderWeapon> traderWeapon;
    Boolean visited = false;

    //initalize 

    public Trader(String name, TraderType type) {
        this.name = name;
        this.type = type;
        traderInventory = new ArrayList<>();
        traderWeapon = new ArrayList<>();
    }

    public void visitTrader() {
        visited = true;
    }

    public Boolean visited() {
        return visited;
    }

    public abstract void populateVendor();

    public abstract String[] getDescription();

    public abstract String[] getDescriptionVisited();

    public abstract void trade(GameContext gc);

    public String getName() {
        return name;
    }

    public TraderType getType() {
        return type;
    }
    
    public void alreadyOwn() {
        System.out.println("--------------------------------------------------");
        System.out.println("You already own this item");
        System.out.println("--------------------------------------------------");  
    }

    public void noMoney() {
        System.out.println("--------------------------------------------------");
        System.out.println("You cannot afford this");
        System.out.println("--------------------------------------------------");
    }

    public void noStock() {
        System.out.println("--------------------------------------------------");
        System.out.println("The item you want has ran out");
        System.out.println("--------------------------------------------------");
    }
    public void printDescription(Boolean visited) {
        String[] desc;
        if (visited == false) {
            desc = getDescription();
        } else {
            desc = getDescriptionVisited();
        }

        System.out.println("==================================================");
        System.out.println(getName());
        System.out.println("==================================================");
        for (int i = 0; i < desc.length; ++i) {
            System.out.println(desc[i]);
        }
        System.out.println("==================================================");
    }
}
