package com.mycompany.quickchatapp;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    // Variables to store message info
    public String messageID; // 10 digit random number
    public int numMessagesSent; // which number message this is
    public String recipient; // phone number of person
    public String message; // the actual text
    public String messageHash; // special code for message
    
    public static int totalMessagesSent = 0; // total count of all sent messages
    public static String allMessages = ""; // stores all messages history

    // Constructor - runs when we create new Message
    public Message(String messageID, String recipient, String message, int count) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
        this.numMessagesSent = count;
        this.messageHash = createMessageHash(); // create hash immediately
    }

    // Generates random 10 digit ID
    public static String generateID() {
        Random rand = new Random();
        String id = "";
        for(int i=0; i<10; i++) {
            id += rand.nextInt(10); // adds 0-9 ten times
        }
        return id;
    }

    // Checks if message ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Checks if recipient number is correct - must have + and <=10 chars
    // Example: +2771234567
    public String checkRecipientCell() {
        if(recipient.length() <= 10 && recipient.contains("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Checks if message is not too long - max 250 characters
    public String checkMessageLength() {
        if(message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250; // how many chars too many
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    // Creates hash code - format: first2DigitsOfID:MessageNumber:FIRSTWORDLASTWORD
    // Example: 001:1:HELLOWORLD
    public String createMessageHash() {
        String[] parts = message.trim().split("\\s+"); // split message into words
        String first = parts.length > 0 ? parts[0] : ""; // first word
        String last = parts.length > 1 ? parts[parts.length-1] : first; // last word
        String firstTwo = messageID.substring(0, 2); // first 2 digits of ID
        return (firstTwo + ":" + numMessagesSent + ":" + (first + last)).toUpperCase();
    }

    // Decides what to do with message: 1=Send, 2=Disregard, 3=Store
    public String SentMessage(int choice) {
        if(choice == 1) {
            totalMessagesSent++; // increase total
            allMessages += printMessages() + "\n---\n"; // save to history
            return "Message successfully sent";
        } else if(choice == 2) {
            return "Press 0 to delete the message";
        } else if(choice == 3) {
            storeMessage(); // save to file
            return "Message successfully stored";
        }
        return "Invalid";
    }

    // Returns all details in one string - for displaying
    public String printMessages() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + message;
    }

    // Returns total number of messages sent - for final display
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // Saves message to JSON file - for storage option
    public void storeMessage() {
        try (FileWriter writer = new FileWriter("storedMessages.json", true)) {
            // writes JSON line to file
            writer.write("{\"messageID\":\""+messageID+"\",\"hash\":\""+messageHash+"\",\"recipient\":\""+recipient+"\",\"message\":\""+message+"\"}\n");
        } catch (IOException e) {
            System.out.println("Error storing: " + e.getMessage());
        }
    }
}