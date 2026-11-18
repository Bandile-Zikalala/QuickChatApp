package com.mycompany.quickchatapp;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    private static int totalSent = 0;
    public static String allMessagesHistory = "";

    // PART 3 ARRAYS - no hard-coding
    public static List<Message> sentMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();
    public static List<Message> disregardedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();

    public Message(String recipient, String messageText, int count) {
        this.messageID = generateID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = count;
        this.messageHash = createMessageHash();
    }

    // For tests - set ID manually
    public Message(String messageID, String recipient, String messageText, String messageHash, int count) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = messageHash;
        this.messageNumber = count;
    }

    private String generateID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) id += rand.nextInt(10);
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        String firstTwo = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String SentMessage(int choice) {
        if (!messageIDs.contains(messageID)) {
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
        }
        if (choice == 1) {
            totalSent++;
            sentMessages.add(this);
            allMessagesHistory += printMessages() + "\n---\n";
            return "Message successfully sent.";
        } else if (choice == 2) {
            disregardedMessages.add(this);
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storedMessages.add(this);
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid choice";
    }

    public String printMessages() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    public int returnTotalMessages() {
        return totalSent;
    }
    // Keep old typo method so old tests don't break
    public int returnTotalMessagess() {
        return totalSent;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public static int getTotalSent() { return totalSent; }

    // Store as proper JSON array
    public static void storeMessage() {
        try (FileWriter writer = new FileWriter("storedMessages.json")) {
            writer.write("[\n");
            for (int i = 0; i < storedMessages.size(); i++) {
                Message m = storedMessages.get(i);
                writer.write("  {\"messageID\":\"" + m.getMessageID() + "\",\"hash\":\"" + m.getMessageHash() + "\",\"recipient\":\"" + m.getRecipient() + "\",\"message\":\"" + m.getMessageText().replace("\"", "'") + "\"}");
                if (i < storedMessages.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("]\n");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Read JSON file back into storedMessages array
public static void loadStoredMessages() {
    try {
        File file = new File("storedMessages.json");
        if (!file.exists()) return;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String content = "";
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        reader.close();

        if (!content.contains("messageID")) return;

        String[] parts = content.split("\\{");
        for (String p : parts) {
            if (!p.contains("messageID")) continue;
            String id = p.split("\"messageID\":\"")[1].split("\"")[0];
            String hash = p.split("\"hash\":\"")[1].split("\"")[0];
            String rec = p.split("\"recipient\":\"")[1].split("\"")[0];
            String msg = p.split("\"message\":\"")[1].split("\"")[0];

            boolean exists = false;
            for (Message m : storedMessages) {
                if (m.getMessageID().equals(id)) exists = true;
            }
            if (!exists) {
                Message loaded = new Message(id, rec, msg, hash, storedMessages.size());
                storedMessages.add(loaded);
                if (!messageIDs.contains(id)) {
                    messageIDs.add(id);
                    messageHashes.add(hash);
                }
            }
        }
    } catch (Exception e) {
        System.out.println("Error loading JSON: " + e.getMessage());
    }
}

    public static void clearArrays() {
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        totalSent = 0;
        allMessagesHistory = "";
    }

    // a) Display sender and recipient of all sent + stored
    public static String displaySendersAndRecipients() {
        String result = "";
        for (Message m : sentMessages) {
            result += "Recipient: " + m.getRecipient() + ", Message: " + m.getMessageText() + "\n";
        }
        for (Message m : storedMessages) {
            result += "Recipient: " + m.getRecipient() + ", Message: " + m.getMessageText() + "\n";
        }
        return result.trim();
    }

    // b) Display longest message - sent + stored (Test Data 2 is stored and is longest)
    public static String displayLongestMessage() {
        String longest = "";
        for (Message m : sentMessages) {
            if (m.getMessageText().length() > longest.length()) longest = m.getMessageText();
        }
        for (Message m : storedMessages) {
            if (m.getMessageText().length() > longest.length()) longest = m.getMessageText();
        }
        return longest;
    }

    // c) Search by Message ID
    public static String searchByMessageID(String id) {
        for (Message m : sentMessages) {
            if (m.getMessageID().equals(id)) return "Recipient: " + m.getRecipient() + "\nMessage: " + m.getMessageText();
        }
        for (Message m : storedMessages) {
            if (m.getMessageID().equals(id)) return "Recipient: " + m.getRecipient() + "\nMessage: " + m.getMessageText();
        }
        return "Message ID not found.";
    }

    // d) Search by Recipient - returns ALL messages for that recipient
    public static String searchByRecipient(String recipient) {
        String result = "";
        for (Message m : sentMessages) {
            if (m.getRecipient().equals(recipient)) result += m.getMessageText() + "\n";
        }
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(recipient)) result += m.getMessageText() + "\n";
        }
        return result.trim().isEmpty() ? "No messages found for that recipient." : result.trim();
    }

    // e) Delete by Hash - must return message text
    public static String deleteByHash(String hash) {
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).getMessageHash().equals(hash)) {
                String msg = sentMessages.get(i).getMessageText();
                messageHashes.remove(sentMessages.get(i).getMessageHash());
                messageIDs.remove(sentMessages.get(i).getMessageID());
                sentMessages.remove(i);
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                String msg = storedMessages.get(i).getMessageText();
                messageHashes.remove(storedMessages.get(i).getMessageHash());
                messageIDs.remove(storedMessages.get(i).getMessageID());
                storedMessages.remove(i);
                storeMessage(); // update JSON file
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // f) Display full report
    public static String displayReport() {
        String report = "--- FULL REPORT ---\n";
        for (Message m : sentMessages) {
            report += "Hash: " + m.getMessageHash() + " | Recipient: " + m.getRecipient() + " | Message: " + m.getMessageText() + "\n";
        }
        for (Message m : storedMessages) {
            report += "Hash: " + m.getMessageHash() + " | Recipient: " + m.getRecipient() + " | Message: " + m.getMessageText() + " | Status: Stored\n";
        }
        return report;
    }
}