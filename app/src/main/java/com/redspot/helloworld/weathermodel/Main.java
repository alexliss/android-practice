package com.redspot.helloworld.weathermodel;

public class Main {
    private float temp;
    private float feelsLike;
    private int pressure;
    private int humidity;

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp() {
        return temp;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getPressure() {
        return pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }
}
