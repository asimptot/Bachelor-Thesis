package com.example.samsung.suggestionsysteminfair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class VenueDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details);

        List<String> data = getIntent().getExtras().getStringArrayList("venueList");

        ArrayAdapter<String> mForecastAdapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.list_item_venue,
                        R.id.list_item_venue_textview,
                        data);

        ListView listView = (ListView) findViewById(R.id.listview_venue);
        listView.setAdapter(mForecastAdapter);
    }
}
