package net.orfjackal.textadventure;

import java.util.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class Room {

    private final String description;
    private final List<String> possibleDirections = new ArrayList<String>();
    private final List<String> items = new ArrayList<String>();

    public Room(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Room withItems(String... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public List<String> namesOfItemsInRoom() {
        return Collections.unmodifiableList(items);
    }

    public Room eastOf(Room other) {
        // TODO: add opposite direction to this room
        other.possibleDirections.add("east");
        return this;
    }

    public Room southOf(Room other) {
        return this;
    }

    public List<String> possibleDirections() {
        return Collections.unmodifiableList(possibleDirections);
    }

    public String takeItem(String item) {
        // TODO: do not remove items not in the room
        items.remove(item);
        return item;
    }
}
