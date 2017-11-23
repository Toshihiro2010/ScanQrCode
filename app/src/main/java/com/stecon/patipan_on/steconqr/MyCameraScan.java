package com.stecon.patipan_on.steconqr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class MyCameraScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private TextView textView;


    private Float fFloat = 1.3f;
    public final static int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera_scan);

//        int cameracheck = checkSelfPermission(Manifest.permission.CAMERA);
//        if (cameracheck != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
//            finish();
//        }




        String title = getIntent().getExtras().getString("title");

        zXingScannerView = (ZXingScannerView) findViewById(R.id.myZxingScan);
        textView = (TextView) findViewById(R.id.tvTitleCamera);
        textView.setText(title);

        //zXingScannerView = new ZXingScannerView(getApplicationContext());//this
        zXingScannerView.setAutoFocus(true);
        //zXingScannerView.setFocusable(false);

        //setContentView(zXingScannerView);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You ไม่ใช้", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //scan();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    private void scan() {
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();


    }

    @Override
    public void handleResult(Result result) {
        //Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("SCAN_RESULT", result.getText());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingScannerView.stopCamera();
    }
}
