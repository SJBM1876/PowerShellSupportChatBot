import React, { useState } from "react";
import axios from "axios";

function App() {
  const [userInput, setUserInput] = useState("");
  const [chatResponse, setChatResponse] = useState("");

  const handleSend = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/chat", userInput, {
        headers: { "Content-Type": "application/json" },
      });
      setChatResponse(response.data);
    } catch (error) {
      console.error("Error fetching chat response:", error);
      setChatResponse("Sorry, there was an error connecting to the chatbot.");
    }
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
      <h1>PowerShell Support Chatbot</h1>
      <textarea
        rows="4"
        cols="50"
        value={userInput}
        onChange={(e) => setUserInput(e.target.value)}
        placeholder="Type your question here..."
      />
      <br />
      <button onClick={handleSend} style={{ marginTop: "10px" }}>Send</button>
      <div>
        <h3>Response:</h3>
        <p>{chatResponse}</p>
      </div>
    </div>
  );
}

export default App;

