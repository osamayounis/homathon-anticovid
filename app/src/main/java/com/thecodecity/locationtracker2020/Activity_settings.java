package com.thecodecity.locationtracker2020;




 import android.view.MenuItem;
 import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.view.Menu;
 import android.view.MenuItem;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;

 import com.thecodecity.locationtracker2020.R;


 public class Activity_settings extends AppCompatActivity {
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_settings);
         Toast.makeText(this, "Setting Said Hello , Stay Home", Toast.LENGTH_SHORT).show();
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

