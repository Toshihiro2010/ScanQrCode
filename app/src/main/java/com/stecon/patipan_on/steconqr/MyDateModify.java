package com.stecon.patipan_on.steconqr;

/**
 * Created by patipan_on on 11/14/2017.
 */

public class MyDateModify {

    private int day;
    private int month;
    private int year;

    private String strDate;

    public MyDateModify(String strInput) {
        String str = strInput;
        int strLength = str.length();
        int first = str.indexOf("/");
        int last = str.lastIndexOf("/");

        this.day = Integer.parseInt(str.substring(0, first));
        this.month = Integer.parseInt(str.substring(first + 1, last));
        this.year = Integer.parseInt(str.substring(last+1));

    }



    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getStrDate(){
        strDate = day + "/" + month + "/" + year;

        return strDate;
    }
}
