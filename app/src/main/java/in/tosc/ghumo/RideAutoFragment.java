package in.tosc.ghumo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by naman on 22/08/15.
 */
public class RideAutoFragment extends Fragment {

    private static final String ACTION ="action";
    public static final String BROWSING_TAXI="browsing_taxi";
    public static final String BROWSING_AUTO="browsing_auto";

    private GoogleMap mMap;

    RecyclerView recyclerView;
    AutoTaxiAdapter adapter;

    public static RideAutoFragment newInstance(String action){
        RideAutoFragment fragment=new RideAutoFragment();
        Bundle args = new Bundle();
        args.putString(ACTION,action);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ride_autotaxi, container, false);

        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

        recyclerView=(RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (getArguments().getString(ACTION).equals(BROWSING_AUTO)){
            //do auto stuff
            adapter=new AutoTaxiAdapter(getActivity(),R.layout.item_ride_auto,new ArrayList());
            recyclerView.setAdapter(adapter);
        } else {
            //do taxi stuff
            adapter=new AutoTaxiAdapter(getActivity(),R.layout.item_ride_taxi,new ArrayList());
            recyclerView.setAdapter(adapter);
        }

        setCurrentLocation();
        return rootView;
    }

    private void setCurrentLocation(){
        try {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    3000, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                            CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
                            mMap.moveCamera(center);
                            mMap.animateCamera(zoom);
                        }
                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }
                        @Override
                        public void onProviderEnabled(String provider) {
                        }
                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }
}
