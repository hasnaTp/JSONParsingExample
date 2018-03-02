package com.tecelm.hasna.jsonparsingexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class EarthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);
        ListView lv = (ListView)findViewById(R.id.list2);
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        CustomAdapter2 adapter = new CustomAdapter2(getApplicationContext(), earthquakes);
        lv.setAdapter(adapter);
    }
}
