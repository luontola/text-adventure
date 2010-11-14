package net.orfjackal.textadventure;

import java.util.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class TextUI {

    public static void main(String[] args) {
        Room room1 = new Room("You are in a big room.").withItems("legs");
        Room room2 = new Room("You are in a small room.").withItems("torso", "head").eastOf(room1);
        Room room3 = new Room("You are in a factory.").withItems("tool bench").southOf(room2);
        Game game = new Game(room1);
        CommandParser parser = new CommandParser(game);

        Scanner in = new Scanner(System.in);

        while (!game.hasEnded()) {
            System.out.println(game.descriptionOfCurrentRoom());

            printList("The room contains", game.namesOfItemsInCurrentRoom());
            printList("There are doors towards", game.possibleDirections());
            printList("You have", game.namesOfItemsOwned());

            System.out.print("> ");
            String message = parser.parse(in.nextLine().trim());
            System.out.println(message);
            System.out.println();
        }
    }

    private static void printList(String title, Collection<String> items) {
        if (!items.isEmpty()) {
            System.out.print(title + ": ");
            boolean first = true;
            for (String item : items) {
                if (!first) {
                    System.out.print(", ");
                } else {
                    first = false;
                }
                System.out.print(item);
            }
            System.out.println();
        }
    }
}
