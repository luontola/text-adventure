package net.orfjackal.textadventure;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Esko Luontola
 * @since 14.11.2010
 */
public class CommandParserTest {

    private static final String MOVE_RESPONSE = "move response";
    private static final String PICK_UP_RESPONSE = "pick up response";
    private static final String USE_RESPONSE = "use response";

    private final Commands target = mock(Commands.class);
    private final CommandParser parser = new CommandParser(target);

    @Before
    public void stubs() {
        stub(target.move(anyString())).toReturn(MOVE_RESPONSE);
        stub(target.pickUp(anyString())).toReturn(PICK_UP_RESPONSE);
        stub(target.use(anyString())).toReturn(USE_RESPONSE);
    }

    @Test
    public void parses_move() {
        String message = parser.parse("move east");

        verify(target).move("east");
        assertThat(message, is(MOVE_RESPONSE));
    }

    @Test
    public void parses_taking() {
        String message = parser.parse("take some item");

        verify(target).pickUp("some item");
        assertThat(message, is(PICK_UP_RESPONSE));
    }

    @Test
    public void parses_using() {
        String message = parser.parse("use some item");

        verify(target).use("some item");
        assertThat(message, is(USE_RESPONSE));
    }

    @Test
    public void unknown_command() {
        String message = parser.parse("foo bar");

        verifyNoMoreInteractions(target);
        assertThat(message, containsString("foo bar"));
    }

    @Test
    public void too_short_command() {
        String message = parser.parse("oneword");

        verifyNoMoreInteractions(target);
        assertThat(message, containsString("oneword"));
    }
}
