package net.orfjackal.textadventure;

import java.util.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class Game {

    private Room currentRoom;
    private final List<String> itemsPlayerHas = new ArrayList<String>();

    public Game(Room startingRoom) {
        this.currentRoom = startingRoom;
    }

    public String descriptionOfCurrentRoom() {
        return currentRoom.getDescription();
    }

    public Collection<String> possibleDirections() {
        return currentRoom.possibleDirections();
    }

    public String moveTo(String direction) {
        Room next = currentRoom.roomTo(direction);
        if (next != null) {
            currentRoom = next;
            return "You moved east.";
        } else {
            return "There is a wall in south and it blocks your way.";
        }
    }

    public List<String> namesOfItemsInCurrentRoom() {
        return currentRoom.namesOfItemsInRoom();
    }

    public List<String> namesOfItemsOwned() {
        return Collections.unmodifiableList(itemsPlayerHas);
    }

    public String pickUp(String item) {
        String pickedUp = currentRoom.takeItem(item);
        if (pickedUp != null) {
            itemsPlayerHas.add(pickedUp);
            return "You picked up " + item + ".";
        } else {
            return "There is no " + item + ".";
        }
    }

    public String use(String item) {
        // TODO: extract tool bench specific stuff
        if (!namesOfItemsInCurrentRoom().contains(item)) {
            return "There is no " + item + ".";
        }
        if (!item.equals("tool bench")) {
            return "You cannot use " + item + ".";
        }
        if (!namesOfItemsOwned().contains("legs")) {
            return "First you need legs.";
        }
        if (!namesOfItemsOwned().contains("torso")) {
            return "First you need torso.";
        }
        if (!namesOfItemsOwned().contains("head")) {
            return "First you need head.";
        }
        itemsPlayerHas.remove("legs");
        itemsPlayerHas.remove("torso");
        itemsPlayerHas.remove("head");
        itemsPlayerHas.add("robot");
        return "You used tool bench to create a robot from your other items.";
    }

    public boolean hasEnded() {
        return false;
    }
}
