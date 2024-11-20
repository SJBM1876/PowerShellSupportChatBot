package chatbot;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PowerShellChatbotTest {

    @Test
    void testHelpMessage() {
        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Start chatbot with "exit" to quickly end interaction
        ByteArrayInputStream inputStream = new ByteArrayInputStream("exit\n".getBytes());
        System.setIn(inputStream);

        PowerShellChatbot chatbot = new PowerShellChatbot();
        chatbot.start();

        String output = outputStream.toString();

        // Check that the help message appears in the output
        assertTrue(output.contains("I'm here to assist with PowerShell commands related to Microsoft 365!"));
    }

    @Test
    void testExitMessage() {
        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Start chatbot with "exit" to quickly end interaction
        ByteArrayInputStream inputStream = new ByteArrayInputStream("exit\n".getBytes());
        System.setIn(inputStream);

        PowerShellChatbot chatbot = new PowerShellChatbot();
        chatbot.start();

        String output = outputStream.toString();

        // Check that the exit message appears in the output
        assertTrue(output.contains("Thank you for using the PowerShell Chatbot!"));
    }

    @Test
    void testHandleKnownCommand() {
        // Simulate input for a known command
        ByteArrayInputStream inputStream = new ByteArrayInputStream("get user\nexit\n".getBytes());
        System.setIn(inputStream);

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PowerShellChatbot chatbot = new PowerShellChatbot();
        chatbot.start();

        String output = outputStream.toString();

        // Check that the correct PowerShell command is returned
        assertTrue(output.contains("Here's the PowerShell command you can use:\nGet-MsolUser -UserPrincipalName <user>@domain.com"));
    }

    @Test
    void testHandleUnknownCommand() {
        // Simulate input for an unknown command
        ByteArrayInputStream inputStream = new ByteArrayInputStream("random input\nexit\n".getBytes());
        System.setIn(inputStream);

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PowerShellChatbot chatbot = new PowerShellChatbot();
        chatbot.start();

        String output = outputStream.toString();

        // Check that OpenAI fallback or error message is displayed
        assertFalse(output.contains("Sorry, there was an error connecting to the AI service.") || output.contains("Here's the PowerShell command you can use:"));
    }
}