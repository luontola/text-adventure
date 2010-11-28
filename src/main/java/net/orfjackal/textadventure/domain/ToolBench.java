package net.orfjackal.textadventure.domain;

/**
 * @author Esko Luontola
 * @since 28.11.2010
 */
public class ToolBench extends Item {

    public ToolBench() {
        super("tool bench");
    }

    @Override
    public boolean isUsable() {
        return true;
    }

    @Override
    public String useOn(Inventory player) {
        if (!player.hasItemNamed("legs")) {
            return "First you need legs.";
        }
        if (!player.hasItemNamed("torso")) {
            return "First you need torso.";
        }
        if (!player.hasItemNamed("head")) {
            return "First you need head.";
        }
        // TODO: use objects instead of the item names
        player.takeItemNamed("legs");
        player.takeItemNamed("torso");
        player.takeItemNamed("head");
        player.putItem(new Item("robot"));
        return "You used tool bench to create a robot from your other items.";
    }
}
