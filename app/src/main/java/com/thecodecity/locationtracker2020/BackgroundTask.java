package com.thecodecity.locationtracker2020;

import android.os.AsyncTask;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class BackgroundTask extends AsyncTask<String,Void,Void> {
    public BackgroundTask(Context baseContext) {
    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

    public void execute(String s, String method, double latitude, double longitude) {
    }
}
