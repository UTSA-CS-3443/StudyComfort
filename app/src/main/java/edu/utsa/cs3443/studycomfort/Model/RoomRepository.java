package edu.utsa.cs3443.studycomfort.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoomRepository {
    private ArrayList<StudyRoom> rooms;

    public RoomRepository() {
        rooms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("utsa_study_rooms.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String building = fields[0];
                int floor = Integer.parseInt(fields[1]);
                String name = fields[2];
                int seatingCapacity = Integer.parseInt(fields[3]);
                String campus = fields[4];
                StudyRoom room = new StudyRoom(building, floor, name, seatingCapacity, campus);
                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<StudyRoom> getRooms() {
        return rooms;
    }

    public void removeRoom(StudyRoom room) {
        rooms.remove(room);
    }

    public ArrayList<StudyRoom> getRoomsByCapacity(int capacity) {
        ArrayList<StudyRoom> matchingRooms = new ArrayList<>();
        for (StudyRoom room : rooms) {
            if (room.matchesCapacity(capacity)) {
                matchingRooms.add(room);
            }
        }
        return matchingRooms;
    }

    public ArrayList<StudyRoom> getRoomsByCampus(String campus) {
        ArrayList<StudyRoom> matchingRooms = new ArrayList<>();
        for (StudyRoom room : rooms) {
            if (room.isDowntown(campus)) {
                matchingRooms.add(room);
            }
        }
        return matchingRooms;
    }

    public ArrayList<StudyRoom> getRoomsByCapacityAndCampus(int capacity, String campus) {
        ArrayList<StudyRoom> matchingRooms = new ArrayList<>();
        for (StudyRoom room : rooms) {
            if (room.matchesCapacity(capacity) && room.isDowntown(campus)) {
                matchingRooms.add(room);
            }
        }
        return matchingRooms;
    }

    public StudyRoom getRoomByName(String name) {
        for (StudyRoom room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloor(String building, int floor) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndName(String building, String name) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloorAndName(String building, int floor, String name) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor && room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloorAndSeatingCapacity(String building, int floor, int seatingCapacity) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor && room.getSeatingCapacity() == seatingCapacity) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloorAndCampus(String building, int floor, String campus) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor && room.getCampus().equals(campus)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloorAndNameAndSeatingCapacity(String building, int floor, String name, int seatingCapacity) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor && room.getName().equals(name) && room.getSeatingCapacity() == seatingCapacity) {
                return room;
            }
            }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloorAndNameAndSeatingCapacityAndCampus(String building, int floor, String name, int seatingCapacity, String campus) {

        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor && room.getName().equals(name) && room.getSeatingCapacity() == seatingCapacity && room.getCampus().equals(campus)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloorAndNameAndCampus(String building, int floor, String name, String campus) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor && room.getName().equals(name) && room.getCampus().equals(campus)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndFloorAndSeatingCapacityAndCampus(String building, int floor, int seatingCapacity, String campus) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getFloor() == floor && room.getSeatingCapacity() == seatingCapacity && room.getCampus().equals(campus)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndNameAndSeatingCapacityAndCampus(String building, String name, int seatingCapacity, String campus) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getName().equals(name) && room.getSeatingCapacity() == seatingCapacity && room.getCampus().equals(campus)) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndNameAndSeatingCapacity(String building, String name, int seatingCapacity) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getName().equals(name) && room.getSeatingCapacity() == seatingCapacity) {
                return room;
            }
        }
        return null;
    }

    public StudyRoom getRoomByBuildingAndNameAndCampus(String building, String name, String campus) {
        for (StudyRoom room : rooms) {
            if (room.getBuilding().equals(building) && room.getName().equals(name) && room.getCampus().equals(campus)) {
                return room;
            }
        }
        return null;
    }
}