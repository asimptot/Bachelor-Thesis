package com.example.samsung.suggestionsysteminfair;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class VenuePhotosActivity extends AppCompatActivity {
    ListView photosListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_photos);

        List<String> data = getIntent().getExtras().getStringArrayList("photosList");


        photosListView = (ListView) findViewById(R.id.list_view_photos);
        photosListView.setAdapter(new dataListAdapter(data));
    }


    class dataListAdapter extends BaseAdapter {
        List<String> urls;

        public dataListAdapter(List<String> urls) {
            this.urls = urls;
        }

        @Override
        public int getCount() {
            return urls.size();
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.single_item_photo, parent, false);
            final ImageView imageView = (ImageView) row.findViewById(R.id.iv_photo);
            Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        URL url = null;
                        try {
                            url = new URL(urls.get(position));
                            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            imageView.setImageBitmap(bmp);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            return (row);
        }
    }
}
