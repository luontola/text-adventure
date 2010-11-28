// Copyright © 2010, Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package net.orfjackal.textadventure;

import net.orfjackal.textadventure.domain.Inventory;

/**
 * @author Esko Luontola
 * @since 28.11.2010
 */
public interface EndingCondition {

    boolean hasEnded(Inventory player);
}
