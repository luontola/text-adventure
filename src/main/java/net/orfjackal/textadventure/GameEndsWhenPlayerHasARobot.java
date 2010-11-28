package net.orfjackal.textadventure;

import net.orfjackal.textadventure.domain.Inventory;

/**
 * @author Esko Luontola
 * @since 28.11.2010
 */
public class GameEndsWhenPlayerHasARobot implements EndingCondition {

    public boolean hasEnded(Inventory player) {
        return player.hasItemNamed("robot");
    }
}
