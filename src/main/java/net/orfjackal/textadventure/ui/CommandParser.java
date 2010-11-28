package net.orfjackal.textadventure.ui;

import net.orfjackal.textadventure.Commands;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class CommandParser {

    private final Commands target;

    public CommandParser(Commands target) {
        this.target = target;
    }

    public String parse(String command) {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2) {
            return "Unknown command: " + command;
        }

        // TODO: remove duplication between commands
        String keyword = parts[0];
        String parameter = parts[1];
        if (keyword.equals("move")) {
            return target.move(parameter);
        }
        if (keyword.equals("take")) {
            return target.pickUp(parameter);
        }
        if (keyword.equals("use")) {
            return target.use(parameter);
        }
        return "Unknown command: " + command;
    }

    // TODO: help text for all command
}
