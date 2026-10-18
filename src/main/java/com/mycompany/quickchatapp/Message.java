package com.mycompany.quickchatapp;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    private static int totalSent = 0;
    // Made public so main can show history for Menu 2
    public static String allMessagesHistory = "";

    // Constructor with 3 params - matches your main
    public Message(String recipient, String messageText, int count) {
        this.messageID = generateID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = count + 1;
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random rand = new Random();
        String id = "";
        for(int i=0; i<10; i++) id += rand.nextInt(10);
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // FIXED: now accepts +27 numbers like +27838568976 (was only <=10 before)
    public String checkRecipientCell() {
        if(recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if(messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length-1] : firstWord;
        String firstTwo = messageID.substring(0, 2);
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String SentMessage(int choice) {
        if(choice == 1) {
            totalSent++;
            allMessagesHistory += printMessages() + "\n---\n";
            return "Message successfully sent";
        } else if(choice == 2) {
            return "Press 0 to delete the message";
        } else if(choice == 3) {
            storeMessage();
            return "Message successfully stored";
        }
        return "Invalid choice";
    }

    public String printMessages() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    public int returnTotalMessagess() {
        return totalSent;
    }

    // Getters for main
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public static int getTotalSent() { return totalSent; }

    // Stores to JSON file
    public void storeMessage() {
        try (FileWriter writer = new FileWriter("storedMessages.json", true)) {
            writer.write("{\"messageID\":\""+messageID+"\",\"hash\":\""+messageHash+"\",\"recipient\":\""+recipient+"\",\"message\":\""+messageText+"\"}\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}