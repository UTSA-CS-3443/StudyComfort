package edu.utsa.cs3443.studycomfort.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.studycomfort.Controller.QuestionnaireController;
import edu.utsa.cs3443.studycomfort.Model.QuestionnaireResponse;
import edu.utsa.cs3443.studycomfort.Model.User;
import edu.utsa.cs3443.studycomfort.Model.UserRepository;
import edu.utsa.cs3443.studycomfort.R;

public class QuestionnaireActivity extends AppCompatActivity {

    private final QuestionnaireController qc = new QuestionnaireController();
    private final UserRepository userRepo = UserRepository.getInstance();

    private Spinner spGrade, spHours, spNoise, spArea, spSeats, spStyle;
    private EditText etResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        String userId = getIntent().getStringExtra("userId");
        if (userId == null || userId.trim().isEmpty()) {
            userRepo.load(getApplicationContext());
            userId = getIntent().getStringExtra("userId");
        }

        User user = (userId == null) ? null : userRepo.getById(userId);
        if (user == null) {
            userRepo.load(getApplicationContext());
            user = (userId == null) ? null : userRepo.getById(userId);
        }
        if (user == null) {
            Toast.makeText(this, "Sign in again to fill the questionnaire.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        final String finalUserId = user.getUserId();

        spGrade = findViewById(R.id.spGrade);
        spHours = findViewById(R.id.spHours);
        spNoise = findViewById(R.id.spNoise);
        spArea  = findViewById(R.id.spArea);
        spSeats = findViewById(R.id.spSeats);
        spStyle = findViewById(R.id.spStyle);
        etResources = findViewById(R.id.etResources);
        Button btnSave = findViewById(R.id.btnSave);

        spGrade.setAdapter(adapter(new String[]{"Freshman","Sophomore","Junior","Senior","Graduate"}));
        spHours.setAdapter(adapter(new String[]{"Morning","Afternoon","Evening","Night"}));
        spNoise.setAdapter(adapter(new String[]{"Low","Medium","High"}));

        // Map to RoomRepository campus values
        spArea.setAdapter(adapter(new String[]{"Any","Main","Downtown","STEM","Business","Library"}));

        // Numeric seat options
        spSeats.setAdapter(adapter(new String[]{"Any","1","2","4","6","8","12"}));

        spStyle.setAdapter(adapter(new String[]{"Quiet","Collaborative","Mixed"}));

        QuestionnaireResponse qr = qc.getOrCreate(user);
        selectIfPresent(spGrade,  qr.getGradeLevel());
        selectIfPresent(spHours,  qr.getPreferredHours());
        selectIfPresent(spNoise,  qr.getNoiseTolerance());
        selectIfPresent(spArea,   qr.getPreferredArea());
        selectIfPresent(spSeats,  qr.getSeatingPreference());
        selectIfPresent(spStyle,  qr.getStudyStyle());
        if (qr.getResourcesNeeded() != null) etResources.setText(qr.getResourcesNeeded());

        btnSave.setOnClickListener(v -> {
            qr.setGradeLevel(String.valueOf(spGrade.getSelectedItem()));
            qr.setPreferredHours(String.valueOf(spHours.getSelectedItem()));
            qr.setNoiseTolerance(String.valueOf(spNoise.getSelectedItem()));
            qr.setPreferredArea(String.valueOf(spArea.getSelectedItem()));
            qr.setSeatingPreference(String.valueOf(spSeats.getSelectedItem()));
            qr.setStudyStyle(String.valueOf(spStyle.getSelectedItem()));
            qr.setResourcesNeeded(etResources.getText().toString());

            userRepo.saveResponse(qr);

            Intent i = new Intent(QuestionnaireActivity.this, RecommendationsActivity.class);
            i.putExtra("userId", finalUserId);
            startActivity(i);
            finish();
        });
    }

    private ArrayAdapter<String> adapter(String[] items) {
        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
    }

    private void selectIfPresent(Spinner sp, String val) {
        if (val == null) return;
        ArrayAdapter<?> ad = (ArrayAdapter<?>) sp.getAdapter();
        for (int i = 0; i < ad.getCount(); i++) {
            if (val.equalsIgnoreCase(String.valueOf(ad.getItem(i)))) {
                sp.setSelection(i);
                break;
            }
        }
    }
}