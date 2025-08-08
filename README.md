# StudyComfort

**Study Comfort** is an Android mobile application developed for the CS-3443 Applications Programming course at the University of Texas at San Antonio (UTSA). The application is designed to help UTSA students locate and reserve study rooms that best meet their individual preferences and needs across campus.

### Overview

Finding an ideal study environment can significantly enhance productivity and comfort. Study Comfort simplifies this process by allowing users to search for study spaces across both the **Main Campus** and **Downtown Campus** using customizable filters. Whether a student prefers a quiet atmosphere, needs a specific seating capacity, or values cleanliness, Study Comfort enables informed decision-making through both objective data and peer feedback.

### Key Features

* **Customizable Filters**: Search for rooms based on:

  * Campus (Main or Downtown)
  * Noise level (quiet, moderate, loud)
  * Seating capacity
  * Cleanliness
  * Additional factors such as lighting, temperature, and privacy

* **User Reviews**: Read and write reviews for each room to share your experience and help fellow students choose the best spot.

* **Smart Recommendations**: Based on a questionnaire, users receive personalized suggestions tailored to their study preferences.

* **User Authentication**:

  * Sign Up and Login pages ensure secure user access.
  * All features related to reviews and recommendations require authentication.

### Pages

* **Login Page**: Secure access for returning users.
* **Sign Up Page**: Easy registration for new users.
* **Questionnaire Page**: Gathers user preferences to power personalized study room recommendations.
* **Recommendations Page**: Displays best-fit rooms based on user input.
* **Reviews Page**: Enables users to explore and contribute ratings and comments for study rooms.

### About the Developers

This project was developed by:

* **Xander Stovall** – A junior majoring in Computer Science at UTSA with academic interests in **Natural Language Processing (NLP)** and **Mandarin-English code-switched data**.
* **Tristan Stiles** – A senior majoring in Computer Science with a strong interest in the intersection of **technology and healthcare/nursing**.

Together, we aimed to create a user-friendly and meaningful application that directly benefits the UTSA student body by improving the study experience.
# StudyComfort

### Description
StudyComfort is an Android mobile application developed for the CS-3443 Applications Programming course at the University of Texas at San Antonio (UTSA). The application is designed to help UTSA students locate and choose study rooms that best meet their individual preferences and needs across campus.

Finding an ideal study environment can significantly enhance productivity and comfort. StudyComfort simplifies this process by allowing users to search for study spaces across both the Main Campus and Downtown Campus using customizable filters. Whether a student prefers a quiet atmosphere, needs a specific seating capacity, or values cleanliness, StudyComfort enables informed decision-making through both objective data and peer feedback.

### Key Features
* Customizable Filters — Search for rooms based on:
  * Campus (Main or Downtown)
  * Noise level (quiet, moderate, loud)
  * Seating capacity
  * Cleanliness
  * Additional factors such as lighting, temperature, and privacy

* User Reviews — Read and write reviews for each room to share your experience and help fellow students choose the best spot.

* Smart Recommendations — Based on a questionnaire, users receive personalized suggestions tailored to their study preferences.

* User Authentication — Sign Up and Login pages ensure secure access. All features related to reviews and recommendations require authentication.

### Application Pages
* Login Page — Secure access for returning users.
* Sign Up Page — Easy registration for new users.
* Questionnaire Page — Gathers user preferences to power personalized study room recommendations.
* Recommendations Page — Displays best-fit rooms based on user input.
* Reviews Page — Enables users to explore and contribute ratings/comments for study rooms.

### Contributors
* Xander Stovall – Junior in Computer Science at UTSA with academic interests in Natural Language Processing (NLP) and Mandarin-English code-switched data.
* Tristan Stiles – Senior in Computer Science with a strong interest in the intersection of technology and healthcare/nursing.

### Requirements
* Android Studio 
* Gradle — use the included Gradle
* JDK — Android Studio’s embedded JDK 
* Android SDK — API Level 26+ (recommend API 34 for target match)
* Internet Access — Not required (all data is local)

### How to Run
1. Clone or Download
   git clone <your-repo-url>
   Or download the ZIP from GitHub and extract it.

2. Open in Android Studio
   * Open the project root folder.
   * Let Gradle sync automatically (Find File then Sync Project with Gradle Files if needed).

3. Check Gradle JDK
   * Set File → Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK to Embedded JDK if prompted.

4. Run
   * Use an emulator (API 26+; Pixel 5 API 34 recommended) or a real device running Android 8.0+.
   * Press Run or play-like button in Android Studio.

### Application Flow for Testing
1. Launch the app → MainActivity home screen.
2. Create Account → enter details (stored locally).
3. Automatically routed to Questionnaire → select preferences.
4. Submit → Recommendations screen shows filtered rooms from res/raw/utsa_study_rooms.csv.
5. Select a room → Reviews to view/add reviews.
6. Sign In page for returning users.

### Known Issues
* Reviews are stored locally (SharedPreferences); they do not sync across devices.
* Clearing app data or uninstalling removes stored users and reviews.
* UI layout may wrap text differently on very small screens (minor issue imoll; I used suggested android).
* Selecting group size larger than any available room (e.g., 8 or 12) returns “No rooms found” — this is intended.

### Testing & Validation
Feel free to verify the app works by:

1. Fresh Clone Test
   * Clone or download ZIP, open in Android Studio, sync, build, run.

2. Functional Flow Test
   * Create account → questionnaire → recommendations → select room → add review → restart app → review still present.

3. Edge Cases
   * Group size 8 or 12 returns no matches.
   * “Any” for all preferences returns full list.

4. Troubleshooting
   * If Gradle sync fails, set Gradle JDK to Embedded JDK.
   * If emulator API mismatch occurs, use API 26+.

### Project Structure (MVC)
* Model — StudyRoom, Review, QuestionnaireResponse, User + repositories (RoomRepository, ReviewRepository, UserRepository)
* Controller — AuthController, NavigationController, QuestionnaireController, RecommendationController
* View — MainActivity, SignInActivity, CreateAccountActivity, QuestionnaireActivity, RecommendationsActivity, ReviewsActivity
