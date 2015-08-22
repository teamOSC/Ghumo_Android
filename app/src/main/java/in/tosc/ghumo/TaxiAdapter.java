package in.tosc.ghumo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.tosc.ghumo.pojos.Cab;

/**
 * Created by naman on 22/08/15.
 */
public class TaxiAdapter extends RecyclerView.Adapter<TaxiAdapter.ItemHolder> {

    private ArrayList<Cab> arraylist;
    private Activity mContext;

    public TaxiAdapter(Activity context, ArrayList<Cab> arrayList) {
        this.mContext = context;
        this.arraylist = arrayList;
        Log.d("lol",String.valueOf(arrayList.size()));

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ride_taxi, null);
        ItemHolder ml = new ItemHolder(v);
        return ml;
    }

    @Override
    public void onBindViewHolder(final ItemHolder itemHolder, int i) {
        Cab cab=arraylist.get(i);
        Picasso.with(mContext).load(cab.getImageURL()).into(itemHolder.image);
        itemHolder.operator.setText(cab.getOperatorName());
        itemHolder.category.setText(" - "+cab.getCategory());
        String estimate = String.valueOf(cab.getTimeLimit());
        String estimateminutes=estimate.substring(0,estimate.indexOf("."));
        itemHolder.eta.setText(""+estimateminutes+"");
        itemHolder.price.setText("₹"+String.valueOf(cab.getPerKmRate())+"/Km + ₹"+cab.getBaseFare()+" Base Fare");
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView operator,category,price,eta;
        protected ImageView image;

        public ItemHolder(View view) {
            super(view);

            image=(ImageView) view.findViewById(R.id.image);
            operator=(TextView) view.findViewById(R.id.operator);
            category=(TextView) view.findViewById(R.id.category);
            price=(TextView) view.findViewById(R.id.price);
            eta=(TextView) view.findViewById(R.id.eta);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }


}






