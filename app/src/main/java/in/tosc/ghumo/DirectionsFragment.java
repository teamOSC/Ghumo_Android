package in.tosc.ghumo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;

/**
 * Created by naman on 22/08/15.
 */
public class DirectionsFragment extends Fragment  {

    protected GoogleMap map;
    protected LatLng start;
    protected LatLng end;
    AutoCompleteTextView starting;
    AutoCompleteTextView destination;
    ImageView send;
    private String LOG_TAG = "MyActivity";
    protected GoogleApiClient mGoogleApiClient;
    private PlaceAutoCompleteAdapter mAdapter;
    private ProgressDialog progressDialog;
    private Polyline polyline;


    private static final LatLngBounds BOUNDS_JAMAICA= new LatLngBounds(new LatLng(-57.965341647205726, 144.9987719580531),
            new LatLng(72.77492067739843, -9.998857788741589));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_directions, container, false);
        starting=(AutoCompleteTextView) rootView.findViewById(R.id.start);
        destination=(AutoCompleteTextView) rootView.findViewById(R.id.destination);
        send=(ImageView) rootView.findViewById(R.id.send);

        return rootView;
    }
}
