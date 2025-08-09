# StudyComfort
###  REQUESTED UML IS IN STUDYCOMFORT FILE AT TOP OF README FOLDER
### Description
StudyComfort is an Android mobile application developed for the CS-3443 Applications Programming course at the University of Texas at San Antonio (UTSA). The application is designed to help UTSA students locate and choose study rooms that best meet their individual preferences and needs across campus.

Finding an ideal study environment can significantly enhance productivity and comfort. StudyComfort simplifies this process by allowing users to search for study spaces across both the Main Campus and Downtown Campus using customizable filters. Whether a student prefers a quiet atmosphere, needs a specific seating capacity, or values cleanliness, StudyComfort enables informed decision-making through both objective data and peer feedback.

### Key Features
* Customizable Filters — Search for rooms based on:
  * Campus (Main or Downtown)
  * Noise level (quiet, moderate, loud)
  * Seating capacity
  * Cleanliness (Future add on; not implemented)
  * Additional factors such as Wi-Fi access
  * General based questions; some don't have complete implementation yet
  * Directional, quick jump tool bar
  * Access to socials where feed back is liked

* User Reviews — While not actually tested, starter reviews are provided (user can add more later; built app links as a start to communication from users to devs plus updates/ future features).

* Smart Recommendations Achieved — Based on a questionnaire, users receive personalized options tailored to their study preferences.

* User Authentication — Sign Up and Login pages ensure secure access. All features related to reviews and recommendations require login.

### Application Pages
* Login Page — Secure access for returning users.
* Sign Up Page — Easy registration for new users.
* Questionnaire Page — Gathers user preferences to power personalized study room recommendations.
* Recommendations Page — Displays best-fit rooms based on user input.
* Reviews Page — Enables users to explore and contribute ratings/comments for study rooms.

### About the Developers/ Contributors

This project was developed by:

* **Xander Stovall** – A junior majoring in Computer Science at UTSA with academic interests in **Natural Language Processing (NLP)** and **Mandarin-English code-switched data**.
* **Tristan Stiles** – A junior majoring in Computer Science with a strong interest in the intersection of **CS and healthcare/nursing**.

Together, we aimed to create a user-friendly and meaningful application that directly benefits the UTSA student body by improving the study experience.

### Requirements
* Android Studio 
* Gradle — use the included Gradle
* JDK — Android Studio’s embedded JDK 
* Android SDK — API Level 26+ (recommend API 34 for same results as ours)
* Internet Access — Not required (all data is local)

### How to Run
1. Clone or Download
   git clone <your-repo-url>
   Or download the ZIP from GitHub and extract it. (Easy Way!)

2. Open in Android Studio
   * Open the project root folder.
   * Let Gradle sync automatically (find "File" then "Sync" project with gradle files if needed).

3. Check Gradle JDK
   * Set File → Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK to Embedded JDK if prompted. (Shouldn't Be This Hard!)

4. Run
   * Use an emulator (API 26+; Pixel 5 API 34 recommended) or a real device running Android 8.0+.
   * Press "Run" or play-like button in Android Studio at top middle-right.

### Application Flow for Testing
1. Launch the app → MainActivity home screen.
2. Create Account → enter details (stored locally).
3. Automatically routed to Questionnaire → select preferences.
4. Submit → Recommendations screen shows filtered rooms from res/raw/utsa_study_rooms.csv.
5. Select a room → Reviews to view/add reviews.
6. Sign In page for returning users.
7. Feel free to visit socials.

### Known Issues
* Reviews are stored locally (SharedPreferences); they do not sync across devices.
* Clearing app data or uninstalling removes stored users and reviews.
* UI layout may wrap text differently on very small screens (minor issue imo; I used suggested android).
* [NOT REALLY A ISSUE BUT WANTED TO CLARIFY] Selecting group size larger than any available room (like 8 or 12) returns “No rooms found” — this is intended.

### Testing & Validation
Feel free to verify the app works by:

1. Fresh Clone Test
   * Clone or download ZIP, open in Android Studio, sync, build, run as already mentioned.

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

###  REQUESTED UML IS IN STUDYCOMFORT FILE AT TOP OF README FOLDER
