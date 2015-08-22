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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import in.tosc.ghumo.pojos.Auto;
import in.tosc.ghumo.widgets.DividerItemDecoration;

/**
 * Created by naman on 22/08/15.
 */
public class RideAutoFragment extends Fragment {

    public static final String BROWSING_TAXI = "browsing_taxi";
    public static final String BROWSING_AUTO = "browsing_auto";
    private static final String ACTION = "action";
    RecyclerView recyclerView;
    AutoAdapter autoAdapter;
    TaxiAdapter taxiAdapter;
    private GoogleMap mMap;

    public static RideAutoFragment newInstance(String action) {
        RideAutoFragment fragment = new RideAutoFragment();
        Bundle args = new Bundle();
        args.putString(ACTION, action);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ride_autotaxi, container, false);

        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (getArguments().getString(ACTION).equals(BROWSING_AUTO)) {
            //do auto stuff
            ArrayList<Auto> autoList =new ArrayList<>();
            autoList.add(new Auto("DL 1RL 2720","1.12.3f Kms"));
            autoList.add(new Auto("DL 1RP 0579","1.28 Kms"));
            autoList.add(new Auto("DL 1RT 0541","2.24 Kms"));
            autoList.add(new Auto("DL 1RN 512.3f3","2.90 Kms"));
            autoList.add(new Auto("DL 1RT 2823","3.52 Kms"));
            autoAdapter = new AutoAdapter(getActivity(),autoList);
            recyclerView.setAdapter(autoAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST,R.drawable.item_divider_black));

            addToMap("28.542,77.259", "");
            addToMap("28.554,77.269","");
            addToMap("28.542,77.244","");
            addToMap("28.558,77.245","");
            addToMap("28.5422,77.234","");

        } else {
            //do taxi stuff

            taxiAdapter = new TaxiAdapter(getActivity(),  new ArrayList());
            recyclerView.setAdapter(taxiAdapter);
        }

        setCurrentLocation();

        return rootView;
    }


    private void setCurrentLocation() {

        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(28.54, 77.27));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12.3f);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 1000, 0,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {

                            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                            CameraUpdate zoom = CameraUpdateFactory.zoomTo(12.3f);

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
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        try {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                            CameraUpdate zoom = CameraUpdateFactory.zoomTo(12.3f);

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
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void addToMap(String latlong,String title){

        MarkerOptions markerOptions;
        LatLng position;
        String lati=latlong.substring(0,latlong.indexOf(",")),longi=latlong.substring(latlong.indexOf(",")+1,latlong.length());

        markerOptions = new MarkerOptions();

        position = new LatLng(Double.parseDouble(lati), Double.parseDouble(longi));
        markerOptions.position(position);
        markerOptions.title(title);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_taxi_map));
        mMap.addMarker(markerOptions);

        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(position, 6.0f);


        mMap.animateCamera(cameraPosition);

    }
}
