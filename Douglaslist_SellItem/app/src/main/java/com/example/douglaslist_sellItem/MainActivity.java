package com.example.douglaslist_sellItem;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    EditText txtTitle, edPrice, txtInputDescription;
    Button btnPost;
    RadioButton rbSell;
    RadioButton rbShare;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        txtTitle = findViewById(R.id.txtTitle);
        btnPost = findViewById(R.id.btnPost);
        rbSell = findViewById(R.id.rbSell);
        rbShare = findViewById(R.id.rbShare);
        edPrice = findViewById(R.id.edPrice);
        txtInputDescription = findViewById(R.id.txtInputDescription);
        databaseHelper = new DatabaseHelper(this);

        // Set initial checked state of RadioButton "rbsell"
        rbSell.setChecked(true);
        // Fill EditText with predetermined price when "rbsell" is checked
        edPrice.setText("$0");

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getText().toString();
                String description = txtInputDescription.getText().toString();
                String price = edPrice.getText().toString();

                // Check if the price is valid
                if (rbSell.isChecked() && price.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a price for selling", Toast.LENGTH_SHORT).show();
                    return;
                } else if (rbSell.isChecked() && Float.parseFloat(price) <= 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid price for selling", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call DatabaseHelper method to insert data into the database
                boolean isInserted = databaseHelper.post(title, Double.parseDouble(price), description);

                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Item posted successfully", Toast.LENGTH_SHORT).show();
                    // Clear input fields after successful insertion
                    txtTitle.setText("");
                    edPrice.setText("");
                    txtInputDescription.setText("");

                    // Create an Intent to navigate to SellingResultActivity
                    Intent intent = new Intent(MainActivity.this, SellingSuccess.class);
                    // Pass data as extras
                    intent.putExtra("title", title);
                    intent.putExtra("description", description);
                    intent.putExtra("price", price);
                    // Start SellingResultActivity
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to post item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



