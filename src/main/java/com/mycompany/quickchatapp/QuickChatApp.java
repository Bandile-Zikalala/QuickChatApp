/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchatapp;
import java.util.Scanner;
/**
 *
 * @author makhu
 */
public class QuickChatApp {

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        //my object for logging in
        Login user= new Login();
        
        //lanake I'll store igama lokuqala nesbongo ukuze soba nomlayezo wokwamukela
        System.out.println("WELCOME TO QUICKCHAT");
        
        System.out.println("Enter First Name:  ");
           user.firstName= input.nextLine();
           
         System.out.println("Enter Last Name");  
           user.lastName= input.nextLine();
           
           String username;
           String password;
           String cellNumber;
           
         //isizolokhu ibuzile uyoze ufake okufanele phela asidlali la
         System.out.println("\n--- Registration ---");
        do {
            System.out.print("Enter username (must contain _ and <=5 chars e.g. kyl_1): ");
            username = input.nextLine();
            if (!user.checkUserName(username)) {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        } while (!user.checkUserName(username));
        System.out.println("Username successfully captured.");
        
        // Solokhu sibuzile nje
        do {
            System.out.print("Enter password (8+ chars, caps, number, special e.g. Ch&&sec@ke99!): ");
            password = input.nextLine();
            if (!user.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!user.checkPasswordComplexity(password));
        System.out.println("Password successfully captured.");
        
        // sisamengakho nje akujiki ndawo
        do {
            System.out.print("Enter cell number (+27... e.g. +27838568976): ");
            cellNumber = input.nextLine();
            if (!user.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
            }
        } while (!user.checkCellPhoneNumber(cellNumber));
        System.out.println("Cell phone number successfully captured.");
        
        // Nakhyaaaaaa, sesibuyile ka IEC
        String result = user.registerUser(username, password, cellNumber);
        System.out.println("\n" + result);
        
        //sesiyakufakake enkundleni yokuxhumana
        System.out.println("\n--- Login ---");
        
        System.out.print("Enter username to login: ");
        String loginUname = input.nextLine();
        
        System.out.print("Enter password to login: ");
        String loginPword = input.nextLine();
        
        // sesisebenzisa leya Methy esiyikhande ngale
        System.out.println(user.returnLoginStatus(loginUname, loginPword));
        
        //ayy khohlwani, nginyukile nayo lento!
    }
}

