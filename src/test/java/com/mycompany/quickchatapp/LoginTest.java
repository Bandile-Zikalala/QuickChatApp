package com.mycompany.quickchatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testCheckUserNameCorrectlyFormatted() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrectlyFormatted() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityCorrect() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityIncorrect() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberCorrect() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27834557896"));
    }

    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUserSuccess() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27834557896");
        assertEquals("User has been registered successfully.", result);
    }

    @Test
    public void testLoginUserSuccess() {
        Login login = new Login();
        login.firstName = "Kyle";
        login.lastName = "Smith";
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27834557896");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Kyle, Smith, it is great to see you again.",
                login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUserFailed() {
        Login login = new Login();
        login.firstName = "Kyle";
        login.lastName = "Smith";
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27834557896");
        assertFalse(login.loginUser("kyl_1", "wrongpass"));
        assertEquals("Username or password is incorrect, please try again.",
                login.returnLoginStatus("kyl_1", "wrongpass"));
    }
}