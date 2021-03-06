package com.example.macie.mobilecheckout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.macie.mobilecheckout.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton goToScannerButton;
    private ImageButton goToBasketButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.Slide;

        setContentView(R.layout.activity_main);

        goToScannerButton = (ImageButton) findViewById(R.id.go_to_scanner_button);
        goToScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScannerActivity.class);
                startActivity(intent);
            }
        });

        goToBasketButton = (ImageButton)findViewById(R.id.main_go_to_basket_button);
        goToBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VirtualBasketActivity.class);
                startActivity(intent);
            }
        });
    }
}
