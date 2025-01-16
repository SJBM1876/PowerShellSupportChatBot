import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [messages, setMessages] = useState([]);
  const [userInput, setUserInput] = useState("");
  const [isSessionActive, setIsSessionActive] = useState(true); // Track session status

  // Set the welcome message when the component mounts
  useEffect(() => {
    setMessages([
      {
        sender: "bot",
        text: `Welcome to the PowerShell Support Chatbot!\nI'm here to assist with PowerShell commands related to Microsoft 365!\nTry asking for things like:\n- 'Get user information'\n- 'Reset password for a user'\n- 'List all groups'\n- 'Get mailbox details'\nType 'exit' to close the chatbot.`,
      },
    ]);
  }, []); // Empty dependency array ensures this runs only once when the component mounts

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!userInput.trim() || !isSessionActive) return;

    const commandInput = userInput.trim();

    // Display user message
    setMessages((prev) => [...prev, { sender: "user", text: userInput }]);

    try {
      const response = await axios.post("http://localhost:8080/api/chat", {
        message: commandInput,
      });

      // Display chatbot response
      setMessages((prev) => [
        ...prev,
        { sender: "bot", text: response.data.response },
      ]);

      // Check if the chatbot session should end
      if (response.data.exit) {
        setIsSessionActive(false); // End the session
        setMessages((prev) => [
          ...prev,
          { sender: "bot", text: "The session has ended. Refresh to start again." },
        ]);
      }
    } catch (error) {
      setMessages((prev) => [
        ...prev,
        { sender: "bot", text: "Sorry, there was an error connecting to the server." },
      ]);
    }

    setUserInput("");
  };

  return (
    <div className="chat-container">
      <h1>PowerShell Support Chatbot</h1>
      <div className="chat-box">
        {messages.map((msg, idx) => (
          <div
            key={idx}
            className={`chat-message ${
              msg.sender === "user" ? "user-message" : "bot-message"
            }`}
          >
            {msg.text.split("\n").map((line, i) => (
              <p key={i}>{line}</p>
            ))}
          </div>
        ))}
      </div>
      {isSessionActive ? (
        <form onSubmit={handleSubmit} className="chat-input-form">
          <input
            type="text"
            value={userInput}
            onChange={(e) => setUserInput(e.target.value)}
            placeholder="Type your message..."
            className="chat-input"
          />
          <button type="submit" className="send-button">
            Send
          </button>
        </form>
      ) : (
        <div className="session-ended">
          <p>The chatbot session has ended. Refresh the page to start again.</p>
        </div>
      )}
    </div>
  );
}

export default App;







