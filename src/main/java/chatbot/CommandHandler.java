package chatbot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CommandHandler {
    private final Map<String, String> commandMap;

    // Constructor: Initializes the PowerShell command mappings
    public CommandHandler() {
        commandMap = new HashMap<>();
        initializeCommands();
    }

    // Method to define common PowerShell commands
    private void initializeCommands() {
        commandMap.put("get user", "Get-MsolUser -UserPrincipalName <user>@domain.com");
        commandMap.put("reset password", "Set-MsolUserPassword -UserPrincipalName <user>@domain.com -NewPassword <NewPassword> -ForceChangePassword $true");
        commandMap.put("list groups", "Get-MsolGroup");
        commandMap.put("get mailbox", "Get-Mailbox -Identity <user>@domain.com");
    }

    // Method to process user input and return a matching PowerShell command
    public String handleCommand(String userInput) {
        String lowerInput = userInput.toLowerCase();

        // Check if user input matches any predefined command
        for (String key : commandMap.keySet()) {
            if (lowerInput.contains(key)) {
                return commandMap.get(key);
            }
        }
        // If no match is found, return null
        return null;
    }
}



