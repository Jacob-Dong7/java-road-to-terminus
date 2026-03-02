package world;
import core.*;
import friendly.*;
import java.util.*;

public class NewConcourse extends Settlement {
    Scanner scnr = new Scanner(System.in);

    public NewConcourse() {
        this.settlementArea = new ArrayList<>();
        this.name = "New Concourse";
        this.traderCount = 2;
        Clara clara = new Clara();
        Joe joe = new Joe();
        this.settlementArea.add(new SettlementArea("Central Concourse",clara, getDescription(1), getDescriptionVisited(1)));
        this.settlementArea.add(new SettlementArea("Service Corrido", joe, getDescription(2), getDescriptionVisited(2)));
    }

    public String[] description() {
    String[] desc = {
        "You climb out of the maintenance shaft and pull the hatch closed behind you.",
        "Cold air cuts through your clothes. Ash drifts across the streets in slow grey waves.",
        "",
        "Skyscrapers rise around you - hollow shells, windows blown out, steel exposed like ribs.",
        "",
        "You walk.",
        "",
        "For hours.",
        "Long enough that the tunnel warmth fades from your bones.",
        "Long enough that your stomach starts to ache.",
        "",
        "You pass intersections buried waist-deep in ash.",
        "Vehicles sit fused together, glass melted, metal warped.",
        "",
        "Skeletons sit behind steering wheels.",
        "Others lie collapsed in the streets, half-covered in grey powder.",
        "Some are nothing but ribs and a skull poking through the drift.",
        "",
        "You keep moving.",
        "Your steps grow slower. Your mouth dry.",
        "",
        "The city does not end.",
        "",
        "Then - smoke.",
        "",
        "Faint. Thin. Rising between towers.",
        "",
        "You follow it.",
        "",
        "A massive structure emerges from the haze.",
        "A mall.",
        "Outer walls reinforced with welded scrap and steel plates.",
        "Barricades choke the entrances.",
        "Real heat vents from the roof.",
        "",
        "Faded letters read: CONCOURSE.",
        "Painted over in white: NEW CONCOURSE.",
        "",
        "Food. Shelter. People.",
        "",
        "You move toward the entrance without thinking.",
        "",
        "\"Stop.\"",
        "",
        "A rifle is suddenly aimed at your chest.",
        "A lookout steps from behind a barricade of welded carts and car doors.",
        "More silhouettes shift along the upper level.",
        "",
        "\"State your business.\""
    };

        return desc;
    }


    public void play(GameContext gc) {
        int input;
        boolean success;
        printDescription(description());
        while (true) {
            System.out.println("======================================================================================================================================================");
            System.out.println("SPEECH");
            System.out.println("======================================================================================================================================================");
            System.out.println("[1] (Intimidate) - You better stop pointing that thing at me. I'm just here to resupply. Let me in, we trade, and no blood will be spilled.");
            System.out.println("[2] (Charisma) - I've been walking since morning. I haven't ate for a while. I'm not here to cause trouble - I just need to resupply.");
            System.out.println("[3] (Leave) - Easy there. I'm leaving. Don't shoot.");
            System.out.println("======================================================================================================================================================");
            input = scnr.nextInt();

            if (input == 1 || input == 2) {
                System.out.println("==================================================");
                if (speech(input, gc) == false) {
                    System.out.println("--------------------------------------------------");
                    System.out.println(">>SPEECH CHECK FAILED");
                    System.out.println("--------------------------------------------------");
                    fail();
                    success = false;
                    break;
                } else {
                    System.out.println("--------------------------------------------------");
                    System.out.println(">>SPEECH CHECK SUCCESSFUL");
                    System.out.println("--------------------------------------------------");
                    success();
                    success = true;
                }
                break;
            } else if (input == 3) {
                System.out.println("==================================================");
                System.out.println("You turn away from New Concourse.");
                System.out.println("Ash swallows your footprints as you return to the ruins.");
                System.out.println();
                System.out.println("You keep walking east.");
                System.out.println("==================================================");
                success = false;
                break;
            } else {
                System.out.println("--------------------------------------------------");
                System.out.println("Choose your next action");
                System.out.println("--------------------------------------------------");
                continue;
            }
        }

        if (success == false) {
            System.out.println(">> LEVEL ADVANCED");
            return;
        } else {
            while (true) {
                gc.control.inSettlement(gc, this.settlementArea);
            System.out.println("==================================================");
            System.out.print("You step away from the noise and warmth of New Concourse.\n");
            System.out.print("The hum of generators fades behind you as the heavy doors close with a dull metallic thud.\n\n");
            System.out.print("Cold air bites immediately.\n");
            System.out.print("Ash drifts across the cracked pavement outside.\n\n");
            System.out.print("Whatever safety the settlement offered is now behind you.\n");
            System.out.print("The surface stretches ahead — silent, grey, and unforgiving.\n");
            System.out.println("==================================================");
            break;
            }
            System.out.println(">> LEVEL ADVANCED");
        }
    }

