package in.tosc.ghumo;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.github.adnansm.timelytextview.TimelyView;

import in.tosc.ghumo.widgets.ScrollingImageView;

public class MeterFragment extends android.support.v4.app.Fragment {

    Button start, stop, reset;
    ScrollingImageView image1,image2;
    View scrollingFrame;
    ImageView van;
    TimelyView timelyView;
    com.nineoldandroids.animation.ObjectAnimator objectAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meter, container, false);

        scrollingFrame=rootView.findViewById(R.id.scrollingFrame);
        image1=(ScrollingImageView) rootView.findViewById(R.id.scollingImage1);
        image2=(ScrollingImageView) rootView.findViewById(R.id.scollingImage2);
        van=(ImageView) rootView.findViewById(R.id.van);
        timelyView=(TimelyView) rootView.findViewById(R.id.timelyView);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

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
                objectAnimator=timelyView.animate(8,9);
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
