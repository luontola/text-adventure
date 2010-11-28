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
        try {
            Item[] robotParts = {
                    requireItem("legs", player),
                    requireItem("torso", player),
                    requireItem("head", player),
            };
            Item robot = buildRobotOutOf(robotParts, player);
            return "You used " + this.getName() + " to create a " + robot.getName() + " from your other items.";

        } catch (RequiredItemMissingException e) {
            return "First you need " + e.getItemName() + ".";
        }
    }

    private static Item requireItem(String name, Inventory player) throws RequiredItemMissingException {
        Item item = player.getItemNamed(name);
        if (item == null) {
            throw new RequiredItemMissingException(name);
        }
        return item;
    }

    private static Item buildRobotOutOf(Item[] robotParts, Inventory player) {
        for (Item item : robotParts) {
            player.removeItem(item);
        }
        Item robot = new Item("robot");
        player.putItem(robot);
        return robot;
    }

    private static class RequiredItemMissingException extends Exception {
        private final String itemName;

        public RequiredItemMissingException(String itemName) {
            this.itemName = itemName;
        }

        public String getItemName() {
            return itemName;
        }
    }
}
