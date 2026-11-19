package com.mycompany.quickchatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 
/**
 *
 * @author makhu
 */
//login validation for QUICKCHATAPP

public class Login {
    String savedUsername;
    String savedPassword;
    String savedCellNumber;
    String firstName;
    String lastName;

    boolean checkUserName(String userName) {
        return userName.contains("_") && userName.length() <= 5;
    }
//imigomo yokukhanda i password
    boolean checkPasswordComplexity(String password) {
        boolean hasLength = password.length() >= 8;
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasLength && hasCapital && hasNumber && hasSpecial;
    }
//imigomo ye cellular
    boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.startsWith("+27") && cellNumber.length() == 12;
    }
//imigomo yokubhalisa
    String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
        this.savedUsername = username;
        this.savedPassword = password;
        this.savedCellNumber = cellNumber;
        return "User has been registered successfully.";
    }

    boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(this.savedUsername) && enteredPassword.equals(this.savedPassword);
    }
//ungenile noma akangenanga??
    String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password is incorrect, please try again.";
        }
    }
}