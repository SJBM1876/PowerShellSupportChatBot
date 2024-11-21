import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [messages, setMessages] = useState([]);
  const [userInput, setUserInput] = useState("");

  // Set the welcome message when the component mounts
  useEffect(() => {
    // Add the welcome message to the messages state
    setMessages([
      {
        sender: "bot",
        text: `Welcome to the PowerShell Support Chatbot!\nI'm here to assist with PowerShell commands related to Microsoft 365!\nTry asking for things like:\n- 'Get user information'\n- 'Reset password for a user'\n- 'List all groups'\n- 'Get mailbox details'\nType 'exit' to close the chatbot.`,
      },
    ]);
  }, []); // Empty dependency array ensures this runs only once when the component mounts

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!userInput.trim()) return;

    // Display user message
    setMessages((prev) => [...prev, { sender: "user", text: userInput }]);

    try {
      const response = await axios.post("http://localhost:8080/api/chat", {
        message: userInput,
      });

      // Display chatbot response
      setMessages((prev) => [
        ...prev,
        { sender: "bot", text: response.data.response },
      ]);
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
      <div className="chat-box">
        {messages.map((msg, idx) => (
          <div
            key={idx}
            className={`chat-message ${
              msg.sender === "user" ? "user-message" : "bot-message"
            }`}
          >
            {msg.text.split("\n").map((line, i) => (
              <p key={i}>{line}</p> // Split the message into multiple lines
            ))}
          </div>
        ))}
      </div>
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
    </div>
  );
}

export default App;



