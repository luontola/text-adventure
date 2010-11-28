package net.orfjackal.textadventure;

import net.orfjackal.textadventure.domain.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class ToolBenchUsageTest {

    private static Game oneRoomWithItemsForARobot() {
        Item legs = new Item("legs");
        Item torso = new Item("torso");
        Item head = new Item("head");
        Item toolBench = new ToolBench();
        return new Game(new Room("room").withItems(legs, torso, head, toolBench));
    }

    @Test
    public void tool_bench_can_be_used_to_make_a_robot() {
        Game game = oneRoomWithItemsForARobot();
        game.pickUp("legs");
        game.pickUp("torso");
        game.pickUp("head");

        assertThat("items owned before", game.namesOfItemsOwned(), containsInAnyOrder("legs", "torso", "head"));

        String message = game.use("tool bench");

        assertThat("message to player", message, containsString("used tool bench"));
        assertThat("items owned after", game.namesOfItemsOwned(), containsInAnyOrder("robot"));
    }

    @Test
    public void cannot_use_items_not_meant_to_be_used() {
        Game game = new Game(new Room("room").withItems(new Item("unusable thing")));

        String message = game.use("unusable thing");

        assertThat("message to player", message, containsString("cannot use unusable thing"));
    }

    @Test
    public void cannot_use_items_not_in_the_room() {
        Game game = new Game(new Room("room"));

        String message = game.use("tool bench");

        assertThat("message to player", message, containsString("no tool bench"));
    }

    @Test
    public void cannot_use_tool_bench_when_missing_legs() {
        Game game = oneRoomWithItemsForARobot();
        game.pickUp("torso");
        game.pickUp("head");

        String message = game.use("tool bench");

        assertThat("message to player", message, containsString("need legs"));
        assertThat("doesn't make a robot", game.namesOfItemsOwned(), not(contains("robot")));
    }

    @Test
    public void cannot_use_tool_bench_when_missing_torso() {
        Game game = oneRoomWithItemsForARobot();
        game.pickUp("legs");
        game.pickUp("head");

        String message = game.use("tool bench");

        assertThat("message to player", message, containsString("need torso"));
        assertThat("doesn't make a robot", game.namesOfItemsOwned(), not(contains("robot")));
    }

    @Test
    public void cannot_use_tool_bench_when_missing_head() {
        Game game = oneRoomWithItemsForARobot();
        game.pickUp("legs");
        game.pickUp("torso");

        String message = game.use("tool bench");

        assertThat("message to player", message, containsString("need head"));
        assertThat("doesn't make a robot", game.namesOfItemsOwned(), not(contains("robot")));
    }
}
