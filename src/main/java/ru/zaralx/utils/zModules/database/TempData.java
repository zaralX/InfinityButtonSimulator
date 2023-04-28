package ru.zaralx.utils.zModules.database;

import ru.zaralx.utils.zModules.comboList;

import java.util.ArrayList;

public class TempData {
    String uuid;

    ArrayList<comboList.arrayClass> data;

    public TempData(String uuid, ArrayList<comboList.arrayClass> data) {
        this.uuid = uuid;
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public Double getBalance() {
        return getDouble("Balance");
    }

    public ArrayList<comboList.arrayClass> getData() {
        return data;
    }

    public Integer getInt(String name) {
        for (comboList.arrayClass datum : data) {
            if (datum.getFirst().equals(name)) {
                return (Integer) datum.getSecond();
            }
        }
        return null;
    }

    public Double getDouble(String name) {
        for (comboList.arrayClass datum : data) {
            if (datum.getFirst().equals(name)) {
                return (Double) datum.getSecond();
            }
        }
        return null;
    }

    public void set(String name, Object to) {
        for (comboList.arrayClass datum : data) {
            if (datum.getFirst().equals(name)) {
                datum.setSecond(to);
                return;
            }
        }
    }
}
