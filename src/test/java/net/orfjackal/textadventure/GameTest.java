package net.orfjackal.textadventure;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class GameTest {

    @Test
    public void full_story() {
        Room room1 = new Room("You are in a big room.").withItems("legs");
        Room room2 = new Room("You are in a small room.").withItems("torso", "head").eastOf(room1);
        Room room3 = new Room("You are in a factory.").southOf(room2);
        Game world = new Game(room1);

        GameRunner game = new GameRunner(world);

        game.showsDescription("You are in a big room.");
        game.canMoveTo("east");
        game.roomHas("legs");
        game.youHave();

        game.pickUp("legs");
        game.roomHas();
        game.youHave("legs");

//        game.move("east");
//        game.showsDescription("You are in a small room.");
//        game.canMoveTo("west", "south");
//        game.roomHas("torso", "head");
//
//        game.pickUp("torso");
//        game.pickUp("head");
//        game.youHave("legs", "torso", "head");
//
//        game.move("south");
//        game.showsDescription("You are in a factory.");
//        game.canMoveTo("north");
//        game.roomHas("toolbench");
//
//        game.use("toolbench");
//        game.youHave("robot");
//
//        game.theEnd();
    }

    @Test
    public void possible_directions_of_horizontally_adjacent_rooms() {
        Room room1 = new Room("room1");
        Room room2 = new Room("room2").eastOf(room1);

        assertThat(room1.possibleDirections(), contains("east"));
        assertThat(room2.possibleDirections(), contains("west"));
    }

    @Test
    public void possible_directions_of_vertically_adjacent_rooms() {
        Room room1 = new Room("room1");
        Room room2 = new Room("room2").southOf(room1);

        assertThat(room1.possibleDirections(), contains("south"));
        assertThat(room2.possibleDirections(), contains("north"));
    }

    @Test
    public void picking_up_an_item_removes_it_from_the_room() {
        Game game = new Game(new Room("room").withItems("item"));

        assertThat("room before", game.namesOfItemsInCurrentRoom(), contains("item"));
        assertThat("owned before", game.namesOfItemsOwned(), not(contains("item")));

        String message = game.pickUp("item");

        assertThat("message to player", message, containsString("picked up item"));
        assertThat("room after", game.namesOfItemsInCurrentRoom(), not(contains("item")));
        assertThat("owned after", game.namesOfItemsOwned(), contains("item"));
    }

    @Test
    public void cannot_pick_items_which_are_not_in_the_room() {
        Game game = new Game(new Room("room"));

        assertThat("owned before", game.namesOfItemsOwned(), not(contains("item")));

        String message = game.pickUp("item");

        assertThat("message to player", message, containsString("no item"));
        assertThat("owned after", game.namesOfItemsOwned(), not(contains("item")));
    }
}
