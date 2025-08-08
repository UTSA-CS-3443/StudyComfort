package edu.utsa.cs3443.studycomfort.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    public User(String firstName, String lastName, String email, String passwordHash) {
        this(UUID.randomUUID().toString(), firstName, lastName, email, passwordHash);
    }

    public User(String userId, String firstName, String lastName, String email, String passwordHash) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public boolean verifyPasswordHash(String sha256Hex) {
        return sha256Hex != null && sha256Hex.equalsIgnoreCase(passwordHash);
    }

    public JSONObject toJson() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("userId", userId);
        o.put("firstName", firstName);
        o.put("lastName", lastName);
        o.put("email", email);
        o.put("passwordHash", passwordHash);
        return o;
    }

    public static User fromJson(JSONObject o) throws JSONException {
        return new User(
                o.getString("userId"),
                o.getString("firstName"),
                o.getString("lastName"),
                o.getString("email"),
                o.getString("passwordHash")
        );
    }

    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return Objects.equals(email, other.email);
    }

    @Override public int hashCode() { return Objects.hash(email); }
}
