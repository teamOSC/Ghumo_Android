package in.tosc.ghumo;

import android.app.Application;

import com.google.android.gms.common.api.Api;

import in.tosc.ghumo.fetchdata.CabApi;
import retrofit.RestAdapter;

/**
 * Created by championswimmer on 22/8/15.
 */
public class GhumoApp extends Application {

    private CabApi apiHandler;
    private RestAdapter restAdapter;

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