    public boolean speech(int option, GameContext gc) {
        int base = 50;
        int buff;

        if (option == 1) {
            buff = 7 * gc.stats.getStrength();
        } else {
            buff = 7 * gc.stats.getCharisma();
        } 

        base += buff;
        return calc(base);
    }

    public boolean calc(int success) {
        Random random = new Random();
        int chance;
        chance = random.nextInt(100) + 1;
        if (chance <= success) {
            return true;
        } else {
            return false;
        }
    }

    public void fail() {
        System.out.println("The lookout doesn't move.");
        System.out.println("If anything, his grip tightens.");
        System.out.println();
        System.out.println("A voice calls down from above.");
        System.out.println("\"I'd suggest you keep moving.\"");
        System.out.println();
        System.out.println("You notice more movement along the upper levels.");
        System.out.println("Barrels shift behind sandbags. Shadows adjust behind cover.");
        System.out.println("You're outnumbered.");
        System.out.println();
        System.out.println("\"Don't make this something it doesn't need to be.\"");
        System.out.println();
        System.out.println("The barricade stays closed.");
        System.out.println("Rifles remain trained on you.");
        System.out.println();
        System.out.println("\"Turn around. Slowly.\"");
        System.out.println();
        System.out.println("You back away into the ash.");
        System.out.println("The mall fades behind you as you return to the ruined streets.");
        System.out.println();
        System.out.println("You keep walking east.");
    }

    public void success() {
        System.out.println("The lookout studies you for a long moment.");
        System.out.println("The rifle stays on you - but it doesn't fire.");
        System.out.println();
        System.out.println("A figure above gives a small hand signal.");
        System.out.println();
        System.out.println("Behind the barricade, metal grinds against metal.");
        System.out.println("Chains rattle. Something heavy is dragged aside.");
        System.out.println("The gate begins to open - slow, deliberate.");
        System.out.println();
        System.out.println("Metal scrapes as part of the barricade is pulled aside.");
        System.out.println("The rifle tracks you as you move.");
        System.out.println();
        System.out.println("\"Holster your weapons and slowly step inside, any sudden movements and you're dead.\"");
        System.out.println();
        System.out.println("You holster your weapon and step through the opening and into the mall.");
        System.out.println();
        System.out.println("Warm air hits you first.");
        System.out.println("The interior is dim, lit by strung cables and hanging work lamps.");
        System.out.println();
        System.out.println("Storefronts line the corridor, their original signs long torn down or painted over.");
        System.out.println("Metal shutters have been reinforced with scrap plates.");
        System.out.println("Makeshift stalls and barricades divide the central walkway.");
        System.out.println();
        System.out.println("People move carefully between them - armed, layered in winter gear, watching.");
        System.out.println();
        System.out.println("Above you, hanging from the ceiling where a polished banner once advertised sales,");
        System.out.println("a massive sheet of canvas has been suspended by chains.");
        System.out.println();
        System.out.println("Painted across it in thick white letters:");
        System.out.println("\"NEW CONCOURSE\"");
        System.out.println();
        System.out.println("The gate slams shut behind you.");
    }

    public String[] getDescription(int area) {
        String[] desc;
            switch (area) {
                case 1:
                    desc = new String[] {
                    "You step into Central Concourse.",
                    "",
                    "The ceiling arches high overhead, skylights patched with sheet metal.",
                    "Cold daylight spills down in muted grey shafts.",
                    "",
                    "Balconies above are reinforced with welded scrap.",
                    "Movement shifts behind barricades.",
                    "",
                    "The old information kiosk sits in the center.",
                    "Crates are stacked around it - canned beans, boxed ammunition, sealed antibiotics, cloth bundles.",
                    "",
                    "A stove burns quietly.",
                    "A woman works the stall with steady hands, sorting goods without looking rushed.",
                    "This is the settlement's trading floor."
                };
                break;

                case 2:
                    desc = new String[] {
                    "You move through a door marked SERVICE ACCESS.",
                    "The noise of the concourse dies behind you.",
                    "The air grows cooler.",
                    "The corridor narrows immediately.",
                    "",
                    "Concrete walls sweat with condensation.",
                    "Exposed wiring runs along the ceiling in bundled veins.",
                    "A reinforced door stands at the far end."
                };
                break;

                default:
                    desc = new String[] {"Unknown"};
                    break;
            }
        return desc;
    }

    public String[] getDescriptionVisited(int area) {
        String[] desc;
        switch (area) {
            case 1:
                desc = new String[] {
                    "You head back into Central Concourse.",
                    "",
                    "Muted daylight filters through patched skylights.",
                    "Movement shifts along the reinforced balconies.",
                    "Clara stands at the kiosk, sorting supplies.."
                };
                break;
            
            case 2:
                desc = new String[] {
                    "You return to the Service Corridor.",
                    "",
                    "The air still smells of oil and metal.",
                    "The reinforced door waits at the end."
                };
                break;

                default:
                    desc = new String[] {"Unknown"};
                    break;
            }

            return desc;
    }
}