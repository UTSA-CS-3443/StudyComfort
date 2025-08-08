package edu.utsa.cs3443.studycomfort.Model;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private static final String PREFS = "studycomfort_reviews";
    private static final String KEY   = "reviews_json";

    private static ReviewRepository instance;
    public static synchronized ReviewRepository getInstance() {
        if (instance == null) instance = new ReviewRepository();
        return instance;
    }

    private ReviewRepository() {}

    /** Uses one review. */
    public void save(Context ctx, Review r) {
        try {
            SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            String raw = sp.getString(KEY, "[]");
            JSONArray arr = new JSONArray(raw);
            arr.put(r.toJson());
            sp.edit().putString(KEY, arr.toString()).apply();
        } catch (Exception ignored) {

        }
    }

    /** Load all reviews, seeding defaults if the store is empty. */
    public List<Review> loadAll(Context ctx) {
        List<Review> out = new ArrayList<>();
        try {
            SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            String raw = sp.getString(KEY, null);

            if (raw == null || raw.trim().isEmpty() || isEmptyArray(raw)) {

                seedDefaults(ctx);
                raw = sp.getString(KEY, "[]");
            }

            JSONArray arr = new JSONArray(raw);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                out.add(Review.fromJson(o));
            }
        } catch (Exception ignored) {

        }
        return out;
    }

    // ---- helpers ----

    private boolean isEmptyArray(String s) {
        String t = s.trim();
        return t.equals("[]") || t.equals("") || t.equals("null");
    }

    private void seedDefaults(Context ctx) {
        try {
            List<Review> defaults = new ArrayList<>();
            defaults.add(new Review("San Pedro I #232", "Xander S.", 5, "Very clean and quiet."));
            defaults.add(new Review("San Pedro I #232", "Max Z.",     4, "Study here every day after class with my buddies"));
            defaults.add(new Review("Study Room A02",   "Jacob B.",   4, "Great room to study and work on large group projects."));
            defaults.add(new Review("Study Room A02",   "Lindsey A.", 5, "Fantastic presentation room!"));
            defaults.add(new Review("Study Room C27",   "Adam H.",    5, "Awesome room that’s not too noisy, but also has other students to study with."));
            defaults.add(new Review("Study Room C27",   "Rodney L.",  5, "Best study room on campus."));

            SharedPreferences sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            JSONArray arr = new JSONArray();
            for (Review r : defaults) arr.put(r.toJson());
            sp.edit().putString(KEY, arr.toString()).apply();
        } catch (Exception ignored) { }
    }
}
