package edu.utsa.cs3443.studycomfort.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.studycomfort.Model.RoomRepository;
import edu.utsa.cs3443.studycomfort.Model.UserRepository;
import edu.utsa.cs3443.studycomfort.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Safe to call multiple times
        UserRepository.getInstance().load(getApplicationContext());
        RoomRepository.getInstance().load(getApplicationContext());

        View btnSignIn = findAny("btnSignIn", "signIn", "buttonSignIn", "signinButton");
        View btnCreate = findAny("btnCreateAccount", "createAccount", "buttonCreateAccount", "btnCreate");

        if (btnSignIn != null) {
            btnSignIn.setOnClickListener(v ->
                    startActivity(new Intent(this, SignInActivity.class)));
        }
        if (btnCreate != null) {
            btnCreate.setOnClickListener(v ->
                    startActivity(new Intent(this, CreateAccountActivity.class)));
        }

        wireLink("btnInstagram", "https://www.instagram.com/studycomfort_app/");
        wireLink("btnTwitter", "https://x.com/StudyComfortApp");
        wireLink("btnFacebook", "https://www.facebook.com/StudyComfortApp");
    }

    private void wireLink(String idName, String url) {
        int id = getResources().getIdentifier(idName, "id", getPackageName());
        if (id != 0) {
            View v = findViewById(id);
            if (v != null) {
                v.setOnClickListener(x ->
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))));
            }
        }
    }

    /** Finds a view by trying several id names; returns null if none exist. */
    private View findAny(String... idNames) {
        for (String name : idNames) {
            int id = getResources().getIdentifier(name, "id", getPackageName());
            if (id != 0) {
                View v = findViewById(id);
                if (v != null) return v;
            }
        }
        return null;
    }
}