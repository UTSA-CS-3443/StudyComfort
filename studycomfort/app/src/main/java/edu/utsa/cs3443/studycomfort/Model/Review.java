package edu.utsa.cs3443.studycomfort.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Review {
    private String roomTitle;
    private String author;
    private int stars;
    private String text;

    public Review(String roomTitle, String author, int stars, String text) {
        this.roomTitle = roomTitle;
        this.author = author;
        this.stars = stars;
        this.text = text;
    }

    public String getRoomTitle() { return roomTitle; }
    public String getAuthor() { return author; }
    public int getStars() { return stars; }
    public String getText() { return text; }

    public JSONObject toJson() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("roomTitle", roomTitle);
        o.put("author", author);
        o.put("stars", stars);
        o.put("text", text);
        return o;
    }

    public static Review fromJson(JSONObject o) throws JSONException {
        return new Review(
                o.getString("roomTitle"),
                o.getString("author"),
                o.getInt("stars"),
                o.getString("text")
        );
    }
}
