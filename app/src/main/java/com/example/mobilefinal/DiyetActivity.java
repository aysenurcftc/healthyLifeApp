package com.example.mobilefinal;

import android.adservices.measurement.MeasurementManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DiyetActivity extends AppCompatActivity {


    Button buttonTamamla;
    int totalKalori;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyet);


        buttonTamamla = findViewById(R.id.tamam);

        CardView yuzKaloriCard = findViewById(R.id.yuzkalori);
        CardView binbesyuzKaloriCard = findViewById(R.id.binbesyuzKalori);
        CardView ikibinCard = findViewById(R.id.ikibin);
        CardView ikibinbesyuzCard = findViewById(R.id.ikibinbesyuz);
        CardView ucbinCard = findViewById(R.id.ucbin);
        CardView ucbinbesyuzCard = findViewById(R.id.ucbinbesyuz);


        buttonTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalCalorie();
                showTotalCalorieAlertDialog(totalKalori);

            }
        });

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


    private void calculateTotalCalorie() {
        totalKalori = 0;
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kaloriler", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM kaloriler", null);

            int kaloriIx = cursor.getColumnIndex("kalori");

            while (cursor.moveToNext()) {
                String kalori = cursor.getString(kaloriIx);
                System.out.println("Kalorim toplam: " + kalori);
                totalKalori += Integer.parseInt(kalori);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void deleteDataFromDatabase() {
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kaloriler", MODE_PRIVATE, null);
            String deleteQuery = "DELETE FROM kaloriler";
            database.execSQL(deleteQuery);
            System.out.println("Veri silindi");
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showTotalCalorieAlertDialog(int totalKalori) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout
        View customLayout = getLayoutInflater().inflate(R.layout.custom_layout_dialog, null);
        builder.setView(customLayout);

        // Find views in the custom layout
        TextView titleTextView = customLayout.findViewById(R.id.dialogTitle);
        TextView messageTextView = customLayout.findViewById(R.id.dialogMessage);
        Button okButton = customLayout.findViewById(R.id.dialogButton);


        String message;
        if (totalKalori > 0 && totalKalori < 200) {
            message = "Toplam Kaloriniz: " + totalKalori + "\nHafif yürüyüş yapabilirsiniz.";


        } else if (totalKalori >= 200 && totalKalori < 400) {
            message = "Toplam Kaloriniz: " + totalKalori + "\nTempolu yürüyüş yapabilirsiniz.";

        } else if (totalKalori >= 400 && totalKalori < 600) {
            message = "Toplam Kaloriniz: " + totalKalori + "\n1 saat yüzebilirsiniz.";

        } else if (totalKalori >= 600 && totalKalori < 900) {
            message = "Toplam Kaloriniz: " + totalKalori + "\n1 saat bisiklete binebilirsiniz";

        }else if (totalKalori< 0 && totalKalori > -200){
            message = "Toplam Kaloriniz: " + totalKalori + "\nBir bardak süt içebilirsiniz.";

        }else if (totalKalori< -200 && totalKalori > -400){
            message = "Toplam Kaloriniz: " + totalKalori + "\nBir meyze yiyebilirsiniz.";

        }else if (totalKalori< -400 && totalKalori > -600){
            message = "Toplam Kaloriniz: " + totalKalori + "\nBir avuç badem yiyebilirsiniz";

        } else if (totalKalori< -600 && totalKalori > -900){
            message = "Toplam Kaloriniz: " + totalKalori + "\nBir avuç ceviz yiyebilirsiniz";

        } else if (totalKalori == 0) {
            message = "Toplam Kaloriniz: " + totalKalori + "\nHedefe Ulaşıldı!";

        } else {
            message = "Toplam Kaloriniz: " + totalKalori;

        }

        titleTextView.setText("Kalan Kalori");
        messageTextView.setText(message);

        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        // Set a listener for the button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (totalKalori != 0) {
                    showAlertDialog();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Show the AlertDialog
        dialog.show();
    }


    private void showAlertDialog() {
        totalKalori = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout
        View customLayout = getLayoutInflater().inflate(R.layout.custom_layout_dialog, null);
        builder.setView(customLayout);

        // Find views in the custom layout
        TextView titleTextView = customLayout.findViewById(R.id.dialogTitle);
        TextView messageTextView = customLayout.findViewById(R.id.dialogMessage);
        Button okButton = customLayout.findViewById(R.id.dialogButton);


        titleTextView.setText("Kalan Kalori");
        messageTextView.setText("Hedefe Ulaşıldı");

        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        // Set a listener for the button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                deleteDataFromDatabase();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });

        // Show the AlertDialog
        dialog.show();
    }



}