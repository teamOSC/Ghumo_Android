package in.tosc.ghumo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by naman on 22/08/15.
 */
public class RideAutoFragment extends Fragment {

    private static final String ACTION ="action";
    public static final String BROWSING_TAXI="browsing_taxi";
    public static final String BROWSING_AUTO="browsing_auto";

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

        return rootView;
    }
}
