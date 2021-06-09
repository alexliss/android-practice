package com.redspot.helloworld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainFragment extends Fragment {

    private final static int REQUEST_CODE_SELECT_CITY = 1;
    public final static String KEY_CITY = "city";
    public final static String KEY_WIND = "wind";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button refresh = Objects.requireNonNull(getView()).findViewById(R.id.refresh);
        final Button changeCity = getView().findViewById(R.id.changeCity);
        final Button goToSettings = getView().findViewById(R.id.goToSettings);
        final Button goToBrowser = getView().findViewById(R.id.goToBrowser);
        final TextView counter = getView().findViewById(R.id.counter);
        final MainPresenter presenter = MainPresenter.getInstance();

        counter.setText(((Integer)presenter.getCounter()).toString());
        initFutureWeather();

        String instanceState;
        if (savedInstanceState == null){
            instanceState = getString(R.string.first_launch);
        }
        else {
            instanceState = getString(R.string.relaunching);
        }

        Toast.makeText(getActivity(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();

        refresh.setOnClickListener(v -> {
            presenter.incrementCounter();
            counter.setText(((Integer)presenter.getCounter()).toString());
        });

        changeCity.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SelectCityActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_CITY);
        });

        goToSettings.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
        });

        goToBrowser.setOnClickListener(v -> {
            final String url = getResources().getString(R.string.site);
            final Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SELECT_CITY:
                // проверка на результат внутри для обработки случаев, когда результат не ок (специфический код, так сказатб)
                if (resultCode == Activity.RESULT_OK) {
                    TextView city = Objects.requireNonNull(getView()).findViewById(R.id.city);
                    city.setText(data.getStringExtra(KEY_CITY));
                    TextView windAndPressure = getView().findViewById(R.id.windAndPressure);
                    if (data.getBooleanExtra(KEY_WIND, false)) {
                        windAndPressure.setVisibility(View.VISIBLE);
                    } else {
                        windAndPressure.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void initFutureWeather() {
        RecyclerView recyclerView = getActivity().findViewById(R.id.futureWeather);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration separator = new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL);
        separator.setDrawable(getActivity().getDrawable(R.drawable.future_weather_separator));
        recyclerView.addItemDecoration(separator);

        FutureWeatherAdapter adapter = new FutureWeatherAdapter(getResources());
        recyclerView.setAdapter(adapter);

    }
}