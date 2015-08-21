package in.tosc.ghumo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by championswimmer on 22/8/15.
 */
public class FareOps {

    public ArrayList<Fare> getFares(Context ctx) {
        ArrayList<Fare> fares = new ArrayList<>();
        String jsonString = getJsonFromAssets("fares.json", ctx);
        try {
            JSONArray fareArr = new JSONArray(jsonString);
            Gson gson = new Gson();
            for (int i = 0; i < fareArr.length(); i++) {
                fares.add(gson.fromJson((fareArr.getJSONObject(i)).toString(), Fare.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fares;
    }

    public String getJsonFromAssets (String assetFileName, Context c) {
        String json = null;
        try {
            InputStream is = c.getAssets().open(assetFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
