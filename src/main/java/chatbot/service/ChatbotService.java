package chatbot.service;

//import chatbot.PowerShellChatbot;
import org.springframework.stereotype.Service;
import chatbot.CommandHandler;
import chatbot.ResponseGenerator;
import chatbot.OpenAIClient;

import java.io.IOException;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;

@Service
public class ChatbotService {
/* The constructor takes three parameters (CommandHandler, ResponseGenerator, and OpenAIClient), representing the dependencies required by this class.
These dependencies are injected when an instance of ChatbotService is created.
The injected dependencies are assigned to the corresponding private fields.
These fields are then available to be used throughout the class.
 */
    private final CommandHandler commandHandler; //final keyword ensures these fields are immutable
    private final ResponseGenerator responseGenerator;
    private final OpenAIClient openAIClient;

    public ChatbotService(CommandHandler commandHandler, ResponseGenerator responseGenerator, OpenAIClient openAIClient) {
        this.commandHandler = commandHandler;
        this.responseGenerator = responseGenerator;
        this.openAIClient = openAIClient;
    }

    public String getChatResponse(String userInput) {
        // Process user input using CommandHandler
        String command = commandHandler.handleCommand(userInput);

        // If CommandHandler returns a match, generate a response using ResponseGenerator
        if (command != null) {
            return responseGenerator.generateResponse(command);
        }

        // Otherwise, invoke OpenAIClient for assistance
        try {
            return openAIClient.getAIResponse(userInput);
        } catch (IOException e) {
            return "Sorry, there was an error connecting to the AI service.";
        }
    }
}






