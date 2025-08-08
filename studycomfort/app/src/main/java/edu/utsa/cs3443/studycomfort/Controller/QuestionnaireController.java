package edu.utsa.cs3443.studycomfort.Controller;

import edu.utsa.cs3443.studycomfort.Model.QuestionnaireResponse;
import edu.utsa.cs3443.studycomfort.Model.User;
import edu.utsa.cs3443.studycomfort.Model.UserRepository;

/**
 * Thin coat around the repository to manage questionnaire.
 */
public class QuestionnaireController {

    private final UserRepository userRepo = UserRepository.getInstance();

    /** Returns an existing response for the user or creates a new blank one. */
    public QuestionnaireResponse getOrCreate(User user) {
        if (user == null) throw new IllegalArgumentException("User required.");
        QuestionnaireResponse r = userRepo.getResponse(user.getUserId());
        if (r == null) {
            r = new QuestionnaireResponse(user.getUserId());
            userRepo.saveResponse(r);
        }
        return r;
    }

    /** Saves the updated questionnaire for the given user. */
    public void save(User user, QuestionnaireResponse updated) {
        if (user == null) throw new IllegalArgumentException("User required.");
        if (updated == null) throw new IllegalArgumentException("Questionnaire required.");
        if (!user.getUserId().equals(updated.getUserId())) {
            throw new IllegalArgumentException("Questionnaire userId mismatch.");
        }
        userRepo.saveResponse(updated);
    }
}
