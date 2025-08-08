package edu.utsa.cs3443.studycomfort.Controller;

/** Implemented this in Activities/Fragments so controllers never touch Intents directly. */
public interface NavigationController {
    void goToHome();
    void goToSignIn();
    void goToQuestionnaire();
    void goToRecommendations();
}

