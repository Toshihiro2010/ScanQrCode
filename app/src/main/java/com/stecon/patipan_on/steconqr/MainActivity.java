package com.stecon.patipan_on.steconqr;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGoScan;
    private Button btnGoMyScan;
    private Button btnGPS;
    public static final int REQUEST_QR_SCAN = 4;


    private TextView tvResult;

    private String strResult;
    private String temp;
    private String myPackageName = "com.google.zxing.client.android";

    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bidWidGet();



        btnGoScan.setOnClickListener(this);
        btnGoMyScan.setOnClickListener(this);
        btnGPS.setOnClickListener(this);

    }



    private void bidWidGet() {
        btnGoScan = (Button) findViewById(R.id.btnGoScanQrCode);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnGoMyScan = (Button) findViewById(R.id.btnGoMyScan);
        btnGPS = (Button) findViewById(R.id.btnGPS);

    }

    @Override
    public void onClick(View view) {
        if (view == btnGoScan) {
//            try {
//
//                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//                //intent.createChooser(intent,"Scan With")
//                //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//                startActivityForResult(intent.createChooser(intent,"Scan with") , REQUEST_QR_SCAN);
//                //startActivityForResult(intent, REQUEST_QR_SCAN);
//
//            } catch (Exception e) {
//                Toast.makeText(this, "e=> Error", Toast.LENGTH_SHORT).show();
//                Log.d("e=> ", e.toString());
//            }
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            Intent chooser = Intent.createChooser(intent, "Scan With");
            chooser.putExtra("SCAN_MODE", "QR_CODE_MODE");


            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(chooser,REQUEST_QR_SCAN);
                //startActivity(intent);
            }else {
                Toast.makeText(this, "Please install Barcode Scanner", Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("market://details?id=com.google.zxing.client.android"));
                startActivity(intent2);

            }

            //startActivityForResult(intent.createChooser(intent,"Scan with") , REQUEST_QR_SCAN);

//            try {
//                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//                startActivityForResult(intent, REQUEST_QR_SCAN);
//            } catch (NullPointerException e) {
//                Log.d("e => ", e.toString());
//                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("market://details?id=" + myPackageName));
//                startActivity(intent);
//            }


        } else if (view == btnGoMyScan) {
            myCheckCamera();
            //startActivity(intent);

        } else if (view == btnGPS) {
            Intent intent = new Intent(this, MyGPS.class);
            startActivity(intent);
        }
    }

    private void myCheckCamera() {

        int cameracheck = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);
        if (cameracheck != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)) {
                showMessageOKCancel("You need to allow access to contacts" ,
                        new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this , new String[]{Manifest.permission.CAMERA}, MyCameraScan.REQUEST_CODE_ASK_PERMISSIONS);
                        }
                });
                return;
            }
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA}, MyCameraScan.REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        Intent intent = new Intent(this, MyCameraScan.class);
        intent.putExtra("title", "สแกนที่จอดรถ");
        startActivityForResult(intent, REQUEST_QR_SCAN);

    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    private void mySetText(){

        tvResult.setText(strResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_QR_SCAN && resultCode == RESULT_OK) {
            strResult = data.getStringExtra("SCAN_RESULT");
            Toast.makeText(this, strResult, Toast.LENGTH_SHORT).show();
            mySetText();

        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("oreiten =", "onSaveInstanceState");
        String temp_value = tvResult.getText().toString();
        outState.putString("tvResult", temp_value);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("oreiten =", "onRestoreInstanceState");
        strResult = savedInstanceState.getString("tvResult");
        mySetText();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MyCameraScan.REQUEST_CODE_ASK_PERMISSIONS:
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


}
