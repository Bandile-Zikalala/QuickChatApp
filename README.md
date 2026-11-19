# QuickChatApp - PROG5121 POE Part 3

**Student:** Bandile Zikalala
**Module:** PROG5121 - Programming 1A
**Part:** Part 3 - Store Data and Display Task Report

## Description
QuickChat is a console-based chat application. 

- **Part 1:** User registration and login with validation.
- **Part 2:** Send messages, check recipient / length, create message hash, send / disregard / store.
- **Part 3:** Store messages in arrays + JSON file, and display a full report with search, longest message, and delete by hash.

## Features Implemented

### Part 1 - Registration & Login (`Login.java`)
- `checkUserName`: username must contain `_` and be <= 5 chars (e.g. `kyl_1`)
- `checkPasswordComplexity`: 8+ chars, capital letter, number, special char (e.g. `Ch&&sec@ke99!`)
- `checkCellPhoneNumber`: SA number with `+27` code, 12 chars (e.g. `+27834557896`)
- `registerUser`: returns registration status messages
- `loginUser` & `returnLoginStatus`: handles login and welcome message

### Part 2 - Messaging (`Message.java`)
- `checkMessageID`: ID is 10 digits, max 10 chars
- `checkRecipientCell`: recipient must start with `+` and be 10-13 chars
- `checkMessageLength`: message max 250 chars, shows excess if too long
- `createMessageHash`: format `firstTwoDigits:messageNumber:FIRSTWORDLASTWORD` in UPPERCASE (e.g. `00:0:DIDCAKE?`)
- `SentMessage(choice)`: 1 = Send, 2 = Disregard, 3 = Store
- `printMessages`: displays ID, Hash, Recipient, Message
- `returnTotalMessages`: returns total messages sent

### Part 3 - Stored Messages & Report (NEW)
Arrays used - no hard-coding:
- `sentMessages`, `disregardedMessages`, `storedMessages`
- `messageHashes`, `messageIDs`

Stored Messages Menu (Option 3 in main menu):
- a) `displaySendersAndRecipients()` - sender/recipient of all sent + stored messages
- b) `displayLongestMessage()` - longest message (sent + stored)
- c) `searchByMessageID(id)` - search by Message ID, shows recipient + message
- d) `searchByRecipient(recipient)` - shows ALL messages for that recipient
- e) `deleteByHash(hash)` - deletes by hash, returns `Message: "..." successfully deleted.`
- f) `displayReport()` - full report with Hash, Recipient, Message

JSON Storage:
- Messages stored to `storedMessages.json` as a proper JSON array

- 
## How to Run
1. Clone repo:
   ```bash
   git clone https://github.com/Bandile-Zikalala/QuickChatApp.git
- On start-up, `loadStoredMessages()` reads the JSON file back into the `storedMessages` array

## Project Structure
