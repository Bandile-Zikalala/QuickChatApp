package com.mycompany.quickchatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    public void setup() {
        Message.clearArrays();
    }

    // --- PART 2 TESTS ---

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27834557896", "Did you get the cake?", 0);
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testCheckRecipientCellCorrect() {
        Message msg = new Message("+27834557896", "Hi", 0);
        assertTrue(msg.checkRecipientCell().contains("successfully"));
    }

    @Test
    public void testCheckRecipientCellIncorrect() {
        Message msg = new Message("0853455789", "Hi", 0);
        assertTrue(msg.checkRecipientCell().contains("incorrectly"));
    }

    @Test
    public void testCheckMessageLengthCorrect() {
        Message msg = new Message("+27834557896", "Did you get the cake?", 0);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLengthExceeds() {
        String longMsg = "a".repeat(251);
        Message msg = new Message("+27834557896", longMsg, 0);
        assertTrue(msg.checkMessageLength().contains("exceeds 250 characters by 1"));
    }

    @Test
    public void testCreateMessageHash() {
        // ID starts with 12, count 0, first word Hello, last word world
        Message msg = new Message("1234567890", "+27834557896", "Hello world", "12:0:HELLOWORLD", 0);
        // Test the real hash generation with auto ID
        Message msg2 = new Message("+27834557896", "Did you get the cake?", 0);
        String hash = msg2.createMessageHash();
        assertTrue(hash.contains(":0:DIDCAKE?"));
        assertTrue(hash.equals(hash.toUpperCase()));
    }

    @Test
    public void testSentMessage() {
        Message msg = new Message("+27834557896", "Did you get the cake?", 0);
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals(1, Message.getTotalSent());
    }

    @Test
    public void testDisregardedMessage() {
        Message msg = new Message("+27834484567", "Yohoooo, I am at your gate.", 2);
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals(1, Message.disregardedMessages.size());
    }

    @Test
    public void testStoredMessage() {
        Message msg = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 1);
        assertEquals("Message successfully stored.", msg.SentMessage(3));
        assertEquals(1, Message.storedMessages.size());
    }

    // --- PART 3 TESTS - EXACT BRIEF TEST DATA ---

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        Message m1 = new Message("1", "+27834557896", "Did you get the cake?", "00:0:DIDCAKE?", 0);
        Message m4 = new Message("4", "0838884567", "It is dinner time !", "00:3:ITTIME!", 3);
        m1.SentMessage(1);
        m4.SentMessage(1);
        assertEquals(2, Message.sentMessages.size());
        assertEquals("Did you get the cake?", Message.sentMessages.get(0).getMessageText());
        assertEquals("It is dinner time !", Message.sentMessages.get(1).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        Message m1 = new Message("1", "+27834557896", "Did you get the cake?", "00:0:DIDCAKE?", 0);
        Message m2 = new Message("2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "00:1:WHERETIME.", 1);
        m1.SentMessage(1);
        m2.SentMessage(3); // stored - longest is stored
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.displayLongestMessage());
    }

    @Test
    public void testSearchByMessageID() {
        Message m4 = new Message("4", "0838884567", "It is dinner time !", "00:3:ITTIME!", 3);
        m4.SentMessage(1);
        String result = Message.searchByMessageID("4");
        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("It is dinner time"));
    }

    @Test
    public void testSearchByRecipient() {
        Message m2 = new Message("2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "00:1:WHERETIME.", 1);
        Message m5 = new Message("5", "+27838884567", "Ok, I am leaving without you.", "00:4:OKYOU.", 4);
        m2.SentMessage(3);
        m5.SentMessage(3);
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you?"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        Message m2 = new Message("2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "00:1:WHERETIME.", 1);
        m2.SentMessage(3);
        String result = Message.deleteByHash("00:1:WHERETIME.");
        assertTrue(result.contains("successfully deleted"));
        assertTrue(result.contains("Where are you?"));
        assertEquals(0, Message.storedMessages.size());
    }

    @Test
    public void testDisplayReport() {
        Message m1 = new Message("1", "+27834557896", "Did you get the cake?", "00:0:DIDCAKE?", 0);
        m1.SentMessage(1);
        String report = Message.displayReport();
        assertTrue(report.contains("00:0:DIDCAKE?"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
    }

    @Test
    public void testDisplaySendersAndRecipients() {
        Message m1 = new Message("1", "+27834557896", "Did you get the cake?", "00:0:DIDCAKE?", 0);
        m1.SentMessage(1);
        String result = Message.displaySendersAndRecipients();
        assertTrue(result.contains("+27834557896"));
        assertTrue(result.contains("Did you get the cake?"));
    }
}