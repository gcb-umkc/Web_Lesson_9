package com.example.vijaya.myorder;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Summary extends AppCompatActivity {
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView textView = findViewById(R.id.textView);
        message = getIntent().getStringExtra("order");
        textView.setText(message);

    }

    public void sendEmail(View view) {
        message = getIntent().getStringExtra("order");

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_SUBJECT, "Pizza Order");
        email.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(email, "Send mail..."));
    }


    public void returnMain(View view){
        Intent returnMain = new Intent(Summary.this, MainActivity.class);
        startActivity(returnMain);
    }
}