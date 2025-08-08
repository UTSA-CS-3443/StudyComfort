package edu.utsa.cs3443.studycomfort.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.studycomfort.Model.RoomRepository;
import edu.utsa.cs3443.studycomfort.Model.UserRepository;
import edu.utsa.cs3443.studycomfort.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserRepository.getInstance().load(getApplicationContext());
        RoomRepository.getInstance().load(getApplicationContext());

        View btnSignIn = findViewById(R.id.btnSignIn);
        View btnCreate = findViewById(R.id.btnCreate);

        if (btnSignIn != null) {
            btnSignIn.setOnClickListener(v ->
                    startActivity(new Intent(this, SignInActivity.class)));
        }
        if (btnCreate != null) {
            btnCreate.setOnClickListener(v ->
                    startActivity(new Intent(this, CreateAccountActivity.class)));
        }

        wireLink(R.id.btnInstagram, "https://www.instagram.com/studycomfort_app/");
        wireLink(R.id.btnTwitter,   "https://x.com/StudyComfortApp");
        wireLink(R.id.btnFacebook,  "https://www.facebook.com/StudyComfortApp");
    }

    private void wireLink(int viewId, String url) {
        View v = findViewById(viewId);
        if (v != null) {
            v.setOnClickListener(x ->
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))));
        }
    }
}



