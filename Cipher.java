/* Ciphering and deciphering algorithm based on various
levels of padding */

import java.util.Scanner;

public class Cipher {
    public static final int MAX_ROWS = 10;
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); 
        showIntro();    
        String input = enterMessage(keyboard);
        encryption(input);
        encryptedWithPadding(input);
        decryption(keyboard);
        keyboard.close();
    }

    // introduction to the product for the user to understand what is happening 
    public static void showIntro() {
        System.out.println("This program demonstrates a transposition cipher.");
        System.out.println("A cipher is an algorithm to encrypt or decrypt a message.");
        System.out.println();
        System.out.println("This program will demonstrate encrypting a message with");
        System.out.println("a columnar transposition cipher both with and without");
        System.out.println("padding characters. The program will then decrypt a message");
        System.out.println("assuming it was encrypted with a columnar transposition cipher");
        System.out.println("with padding.");
        System.out.println("After accepting user input, the program displays some tests.");
        System.out.println("\n\nA demonstration of encrypting with a columnar transposition cipher:");
    }
    
    // asks the user to input message in order to encrypt it
    public static String enterMessage(Scanner keyboard) {
        System.out.print("\nEnter the message to encrypt: ");
        String input = keyboard.nextLine();
        System.out.println("\nMessage encrypted with columnar transposition cipher and no padding.");
        return input;
    }
    
    // encrypts the user input message without padding, takes input as a parameter
    public static void encryption(String input) {
        for (int rows = 2; rows <= MAX_ROWS; rows++) {
            System.out.print("Encrypted with " + rows + " rows: ");
            for (int repeat = 0; repeat < rows; repeat++) {
                for (int character = repeat; character < input.length(); character += rows) {
                    System.out.print(input.charAt(character));
                }
            }
            System.out.println();
        }
    }
    
    // encrypts message with the padding, also takes input as a parameter
    public static void encryptedWithPadding (String input) {
        System.out.println("\nMessage encrypted with columnar transposition cipher and padding.\n");
        for (int rows = 2; rows <= MAX_ROWS; rows++) {
            int numCols = (input.length() / rows) + Math.min(1, input.length() % rows);
            int charsNeeded = numCols * rows;
            int numCharsForPadding = charsNeeded - input.length();
            String X = "XXXXXXXXXXXXXXXXXXXXXXXX";
            String newInput = input + X.substring(0, numCharsForPadding);

            System.out.print("Clear text padded for " + rows + " rows: " + newInput);
            System.out.print("\nEncrypted with " + rows + " rows: ");
            for (int repeat = 0; repeat < rows; repeat++) {
                for (int character = repeat; character < newInput.length(); character += rows) {
                    System.out.print(newInput.charAt(character));
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    // decrypts the statement input by the user, takes scanner as a parameter
    public static void decryption (Scanner keyboard)  {
        System.out.println("\nA demonstration of decrypting with a columnar transposition cipher:");
        System.out.println("If the length of the message is not a multiple of the number of rows");
        System.out.println("it will be padded which may throw off the decryption.");
        System.out.print("\nEnter the message to decrypt: ");
        String input = keyboard.nextLine();
        System.out.println("\nMessages Decrypted with a Columnar Transposition Cipher\n");
        
        // determines the number of rows and how the decryption will be printed in the for loops
        for (int rows = 2; rows <= MAX_ROWS; rows++) {
            int numCols = (input.length() / rows) + Math.min(1, input.length() % rows);
            int charsNeeded = numCols * rows;
            int numCharsForPadding = charsNeeded - input.length();
            String X = "XXXXXXXXXXXXXXXXXXXXXXXX";
            String newInput = input + X.substring(0, numCharsForPadding);

            System.out.println("Decrypted text padded for " + rows + " rows: " + newInput);
            System.out.print("Decrypted with " + rows + " rows: ");
            int columns = (int) Math.ceil(newInput.length()) / (rows);
            for (int i = 0; i < columns; i++) {
                for (int increment = i; increment < newInput.length(); increment += columns) {
                    System.out.print(newInput.charAt(increment));
                }
            }
            System.out.println();
            System.out.println();
        }
    }
}
