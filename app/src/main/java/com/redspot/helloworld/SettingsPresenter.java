package com.redspot.helloworld;

public final class SettingsPresenter {

    private static SettingsPresenter instance = null;

    private static final Object syncObj = new Object();

    private boolean darkThemeSwitch;

    private SettingsPresenter() {
        darkThemeSwitch = false;
    }

    public void setDarkThemeSwitch(boolean state) {
        this.darkThemeSwitch = state;
    }

    public boolean isThemeDark() {
        return darkThemeSwitch;
    }

    public static SettingsPresenter getInstance() {
        synchronized (syncObj) {
            if (instance == null) {
                instance = new SettingsPresenter();
            }
            return instance;
        }
    }
}
