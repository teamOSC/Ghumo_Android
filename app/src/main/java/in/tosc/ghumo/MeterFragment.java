package in.tosc.ghumo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import in.tosc.ghumo.fetchdata.FareOps;
import in.tosc.ghumo.pojos.Fare;

public class MeterFragment extends android.support.v4.app.Fragment {

    Button start, stop, reset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meter, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);

        final ArrayList<Fare> list = FareOps.getFares(getActivity());
        ArrayList<String> fareString = new ArrayList<>();

        for(Fare fare : list){
            fareString.add(fare.getCity() + ", " + fare.getOperator());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.select_dialog_item, fareString);
        spinner.setAdapter(adapter);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((GhumoApp) getActivity().getApplication()).setFare(list.get(i));
            }
        });

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Meter");

        start = (Button) rootView.findViewById(R.id.meter_start);
        stop = (Button) rootView.findViewById(R.id.meter_stop);
        reset = (Button) rootView.findViewById(R.id.meter_reset);

        final Intent meterIntent = new Intent(getContext(), MeterService.class);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(meterIntent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(meterIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
