package chatbot;

import java.io.IOException;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class PowerShellChatbot {

    private final CommandHandler commandHandler;
    private final ResponseGenerator responseGenerator;
    private final OpenAIClient openAIClient;

    public PowerShellChatbot(CommandHandler commandHandler, ResponseGenerator responseGenerator, OpenAIClient openAIClient) {
        this.commandHandler = commandHandler;
        this.responseGenerator = responseGenerator;
        this.openAIClient = openAIClient;
    }

    public String getResponse(String userInput) {
        // Check if the user input matches a PowerShell command
        String command = commandHandler.handleCommand(userInput);
        String response;

        if (command != null) {
            response = responseGenerator.generateResponse(command);
        } else {
            // Get a response from OpenAI's API if no PowerShell command matches
            try {
                response = openAIClient.getAIResponse(userInput);
            } catch (IOException e) {
                response = "Sorry, there was an error connecting to the AI service.";
            }
        }

        return response;
    }
}













