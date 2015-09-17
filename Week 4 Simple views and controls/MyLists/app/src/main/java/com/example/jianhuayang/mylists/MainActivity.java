package com.example.jianhuayang.mylists;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ListActivity {
//    String[] presidents = {
//            "Dwight D. Eisenhower",
//            "John F. Kennedy",
//            "Lyndon B. Johnson",
//            "Richard Nixon",
//            "Gerald Ford",
//            "Jimmy Carter",
//            "Ronald Reagan",
//            "George H. W. Bush",
//            "Bill Clinton",
//            "George W. Bush",
//            "Barack Obama"
//    };

    String[] presidents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        presidents = getResources().getStringArray(R.array.first_names);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, presidents));
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "this is president" + presidents[position], Toast.LENGTH_SHORT).show();
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
