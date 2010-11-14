package net.orfjackal.textadventure;

import java.util.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class Room {

    private final String description;
    private final Map<String, Room> possibleDirections = new HashMap<String, Room>();
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

    public String takeItem(String item) {
        if (items.remove(item)) {
            return item;
        }
        return null;
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
