package ua.zorii.helsiPet.util;

public class HashPassword {

    public static String hashPassword(String passwordPlaintext) {
        return BCrypt.hashpw(passwordPlaintext, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String passwordPlaintext, String storedHash) {
        boolean isPasswordVerified;

        if (storedHash == null || !storedHash.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }

        isPasswordVerified = BCrypt.checkPassword(passwordPlaintext, storedHash);

        return isPasswordVerified;
    }

}
