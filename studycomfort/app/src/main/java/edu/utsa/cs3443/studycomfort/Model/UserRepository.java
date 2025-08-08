package edu.utsa.cs3443.studycomfort.Model;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    private static final String USERS_FILE = "users.jsonl";
    private static final String RESPONSES_FILE = "responses.jsonl";

    private static UserRepository instance;
    public static synchronized UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

    private final Map<String, User> usersByEmail = new ConcurrentHashMap<>();
    private final Map<String, QuestionnaireResponse> responsesByUser = new ConcurrentHashMap<>();

    private UserRepository() {}

    public User findByEmail(String email) { return usersByEmail.get(normalize(email)); }
    public boolean existsEmail(String email) { return usersByEmail.containsKey(normalize(email)); }
    public void add(User u) { if (u != null) usersByEmail.put(normalize(u.getEmail()), u); }

    public void saveResponse(QuestionnaireResponse r) {
        if (r != null && r.getUserId() != null) responsesByUser.put(r.getUserId(), r);
    }
    public QuestionnaireResponse getResponse(String userId) { return responsesByUser.get(userId); }

    // Fetch user by id
    public User getById(String userId) {
        if (userId == null) return null;
        for (User u : usersByEmail.values()) {
            if (userId.equals(u.getUserId())) return u;
        }
        return null;
    }

    public synchronized void persist(Context ctx) {
        try {
            // Users
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ctx.getFilesDir(), USERS_FILE)))) {
                for (User u : usersByEmail.values()) {
                    bw.write(u.toJson().toString());
                    bw.newLine();
                }
            }
            // Questionnaire responses
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ctx.getFilesDir(), RESPONSES_FILE)))) {
                for (QuestionnaireResponse r : responsesByUser.values()) {
                    bw.write(r.toJson().toString());
                    bw.newLine();
                }
            }
        } catch (Exception ignored) { }
    }

    public synchronized void load(Context ctx) {
        try {
            // Users
            File usersFile = new File(ctx.getFilesDir(), USERS_FILE);
            if (usersFile.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        User u = User.fromJson(new JSONObject(line));
                        usersByEmail.put(normalize(u.getEmail()), u);
                    }
                }
            }
            // Questionnaire responses
            File respFile = new File(ctx.getFilesDir(), RESPONSES_FILE);
            if (respFile.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(respFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        QuestionnaireResponse r = QuestionnaireResponse.fromJson(new JSONObject(line));
                        responsesByUser.put(r.getUserId(), r);
                    }
                }
            }
        } catch (Exception ignored) { /* keeps in-memory */ }
    }

    private String normalize(String email) { return email == null ? "" : email.trim().toLowerCase(); }
}