package com.redspot.helloworld;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder> {

    ArrayMap<String, Integer> data = new ArrayMap<String, Integer>();

    public FutureWeatherAdapter(Resources resources) {
        super();
        int[] temperatures = resources.getIntArray(R.array.temperatures);
        String[] days = resources.getStringArray(R.array.days);
        for(int i = 0; i < temperatures.length; i++) {
            data.put(days[i], temperatures[i]);
        }
    }

    @NonNull
    @Override
    public FutureWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FutureWeatherAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(data.valueAt(i), data.keyAt(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final private TextView temperature;
        final private TextView day;

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
