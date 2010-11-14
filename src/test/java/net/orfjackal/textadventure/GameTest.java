package net.orfjackal.textadventure;

import org.junit.Test;

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
}
