package com.redspot.helloworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    private static final String NameSharedPreference = "LOGIN";

    private static final String IsDarkTheme = "IS_DARK_THEME";

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
        Button resetSettings = getActivity().findViewById(R.id.resetSettings);
        Switch toDarkTheme = getActivity().findViewById(R.id.switchTheme);

        final SettingsPresenter presenter = SettingsPresenter.getInstance();
        toDarkTheme.setChecked(presenter.isThemeDark());

        toDarkTheme.setOnClickListener(v -> presenter.setDarkThemeSwitch(toDarkTheme.isChecked()));

        backToMain.setOnClickListener(v -> Objects.requireNonNull(getActivity()).finish());

        resetSettings.setOnClickListener(v -> {
            Snackbar.make(v, getActivity().getString(R.string.reset_settings_confirm), Snackbar.LENGTH_LONG)
                    .setAction(getActivity().getString(R.string.confirm), v1 -> {
                        toDarkTheme.setChecked(false);
                        presenter.setDarkThemeSwitch(false);
                    }).show();
        });
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IsDarkTheme, isDarkTheme);
        editor.apply();
    }

}