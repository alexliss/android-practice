package com.redspot.helloworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button backToMain = Objects.requireNonNull(getActivity()).findViewById(R.id.backToMain);
        Switch toDarkTheme = getActivity().findViewById(R.id.switchTheme);

        final SettingsPresenter presenter = SettingsPresenter.getInstance();
        toDarkTheme.setChecked(presenter.isThemeDark());

        toDarkTheme.setOnClickListener(v -> presenter.setDarkThemeSwitch(toDarkTheme.isChecked()));

        backToMain.setOnClickListener(v -> Objects.requireNonNull(getActivity()).finish());
    }
}