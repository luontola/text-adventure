package net.orfjackal.textadventure;

import java.util.*;

/**
 * @author Esko Luontola
 * @since 28.11.2010
 */
public class Inventory {

    private final Map<String, Item> inventory = new HashMap<String, Item>();

    public void putItem(Item item) {
        inventory.put(item.getName(), item);
    }

    /**
     * Returns the item, or null if doesn't have it.
     */
    public Item takeItemNamed(String name) {
        return inventory.remove(name);
    }

    public boolean hasItemNamed(String name) {
        return inventory.containsKey(name);
    }

    public Collection<String> namesOfItems() {
        return Collections.unmodifiableCollection(inventory.keySet());
    }
}
