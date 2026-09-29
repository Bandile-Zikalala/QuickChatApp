/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Part 2 - Message class
 */
public class Message {
    private String messageID;
    private int numMessage;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static int totalMessagesSent = 0;
    private static ArrayList<Message> sentMessagesList = new ArrayList<>();

    public Message(String recipient, String messageText, int numMessage) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessage = numMessage;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random rand = new Random();
        long num = (long) (rand.nextDouble() * 10000000000L);
        return String.format("%010d", num);
    }

    // Boolean: checkMessageID()
    public Boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    // String: checkRecipientCell() - must have international code and <=10 chars + code
    // Your Part 1 checks +27 and 12 length, so we keep same logic for Part 2
    public String checkRecipientCell() {
        if (recipient!= null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured";
        } else if (recipient!= null && recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again";
        }
    }

    public boolean isValidRecipient() {
        return checkRecipientCell().contains("successfully");
    }

    // Check message length 250
    public String checkMessageLength() {
        if (messageText.length() > 250) {
            return "Please enter a message of less than 250 characters.";
        } else {
            return "Message sent";
        }
    }

    // String: createMessageHash() -> 00:0:HITHANKS
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        return (firstTwo + ":" + numMessage + ":" + firstWord + lastWord).toUpperCase();
    }

    // String: SentMessage() - options Send / Disregard / Store
    public String SentMessage(int option) {
        if (option == 1) {
            totalMessagesSent++;
            sentMessagesList.add(this);
            storeMessage();
            return "Message successfully sent";
        } else if (option == 2) {
            return "Press 0 to delete the message";
        } else if (option == 3) {
            storeMessage();
            return "Message successfully stored";
        } else {
            return "Invalid option";
        }
    }

    // String: printMessages() - returns all messages sent while program running
    public String printMessages() {
        if (sentMessagesList.isEmpty()) {
            return "No messages sent.";
        }
        String output = "";
        for (Message m : sentMessagesList) {
            output += "\nMessageID: " + m.messageID +
                    "\nMessage Hash: " + m.messageHash +
                    "\nRecipient: " + m.recipient +
                    "\nMessage: " + m.messageText + "\n---";
        }
        return output;
    }

    // Int: returnTotalMessagess()
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // Your own defined storeMessage() method - stores in JSON
    public void storeMessage() {
        try {
            FileWriter file = new FileWriter("storedMessages.json", true);
            String json = "{\"MessageID\":\"" + messageID + "\",\"MessageHash\":\"" + messageHash + "\",\"Recipient\":\"" + recipient + "\",\"Message\":\"" + messageText.replace("\"", "'") + "\"}\n";
            file.write(json);
            file.close();
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Getters for display - Requirement 7 order: Message ID, Message Hash, Recipient, Message
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }

    public static int getTotalSent() { return totalMessagesSent; }
    public static ArrayList<Message> getSentList() { return sentMessagesList; }
}