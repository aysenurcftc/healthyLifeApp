package com.example.mobilefinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DiyetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyet);

        // Get references to each CardView
        CardView yuzKaloriCard = findViewById(R.id.yuzkalori);
        CardView binbesyuzKaloriCard = findViewById(R.id.binbesyuzKalori);
        CardView ikibinCard = findViewById(R.id.ikibin);
        CardView ikibinbesyuzCard = findViewById(R.id.ikibinbesyuz);
        CardView ucbinCard = findViewById(R.id.ucbin);
        CardView ucbinbesyuzCard = findViewById(R.id.ucbinbesyuz);

        // Set OnClickListener for each CardView
        yuzKaloriCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), binKaloriActivity.class);
                startActivity(intent);
            }
        });

        binbesyuzKaloriCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), binbesyuzActivity.class);
                startActivity(intent);

            }
        });

        ikibinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ikibinActivity.class);
                startActivity(intent);
            }
        });

        ikibinbesyuzCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ikibinbesyuzActivity.class);
                startActivity(intent);
            }
        });

        ucbinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ucbinActivity.class);
                startActivity(intent);
            }
        });

        ucbinbesyuzCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ucbinbesyuzActivity.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}