package net.orfjackal.textadventure;

import net.orfjackal.textadventure.domain.*;

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
        Item item = currentRoom.getItem(itemName);
        if (item == null) {
            return "There is no " + itemName + ".";
        }
        if (!item.isUsable()) {
            return "You cannot use " + itemName + ".";
        }
        return item.useOn(player);
    }

    public boolean hasEnded() {
        // TODO: extract ending condition
        return player.hasItemNamed("robot");
    }
}
