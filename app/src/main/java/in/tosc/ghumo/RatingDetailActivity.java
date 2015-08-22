package in.tosc.ghumo;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by naman on 22/08/15.
 */
public class RatingDetailActivity extends AppCompatActivity {

    RatingBar ratingBar,ratingBarUser;
    View submitFeedback;
    TextView submitText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_detail);

        ratingBar=(RatingBar) findViewById(R.id.rating);
        ratingBarUser=(RatingBar) findViewById(R.id.rating_user);
        submitFeedback=findViewById(R.id.submit_feedback_button);
        submitText=(TextView) findViewById(R.id.submit_text);

        ratingBar.setRating(4.4f);
        ratingBarUser.setRating(0);

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        LayerDrawable stars1 = (LayerDrawable) ratingBarUser.getProgressDrawable();
        stars1.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Driver Ratings");

        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitText.setText("Submitting Feedback...");
                submitText.setCompoundDrawables(null,null,null,null);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RatingDetailActivity.this,"Feedback Submitted",Toast.LENGTH_SHORT).show();
                        submitText.setText("Submit Feedback");
                    }
                },1500);
            }
        });

    }
}
