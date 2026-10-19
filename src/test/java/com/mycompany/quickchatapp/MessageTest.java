/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.quickchatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    String recipient1 = "+27718693002";
    String message1 = "Hi Mike, can you join us for dinner tonight?";
    
    String recipient2 = "08575975889";
    String message2 = "Hi Keegan, did you receive the payment?";

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(recipient1, message1, 0);
        String result = msg.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "a".repeat(260);
        Message msg = new Message(recipient1, longMsg, 0);
        String result = msg.checkMessageLength();
        assertTrue(result.contains("exceeds 250 characters"));
    }

    @Test
    public void testRecipientNumberSuccess() {
        Message msg = new Message(recipient1, message1, 0);
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientNumberFailure() {
        Message msg = new Message(recipient2, message2, 1);
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message(recipient1, message1, 0);
        String hash = msg.getMessageHash();
        // Should contain :0: and HI and TONIGHT
        assertTrue(hash.contains(":0:"));
        assertTrue(hash.toUpperCase().contains("HI"));
        assertTrue(hash.toUpperCase().contains("TONIGHT"));
    }

    @Test
    public void testMessageIDCreated() {
        Message msg = new Message(recipient1, message1, 0);
        String id = msg.getMessageID();
        assertNotNull(id);
        assertTrue(id.length() <= 10);
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessageSent_Sent() {
        Message msg = new Message(recipient1, message1, 0);
        String result = msg.SentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testMessageSent_Disregard() {
        Message msg = new Message(recipient2, message2, 1);
        String result = msg.SentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testMessageSent_Store() {
        Message msg = new Message(recipient1, message1, 0);
        String result = msg.SentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testReturnTotalMessages() {
        int before = Message.getTotalSent();
        Message msg = new Message(recipient1, message1, 0);
        msg.SentMessage(1);
        int after = Message.getTotalSent();
        assertEquals(before + 1, after);
    }
}