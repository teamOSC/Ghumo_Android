package in.tosc.ghumo.fetchdata;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import in.tosc.ghumo.pojos.Fare;

/**
 * Created by championswimmer on 22/8/15.
 */
public class FareOps {

    public static ArrayList<Fare> getFares(Context ctx) {
        ArrayList<Fare> fares = new ArrayList<>();
        String jsonString = getJsonFromAssets("fares.json", ctx);
        try {
            JSONArray fareArr = new JSONArray(jsonString);
            Gson gson = new Gson();
            for (int i = 0; i < fareArr.length(); i++) {
                if(fareArr.getJSONObject(i).getString("city").toUpperCase().contains("DELHI")) {
                    try {
                        fares.add(gson.fromJson((fareArr.getJSONObject(i)).toString(), Fare.class));
                    } catch (Exception e) {
                        Log.d("Son,", "you disappoint ",e);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fares;
    }

    public static float calcFare(float kiloMeters, Fare fare) {
        float totalFare = 0;
        totalFare += fare.getBooking_fee();
        totalFare += fare.getMin_fare();

        float chargeableKm = kiloMeters - fare.getMin_dist();
        chargeableKm = (chargeableKm>0) ? chargeableKm : 0;

        totalFare += chargeableKm * fare.getFare_per_km();

        return totalFare;
    }

    public static String getJsonFromAssets (String assetFileName, Context c) {
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
