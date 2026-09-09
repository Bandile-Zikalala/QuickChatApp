package com.mycompany.quickchatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    public LoginTest() {
    }

    @Test
    public void testCheckUserNameCorrectlyFormatted() {
        System.out.println("checkUserName - correctly formatted");
        String userName = "kyl_1";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkUserName(userName);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserNameIncorrectlyFormatted() {
        System.out.println("checkUserName - incorrectly formatted");
        String userName = "kyle!!!!!!!";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkUserName(userName);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        System.out.println("checkPasswordComplexity - valid Ch&&sec@ke99!");
        String password = "Ch&&sec@ke99!";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        System.out.println("checkPasswordComplexity - invalid password");
        String password = "password";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        System.out.println("checkCellPhoneNumber - +27838968976");
        String cell = "+27838968976";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cell);
        assertEquals(expResult, result);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        System.out.println("checkCellPhoneNumber - 08966553");
        String cell = "08966553";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cell);
        assertEquals(expResult, result);
    }

    @Test
    public void testLoginSuccessful() {
        System.out.println("loginUser - successful");
        Login instance = new Login();
        instance.setSavedCredentials("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        boolean expResult = true;
        boolean result = instance.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals(expResult, result);
    }

    @Test
    public void testLoginFailed() {
        System.out.println("loginUser - failed");
        Login instance = new Login();
        instance.setSavedCredentials("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        boolean expResult = false;
        boolean result = instance.loginUser("kyl_1", "wrongPass");
        assertEquals(expResult, result);
    }
}