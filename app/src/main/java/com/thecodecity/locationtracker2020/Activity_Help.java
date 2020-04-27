package com.thecodecity.locationtracker2020;


import android.view.MenuItem;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;


public class Activity_Help extends AppCompatActivity {

    TextView age ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //age =  (TextView)findViewById(R.id.age);


        //TODO All the names and the date  day will be get from

       // Toast.makeText(this, "vaccinations Said Hello , Stay Home", Toast.LENGTH_SHORT).show();
        String Fname = "Admen";
        String Mname = "Raya";
        String[] SunsName = new String[10] ;

        SunsName[0]= "Hassan";
        Date hassan = new Date( 2012,10,3);//x

        SunsName[1]= "Assad";
        Date assad = new Date( 2014,1,17);//x

        SunsName[2] ="Kawther";
        Date kawther = new Date( 2016,8,13);////4-6

        SunsName[3]= "Omer";
        Date omer = new Date( 2018,12,20);//16 month

        SunsName[4]= "Yasmen";
        Date yasmen = new Date( 2019,10,3);//7 month




        String month1 = "BCG , OPV";
        String month2 = "OPV , DPT , Hib , Hepatitis B ,(+First)";
        String month4 = "OPV , DPT , Hib , Hepatitis B ,(+Second)";
        String month6 = "OPV , DPT , Hib , Hepatitis B ,(+Third)";
        String month9 = "OPV ,(+Forth)" ;
        String month12 = "MMR (+First), OPV (+Booster)";
        String month18 = "MMR (+Second), OPV (+Booster) , DPT (+Booster)";
        String  month64 = "OPV , DT ,(+Booster)";

       // long hage = getAgeMonths(hassan);














    }




    private long getAgeMonths(Date EDate){
       // Date today = Calendar.getInstance().getTime();
        long months ;
        months = EDate.getTime()/1000/60/60/24/30;

        return months;

       // age.setText("age in month"+ months);

        //SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
       // int ageMon = today.get()

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){
            super.onBackPressed();
            //  Intent HelpIntent = new Intent(this,MainActivity.class);
            //startActivity(HelpIntent);
            //Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

}











































































//TODO //Iam Mohamed Hassan Mohamed
//TODO //Iam Programmer from sudan ^_*   //  (0)(0)
