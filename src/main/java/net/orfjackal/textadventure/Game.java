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

    public List<String> possibleDirections() {
        return currentRoom.possibleDirections();
    }

    public String move(String direction) {
        return "";
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
        return "";
    }

    public boolean hasEnded() {
        return false;
    }
}
