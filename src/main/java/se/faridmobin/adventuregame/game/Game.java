package se.faridmobin.adventuregame.game;

import se.faridmobin.adventuregame.models.Resident;
import se.faridmobin.adventuregame.models.Burglar;
import java.util.Scanner;

public class Game {
    private Resident resident;
    private Burglar burglar;
    private boolean fryingPanFound = false;
    private boolean hasFryingPan = false;
    private boolean running = true;

    public Game() {
        resident = new Resident();
        burglar = new Burglar();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Du sover på soffan i vardagsrummet. Du hör ett konstigt ljud och vaknar till. \nDu kollar runt men ser inget. Du vill sova igen men du hör ljudet igen...");

        while (running && resident.isConscious()) {
            System.out.println("\nDu är i vardagsrummet. Vad ska du göra?");
            System.out.println("Välj en av följande och tryck på retur: gå till köket, gå till hallen, gå till kontoret, gå till sovrummet, avsluta");
            String action = scanner.nextLine();

            switch (action) {
                case "gå till köket":
                    goToKitchen(scanner);
                    break;
                case "gå till hallen":
                    goToHall(scanner);
                    break;
                case "gå till kontoret":
                    goToOffice(scanner);
                    break;
                case "gå till sovrummet":
                    goToBedroom();
                    break;
                case "avsluta":
                    System.out.println("Du har avslutat spelet.");
                    running = false;
                    return;
                default:
                    System.out.println("Ogiltigt val. Var god skriv valet igen.");
            }
        }

        if (resident.isConscious() && running) {
            System.out.println("Grattis! DU har vunnit!");
        }
    }

    private void goToKitchen(Scanner scanner) {
        if (!fryingPanFound) {
            System.out.println("Du ser stekpannan på spisen. Vill du plocka den? (ja/nej)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("ja")) {
                hasFryingPan = true;
                fryingPanFound = true;
                resident.addDamage(3);
                System.out.println("Du har plockat stekpannan. Din anfallsstyrka höjs med 3");
            } else {
                System.out.println("Du ignorerar stek pannan och fortsätter");
            }
        } else {
            System.out.println("Det finns inget märkvärdigt i köket.");
        }
    }

    private void goToHall(Scanner scanner) {
        if (burglar.isConscious()) {
            System.out.println("Du ser en tjuv med en ficklampa och en kniv i handen! Han har en mask! \n Ni båda är chockade! Ett bråk börjar...");
            startFight(scanner);
        } else {
            System.out.println("Du manglade sönder tjuven och har lärt honom en läxa.");
        }
    }

    private void goToOffice(Scanner scanner) {
        if (!burglar.isConscious()) {
            System.out.println("Din telefon är på bordet. Vill du ringa polisen? (ja/nej)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("ja")) {
                System.out.println("Du ringer polisen. Du kommer snart och griper tjuven");
                System.out.println("Spelet är slut. Du har vunnit!");
                running = false;
            } else {
                System.out.println("Du ska inte ringa någon än.");
            }
        } else {
            System.out.println("Du ska inte ringa någon än.");
        }
    }

    private void goToBedroom() {
        System.out.println("Du är i Sovrummet. Det finns ingenting märkvärdigt här.");
    }

    private void startFight(Scanner scanner) {
        while (resident.isConscious() && burglar.isConscious()) {
            System.out.println("\nVill du attackera tjuven nu? (ja/nej)");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("ja")) {
                System.out.println("Boendet attackerar tjuven!");
                resident.punch(burglar);
            } else {
                System.out.println("Du är fortfarande chockad. Du vet inte vad du ska göra...Tjuven attackerar dig!");
            }

            if (burglar.isConscious()) {
                System.out.println("Tjuven attackerar Boendet!");
                burglar.punch(resident);
            }

            System.out.println("Boendets hälsa: " + resident.getHealth());
            System.out.println("tjuvens hälsa " + burglar.getHealth());
        }

        if (!resident.isConscious()) {
            System.out.println("Du förlurade slaget. tjuven tar alla dina egendomar och rymmer. Spelet är slut!");
            running = false;
        } else {
            System.out.println("Du manglade sönder tjuven!");
        }
    }
}
