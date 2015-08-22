package in.tosc.ghumo;

import android.app.Application;

import in.tosc.ghumo.fetchdata.CabApi;
import in.tosc.ghumo.pojos.Fare;
import retrofit.RestAdapter;

/**
 * Created by championswimmer on 22/8/15.
 */
public class GhumoApp extends Application {

    private CabApi apiHandler;
    private RestAdapter restAdapter;
    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    private Fare fare = new Fare(
            0,
            "Delhi",
            2,
            0,
            "Auto",
            9,
            25
    );


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public CabApi getApiHandler() {
        //Make sure we just have one instance
        if (apiHandler == null) {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(CabApi.BASE_URL)
                    .build();
            apiHandler = restAdapter.create(CabApi.class);
        }
        return apiHandler;
    }
}
