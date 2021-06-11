package com.redspot.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

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
        final Button backToMain = Objects.requireNonNull(getView()).findViewById(R.id.editCityDone);
        final CheckBox windAndPressure = getView().findViewById(R.id.showWindAndPressure);
        final TextInputEditText city = getView().findViewById(R.id.editTextCity);

        backToMain.setOnClickListener(view -> {
            Intent intentResult = new Intent(getContext(), MainActivity.class);
            intentResult.putExtra(MainFragment.KEY_CITY, city.getText().toString());
            intentResult.putExtra(MainFragment.KEY_WIND, windAndPressure.isChecked());
            Objects.requireNonNull(getActivity()).setResult(Activity.RESULT_OK, intentResult);
            getActivity().finish();
        });
    }
}