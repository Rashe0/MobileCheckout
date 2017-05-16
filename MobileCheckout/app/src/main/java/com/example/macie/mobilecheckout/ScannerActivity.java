package com.example.macie.mobilecheckout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScannerActivity extends AppCompatActivity {

    private SurfaceView cameraPreview;
    private BarcodeDetector barcodeDetector;
    private static int MY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        cameraPreview = (SurfaceView) findViewById(R.id.camera_preview);
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

        barcodeDetection(); // mam bc
        // pop-up
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
        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
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
                    final BroadcastReceiver productReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if(!PopupFragment.isOperating()) {
                                showDialog((Product) intent.getParcelableExtra("product"));
                            }
                        }
                    };
                    registerReceiver(productReceiver, new IntentFilter("Product Information"));


                    BroadcastReceiver popupCloseReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            barcodeDetection();
                        }
                    };
                    registerReceiver(popupCloseReceiver, new IntentFilter("Close Popup"));
//                    finish();
                }
            }
        });
    }

    public void showDialog(Product product) {
        DialogFragment newFragment = PopupFragment.newInstance(R.string.app_name, product);
        newFragment.show(getSupportFragmentManager().beginTransaction(), "dialog");
    }
}
