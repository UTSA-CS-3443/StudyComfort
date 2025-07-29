package edu.utsa.cs3443.studycomfort.Model;
enum GradeLevel {
    FRESHMAN,
    SOPHOMORE,
    JUNIOR,
    SENIOR
}

enum NoiseLevel {
    LOW,
    MEDIUM,
    HIGH
}

enum Hours {
    MORNING,
    AFTERNOON,
    EVENING
}

enum Campus {
    MAIN,
    DOWNTOWN
}

enum SeatingCapacity {
    SMALL,
    MEDIUM,
    LARGE
}
public class QuestionnaireRespone {

    private String userID;
    private GradeLevel gradeLevel;
    private NoiseLevel noiseTolerance;
    private Hours preferredHours;
    private Campus preferredCampus;
    private SeatingCapacity seatingPreference;

    public QuestionnaireRespone(String userID, GradeLevel gradeLevel, NoiseLevel noiseTolerance, Hours preferredHours, Campus preferredCampus, SeatingCapacity seatingPreference) {
        this.userID = userID;
        this.gradeLevel = gradeLevel;
        this.noiseTolerance = noiseTolerance;
        this.preferredHours = preferredHours;
        this.preferredCampus = preferredCampus;
        this.seatingPreference = seatingPreference;
    }

    public String getUserID() {
        return userID;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public NoiseLevel getNoiseTolerance() {
        return noiseTolerance;
    }

    public Hours getPreferredHours() {
        return preferredHours;
    }

    public Campus getPreferredCampus() {
        return preferredCampus;
    }

    public SeatingCapacity getSeatingPreference() {
        return seatingPreference;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public void setNoiseTolerance(NoiseLevel noiseTolerance) {
        this.noiseTolerance = noiseTolerance;
    }

    public void setPreferredHours(Hours preferredHours) {
        this.preferredHours = preferredHours;
    }

    public void setPreferredCampus(Campus preferredCampus) {
        this.preferredCampus = preferredCampus;
    }

    public void setSeatingPreference(SeatingCapacity seatingPreference) {
        this.seatingPreference = seatingPreference;
    }
}