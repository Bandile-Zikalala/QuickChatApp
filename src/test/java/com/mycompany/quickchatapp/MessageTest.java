package com.mycompany.quickchatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    Message message1;
    Message message2;
    Message message3;
    Message message4;
    Message message5;

    @BeforeEach
    public void setUp() {
        // Clear all static arrays before each test - VERY IMPORTANT
        Message.clearArrays();

        // Test Data EXACTLY as per Part 3 Brief PDF Page 3-4
        message1 = new Message("M001", "+27834557896", "Did you get the cake?", "00:0:DIDCAKE", 0);
        message2 = new Message("M002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "11:1:WHERE TIME.", 1);
        message3 = new Message("M003", "+27834484567", "Yohoooo, I am at your gate.", "22:2:YOHOOOO GATE.", 2);
        message4 = new Message("0838884567", "+27838884567", "It is dinner time !", "33:3:IT!", 3);
        message5 = new Message("M005", "+27838884567", "Ok, I am leaving without you.", "44:4:OKYOU.", 4);

        // Populate arrays as per flags: Sent, Stored, Disregard, Sent, Stored
        message1.SentMessage(1);
        message2.SentMessage(3);
        message3.SentMessage(2);
        message4.SentMessage(1);
        message5.SentMessage(3);
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Returns "Did you get the cake?", "It is dinner time !"
        assertEquals(2, Message.sentMessages.size());
        assertTrue(Message.sentMessages.get(0).getMessageText().contains("Did you get the cake?"));
        assertTrue(Message.sentMessages.get(1).getMessageText().contains("It is dinner time"));
    }

    @Test
    public void testDisplayLongestMessage() {
        // Returns "Where are you? You are late! I have asked you to be on time."
        String longest = Message.displayLongestMessage();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchForMessageID() {
        // Test data message 4, "0838884567", Returns "It is dinner time !"
        String result = Message.searchByMessageID("0838884567");
        assertTrue(result.contains("It is dinner time"));
    }

    @Test
    public void testSearchForRecipient() {
        // Test Data +27838884567, Returns 2 messages
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        // Test Data Test Message 2, Returns "Message successfully deleted."
        String result = Message.deleteByHash("11:1:WHERE TIME.");
        assertEquals("Message successfully deleted.", result);
        assertEquals(1, Message.storedMessages.size());
    }

    @Test
    public void testDisplayReport() {
        String report = Message.displayReport();
        System.out.println(report);
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("00:0:DIDCAKE"));
        assertTrue(report.contains("It is dinner time"));
    }
}


