package edu.utsa.cs3443.studycomfort.Model;

public class StudyRoom {
    private String building;
    private int floor;
    private String name;
    private int seatingCapacity;
    private String campus;

    public StudyRoom(String building, int floor, String name, int seatingCapacity, String campus) {
        this.building = building;
        this.floor = floor;
        this.name = name;
        this.seatingCapacity = seatingCapacity;
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public int getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getCampus() {
        return campus;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public boolean matchesCapacity (int capacity) {
        return this.seatingCapacity >= capacity;
    }

    public boolean isDowntown (String campus) {
        return this.campus.equals(campus);
    }
}
