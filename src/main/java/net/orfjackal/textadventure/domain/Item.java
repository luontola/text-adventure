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
