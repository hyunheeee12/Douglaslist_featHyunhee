package com.example.douglaslist_sellItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SellingSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_success);
        // Initialize TextViews

        TextView txtSummary = findViewById(R.id.txtSummary);

                // Get data passed from MainActivity
                Intent intent = getIntent();
                if (intent != null && intent.getExtras() != null) {
                    String title = intent.getStringExtra("title");
                    String description = intent.getStringExtra("description");
                    String price = intent.getStringExtra("price");

                    // Set data to TextViews

                    txtSummary.setText("Title: " + title + "\nDescription: " + description + "\nPrice: $"+ price);

                }
            }
        }