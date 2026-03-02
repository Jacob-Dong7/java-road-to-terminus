package Inventory;
import java.util.ArrayList;

import java.util.Scanner;
import items.*;
import player.Player;

public class Inventory {
    private ArrayList<HealingItem> medPouch;
    private ArrayList<Weapon> weaponSling;
    private Currency wallet;
    private Ammo ammoPouch;

    private static final Scanner scnr = new Scanner(System.in);

    public Inventory() {
        wallet = new Currency(20); //start user off with 20 bucks
        medPouch = new ArrayList<>();
        weaponSling = new ArrayList<>();  
        ammoPouch = new Ammo(5);

        medPouch.add(new HealingItem(Healing.BANDAGE, 3)); //start user off with 2 bandage
        weaponSling.add(Weapon.KNIFE); //starting weapon is knife
        weaponSling.add(Weapon.PISTOL);
    }

    public void switchWeapon(Player player) {
        int input;
        System.out.println("==================================================");
        System.out.println("WEAPON SLING");
        System.out.println("==================================================");
        System.out.println("Your Weapons:");
        for (int i = 0; i < weaponSling.size(); ++i) {
            System.out.print("[");
            System.out.print(i + 1);
            System.out.print("]");
            if (player.getWeapon() == weaponSling.get(i)) {
                System.out.println(" " + weaponSling.get(i).getName() + " (DMG " + weaponSling.get(i).getDamage() + ")    [EQUIPPED]");
            } else {
                System.out.println(" " + weaponSling.get(i).getName() + " (DMG " + weaponSling.get(i).getDamage() + ")");
            }
        }

        System.out.println("==================================================");

        //changing weapon
        input = scnr.nextInt() - 1;

        player.switchWeapon(weaponSling.get(input));

        
    }

    public void heal(Player player) {
        int input;
        int num = 0;
        System.out.println("--------------------------------------------------");
        if (player.getHealth() == 100) {
            System.out.println("You check your wounds, but there's nothing to treat.");
            System.out.println("--------------------------------------------------");
            return;
        }

        System.out.println("==================================================");
        System.out.println("MEDICAL POUCH");
        System.out.println("==================================================");
        for (int i = 0; i < medPouch.size(); ++i) {
            num = i + 1;
            System.out.println("[" + num + "] " + medPouch.get(i).getHeal().getName() + ": " + medPouch.get(i).getAmount() + "  -" + medPouch.get(i).getHeal().getDescription());
        }
        System.out.println("--------------------------------------------------");
        System.out.println("0. Return");
        System.out.println("==================================================");

        input = scnr.nextInt() - 1;

        if (input < 0 || input >= medPouch.size()) {
            System.out.println("--------------------------------------------------");
            System.out.println("You fumble through your pack but grab nothing useful.");
            System.out.println("--------------------------------------------------");
            return;
        }


        if (medPouch.get(input).getAmount() <= 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("You have ran out of the healing item you want");
            System.out.println("--------------------------------------------------");
            return;
        }
        int healingFactor = medPouch.get(input).getHeal().getValue();
        player.healBy(healingFactor);
        player.healPrompt(medPouch.get(input).getHeal());
        medPouch.get(input).useItem();

        
    }

    public int getAmmo() {
        return ammoPouch.getAmount();
    }

    public ArrayList<Weapon> getWeaponSling() {
        return weaponSling;
    }
    public void useAmmo() {
        ammoPouch.useAmmo();
    }
    public void useMoney(int amount) {
        wallet.decrease(amount);
    }

    public int getMoney() {
        return wallet.getAmount();
    }

    public void findAmmo(int amount) {
        ammoPouch.findAmmo(amount);
    }

    public void findMoney(int amount) {
        wallet.increase(amount);
    }

    public void findWeapon(Weapon weapon) {
        weaponSling.add(weapon);
    }

    public ArrayList<HealingItem> getMedPouch() {
        return medPouch;
    }

}

