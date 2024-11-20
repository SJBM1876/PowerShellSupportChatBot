package chatbot;

import java.io.IOException;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service

public class PowerShellChatbot {
    private final CommandHandler commandHandler;
    private final ResponseGenerator responseGenerator;
    private final OpenAIClient openAIClient;

    public PowerShellChatbot() {
        this.commandHandler = new CommandHandler();
        this.responseGenerator = new ResponseGenerator();
        this.openAIClient = new OpenAIClient();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(responseGenerator.generateHelpMessage());

        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println(responseGenerator.generateExitMessage());
                break;
            }

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

            System.out.println(response);
        }
        scanner.close();
    }
}











