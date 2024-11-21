import React, { useState } from "react";
import axios from "axios";

function App() {
  const [userInput, setUserInput] = useState("");
  const [chatResponse, setChatResponse] = useState("");

  const handleSend = async () => {
    try {
          // Send the user input to the backend API
          const response = await axios.post(
            "http://localhost:8080/api/chat",
            { input: userInput }, // JSON payload
            {
              headers: { "Content-Type": "application/json" }, // Content type header
            }
          );

          // Update the response from the chatbot
          setChatResponse(response.data.message); // Assuming backend returns { "message": "..." }
        } catch (error) {
          console.error("Error fetching chat response:", error);
          setChatResponse("Sorry, there was an error connecting to the chatbot.");
        }
      };

      return (
        <div style={styles.container}>
          <h1 style={styles.title}>PowerShell Support Chatbot</h1>
          <textarea
            style={styles.textarea}
            rows="4"
            cols="50"
            value={userInput}
            onChange={(e) => setUserInput(e.target.value)}
            placeholder="Type your question here..."
          />
          <br />
          <button onClick={handleSend} style={styles.button}>
            Send
          </button>
          <div style={styles.responseContainer}>
            <h3 style={styles.responseTitle}>Response:</h3>
            <p style={styles.responseText}>{chatResponse}</p>
          </div>
        </div>
      );
    }

    // Inline styles for the components
    const styles = {
      container: {
        padding: "20px",
        fontFamily: "Arial, sans-serif",
        maxWidth: "600px",
        margin: "0 auto",
        textAlign: "center",
        border: "1px solid #ccc",
        borderRadius: "10px",
        backgroundColor: "#f9f9f9",
        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
      },
      title: {
        marginBottom: "20px",
        color: "#333",
      },
      textarea: {
        width: "100%",
        padding: "10px",
        fontSize: "16px",
        borderRadius: "5px",
        border: "1px solid #ccc",
        resize: "none",
      },
      button: {
        marginTop: "10px",
        padding: "10px 20px",
        fontSize: "16px",
        borderRadius: "5px",
        backgroundColor: "#007BFF",
        color: "white",
        border: "none",
        cursor: "pointer",
      },
      responseContainer: {
        marginTop: "20px",
        textAlign: "left",
      },
      responseTitle: {
        fontWeight: "bold",
        color: "#555",
      },
      responseText: {
        color: "#333",
        whiteSpace: "pre-wrap", // Preserves line breaks in chatbot response
        fontSize: "16px",
      },
    };

    export default App;