package com.stecon.patipan_on.steconqr;

import android.app.DatePickerDialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConfirmActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView txtDate;
    private DatePickerDialog datePickerDialog;

    int mDay ;
    int mMonth ;
    int mYear ;

    int tvDay , tvMonth , tvYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        txtDate = (TextView) findViewById(R.id.txtDate);

//        Date date = new Date();
//        String myString = DateFormat.getDateInstance().format(date);
//        Log.d("Date =>", myString);
//
//        Log.d("Date =>", mDay + "/" + mMonth + "/" + mYear);

        final Date date = new Date();
        Log.d("date => " , date.toString());

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(date);
        Log.d("date => ", timeStamp);
        txtDate.setText(timeStamp);

        //updateDisplay();

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tvGetDate = txtDate.getText().toString().trim();

                if (!tvGetDate.equals("")) {

                    Log.d("date => ", mYear + "/" + mMonth + "/" + mDay);
                    MyDateModify myDateModify = new MyDateModify(tvGetDate);
                    mYear = myDateModify.getYear();
                    mMonth = myDateModify.getMonth();
                    mDay = myDateModify.getDay();

                    Log.d("date => ", mYear + "/" + mMonth + "/" + mDay);

                }else{
                    String tempYear = new SimpleDateFormat("yyyy").format(date);
                    mYear = Integer.parseInt(tempYear);

                    String tempMonth = new SimpleDateFormat("MM").format(date);
                    mMonth = Integer.parseInt(tempMonth);

                    String tempDay = new SimpleDateFormat("dd").format(date);
                    mDay = Integer.parseInt(tempDay);
                    Log.d("date => ", mYear + "/" + mMonth + "/" + mDay);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(ConfirmActivity.this, ConfirmActivity.this, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });


    }

    private void updateDisplay() {
        txtDate.setText(new StringBuilder().append(mDay).append("-").append(mMonth).append("-").append(mYear));

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mYear = i;
        mMonth = i1;
        mDay = i2;

        txtDate.setText(mDay + "/" + mMonth + "/" + mYear);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
