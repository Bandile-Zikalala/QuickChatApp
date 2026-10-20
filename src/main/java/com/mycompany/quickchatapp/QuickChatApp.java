package com.mycompany.quickchatapp;
import java.util.Scanner;

public class QuickChatApp {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        Login user= new Login();

        System.out.println("WELCOME TO QUICKCHAT");
        System.out.print("Enter First Name: ");
        user.firstName= input.nextLine();
        System.out.print("Enter Last Name: ");
        user.lastName= input.nextLine();

        String username, password, cellNumber;

        System.out.println("\n--- Registration ---");
        do {
            System.out.print("Enter username (must contain _ and <=5 chars e.g. kyl_1): ");
            username = input.nextLine().trim();
            if (!user.checkUserName(username)) {
                System.out.println("Username is not correctly formatted...");
            }
        } while (!user.checkUserName(username));
        System.out.println("Username successfully captured.");

        do {
            System.out.print("Enter password (8+ chars, caps, number, special): ");
            password = input.nextLine().trim();
            if (!user.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted...");
            }
        } while (!user.checkPasswordComplexity(password));
        System.out.println("Password successfully captured.");

        do {
            System.out.print("Enter cell number (+27...): ");
            cellNumber = input.nextLine().trim();
            if (!user.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell phone number is incorrectly formatted...");
            }
        } while (!user.checkCellPhoneNumber(cellNumber));
        System.out.println("Cell phone number successfully captured.");

        System.out.println("\n" + user.registerUser(username, password, cellNumber));

        System.out.println("\n--- Login ---");
        System.out.print("Enter username to login: ");
        String loginUname = input.nextLine().trim();
        System.out.print("Enter password to login: ");
        String loginPword = input.nextLine().trim();

        boolean loggedIn = user.loginUser(loginUname, loginPword);
        System.out.println(user.returnLoginStatus(loginUname, loginPword));
        if (!loggedIn) {
            System.out.println("Login failed. Cannot access QuickChat.");
            input.close();
            return;
        }

        System.out.println("\nWelcome to QuickChat.");
        int totalToEnter = 0;
        while(totalToEnter <= 0) {
            try {
                System.out.print("How many messages do you wish to enter? ");
                totalToEnter = Integer.parseInt(input.nextLine().trim());
                if(totalToEnter <=0) System.out.println("Please enter a number greater than 0");
            } catch(Exception e) {
                System.out.println("Please enter a valid number");
            }
        }
        
        int messagesEntered = 0;

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages Report");
            System.out.println("4) Quit");
            System.out.print("Choose option: ");
            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                if (messagesEntered >= totalToEnter) {
                    System.out.println("You have finished sending all " + totalToEnter + " messages. Check Report in option 3.");
                    continue;
                }

                System.out.print("Enter recipient cell (e.g. +27834557896): ");
                String recipient = input.nextLine().trim();
                System.out.print("Enter your message (max 250 chars): ");
                String msgText = input.nextLine().trim();

                Message msg = new Message(recipient, msgText, messagesEntered);

                if (!msg.checkMessageID()) {
                    System.out.println("Message ID is too long");
                    continue;
                }

                String recipientCheck = msg.checkRecipientCell();
                System.out.println(recipientCheck);
                if (!recipientCheck.contains("successfully")) continue;

                String lengthCheck = msg.checkMessageLength();
                System.out.println(lengthCheck);
                if (lengthCheck.contains("exceeds")) continue;

                System.out.println("\nChoose:");
                System.out.println("1) Send Message");
                System.out.println("2) Disregard Message");
                System.out.println("3) Store Message to send later");
                System.out.print("Enter choice: ");
                int sendOption;
                try {
                    sendOption = Integer.parseInt(input.nextLine().trim());
                } catch(Exception e) { sendOption = 0; }
                
                String sendResult = msg.SentMessage(sendOption);
                System.out.println(sendResult);

                if (sendOption == 1) {
                    System.out.println("\n--- Message Details ---");
                    System.out.println(msg.printMessages());
                    messagesEntered++;
                } else if (sendOption == 2) {
                    System.out.print("Press 0 to delete. Enter 0: ");
                    String del = input.nextLine().trim();
                    if (del.equals("0")) System.out.println("Message deleted");
                    messagesEntered++;
                } else if (sendOption == 3) {
                    System.out.println("Stored for later in storedMessages.json");
                    messagesEntered++;
                }

            } else if (choice.equals("2")) {
                System.out.println(Message.displayReport());
            } else if (choice.equals("3")) {
                System.out.println("\n--- STORED MESSAGES MENU ---");
                System.out.println("a) Display sender and recipient of all sent messages");
                System.out.println("b) Display longest message");
                System.out.println("c) Search by Message ID");
                System.out.println("d) Search by Recipient");
                System.out.println("e) Delete by Hash");
                System.out.println("f) Display full report");
                System.out.print("Choose: ");
                String sub = input.nextLine().trim().toLowerCase();
                if(sub.equals("a")) System.out.println(Message.displaySendersAndRecipients());
                else if(sub.equals("b")) System.out.println("Longest: " + Message.displayLongestMessage());
                else if(sub.equals("c")) {
                    System.out.print("Enter Message ID: "); String id = input.nextLine().trim();
                    System.out.println(Message.searchByMessageID(id));
                }
                else if(sub.equals("d")) {
                    System.out.print("Enter Recipient (e.g. +27838884567): "); String rec = input.nextLine().trim();
                    System.out.println(Message.searchByRecipient(rec));
                }
                else if(sub.equals("e")) {
                    System.out.print("Enter Hash: "); String hash = input.nextLine().trim();
                    System.out.println(Message.deleteByHash(hash));
                }
                else if(sub.equals("f")) System.out.println(Message.displayReport());
                
            } else if (choice.equals("4")) {
                System.out.println("Total messages sent: " + Message.getTotalSent());
                System.out.println("Quitting QuickChat. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option");
            }
        }
        input.close();
    }
}