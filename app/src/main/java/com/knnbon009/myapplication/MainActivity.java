package com.knnbon009.myapplication;;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import knnbon009.util.Data;
import knnbon009.util.DatabaseHelper;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper =  new DatabaseHelper(this, null, null, 1);
        displayList();
    }

    public void onButtonTap(View v){
        startActivity(new Intent(getApplicationContext(), AddActivity.class));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here

        displayList();
    }

    private void displayList(){

        ArrayList<Data> activities  = dbHelper.getDatabaseContent();
        double total=0;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout scrollView = (LinearLayout)findViewById(R.id.diaryEntryList);
        scrollView.removeAllViews();

        for(Data data: activities){

            CardView cardView =
                    new CardView(this);
            cardView.setLayoutParams(params);

            cardView.setContentPadding(15, 15, 15, 15);
            cardView.setRadius(9);
            TextView textView = new TextView(this);
            cardView.addView(textView);
            scrollView.addView(cardView);
            textView.setText(data+"");
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            textView.setGravity(Gravity.CENTER);;
            total += data.getLitres();
        }
        TextView textView = (TextView)findViewById(R.id.text1);
        textView.setText(total+"");
    }
}
