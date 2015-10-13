package com.example.jianhuayang.myactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    String tag = "Lifecycle";
    String logM = "yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "In the onCreate() event");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(tag, "In the onStart() event");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(tag, "In the onResume() event");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(tag, "In the onPause() event");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(tag, "In the onStop() event");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "In the onDestroy() event");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void go2nd(View arg0) {
        Intent aIntent = new Intent("com.example.jianhuayang.myactivities.SecondActivity");
        aIntent.putExtra("string1", "this is first string");
        aIntent.putExtra("string2", "this is second string");
        Bundle aBundle = new Bundle();
        aBundle.putString("bundlestr", "this is bundle string");
        aIntent.putExtras(aBundle);
        startActivityForResult(aIntent, 1234);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            Log.d(logM, "success return");
            Log.d(logM, data.getStringExtra("stringback"));
        }
    }
}
