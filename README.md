# QuickChatApp - PROG5121 POE Final (Part 3)

**Student:** Bandile Zikalala  
**Module:** PROG5121 - Programming 1A  
**Year:** 2026  
**Repo:** https://github.com/Bandile-Zikalala/QuickChatApp

## Project Description
QuickChat is a console-based messaging application developed for the PROG5121 POE. It handles user registration, login, message creation, storage in JSON, and advanced reporting using arrays.

This is the FINAL submission containing Part 1, Part 2 and Part 3.

## Features Implemented

### Part 1 - Registration & Login
- `checkUserName()`: validates username contains underscore and <=5 chars (e.g. kyl_1)
- `checkPasswordComplexity()`: validates 8+ chars, capital, number, special char (e.g. Ch&&sec@ke99!)
- `checkCellPhoneNumber()`: validates +27 international format
- `registerUser()`, `loginUser()`, `returnLoginStatus()`

### Part 2 - Messaging
- `checkMessageID()`, `checkRecipientCell()`, `checkMessageLength()`
- `createMessageHash()`: generates hash as 00:0:FIRSTLAST
- `SentMessage()`: Send / Disregard / Store options
- `printMessages()`, `returnTotalMessagess()`, `storeMessage()` to JSON
- Menu system with 1) Send Messages 2) Show recently sent 3) Quit

### Part 3 - Store Data and Display Report (NEW)
**Required Arrays (No hard-coding):**
- Sent Message, Disregarded Messages, Stored Messages (from JSON), Message Hash, Message ID

**New Main Menu Option 4 - Stored Messages:**
- a) Display sender and recipient of all sent messages
- b) Display the longest stored message
- c) Search for a message ID and display recipient + message
- d) Search for all messages for a particular recipient
- e) Delete a message using message hash
- f) Display a report with full details (Hash, Recipient, Message)

## How to Run
1. Clone: `git clone https://github.com/Bandile-Zikalala/QuickChatApp.git`
2. Open in NetBeans / IntelliJ
3. Run `QuickChatApp.java`
4. Register -> Login -> Enter number of messages -> Use Menu

## Test Data (Part 3 Brief - Page 3-4)
| Message | Recipient | Text | Flag |
| :--- | :--- | :--- | :--- |
| 1 | +27834557896 | Did you get the cake? | Sent |
| 2 | +27838884567 | Where are you? You are late! I have asked you to be on time. | Stored |
| 3 | +27834484567 | Yohoooo, I am at your gate. | Disregard |
| 4 | 0838884567 | It is dinner time ! | Sent |
| 5 | +27838884567 | Ok, I am leaving without you. | Stored |

## Unit Tests - Part 3
All tests in `src/test/java/com/mycompany/quickchatapp/MessageTest.java`:

1. **Sent Messages array correctly populated** - expects "Did you get the cake?", "It is dinner time!"
2. **Display longest Message** - expects "Where are you? You are late! I have asked you to be on time."
3. **Search for messageID** - search 0838884567 -> returns "It is dinner time!"
4. **Search by recipient** - search +27838884567 -> returns 2 messages
5. **Delete by hash** - deletes Message 2 successfully
6. **Display Report** - report shows Hash, Recipient, Message

Run: Right-click project -> Test -> All 9 tests green (6 Part 3 + 3 Part 2)

## Files
- `Login.java` - Part 1
- `Message.java` - Part 2 & 3 (with static arrays and report methods)
- `QuickChatApp.java` - Main with 4-option menu
- `storedMessages.json` - created at runtime
- `MessageTest.java` - JUnit 5 tests

## GitHub Commits
This project has 10+ commits showing incremental development:
- Part 1 commits, Part 2 commits, Part 3 final

## Video Demo
[Add your YouTube link here - show menu 3 report features]
