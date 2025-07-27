import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input phrase from user
        System.out.print("Enter a word or phrase: ");
        String input = scanner.nextLine();

        // Step 1: Normalize the input - remove non-alphabet characters and convert to lowercase
        String cleaned = input.replaceAll("[^a-zA-Z]", "").toLowerCase();

        // Step 2: Reverse the cleaned string
        String reversed = new StringBuilder(cleaned).reverse().toString();

        // Step 3: Compare original (cleaned) and reversed strings
        if (cleaned.equals(reversed)) {
            System.out.println("--> It is a palindrome.");
        } else {
            System.out.println("--> It is not a palindrome.");
        }

        scanner.close();
    }
}
