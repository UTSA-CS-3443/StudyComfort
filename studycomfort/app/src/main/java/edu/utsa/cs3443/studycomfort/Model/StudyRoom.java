package edu.utsa.cs3443.studycomfort.Model;

public class StudyRoom {

    private final String building;
    private final int floor;
    private final String name;
    private final int seatingCapacity;
    private final String campus;

    public StudyRoom(String building, int floor, String name, int seatingCapacity, String campus) {
        this.building = building;
        this.floor = floor;
        this.name = name;
        this.seatingCapacity = seatingCapacity;
        this.campus = campus;
    }

    public String getBuilding() { return building; }
    public int getFloor() { return floor; }
    public String getName() { return name; }
    public int getSeatingCapacity() { return seatingCapacity; }
    public String getCampus() { return campus; }

    public String getPhotoKey() {
        String b = safeLower(building);
        String n = safeLower(name);

        // San Pedro I — Study Room 232 / 314 → sr_232 / sr_314
        if (b.contains("san pedro")) {
            if (n.contains("232")) return "sr_232";
            if (n.contains("314")) return "sr_314";
        }

        // Engineering & Technology Library — Room A/C → aet_sr_a / aet_sr_c
        if (b.contains("applied engineering") || b.contains("technology library")) {
            if (n.contains("room a")) return "aet_sr_a";
            if (n.contains("room c")) return "aet_sr_c";
        }

        // John Peace Library — A02/A05/C02/C03
        if (b.contains("john peace")) {
            if (n.contains("room a2") || n.endsWith("a2)")) return "a02";
            if (n.contains("room a5") || n.endsWith("a5)")) return "a05";
            if (n.contains("room c2") || n.endsWith("c2)")) return "c02";
            if (n.contains("room c3") || n.endsWith("c3)")) return "c03";

            if (n.contains("room b4")) return "a02";
            if (n.contains("room b12")) return "a05";
        }

        // Downtown Library — M2 / O3
        if (b.contains("downtown")) {
            if (n.contains("room m2") || n.endsWith("m2)")) return "m2";
            if (n.contains("room o3") || n.endsWith("o3)")) return "dtlroom03";
        }

        return null;
    }

    private String safeLower(String s) {
        return s == null ? "" : s.toLowerCase().trim();
    }
}
