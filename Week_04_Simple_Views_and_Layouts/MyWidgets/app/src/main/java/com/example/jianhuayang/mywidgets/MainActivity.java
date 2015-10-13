package com.example.jianhuayang.mywidgets;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.os.Handler;


public class MainActivity extends ActionBarActivity {

    private ProgressBar mProgress;
    private int mProgressStatus;
    private static int mProgressStatic;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doSomeWork();
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
            }

            private int doSomeWork() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ++mProgressStatic;
            }
        }).start();

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
