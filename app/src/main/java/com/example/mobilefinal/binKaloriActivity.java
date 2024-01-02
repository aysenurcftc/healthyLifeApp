package com.example.mobilefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class binKaloriActivity extends AppCompatActivity {


    Button sabahYemekButton, ogleYemekButton, aksamYemekButton;
     int totalKalori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_kalori);

        sabahYemekButton = findViewById(R.id.sabahyemek);
        ogleYemekButton = findViewById(R.id.ogleyemek);
        aksamYemekButton = findViewById(R.id.aksamyemek);

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kaloriler", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM kaloriler", null);

            int kaloriIx = cursor.getColumnIndex("kalori");

            while (cursor.moveToNext()) {
                String kalori = cursor.getString(kaloriIx);
                System.out.println("Kalorim toplam: " + kalori);
                totalKalori  = Integer.parseInt(kalori);
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        sabahYemekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalKalori -= 200;
                sabahYemekButton.setClickable(false);
                System.out.println(totalKalori);
                updateDatabase(totalKalori);
                sabahYemekButton.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        ogleYemekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalKalori -=300;
                ogleYemekButton.setClickable(false);
                System.out.println(totalKalori);
                updateDatabase(totalKalori);
                ogleYemekButton.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        aksamYemekButton.setOnClickListener(v -> {
           totalKalori -= 500;
           aksamYemekButton.setClickable(false);
            System.out.println(totalKalori);
            updateDatabase(totalKalori);
            aksamYemekButton.setBackgroundColor(getResources().getColor(R.color.red));
        });
    }


    private void updateDatabase(int totalKalori) {
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kaloriler", MODE_PRIVATE, null);
            String updateQuery = "UPDATE kaloriler SET kalori = " + totalKalori;
            database.execSQL(updateQuery);
            System.out.println(totalKalori);
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}