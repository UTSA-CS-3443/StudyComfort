package edu.utsa.cs3443.studycomfort.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionnaireResponse {

    private String userId;
    private String gradeLevel;
    private String noiseTolerance;
    private String preferredHours;
    private String preferredArea;
    private String seatingPreference;

    private String studyStyle;
    private String resourcesNeeded;

    public QuestionnaireResponse(String userId) { this.userId = userId; }

    public String getUserId() { return userId; }

    public String getGradeLevel() { return gradeLevel; }
    public void setGradeLevel(String gradeLevel) { this.gradeLevel = gradeLevel; }

    public String getNoiseTolerance() { return noiseTolerance; }
    public void setNoiseTolerance(String noiseTolerance) { this.noiseTolerance = noiseTolerance; }

    public String getPreferredHours() { return preferredHours; }
    public void setPreferredHours(String preferredHours) { this.preferredHours = preferredHours; }

    public String getPreferredArea() { return preferredArea; }
    public void setPreferredArea(String preferredArea) { this.preferredArea = preferredArea; }

    public String getSeatingPreference() { return seatingPreference; }
    public void setSeatingPreference(String seatingPreference) { this.seatingPreference = seatingPreference; }

    public String getStudyStyle() { return studyStyle; }
    public void setStudyStyle(String studyStyle) { this.studyStyle = studyStyle; }

    public String getResourcesNeeded() { return resourcesNeeded; }
    public void setResourcesNeeded(String resourcesNeeded) { this.resourcesNeeded = resourcesNeeded; }

    public JSONObject toJson() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("userId", userId);
        o.put("gradeLevel", gradeLevel);
        o.put("noiseTolerance", noiseTolerance);
        o.put("preferredHours", preferredHours);
        o.put("preferredArea", preferredArea);
        o.put("seatingPreference", seatingPreference);
        o.put("studyStyle", studyStyle);
        o.put("resourcesNeeded", resourcesNeeded);
        return o;
    }

    public static QuestionnaireResponse fromJson(JSONObject o) throws JSONException {
        QuestionnaireResponse r = new QuestionnaireResponse(o.getString("userId"));
        r.setGradeLevel(o.optString("gradeLevel", null));
        r.setNoiseTolerance(o.optString("noiseTolerance", null));
        r.setPreferredHours(o.optString("preferredHours", null));
        r.setPreferredArea(o.optString("preferredArea", null));
        r.setSeatingPreference(o.optString("seatingPreference", null));
        r.setStudyStyle(o.optString("studyStyle", null));
        r.setResourcesNeeded(o.optString("resourcesNeeded", null));
        return r;
    }
}