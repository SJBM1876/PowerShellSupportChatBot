package chatbot;

// Importing the Scanner class for reading user input from the console
import java.util.Scanner;

public class PowerShellChatbot {
    // Declaring a private final instance of CommandHandler
    // This will be used to process user commands
    private final CommandHandler commandHandler;

    // Constructor for PowerShellChatbot
    // It initializes the commandHandler instance
    public PowerShellChatbot() {
        // Creating a new CommandHandler object to handle PowerShell commands
        this.commandHandler = new CommandHandler();
    }

    // Method to start the chatbot interaction with the user
    public void start() {
        // Creating a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
        // Displaying a welcome message to the user
        System.out.println("How can I help you with PowerShell today?");

        // Starting an infinite loop to keep the chatbot running until the user decides to exit
        while (true) {
            // Prompting the user for input
            System.out.print("> ");

            // Reading the user's input as a line of text
            String userInput = scanner.nextLine();

            // Checking if the user wants to exit the chatbot
            if (userInput.equalsIgnoreCase("exit")) {
                // If the user types "exit", print a goodbye message and break the loop
                System.out.println("Goodbye!");
                break; // Exits the while loop, ending the chatbot session
            }

            // Using the CommandHandler to process the user's input and generate a response
            String response = commandHandler.handleCommand(userInput);

            // Displaying the response back to the user
            System.out.println(response);
        }

        // Closing the Scanner to release system resources
        scanner.close();

    }
}
