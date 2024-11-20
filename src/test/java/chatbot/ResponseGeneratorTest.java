package chatbot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ResponseGeneratorTest {

    private ResponseGenerator responseGenerator = new ResponseGenerator();

    @Test
    public void testGenerateResponse_validCommand() {
        String command = "Get-MsolUser -UserPrincipalName <user>@domain.com";
        String result = responseGenerator.generateResponse(command);
        assertTrue(result.contains("Here's the PowerShell command"), "The response should contain a command message.");
    }

    @Test
    public void testGenerateResponse_invalidCommand() {
        String result = responseGenerator.generateResponse("");
        assertEquals("Hmm, I couldn't find a matching PowerShell command for that. Could you try again?", result);
    }

    @Test
    public void testGenerateHelpMessage() {
        String result = responseGenerator.generateHelpMessage();
        assertTrue(result.contains("I'm here to assist"), "The help message should contain an introductory sentence.");
    }

    @Test
    public void testGenerateExitMessage() {
        String result = responseGenerator.generateExitMessage();
        assertTrue(result.contains("Thank you for using the PowerShell Chatbot!"), "The exit message should contain a thank-you note.");
    }
}
