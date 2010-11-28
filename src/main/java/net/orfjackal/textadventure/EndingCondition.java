package net.orfjackal.textadventure;

import net.orfjackal.textadventure.domain.Inventory;

/**
 * @author Esko Luontola
 * @since 28.11.2010
 */
public interface EndingCondition {

    boolean hasEnded(Inventory player);
}
