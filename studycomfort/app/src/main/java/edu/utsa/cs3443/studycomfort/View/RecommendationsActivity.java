package edu.utsa.cs3443.studycomfort.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utsa.cs3443.studycomfort.Controller.RecommendationController;
import edu.utsa.cs3443.studycomfort.Model.QuestionnaireResponse;
import edu.utsa.cs3443.studycomfort.Model.RoomRepository;
import edu.utsa.cs3443.studycomfort.Model.StudyRoom;
import edu.utsa.cs3443.studycomfort.Model.UserRepository;
import edu.utsa.cs3443.studycomfort.R;

public class RecommendationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        final String userId = getIntent().getStringExtra("userId");

        TextView tv = findViewById(R.id.tvTitle);
        if (tv != null) tv.setText("Recommended Study Rooms");

        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            PopupMenu pm = new PopupMenu(this, v);
            pm.getMenu().add("Preferences");
            pm.getMenu().add("Reviews");
            pm.getMenu().add("Logout");
            pm.setOnMenuItemClickListener(item -> {
                String t = String.valueOf(item.getTitle());
                if (t.equals("Preferences")) {
                    Intent i = new Intent(this, QuestionnaireActivity.class);
                    if (userId != null) i.putExtra("userId", userId);
                    startActivity(i);
                } else if (t.equals("Reviews")) {
                    Intent i = new Intent(this, ReviewsActivity.class);
                    if (userId != null) i.putExtra("userId", userId);
                    startActivity(i);
                } else if (t.equals("Logout")) {
                    startActivity(new Intent(this, MainActivity.class));
                }
                return true;
            });
            pm.show();
        });

        // Load + show list (no fallback if questionnaire exists)
        UserRepository.getInstance().load(getApplicationContext());
        RoomRepository roomRepo = RoomRepository.getInstance();
        roomRepo.load(getApplicationContext());
        List<StudyRoom> allRooms = roomRepo.getAllRooms();

        QuestionnaireResponse q = (userId == null)
                ? null
                : UserRepository.getInstance().getResponse(userId);

        List<StudyRoom> rooms;
        if (q == null) {
            rooms = allRooms;
        } else {
            rooms = new RecommendationController().recommend(q);
            if ((rooms == null || rooms.isEmpty()) && tv != null) {
                tv.setText("No rooms found");
            }
        }

        RecyclerView rv = findViewById(R.id.recyclerRooms);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new StudyRoomAdapter(rooms));
    }
}