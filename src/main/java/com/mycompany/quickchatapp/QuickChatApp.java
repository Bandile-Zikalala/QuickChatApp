package com.mycompany.quickchatapp;
import java.util.Scanner;

public class QuickChatApp {

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        Login user= new Login();

        System.out.println("WELCOME TO QUICKCHAT");

        System.out.println("Enter First Name: ");
           user.firstName= input.nextLine();

         System.out.println("Enter Last Name");
           user.lastName= input.nextLine();

           String username;
           String password;
           String cellNumber;

         System.out.println("\n--- Registration ---");
        do {
            System.out.print("Enter username (must contain _ and <=5 chars e.g. kyl_1): ");
            username = input.nextLine();
            if (!user.checkUserName(username)) {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        } while (!user.checkUserName(username));
        System.out.println("Username successfully captured.");

        do {
            System.out.print("Enter password (8+ chars, caps, number, special e.g. Ch&&sec@ke99!): ");
            password = input.nextLine();
            if (!user.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!user.checkPasswordComplexity(password));
        System.out.println("Password successfully captured.");

        do {
            System.out.print("Enter cell number (+27... e.g. +27838568976): ");
            cellNumber = input.nextLine();
            if (!user.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
            }
        } while (!user.checkCellPhoneNumber(cellNumber));
        System.out.println("Cell phone number successfully captured.");

        String result = user.registerUser(username, password, cellNumber);
        System.out.println("\n" + result);

        System.out.println("\n--- Login ---");
        System.out.print("Enter username to login: ");
        String loginUname = input.nextLine();
        System.out.print("Enter password to login: ");
        String loginPword = input.nextLine();

        boolean loggedIn = user.loginUser(loginUname, loginPword);
        System.out.println(user.returnLoginStatus(loginUname, loginPword));

        if (!loggedIn) {
            System.out.println("Login failed. Cannot access QuickChat.");
            return;
        }

        System.out.println("\nWelcome to QuickChat.");
        System.out.print("How many messages do you wish to enter? ");
        int totalToEnter = Integer.parseInt(input.nextLine());
        int messagesEntered = 0;

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose option: ");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                if (messagesEntered >= totalToEnter) {
                    System.out.println("You have finished sending all " + totalToEnter + " messages.");
                    continue;
                }

                System.out.print("Enter recipient cell (e.g. +27838568976): ");
                String recipient = input.nextLine();
                System.out.print("Enter your message (max 250 chars): ");
                String msgText = input.nextLine();

                Message msg = new Message(recipient, msgText, messagesEntered);

                if (!msg.checkMessageID()) {
                    System.out.println("Message ID is too long");
                    continue;
                }

                String recipientCheck = msg.checkRecipientCell();
                System.out.println(recipientCheck);
                if (!recipientCheck.contains("successfully")) {
                    continue;
                }

                String lengthCheck = msg.checkMessageLength();
                System.out.println(lengthCheck);
                if (lengthCheck.contains("exceeds")) {
                    continue;
                }

                System.out.println("\nChoose:");
                System.out.println("1) Send Message");
                System.out.println("2) Disregard Message");
                System.out.println("3) Store Message to send later");
                System.out.print("Enter choice: ");
                int sendOption = Integer.parseInt(input.nextLine());

                String sendResult = msg.SentMessage(sendOption);
                System.out.println(sendResult);

                if (sendOption == 1) {
                    System.out.println("\n--- Message Details ---");
                    System.out.println("Message ID: " + msg.getMessageID());
                    System.out.println("Message Hash: " + msg.getMessageHash());
                    System.out.println("Recipient: " + msg.getRecipient());
                    System.out.println("Message: " + msg.getMessageText());
                    messagesEntered++;
                } else if (sendOption == 2) {
                    System.out.print("Press 0 to delete. Enter 0: ");
                    String del = input.nextLine();
                    if (del.equals("0")) {
                        System.out.println("Message deleted");
                    }
                    messagesEntered++;
                } else if (sendOption == 3) {
                    System.out.println("Stored for later in storedMessages.json");
                    messagesEntered++;
                }

            } else if (choice.equals("2")) {
                // PART 2 REQUIREMENT - Must say Coming Soon
                System.out.println("Coming Soon.");
            } else if (choice.equals("3")) {
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