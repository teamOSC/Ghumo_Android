package in.tosc.ghumo;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.adnansm.timelytextview.TimelyView;

import java.util.ArrayList;

import in.tosc.ghumo.fetchdata.FareOps;
import in.tosc.ghumo.pojos.Fare;
import in.tosc.ghumo.widgets.ScrollingImageView;

public class MeterFragment extends android.support.v4.app.Fragment {

    Button start, stop, reset;
    ScrollingImageView image1,image2;
    View scrollingFrame;
    ImageView van;
    TimelyView timelyView11,timelyView12,timelyView13,timelyView14,timelyView21,timelyView22,timelyView23,timelyView24;
    com.nineoldandroids.animation.ObjectAnimator objectAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meter, container, false);

        scrollingFrame=rootView.findViewById(R.id.scrollingFrame);
        image1=(ScrollingImageView) rootView.findViewById(R.id.scollingImage1);
        image2=(ScrollingImageView) rootView.findViewById(R.id.scollingImage2);
        van=(ImageView) rootView.findViewById(R.id.van);
        timelyView11=(TimelyView) rootView.findViewById(R.id.timelyView11);
        timelyView12=(TimelyView) rootView.findViewById(R.id.timelyView12);
        timelyView13=(TimelyView) rootView.findViewById(R.id.timelyView13);
        timelyView14=(TimelyView) rootView.findViewById(R.id.timelyView14);
        timelyView21=(TimelyView) rootView.findViewById(R.id.timelyView21);
        timelyView22=(TimelyView) rootView.findViewById(R.id.timelyView22);
        timelyView23=(TimelyView) rootView.findViewById(R.id.timelyView23);
        timelyView24=(TimelyView) rootView.findViewById(R.id.timelyView24);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);

        final ArrayList<Fare> list = FareOps.getFares(getActivity());
        ArrayList<String> fareString = new ArrayList<>();

        for(Fare fare : list){
            fareString.add(fare.getCity() + ", " + fare.getOperator());
        }

        Log.d("Size", fareString.size() + "");

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.select_dialog_item, fareString);
        spinner.setAdapter(adapter);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((GhumoApp) getActivity().getApplication()).setFare(list.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                startMovingAnimation();
                getActivity().startService(meterIntent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMovingAnimation();
                getActivity().stopService(meterIntent);
            }
        });

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                objectAnimator=timelyView11.animate(8,9);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        },2000);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void startMovingAnimation(){
        scrollingFrame.setVisibility(View.VISIBLE);
        image1.start();
        image2.start();
        Animation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.1f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f);
        mAnimation.setDuration(200);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        van.setAnimation(mAnimation);
    }

    private void stopMovingAnimation(){
        scrollingFrame.setVisibility(View.GONE);
        image1.stop();
        image2.stop();
    }
}
