package com.example.macie.mobilecheckout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mScannerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScannerButton = (Button) findViewById(R.id.to_scanner_button);
        mScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToScanner();
            }
        });
    }

    public void goToScanner(){
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }
}
