package chatbot.service;

import chatbot.PowerShellChatbot;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Service
public class ChatbotService {

    private final PowerShellChatbot chatbot;

    public ChatbotService() {
        this.chatbot = new PowerShellChatbot();
    }

    public String getChatResponse(String userInput) {
        // Redirect user input to the chatbot
        try {
            // Simulate user input using ByteArrayInputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream((userInput + "\nexit\n").getBytes());
            System.setIn(inputStream);

            // Capture chatbot output using ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            // Start the chatbot (this will process the input and then exit)
            chatbot.start();

            // Return the chatbot's response as a string
            return outputStream.toString();
        } finally {
            // Restore the original System input/output streams
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }
}
