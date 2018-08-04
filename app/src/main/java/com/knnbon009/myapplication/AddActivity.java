package com.knnbon009.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


import knnbon009.util.Data;
import knnbon009.util.DatabaseHelper;

/**
 * A login screen that offers login via email/password.
 */
public class AddActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbHelper =  new DatabaseHelper(this, null, null, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        return true;
    }

    public void onButtonTap(View v){
        EditText activity = (EditText)findViewById(R.id.activity);
        EditText amount = (EditText)findViewById(R.id.amount);

        dbHelper.addProduct(new Data(activity.getText().toString(),Double.parseDouble(amount.getText().toString())));
        Toast.makeText(getApplicationContext(), activity.getText().toString()+"  added", Toast.LENGTH_SHORT).show();
        activity.getText().clear();amount.getText().clear();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



}

