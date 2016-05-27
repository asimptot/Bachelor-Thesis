package com.example.samsung.suggestionsysteminfair;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class FetchNextVenueTask extends AsyncTask<String, Void, String[]> {

    private final String LOG_TAG = FetchNextVenueTask.class.getSimpleName();
    private Activity activity;

    public FetchNextVenueTask(Activity activity) {
        this.activity = activity;
    }

    private String[] getNextVenuesDataFromJson(String forecastJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String OWM_RESPONSE = "response";
        final String OWM_NEXT_VENUES = "nextVenues";
        final String OWM_ITEMS = "items";
        final String OWM_NAME = "name";

        JSONObject responseJson = new JSONObject(forecastJsonStr);
        JSONObject nextVenuesObj = responseJson.getJSONObject(OWM_RESPONSE).getJSONObject(OWM_NEXT_VENUES);

        JSONArray itemsList = nextVenuesObj.getJSONArray(OWM_ITEMS);

        String[] resultStrs = new String[itemsList.length()];
        for (int i = 0; i < itemsList.length(); i++) {
            resultStrs[i] = itemsList.getJSONObject(i).getString(OWM_NAME);
            Log.i(LOG_TAG, "Next venue entry: " + resultStrs[i]);
        }
        return resultStrs;

    }

    @Override
    protected String[] doInBackground(String... params) {

        // If there's no zip code, there's nothing to look up.  Verify size of params.
        if (params.length == 0) {
            return null;
        }

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            final String FORECAST_BASE_URL =
                    "https://api.foursquare.com/v2/venues/" + params[0] + "/nextvenues?";
            final String OAUTH_TOKEN_PARAM = "oauth_token";
            final String DATE_PARAM = "v";

            final String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(DATE_PARAM, date)
                    .appendQueryParameter(OAUTH_TOKEN_PARAM, "PGJSF4AMJ2E2SJIKSD1EHWVN110YCULONJA2BU0ID0SEERUK")
                    .build();

            URL url = new URL(builtUri.toString());

            Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();

            Log.v(LOG_TAG, "Forecast string: " + forecastJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getNextVenuesDataFromJson(forecastJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        // This will only happen if there was an error getting or parsing the forecast.
        return null;
    }

    @Override
    protected void onPostExecute(String[] result) {
        if (result != null && result.length >= 1) {
            Intent intent = new Intent(activity, VenueDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("venueList", new ArrayList<String>(Arrays.asList(result)));
            intent.putExtras(bundle);
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "No suggestion", Toast.LENGTH_SHORT).show();
        }
    }
}
