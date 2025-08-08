package edu.utsa.cs3443.studycomfort.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3443.studycomfort.Model.Review;
import edu.utsa.cs3443.studycomfort.Model.ReviewRepository;
import edu.utsa.cs3443.studycomfort.R;

public class ReviewsActivity extends AppCompatActivity {

    private final List<Review> data = new ArrayList<>();
    private ReviewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        final String userId = getIntent().getStringExtra("userId");

        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            PopupMenu pm = new PopupMenu(this, v);
            pm.getMenu().add("Preferences");
            pm.getMenu().add("Recommendations");
            pm.getMenu().add("Logout");
            pm.setOnMenuItemClickListener(item -> {
                String t = String.valueOf(item.getTitle());
                if (t.equals("Preferences")) {
                    Intent i = new Intent(this, QuestionnaireActivity.class);
                    if (userId != null) i.putExtra("userId", userId);
                    startActivity(i);
                } else if (t.equals("Recommendations")) {
                    Intent i = new Intent(this, RecommendationsActivity.class);
                    if (userId != null) i.putExtra("userId", userId);
                    startActivity(i);
                } else if (t.equals("Logout")) {
                    startActivity(new Intent(this, MainActivity.class));
                }
                return true;
            });
            pm.show();
        });

        RecyclerView rv = findViewById(R.id.recyclerReviews);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReviewsAdapter(data);
        rv.setAdapter(adapter);

        // load
        data.clear();
        data.addAll(ReviewRepository.getInstance().loadAll(getApplicationContext()));
        adapter.notifyDataSetChanged();

        Button btnAdd = findViewById(R.id.btnAddReview);
        if (btnAdd != null) btnAdd.setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        final android.view.View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_review, null, false);

        final EditText etRoomTitle = view.findViewById(R.id.etRoomTitle);
        final EditText etAuthor    = view.findViewById(R.id.etAuthor);
        final Spinner  spStars     = view.findViewById(R.id.spStars);
        final EditText etText      = view.findViewById(R.id.etText);

        spStars.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new Integer[]{1,2,3,4,5}));

        Button btnSave = view.findViewById(R.id.btnSaveReview);
        btnSave.setOnClickListener(v -> {
            String title  = safe(etRoomTitle.getText().toString());
            String author = safe(etAuthor.getText().toString());
            String text   = safe(etText.getText().toString());
            int stars     = (Integer) spStars.getSelectedItem();
            if (title.isEmpty()) {
                Toast.makeText(this, "Please enter a room title.", Toast.LENGTH_SHORT).show();
                return;
            }
            Review r = new Review(title, author.isEmpty() ? "Anonymous" : author, stars, text);
            ReviewRepository.getInstance().save(getApplicationContext(), r);
            dlg.dismiss();
            data.clear();
            data.addAll(ReviewRepository.getInstance().loadAll(getApplicationContext()));
            adapter.notifyDataSetChanged();
        });

        dlg.setView(view);
        dlg.show();
    }

    private String safe(String s) { return s == null ? "" : s.trim(); }
}
