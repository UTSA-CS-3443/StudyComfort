package edu.utsa.cs3443.studycomfort.Controller;

import edu.utsa.cs3443.studycomfort.Model.User;
import edu.utsa.cs3443.studycomfort.Model.UserRepository;

import java.security.MessageDigest;
import java.util.Locale;

/**
 * Handles registration and sign-in logic (validation + hashing),
 * while persistence is handled by UserRepository.
 */
public class AuthController {

    private final UserRepository userRepo = UserRepository.getInstance();

    /** Register a new user. Throws IllegalArgumentException on bad input or duplicate email. */
    public User register(String firstName, String lastName, String email, String rawPassword) {
        validate(firstName, "first name");
        validate(lastName, "last name");
        validateEmail(email);
        validate(rawPassword, "password");

        if (userRepo.existsEmail(email)) {
            throw new IllegalArgumentException("An account with that email already exists.");
        }

        String hash = sha256Hex(rawPassword);
        User u = new User(
                safeTrim(firstName),
                safeTrim(lastName),
                normalizeEmail(email),
                hash
        );
        userRepo.add(u);
        return u;
    }

    /** Returns the User on success, or null if credentials are invalid. */
    public User signIn(String email, String rawPassword) {
        validateEmail(email);
        validate(rawPassword, "password");

        User u = userRepo.findByEmail(normalizeEmail(email));
        if (u == null) return null;

        String tryHash = sha256Hex(rawPassword);
        return tryHash.equals(u.getPasswordHash()) ? u : null;
    }

    // ---- helpers ----
    private void validate(String s, String label) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid " + label + ".");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
    }

    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    private String normalizeEmail(String email) {
        return safeTrim(email).toLowerCase(Locale.US);
    }

    private String sha256Hex(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(raw.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) sb.append(String.format(Locale.US, "%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing error", e);
        }
    }
}