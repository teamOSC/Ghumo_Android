package in.tosc.ghumo.fetchdata;

/**
 * Created by championswimmer on 22/8/15.
 */
import android.support.annotation.Nullable;

import java.util.List;

import in.tosc.ghumo.pojos.CabOperator;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by championswimmer on 29/6/15.
 */
public interface CabApi {

    String BASE_URL = "http://128.199.128.227:9057/api";

    @GET("/newcabs")
    void getCabs(
            @Query("lat") float lat,
            @Query("lng") float lng,
            Callback<List<CabOperator>> callback);

}