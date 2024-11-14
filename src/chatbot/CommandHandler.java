package chatbot;

import java.util.Scanner;

public class PowerShellChatbot {
    private final CommandHandler commandHandler; // Handles user input related to PowerShell commands
    private final ResponseGenerator responseGenerator; // Generates friendly responses for the chatbot

    // Constructor: Initializes the CommandHandler and ResponseGenerator classes
    public PowerShellChatbot() {
        this.commandHandler = new CommandHandler();
        this.responseGenerator = new ResponseGenerator();
    }

    // This method starts the chatbot and handles the interaction with the user
    public void start() {
        Scanner scanner = new Scanner(System.in); // Used to read user input from the console
        System.out.println(responseGenerator.generateHelpMessage()); // Show help message when the chatbot starts

        // Infinite loop to continuously accept user input until the user decides to exit
        while (true) {
            System.out.print("> "); // Prompt for user input
            String userInput = scanner.nextLine(); // Read a line of input from the user

            // Check if the user wants to exit the chatbot
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println(responseGenerator.generateExitMessage()); // Show a farewell message
                break; // Exit the loop, ending the chatbot session
            }

            // Handle the user's input using the CommandHandler
            String command = commandHandler.handleCommand(userInput);

            // Generate a response using the ResponseGenerator
            String response = responseGenerator.generateResponse(command);

            // Display the generated response to the user
            System.out.println(response);
        }

        // Close the Scanner object to release resources
        scanner.close();
    }
}


