// Copyright © 2010, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package net.orfjackal.textadventure.domain;

import javax.annotation.Nullable;
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

    @Nullable
    public Item getItemNamed(String name) {
        return inventory.get(name);
    }

    @Nullable
    public Item takeItemNamed(String name) {
        return inventory.remove(name);
    }

    public void removeItem(Item item) {
        boolean removed = inventory.values().remove(item);
        if (!removed) {
            throw new IllegalArgumentException("Did not have item: " + item);
        }
    }

    public boolean hasItemNamed(String name) {
        return inventory.containsKey(name);
    }

    public Collection<String> namesOfItems() {
        return Collections.unmodifiableCollection(inventory.keySet());
    }
}
