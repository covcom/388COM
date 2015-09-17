package com.example.jianhuayang.mysqlite;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    EditText idText, nameText, phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idText = (EditText) findViewById(R.id.IDText);
        nameText = (EditText) findViewById(R.id.nameText);
        phoneText = (EditText) findViewById(R.id.phoneText);
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
        int anID = Integer.parseInt(idText.getText().toString());
        String aName = nameText.getText().toString();
        String aPhone = phoneText.getText().toString();
        DatabaseHandler db = new DatabaseHandler(this);
        db.addContact(new Contact(anID, aName, aPhone));
    }

    public void parse(View v){
        String jsonStr = new StringBuilder()
                .append("{\n")
                .append("\"id\":2,\n")
                .append("\"name\": \"Paul Nurse\",\n")
                .append("\"phone\": \"654321\"\n")
                .append("}")
                .toString();
        try{
            JSONObject reader = new JSONObject(jsonStr);
            int id  = reader.getInt("id");
            String name  = reader.getString("name");
            String phone  = reader.getString("phone");
            Log.d("parsing results", id + " " + name + " " + phone);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
