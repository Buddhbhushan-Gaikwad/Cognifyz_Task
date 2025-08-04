import java.util.Scanner;

public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔐 Enter your password to check its strength:");
        String password = scanner.nextLine();

        int score = 0;
        StringBuilder feedback = new StringBuilder();

        // Check password length
        if (password.length() >= 8) {
            score++;
        } else {
            feedback.append("- Password should be at least 8 characters long.\n");
        }

        // Check for lowercase letters
        if (password.matches(".*[a-z].*")) {
            score++;
        } else {
            feedback.append("- Add at least one lowercase letter.\n");
        }

        // Check for uppercase letters
        if (password.matches(".*[A-Z].*")) {
            score++;
        } else {
            feedback.append("- Add at least one uppercase letter.\n");
        }

        // Check for numbers
        if (password.matches(".*\\d.*")) {
            score++;
        } else {
            feedback.append("- Add at least one number (0-9).\n");
        }

        // Check for special characters
        if (password.matches(".*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|].*")) {
            score++;
        } else {
            feedback.append("- Add at least one special character (e.g. @, #, $).\n");
        }

        // Evaluate password strength
        System.out.print("\nPassword Strength: ");
        switch (score) {
            case 5 -> System.out.println("\nStrong");
            case 3, 4 -> System.out.println("\nMedium");
            default -> System.out.println("\nWeak");
        }

        // Show suggestions if not strong
        if (score < 5) {
            System.out.println("\nSuggestions to improve your password:");
            System.out.println(feedback);
        }

        scanner.close();
    }
}

