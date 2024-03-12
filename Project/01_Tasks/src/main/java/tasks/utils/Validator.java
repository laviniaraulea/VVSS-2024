package tasks.utils;

public class Validator {

    // Method to check if a string is empty or null
    public static void validateNullOrEmpty(String str) throws IllegalArgumentException {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("String is empty or null");
        }
    }

    // Method to check if an integer is within a specified range
    public static void validateInRange(int num, int min, int max) throws IllegalArgumentException {
        if (num < min || num > max) {
            throw new IllegalArgumentException("Number is not within the specified range");
        }
    }

    // Method to check if a string contains only letters
    public static void validateContainsOnlyLetters(String str) throws IllegalArgumentException {
        if (!str.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("String contains non-letter characters");
        }
    }

    // Method to check if a string is a valid email address (simple validation)
    public static void validateEmail(String email) throws IllegalArgumentException {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email address format");
        }
    }

    // Method to check if a string is a valid URL (simple validation)
    public static void validateUrl(String url) throws IllegalArgumentException {
        if (!url.matches("^http(s)?://.*")) {
            throw new IllegalArgumentException("Invalid URL format");
        }
    }

    // Method to check if a string represents a positive integer
    public static void validatePositiveInteger(String str) throws IllegalArgumentException {
        if (!str.matches("\\d+") || str.startsWith("0")) {
            throw new IllegalArgumentException("Not a positive integer");
        }
    }

    // Method to check if a string represents a valid date in the format "yyyy-MM-dd"
    public static void validateDate(String date) throws IllegalArgumentException {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}