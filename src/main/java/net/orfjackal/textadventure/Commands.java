package net.orfjackal.textadventure;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public interface Commands {

    String move(String direction);

    String pickUp(String item);

    String use(String item);
}
