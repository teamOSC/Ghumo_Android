package in.tosc.ghumo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.tosc.ghumo.pojos.Auto;

/**
 * Created by naman on 22/08/15.
 */
public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.ItemHolder> {

    private ArrayList<Auto> arraylist;
    private Activity mContext;

    public AutoAdapter(Activity context, ArrayList<Auto> arrayList) {
        this.mContext = context;
        this.arraylist = arrayList;

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ride_auto, null);
        ItemHolder ml = new ItemHolder(v);
        return ml;
    }

    @Override
    public void onBindViewHolder(final ItemHolder itemHolder, int i) {
        Auto auto=arraylist.get(i);
        itemHolder.number.setText(auto.number);
        itemHolder.distance.setText(auto.distance);
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView number,distance;
        protected ImageView call;

        public ItemHolder(View view) {
            super(view);

            number=(TextView) view.findViewById(R.id.number);
            distance=(TextView) view.findViewById(R.id.distance);
            call=(ImageView) view.findViewById(R.id.call);

            call.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + "9867457644"));
            mContext.startActivity(intent);

        }

    }


}





