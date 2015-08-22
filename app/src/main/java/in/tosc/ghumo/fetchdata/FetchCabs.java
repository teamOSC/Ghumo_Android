package in.tosc.ghumo.fetchdata;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.common.api.Api;

import in.tosc.ghumo.GhumoApp;

import java.util.ArrayList;
import java.util.List;

import in.tosc.ghumo.pojos.Cab;
import in.tosc.ghumo.pojos.CabOperator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by championswimmer on 22/8/15.
 */
public class FetchCabs {

    public ArrayList<Cab> fetchCabs (Activity act, float lat, float lng) {
        CabApi api = ((GhumoApp) act.getApplication()).getApiHandler();
        final ArrayList<Cab> cabs = new ArrayList<>();
        api.getCabs(lat, lng, new Callback<List<CabOperator>>() {
            @Override
            public void success(List<CabOperator> cabOperators, Response response) {
                for (CabOperator cabOp : cabOperators) {
                    ArrayList<CabOperator.CabOptions> cabOptions = cabOp.getOptions();
                    for (CabOperator.CabOptions cabOpt : cabOptions) {
                        cabs.add(new Cab(
                                cabOp.getOperatorName(),
                                cabOpt.getCategory(),
                                cabOpt.getTotalCost(),
                                cabOpt.getPerKmRate(),
                                cabOpt.getImageURL(),
                                cabOpt.getBaseFare(),
                                cabOpt.getMinDistance(),
                                cabOpt.getBaseDistance(),
                                cabOpt.getNearestCab().getTimeLimit(),
                                cabOpt.getNearestCab().getLat(),
                                cabOpt.getNearestCab().getLng()
                        ));
                    }
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

        return cabs;
    }
}
