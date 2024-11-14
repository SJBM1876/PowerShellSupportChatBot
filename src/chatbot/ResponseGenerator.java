package chatbot;

public class ResponseGenerator {

    /**
     * Generates a response based on the PowerShell command provided.
     * This method adds a bit of personality and context to the chatbot's responses.
     *
     * @param command The PowerShell command to include in the response.
     * @return A formatted string response for the user.
     */
    public String generateResponse(String command) {
        if (command == null || command.isEmpty()) {
            return "Hmm, I couldn't find a matching PowerShell command for that. Could you try again?";
        }
        return "Here's the PowerShell command you can use:\n" + command +
                "\n\nTip: Make sure to replace placeholders like <user> and <NewPassword> with actual values!";
    }

    /**
     * Generates a help message for users to guide them on how to interact with the bot.
     *
     * @return A helpful string with instructions.
     */
    public String generateHelpMessage() {
        return "I'm here to assist with PowerShell commands related to Microsoft 365!\n" +
                "Try asking for things like:\n" +
                "- 'Get user information'\n" +
                "- 'Reset password for a user'\n" +
                "- 'List all groups'\n" +
                "- 'Get mailbox details'\n" +
                "Type 'exit' to close the chatbot.";
    }

    /**
     * Generates a farewell message when the user chooses to exit the chatbot.
     *
     * @return A friendly goodbye message.
     */
    public String generateExitMessage() {
        return "Thank you for using the PowerShell Chatbot! If you need more help, just ask anytime. Goodbye!";
    }
}




