package com.example.mobilefinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class SporActivity extends AppCompatActivity {


    int secilenKaloriMiktari = 0;
    int totalKalori = 0;
    Button button3;

    boolean tamamlandi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spor);
        button3 = findViewById(R.id.button3);



        CardView kosuCard = findViewById(R.id.kosu);
        CardView tenisCard = findViewById(R.id.tenis);
        CardView voleybolCard = findViewById(R.id.voleybol);
        CardView beyzbolCard = findViewById(R.id.beyzbol);
        CardView yuzmeCard = findViewById(R.id.yuzme);
        CardView platesCard = findViewById(R.id.plates);
        CardView jimlastikCard = findViewById(R.id.jimlastik);
        CardView bisikletCard = findViewById(R.id.bisiklet);
        CardView antrenmanCard = findViewById(R.id.antrenman);

        setCardClickListener(kosuCard, 900);
        setCardClickListener(tenisCard,318);
        setCardClickListener(voleybolCard,400);
        setCardClickListener(beyzbolCard,500);
        setCardClickListener(yuzmeCard,600);
        setCardClickListener(platesCard,250);
        setCardClickListener(jimlastikCard,250);
        setCardClickListener(bisikletCard,650);
        setCardClickListener(antrenmanCard,300);


        button3.setOnClickListener(v -> {
            tamamlandi = true;
            System.out.println("Toplam Seçilen Kalori: " + secilenKaloriMiktari);
            System.out.println("Kalan kalori: " + totalKalori);
            updateDatabaseWithTotalCalorie(totalKalori);
            showToast("Seçim Eklendi");
        });


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

    }

    private void setCardClickListener(final CardView cardView, final int kaloriMiktari) {
        cardView.setOnClickListener(v -> {
            cardView.setCardBackgroundColor(getResources().getColor(R.color.lavender));
            resetOtherCardColors(cardView.getId());

            if (!tamamlandi) {
                secilenKaloriMiktari += kaloriMiktari;
                System.out.println("Seçilen Kalori : " + secilenKaloriMiktari);
            }
        });
    }


    private void updateDatabaseWithTotalCalorie(int totalKalori) {
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Kaloriler", MODE_PRIVATE, null);

            totalKalori -= secilenKaloriMiktari;
            String updateQuery = "UPDATE kaloriler SET kalori = " + totalKalori;
            database.execSQL(updateQuery);
            System.out.println(totalKalori);

            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private void resetOtherCardColors(int selectedCardId) {
        CardView kosuCard = findViewById(R.id.kosu);
        CardView tenisCard = findViewById(R.id.tenis);
        CardView voleybolCard = findViewById(R.id.voleybol);
        CardView beyzbolCard = findViewById(R.id.beyzbol);
        CardView yuzmeCard = findViewById(R.id.yuzme);
        CardView platesCard = findViewById(R.id.plates);
        CardView jimlastikCard = findViewById(R.id.jimlastik);
        CardView bisikletCard = findViewById(R.id.bisiklet);
        CardView antrenmanCard = findViewById(R.id.antrenman);

        if (selectedCardId != R.id.kosu) {
            kosuCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.tenis) {
            tenisCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.voleybol) {
            voleybolCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.beyzbol) {
            beyzbolCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.yuzme) {
            yuzmeCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.plates) {
            platesCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.jimlastik) {
            jimlastikCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.bisiklet) {
            bisikletCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (selectedCardId != R.id.antrenman) {
            antrenmanCard.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




}