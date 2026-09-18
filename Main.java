/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.util.Scanner;

/**
 * Entry point - collects input, loops until it is valid,
 * and delegates all validation to Login.
 */
public class Main {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Login login = new Login();

        System.out.println("=== User Registration ===");
        String firstName = getNonBlankInput("Enter first name: ");
        String lastName = getNonBlankInput("Enter last name: ");
        String username = getValidUsername(login);
        String password = getValidPassword(login);
        String cellPhoneNumber = getValidCellPhoneNumber(login);

        User newUser = new User(firstName, lastName, username, password, cellPhoneNumber);
        System.out.println("\n" + login.registerUser(newUser));

        System.out.println("\n=== User Login ===");
        performLogin(login);

        keyboard.close();
    }

    // Loops until the user enters something (not blank)
    private static String getNonBlankInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = keyboard.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    // Loops until a correctly formatted username is entered
    private static String getValidUsername(Login login) {
        String username;
        do {
            username = getNonBlankInput("Enter a username (must contain '_' and be no more than 5 characters): ");
            if (!login.checkUserName(username)) {
                System.out.println("Invalid username. Please try again.");
            }
        } while (!login.checkUserName(username));
        return username;
    }

    // Loops until a correctly formatted password is entered
    private static String getValidPassword(Login login) {
        String password;
        do {
            password = getNonBlankInput("Enter a password (min 8 characters, 1 capital, 1 number, 1 special character): ");
            if (!login.checkPasswordComplexity(password)) {
                System.out.println("Invalid password. Please try again.");
            }
        } while (!login.checkPasswordComplexity(password));
        return password;
    }

    // Loops until a correctly formatted cell number is entered
    private static String getValidCellPhoneNumber(Login login) {
        String cellPhoneNumber;
        do {
            cellPhoneNumber = getNonBlankInput("Enter cell number (+27 followed by 9 digits, e.g. +27646829986): ");
            if (!login.checkCellPhoneNumber(cellPhoneNumber)) {
                System.out.println("Invalid cell number. Please try again.");
            }
        } while (!login.checkCellPhoneNumber(cellPhoneNumber));
        return cellPhoneNumber;
    }

    // Loops until the entered username and password match the registered user
    private static void performLogin(Login login) {
        boolean loginSuccessful;
        do {
            String loginUsername = getNonBlankInput("Enter username: ");
            String loginPassword = getNonBlankInput("Enter password: ");
            loginSuccessful = login.loginUser(loginUsername, loginPassword);
            System.out.println("\n" + login.returnLoginStatus(loginSuccessful));
        } while (!loginSuccessful);
    }
}