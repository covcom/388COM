package com.example.jianhuayang.myfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.WindowManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager aFragmentManager = getFragmentManager();
        FragmentTransaction aFragmentTransaction = aFragmentManager.beginTransaction();

        WindowManager aWindowManager = getWindowManager();
        Display aDisplay = aWindowManager.getDefaultDisplay();

        int orientation = aDisplay.getRotation();

        if (orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270) {
            BlankFragment frag1 = new BlankFragment();
            aFragmentTransaction.replace(R.id.container, frag1);
        } else {
            BlankFragment2 frag2 = new BlankFragment2();
            aFragmentTransaction.replace(R.id.container, frag2);
        }
        aFragmentTransaction.commit();
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
}
