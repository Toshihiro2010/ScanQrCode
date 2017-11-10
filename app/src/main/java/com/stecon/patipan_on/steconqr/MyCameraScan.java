package com.stecon.patipan_on.steconqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class MyCameraScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private SurfaceView surfaceView;
    private ZXingScannerView zXingScannerView;
    private TextView textView;


    private Float fFloat = 1.3f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera_scan);
        zXingScannerView = (ZXingScannerView) findViewById(R.id.myZxingScan);
        //zXingScannerView = new ZXingScannerView(getApplicationContext());//this
        zXingScannerView.setAutoFocus(true);
        zXingScannerView.setFocusable(false);

        //setContentView(zXingScannerView);



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
