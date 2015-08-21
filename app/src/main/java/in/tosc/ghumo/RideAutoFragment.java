package in.tosc.ghumo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

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

        return rootView;
    }
}
