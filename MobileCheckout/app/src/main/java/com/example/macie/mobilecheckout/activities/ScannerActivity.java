package com.example.macie.mobilecheckout.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.macie.mobilecheckout.fragments.PopupFragment;
import com.example.macie.mobilecheckout.R;
import com.example.macie.mobilecheckout.program_logic.Product;
import com.example.macie.mobilecheckout.program_logic.ProductFactory;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScannerActivity extends AppCompatActivity {

    private SurfaceView cameraPreview;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;
    private BroadcastReceiver productReceiver, popupCloseReceiver;
    private ImageButton goToBasketButton;
    private static int MY_REQUEST_CODE = 0;
    private static boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.Slide;
        setContentView(R.layout.activity_scanner);

        cameraPreview = (SurfaceView)findViewById(R.id.camera_preview);
        goToBasketButton = (ImageButton)findViewById(R.id.scanner_go_to_basket_button);

        goToBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VirtualBasketActivity.class);
                startActivity(intent);
            }
        });

        barcodeDetector = new BarcodeDetector.Builder(this).build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                requestCameraPermission();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        productReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                    showDialog((Product) intent.getParcelableExtra("product"));
            }
        };
        registerReceiver(productReceiver, new IntentFilter("Product Information"));


        popupCloseReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                barcodeDetection();
            }
        };
        registerReceiver(popupCloseReceiver, new IntentFilter("Close Popup"));

        barcodeDetection(); // mam bc
        // pop-up
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(productReceiver);
        unregisterReceiver(popupCloseReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permission, grantResult);
        if(requestCode == MY_REQUEST_CODE) {
            if (grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                createCameraSource();
            } else {
                // THIS DOESNT WORK
                Toast.makeText(ScannerActivity.this, R.string.camera_denied_message, Toast.LENGTH_SHORT);
                requestCameraPermission();
            }
        }
    }
    // TO REVISE
    private void requestCameraPermission() {
        requestPermissions(new String[]{android.Manifest.permission.CAMERA}, MY_REQUEST_CODE);
    }

    private void createCameraSource() {
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();

        try {
            if (ActivityCompat.checkSelfPermission(ScannerActivity.this,
                    android.Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED) {
                //permission denied
                finish();
            }
            cameraSource.start(cameraPreview.getHolder());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void barcodeDetection() {
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if(barcodes.size()>0){
                    barcodeDetector.release();
                    Barcode barcode = barcodes.valueAt(0);
                    ProductFactory.downloadProductInformation(getApplicationContext(), barcode.displayValue);

                }
            }
        });
    }

    public void showDialog(Product product) {
        System.out.println("haha");
        DialogFragment newFragment = PopupFragment.newInstance(R.string.app_name, product);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        newFragment.show(ft, "dialog");
    }
}
