package net.orfjackal.textadventure;

import java.util.Collection;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class Game implements Commands {

    private Room currentRoom;
    private final Inventory player = new Inventory();

    public Game(Room startingRoom) {
        this.currentRoom = startingRoom;
    }

    public String descriptionOfCurrentRoom() {
        return currentRoom.getDescription();
    }

    public Collection<String> possibleDirections() {
        return currentRoom.possibleDirections();
    }

    public String move(String direction) {
        Room next = currentRoom.roomTo(direction);
        if (next != null) {
            currentRoom = next;
            return "You moved east.";
        } else {
            return "There is a wall in south and it blocks your way.";
        }
    }

    public Collection<String> namesOfItemsInCurrentRoom() {
        return currentRoom.namesOfItemsInRoom();
    }

    public Collection<String> namesOfItemsOwned() {
        return player.namesOfItems();
    }

    public String pickUp(String itemName) {
        Item pickedUp = currentRoom.takeItem(itemName);
        if (pickedUp != null) {
            player.putItem(pickedUp);
            return "You picked up " + itemName + ".";
        } else {
            return "There is no " + itemName + ".";
        }
    }

    public String use(String itemName) {
        // TODO: extract tool bench specific stuff
        if (!namesOfItemsInCurrentRoom().contains(itemName)) {
            return "There is no " + itemName + ".";
        }
        if (!itemName.equals("tool bench")) {
            return "You cannot use " + itemName + ".";
        }
        if (!player.hasItemNamed("legs")) {
            return "First you need legs.";
        }
        if (!player.hasItemNamed("torso")) {
            return "First you need torso.";
        }
        if (!player.hasItemNamed("head")) {
            return "First you need head.";
        }
        player.takeItemNamed("legs");
        player.takeItemNamed("torso");
        player.takeItemNamed("head");
        player.putItem(new Item("robot"));
        return "You used tool bench to create a robot from your other items.";
    }

    public boolean hasEnded() {
        // TODO: extract ending condition
        return player.hasItemNamed("robot");
    }
}
