package edu.utsa.cs3443.studycomfort.Model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.utsa.cs3443.studycomfort.R;

/**
 * parses res/raw/utsa_study_rooms.csv once.
 * CSV header: building,floor,name,seating capacity,campus
 */
public class RoomRepository {

    private static final String TAG = "RoomRepository";

    private static RoomRepository instance;
    public static synchronized RoomRepository getInstance() {
        if (instance == null) instance = new RoomRepository();
        return instance;
    }

    private final List<StudyRoom> rooms = new ArrayList<>();
    private boolean loaded = false;

    private RoomRepository() { }

    /** Safe to call multiple times; loads once. Never throws. */
    public synchronized void load(Context ctx) {
        if (loaded) return;

        BufferedReader br = null;
        try {
            // Uses generated resource ID directly (safer than getIdentifier I've learned)
            InputStream is = ctx.getResources().openRawResource(R.raw.utsa_study_rooms);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {

                if (isHeader) line = stripBom(line);
                line = line.trim();
                if (line.isEmpty()) continue;

                if (isHeader) { // skip header row exactly once
                    isHeader = false;
                    continue;
                }

                String[] cols = line.split("\\s*,\\s*");
                if (cols.length < 5) {
                    Log.w(TAG, "Skipping malformed row: " + line);
                    continue;
                }

                String building = cols[0];
                int floor = safeInt(cols[1], 0);
                String name = cols[2];
                int seating = safeInt(cols[3], 1);
                String campus = cols[4];

                rooms.add(new StudyRoom(building, floor, name, seating, campus));
            }

            // Sort for display
            Collections.sort(rooms, Comparator
                    .comparing(StudyRoom::getCampus, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(StudyRoom::getBuilding, String.CASE_INSENSITIVE_ORDER)
                    .thenComparingInt(StudyRoom::getFloor)
                    .thenComparing(StudyRoom::getName, String.CASE_INSENSITIVE_ORDER));

        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "CSV resource res/raw/utsa_study_rooms.csv is missing.", e);
        } catch (IOException e) {
            Log.e(TAG, "Failed reading rooms CSV.", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error loading rooms CSV.", e);
        } finally {
            loaded = true;
            if (br != null) try { br.close(); } catch (IOException ignored) {}
        }
    }

    public List<StudyRoom> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public List<StudyRoom> filter(String campus, Integer minCap, String ignoredMaxNoise) {
        List<StudyRoom> out = new ArrayList<>();
        for (StudyRoom r : rooms) {
            boolean campusOk = (campus == null || campus.isEmpty()) || r.getCampus().equalsIgnoreCase(campus);
            boolean capacityOk = (minCap == null || minCap <= 1) || r.getSeatingCapacity() >= minCap;
            if (campusOk && capacityOk) out.add(r);
        }
        return out;
    }

    private int safeInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }

    private String stripBom(String s) {
        if (s != null && !s.isEmpty() && s.charAt(0) == '\uFEFF') {
            return s.substring(1);
        }
        return s;
    }
}