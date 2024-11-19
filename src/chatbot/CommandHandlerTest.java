package chatbot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHandlerTest {

    private CommandHandler commandHandler;

    @BeforeEach
    public void setUp() {
        commandHandler = new CommandHandler();
    }

    @Test
    public void testHandleCommand_validInput() {
        String input = "get user";
        String expected = "Get-MsolUser -UserPrincipalName <user>@domain.com";
        String result = commandHandler.handleCommand(input);
        assertEquals(expected, result, "The PowerShell command should match.");
    }

    @Test
    public void testHandleCommand_invalidInput() {
        String input = "unknown command";
        String result = commandHandler.handleCommand(input);
        assertNull(result, "The result should be null for an unrecognized command.");
    }

    @Test
    public void testHandleCommand_partialMatch() {
        String input = "list groups";
        String expected = "Get-MsolGroup";
        String result = commandHandler.handleCommand(input);
        assertEquals(expected, result, "The PowerShell command should partially match.");
    }
}
