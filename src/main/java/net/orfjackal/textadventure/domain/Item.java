// Copyright © 2010, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package net.orfjackal.textadventure.domain;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class Item {

    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String useOn(Inventory player) {
        return "You cannot use " + getName() + ".";
    }

    @Override
    public boolean equals(Object other) {
        Item that = (Item) other;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
