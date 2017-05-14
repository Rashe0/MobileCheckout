package com.example.macie.mobilecheckout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {

    private Button mScannerButton;
    private TextView barcodeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScannerButton = (Button) findViewById(R.id.to_scanner_button);
        barcodeResult = (TextView) findViewById(R.id.barcode_result);
        mScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToScanner();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    //barcodeResult.setText("Barcode value: " + barcode.displayValue);
                    //ProductFactory.createProduct(barcode.displayValue);
                    showDialog();

                } else {
                    barcodeResult.setText("No barcode found");
                }
            }
        }
    }

    public void showDialog() {
        DialogFragment newFragment = PopupFragment.newInstance(R.string.app_name);
        newFragment.show(getSupportFragmentManager().beginTransaction(), "dialog");
    }

    public void goToScanner(){
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivityForResult(intent, 0);
    }

}
