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

    public void move(String direction) {
    }

    public List<String> namesOfItemsInRoom() {
        return currentRoom.namesOfItemsInRoom();
    }

    public List<String> namesOfItemsOwned() {
        return Collections.unmodifiableList(itemsPlayerHas);
    }

    public void pickUp(String item) {
        String pickedUp = currentRoom.takeItem(item);
        itemsPlayerHas.add(pickedUp);
    }

    public void use(String item) {
    }

    public boolean hasEnded() {
        return false;
    }
}
