package com.redspot.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Objects;

public class SelectCityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_city, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button backToMain = Objects.requireNonNull(getView()).findViewById(R.id.editCityDone);
        CheckBox windAndPressure = getView().findViewById(R.id.showWindAndPressure);
        EditText city = getView().findViewById(R.id.editTextCity);

        backToMain.setOnClickListener(v -> {
            Intent intentResult = new Intent(getActivity(), MainActivity.class);
            intentResult.putExtra(MainFragment.KEY_CITY, city.getText().toString());
            intentResult.putExtra(MainFragment.KEY_WIND, windAndPressure.isChecked());
            Objects.requireNonNull(getActivity()).setResult(Activity.RESULT_OK, intentResult);
            getActivity().finish();
        });
    }
}