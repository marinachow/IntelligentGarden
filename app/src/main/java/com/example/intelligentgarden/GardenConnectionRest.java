package com.example.intelligentgarden;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GardenConnectionRest extends AsyncTask<String, Void, String> {
    private final static String URL = "https://api.munier.me/";
    private JSONObject jsonObj = null;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    protected String doInBackground(String... strings) {
        try {
            return get(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String get(String methode) throws IOException, JSONException {
        String url = URL + uid + "/plants/";
        InputStream is = null;
        String parameters = "";
        if (!methode.equals("POST") && !methode.equals("EDIT_SURFACE") && (jsonObj != null)) {
            url += jsonObj.getInt("id");
        }
        if (jsonObj != null) {
            if (methode.equals("PUT")) {
                jsonObj.remove("id");
            }
            parameters  = "data="+ URLEncoder.encode(jsonObj.toString(), "utf-8");
        }
        if (methode.equals("GET_SURFACE")) {
            methode = "GET";
            url = URL + uid + "/dimensions/1";
        }
        if (methode.equals("CREATE_SURFACE")) {
            jsonObj = new JSONObject();
            jsonObj.put("numRows", 1);
            jsonObj.put("numCols", 1);
            methode = "POST";
            url = URL + uid + "/dimensions/1";
            parameters  = "data="+ URLEncoder.encode(jsonObj.toString(), "utf-8");
        }
        if (methode.equals("EDIT_SURFACE")) {
            if (jsonObj != null) {
                methode = "PUT";
                url = URL + uid + "/dimensions/1";
            }
        }
        Log.v("methode", methode);
        Log.v("URL", url+" "+parameters);
        try {
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod(methode);

            if (methode.equals("POST")||methode.equals("PUT")){
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(parameters);
                out.close();
            } else {
                conn.setDoInput(true);
                conn.connect();
            }

            is = conn.getInputStream();
            return readIt(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private String readIt(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            response.append(line).append('\n');
        }
        Log.v("DEBUG",""+response);
        return response.toString();
    }

    public ArrayList<Plant> parsePlant(final String json) {
        try {
            final ArrayList plants = new ArrayList<>();
            final JSONArray jPlantArray = new JSONArray(json);
            for (int i = 0; i < jPlantArray.length(); i++) {
                plants.add(new Plant(jPlantArray.optJSONObject(i)));
            }
            return plants;
        } catch (JSONException e) {
            Log.v("TAG","[JSONException] e : " + e.getMessage());
        }
        return null;
    }

    public Dimensions parseDimensions(final String json) {
        try {
            final JSONObject jDimensionsObj = new JSONObject(json);
            final Dimensions dimensions = new Dimensions(jDimensionsObj);
            return dimensions;
        } catch (JSONException e) {
            Log.v("TAG","[JSONException] e : " + e.getMessage());
        }
        return null;
    }

    public void setJsonObj(JSONObject jsonObj){
        this.jsonObj = jsonObj;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}

