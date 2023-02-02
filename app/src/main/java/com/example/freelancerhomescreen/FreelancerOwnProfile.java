package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FreelancerOwnProfile extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    TextView freelancerName, freelancerDescription, editBtn;
    SharedPreferences prefs;
    private final String IdentityID = "IdentityID";
    private final String UserID = "UserID";
    private final String RoleID = "RoleID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_own_profile);
        int profileID = 1;
        //todo: get from sharedPrefs
//        prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
//        String identity_id = prefs.getString(IdentityID, "");
//        String user_id = prefs.getString(UserID, "");
//        String role_id = prefs.getString(RoleID, "");
//        Log.d("FreelancerOwnProfile: identity_id", identity_id);
//        Log.d("FreelancerOwnProfile: user_id", (user_id));
//        Log.d("FreelancerOwnProfile: role_id", role_id);

        DatabaseHandler db = new DatabaseHandler(this);
        Freelancer fl = db.getFreelancer(profileID);
        freelancerName = findViewById(R.id.name);
        freelancerDescription = findViewById(R.id.desc);
        editBtn = findViewById(R.id.editFreelancer);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FreelancerEditProfile.class);
                startActivity(i);
                // comment as user should be able to go back if they want
                //finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        int profileID = 1;
        //todo: get from sharedPrefs
        DatabaseHandler db = new DatabaseHandler(this);
        prefs = getSharedPreferences("FreelancerUserDetails", MODE_PRIVATE);
        int userId = Integer.parseInt(prefs.getString("Identity ID","-1"));
        Freelancer fl = db.getFreelancer(userId);
        freelancerName = findViewById(R.id.name);
        freelancerDescription = findViewById(R.id.desc);
        editBtn = findViewById(R.id.editFreelancer);
        freelancerName.setText(fl.getName());
        freelancerDescription.setText(fl.getDescription());
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FreelancerEditProfile.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.certificationsCard:
                i = new Intent(getApplicationContext(), CertificationPage.class);
                startActivity(i);

                break;
            case R.id.skillsCard:
                i = new Intent(getApplicationContext(), SkillsPage.class);
                startActivity(i);

                break;
            case R.id.experienceCard:
                i = new Intent(getApplicationContext(), ExperienceMainActivity.class);
                startActivity(i);

                break;
            case R.id.projectsCard:
                i = new Intent(getApplicationContext(), ProjectsPage.class);
                startActivity(i);

                break;
        }

    }


}