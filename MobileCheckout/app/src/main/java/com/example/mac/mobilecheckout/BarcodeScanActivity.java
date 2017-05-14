package com.example.mac.mobilecheckout;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mac.fragments.ProductDetailsFragment;

public class BarcodeScanActivity extends AppCompatActivity {

    private Button mScannedButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scan);

        mScannedButton = (Button)findViewById(R.id.post_scan_button);
        mScannedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailsFragment productDetailsFragment =
                        new ProductDetailsFragment();
                FragmentTransaction ft =
                        getSupportFragmentManager().beginTransaction();

                ft.add(R.id.fragment_container, productDetailsFragment);
                ft.commit();

            }
        });


    }
}
