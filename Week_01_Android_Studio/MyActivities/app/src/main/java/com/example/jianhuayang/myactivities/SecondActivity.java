package com.example.jianhuayang.myactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class SecondActivity extends Activity {
    String logm = "xxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent theInt = getIntent();
        Log.d(logm, theInt.getStringExtra("string1"));
        Log.d(logm, theInt.getStringExtra("string2"));
        Log.d(logm, theInt.getExtras().getString("bundlestr"));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickText(View arg0) {

        Intent bIntent = new Intent();
        bIntent.putExtra("stringback", "this is the string come back");
        setResult(RESULT_OK, bIntent);
        finish();
    }
}
