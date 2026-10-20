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
    System.out.println("3) Stored Messages Report (Part 3)");
    System.out.println("4) Quit");
    System.out.print("Choose option: ");
    String choice = input.nextLine();

    if (choice.equals("1")) {
        // ... keep your existing send logic ...
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
        String sub = input.nextLine();
        
        if(sub.equalsIgnoreCase("a")) System.out.println(Message.displaySendersAndRecipients());
        
        else if(sub.equalsIgnoreCase("b")) System.out.println("Longest: " + Message.displayLongestMessage());
        
        else if(sub.equalsIgnoreCase("c")) {
            
            System.out.print("Enter Message ID: "); String id = input.nextLine();
            
            System.out.println(Message.searchByMessageID(id));
        }
        else if(sub.equalsIgnoreCase("d")) {
            System.out.print("Enter Recipient: "); String rec = input.nextLine();
            System.out.println(Message.searchByRecipient(rec));
        }
        else if(sub.equalsIgnoreCase("e")) {
            System.out.print("Enter Hash: "); String hash = input.nextLine();
            System.out.println(Message.deleteByHash(hash));
        }
        else if(sub.equalsIgnoreCase("f")) System.out.println(Message.displayReport());
        
    } else if (choice.equals("4")) {
        System.out.println("Total messages sent: " + Message.getTotalSent());
        System.out.println("Quitting QuickChat. Goodbye!");
        
        break;
        
    } else {
        System.out.println("Invalid option");
    }
}
    }
}