package project.bc.nu.projects.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import project.bc.nu.projects.R;

public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private int MY_REQUEST_CODE = 99;
    private ZXingScannerView mScannerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent newActivity = new Intent(ScanQRActivity.this, ResultScanQRActivity.class);
        // newActivity.putExtra("VegID", VegID);
        newActivity.putExtra("VegID", rawResult.getText().toString());
        startActivity(newActivity);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        mScannerView.resumeCameraPreview(this);


        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onRestart() {
        super.onRestart();

        mScannerView.startCamera();
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
