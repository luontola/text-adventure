package net.orfjackal.textadventure.domain;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class Room {

    private final String description;
    private final Map<String, Room> possibleDirections = new HashMap<String, Room>();
    private final Inventory room = new Inventory();

    public Room(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Room withItems(Item... items) {
        for (Item item : items) {
            room.putItem(item);
        }
        return this;
    }

    public Collection<String> namesOfItemsInRoom() {
        return room.namesOfItems();
    }

    @Nullable
    public Item getItem(String name) {
        return room.getItemNamed(name);
    }

    @Nullable
    public Item takeItem(String name) {
        return room.takeItemNamed(name);
    }

    public Room eastOf(Room that) {
        that.possibleDirections.put("east", this);
        this.possibleDirections.put("west", that);
        return this;
    }

    public Room southOf(Room that) {
        that.possibleDirections.put("south", this);
        this.possibleDirections.put("north", that);
        return this;
    }

    public Collection<String> possibleDirections() {
        return Collections.unmodifiableCollection(possibleDirections.keySet());
    }

    public Room roomTo(String direction) {
        return possibleDirections.get(direction);
    }
}
