package edu.utsa.cs3443.studycomfort.Controller;

import java.util.List;

import edu.utsa.cs3443.studycomfort.Model.QuestionnaireResponse;
import edu.utsa.cs3443.studycomfort.Model.RoomRepository;
import edu.utsa.cs3443.studycomfort.Model.StudyRoom;

public class RecommendationController {

    /**
     * Recommend rooms based on questionnaire:
     * - preferredArea -> campus filter (null/"Any" = no filter)
     * - seatingPreference -> minimum capacity (numeric) or none if "Any"
     */
    public List<StudyRoom> recommend(QuestionnaireResponse q) {
        if (q == null) return RoomRepository.getInstance().getAllRooms();

        String campus = normalizeCampus(q.getPreferredArea());
        Integer minCap = parseMinCap(q.getSeatingPreference());

        return RoomRepository.getInstance().filter(campus, minCap, null);
    }

    private String normalizeCampus(String area) {
        if (area == null) return null;
        String v = area.trim();
        if (v.equalsIgnoreCase("Any") || v.isEmpty()) return null;

        return v;
    }

    private Integer parseMinCap(String seatingPref) {
        if (seatingPref == null) return null;
        String s = seatingPref.trim();
        if (s.equalsIgnoreCase("Any") || s.isEmpty()) return null;
        try { return Integer.parseInt(s); } catch (Exception ignored) {}
        return null;
    }
}
