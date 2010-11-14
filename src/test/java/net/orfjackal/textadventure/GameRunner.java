package net.orfjackal.textadventure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class GameRunner {
    private final Game game;

    public GameRunner(Game game) {
        this.game = game;
    }

    public void showsDescription(String description) {
        assertFalse("game should be running", game.hasEnded());
        assertThat(game.descriptionOfCurrentRoom(), is(description));
    }

    public void canMoveTo(String... directions) {
        assertThat(game.possibleDirections(), containsInAnyOrder(directions));
    }

    public void move(String direction) {
        game.moveTo(direction);
    }

    public void roomHas(String... items) {
        assertThat(game.namesOfItemsInCurrentRoom(), containsInAnyOrder(items));
    }

    public void youHave(String... items) {
        assertThat(game.namesOfItemsOwned(), containsInAnyOrder(items));
    }

    public void pickUp(String item) {
        game.pickUp(item);
    }

    public void use(String item) {
        game.use(item);
    }

    public void theEnd() {
        assertTrue("game should have ended", game.hasEnded());
    }
}
