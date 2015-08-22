package in.tosc.ghumo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
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
import android.widget.Button;
import android.widget.ImageView;

import com.github.adnansm.timelytextview.TimelyView;
import com.nineoldandroids.animation.ObjectAnimator;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.text.DecimalFormat;
import com.github.adnansm.timelytextview.TimelyView;

import java.util.ArrayList;

import in.tosc.ghumo.fetchdata.FareOps;
import in.tosc.ghumo.pojos.Fare;
import in.tosc.ghumo.widgets.ScrollingImageView;

public class MeterFragment extends android.support.v4.app.Fragment{

    Button start, stop, reset;
    ScrollingImageView image1,image2;
    View scrollingFrame;
    ImageView van;
    TimelyView timelyView11,timelyView12,timelyView13,timelyView14,timelyView21,timelyView22,timelyView23,timelyView24;
    com.nineoldandroids.animation.ObjectAnimator objectAnimator;

    int[] distArr = new int[] {0,0,0,0};
    int[] fareArr = new int[] {0,0,0,0};

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

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDigits();
            }
        });

        resetDigits();

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

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext().getApplicationContext()).unregisterReceiver(mMessageReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext().getApplicationContext()).registerReceiver(
                mMessageReceiver, new IntentFilter("fare_update"));
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Float distance = intent.getFloatExtra("distance", 0);
            Float fare = intent.getFloatExtra("fare", 0);
            Log.d("Location", "Km = " + distance + " Fare = " + fare);

            DecimalFormat df = new DecimalFormat("00.00");
            Log.d("Location", "Km = " + (df.format(distance)) + " Fare = " + (df.format(fare)));
            char[] fareCharArray = (df.format(fare)).toCharArray();
            char[] distanceCharArray = (df.format(distance)).toCharArray();

            Log.d("Location", "onReceive " + fareCharArray[0] + fareCharArray[1] + fareCharArray[3] + fareCharArray[4]);
            Log.d("Location", "onReceive " + distanceCharArray[0] + distanceCharArray[1] + distanceCharArray[3] + distanceCharArray[4]);

            tv11((distanceCharArray[0] - '0'));
            tv12((distanceCharArray[1] - '0'));
            tv13((distanceCharArray[3] - '0'));
            tv14((distanceCharArray[4] - '0'));

            tv21((fareCharArray[0] - '0'));
            tv22((fareCharArray[1] - '0'));
            tv23((fareCharArray[3] - '0'));
            tv24((fareCharArray[4] - '0'));


        }
    };

    public void changeDigit (TimelyView tv, int end) {
        ObjectAnimator obja = tv.animate(end);
        obja.setDuration(600);
        obja.start();
    }

    public void changeDigit (TimelyView tv, int start, int end) {
        ObjectAnimator obja = tv.animate(start, end);
        obja.setDuration(600);
        obja.start();
    }

    public void tv11 (int a) {
        if (a != distArr[0]) {
            changeDigit(timelyView11, distArr[0], a);
            distArr[0] = a;
        }
    }
    public void tv12 (int a) {
        if (a != distArr[1]) {
            changeDigit(timelyView12, distArr[1], a);
            distArr[1] = a;
        }
    }
    public void tv13 (int a) {
        if (a != distArr[2]) {
            changeDigit(timelyView13, distArr[2], a);
            distArr[2] = a;
        }
    }
    public void tv14 (int a) {
        if (a != distArr[3]) {
            changeDigit(timelyView14, distArr[3], a);
            distArr[3] = a;
        }
    }


    public void tv21 (int a) {
        if (a != fareArr[0]) {
            changeDigit(timelyView21, fareArr[0], a);
            fareArr[0] = a;
        }
    }
    public void tv22 (int a) {
        if (a != fareArr[1]) {
            changeDigit(timelyView22, fareArr[1], a);
            fareArr[1] = a;
        }
    }
    public void tv23 (int a) {
        if (a != fareArr[2]) {
            changeDigit(timelyView23, fareArr[2], a);
            fareArr[2] = a;
        }
    }
    public void tv24 (int a) {
        if (a != fareArr[3]) {
            changeDigit(timelyView24, fareArr[3], a);
            fareArr[3] = a;
        }
    }

    public void resetDigits () {
        changeDigit(timelyView11, 0);
        changeDigit(timelyView12, 0);
        changeDigit(timelyView13, 0);
        changeDigit(timelyView14, 0);

        changeDigit(timelyView21, 0);
        changeDigit(timelyView22, 0);
        changeDigit(timelyView23, 0);
        changeDigit(timelyView24, 0);
    }


}
