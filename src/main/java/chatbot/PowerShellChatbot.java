package chatbot;

import java.io.IOException;
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

    /**
     * Processes user input and returns a chatbot response.
     *
     * @param userInput The input received from the user.
     * @return A response to display in the frontend.
     */
    public String processInput(String userInput) {
        // Trim and check if the input is "exit"
        if ("exit".equalsIgnoreCase(userInput.trim())) {
            return responseGenerator.generateExitMessage();
        }

        // Process other inputs
        String command = commandHandler.handleCommand(userInput);
        if (command != null) {
            return responseGenerator.generateResponse(command);
        } else {
            try {
                return openAIClient.getAIResponse(userInput);
            } catch (IOException e) {
                return "Sorry, there was an error connecting to the AI service.";
            }
        }
    }

    /**
     * Provides an initial help message when the chatbot is loaded.
     *
     * @return The help message.
     */
    public String getWelcomeMessage() {
        return responseGenerator.generateHelpMessage();
    }

    /**
     * Generates a response based on user input.
     *
     * @param userInput The input received from the user.
     * @return A chatbot response.
     */
    public String getResponse(String userInput) {
        // Check for "exit" command first
        if ("exit".equalsIgnoreCase(userInput.trim())) {
            return responseGenerator.generateExitMessage();
        }

        // Check for PowerShell command match
        String command = commandHandler.handleCommand(userInput);
        if (command != null) {
            return responseGenerator.generateResponse(command);
        }

        // Fall back to AI response if no match is found
        try {
            return openAIClient.getAIResponse(userInput);
        } catch (IOException e) {
            return "Sorry, there was an error connecting to the AI service.";
        }
    }
}
















