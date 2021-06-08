package com.redspot.helloworld;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder> {

    private int[] temp = {23, 28, 20, 18};
    private String[] day = {"tomorrow", "day after", "12.06", "13.06"};

    @NonNull
    @Override
    public FutureWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull FutureWeatherAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(temp[i], day[i]);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView temperature;
        private TextView day;

        public ViewHolder(View itemView) {
            super(itemView);
            temperature = itemView.findViewById(R.id.temperatureFuture);
            day = itemView.findViewById(R.id.dayFuture);
        }

        public void setData(int temperature, String day){
            getTemperature().setText(temperature + "℃");
            getDay().setText(day);
        }

        public TextView getTemperature() {
            return temperature;
        }

        public TextView getDay() {
            return day;
        }
    }
}
