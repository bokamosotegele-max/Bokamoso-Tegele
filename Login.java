/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.regex.Pattern;

/**
 * Handles registration and login validation for a User.
 * Regex references: https://www.regular-expressions.info/lookaround.html
 * and https://en.wikipedia.org/wiki/Telephone_numbers_in_South_Africa
 */
public class Login {

    // Must contain '_' and be 1-5 characters
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*_)[A-Za-z0-9_]{1,5}$");

    // At least 8 chars, 1 capital, 1 digit, 1 special character
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$");

    // "+27" followed by exactly 9 digits, e.g. +27646829986
    private static final Pattern CELLPHONE_PATTERN = Pattern.compile("^\\+27[0-9]{9}$");

    // Stores the currently registered user in memory
    private User registeredUser;

    // Checks the username contains '_' and is no more than 5 characters
    public boolean checkUserName(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    // Checks the password meets complexity rules
    public boolean checkPasswordComplexity(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    // Checks the cell number starts with +27 and has 9 digits after it
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        return cellPhoneNumber != null && CELLPHONE_PATTERN.matcher(cellPhoneNumber).matches();
    }

    // Validates all fields and stores the user if everything passes
    public String registerUser(User newUser) {
        StringBuilder resultMessage = new StringBuilder();

        if (!checkUserName(newUser.getUsername())) {
            return "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.";
        }
        resultMessage.append("Username successfully captured.\n");

        if (!checkPasswordComplexity(newUser.getPassword())) {
            return "Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number, "
                    + "and a special character.";
        }
        resultMessage.append("Password successfully captured.\n");

        if (!checkCellPhoneNumber(newUser.getCellPhoneNumber())) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        resultMessage.append("Cell phone number successfully added.\n");

        registeredUser = newUser;
        resultMessage.append("User ").append(newUser.getUsername()).append(" registered successfully!");
        return resultMessage.toString();
    }

    // Checks entered credentials against the registered user
    public boolean loginUser(String username, String password) {
        return registeredUser != null
                && registeredUser.getUsername().equals(username)
                && registeredUser.getPassword().equals(password);
    }

    // Returns the message for a login attempt
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful && registeredUser != null) {
            return "Welcome " + registeredUser.getFirstName() + ", "
                    + registeredUser.getLastName() + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public User getRegisteredUser() {
        return registeredUser;
    }
}