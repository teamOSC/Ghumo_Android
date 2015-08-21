package in.tosc.ghumo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by naman on 22/08/15.
 */
public class AutoTaxiAdapter extends RecyclerView.Adapter<AutoTaxiAdapter.ItemHolder> {

    private ArrayList arraylist;
    private Activity mContext;
    private int layoutID;

    public AutoTaxiAdapter(Activity context, int layoutResID, ArrayList arrayList) {
        this.mContext = context;
        this.layoutID = layoutResID;
        this.arraylist = arrayList;

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layoutID, null);
        ItemHolder ml = new ItemHolder(v);
        return ml;
    }

    @Override
    public void onBindViewHolder(final ItemHolder itemHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ItemHolder(View view) {
            super(view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }


}





