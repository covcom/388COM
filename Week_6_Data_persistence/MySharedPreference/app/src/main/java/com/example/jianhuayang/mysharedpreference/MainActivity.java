package com.example.jianhuayang.mysharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText name;
    EditText phone;
    public static final String nameKey = "nameKey";
    public static final String phoneKey = "phoneKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameText);
        phone = (EditText) findViewById(R.id.phoneText);
        sharedPreferences = getSharedPreferences("MySharedPreMain", Context.MODE_PRIVATE);

        if (sharedPreferences.contains(nameKey)) {
            name.setText(sharedPreferences.getString(nameKey, ""));
        }

        if (sharedPreferences.contains(phoneKey)) {
            phone.setText(sharedPreferences.getString(phoneKey, ""));
        }

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

    public void save(View v){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nameKey, name.getText().toString());
        editor.putString(phoneKey, phone.getText().toString());
        editor.commit();
        Toast.makeText(this,"data saved",Toast.LENGTH_SHORT).show();

    }
}
