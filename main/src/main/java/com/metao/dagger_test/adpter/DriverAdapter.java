package com.metao.dagger_test.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metao.dagger_test.R;
import com.metao.dagger_test.models.Driver;

import java.util.List;

/**
 * Created by meto on 3/26/17.
 */
public class DriverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Driver> driverList;
    private final Activity activity;
    private final LayoutInflater inflater;

    public DriverAdapter(Activity activity, List<Driver> driverList) {
        this.activity = activity;
        this.driverList = driverList;
        inflater = LayoutInflater.from(activity.getBaseContext());
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View driverView = inflater.inflate(R.layout.driver_item, parent, false);
        return new DriverViewHolder(driverView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DriverViewHolder driverViewHolder = (DriverViewHolder) holder;
        if (driverList != null && driverList.get(position) != null) {
            driverViewHolder.bind(driverList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return this.driverList.size();
    }

    class DriverViewHolder extends RecyclerView.ViewHolder {

        private View driverView;
        private TextView driverNameTextView, driverVinTextView;

        public DriverViewHolder(View itemView) {
            super(itemView);
            this.driverView = itemView;
        }

        public void bind(Driver driver) {
            driverNameTextView = (TextView) this.driverView.findViewById(R.id.driver_name);
            driverVinTextView = (TextView) this.driverView.findViewById(R.id.driver_vin);
            driverNameTextView.setText(driver.name);
            driverVinTextView.setText(driver.vin);
        }
    }
}
