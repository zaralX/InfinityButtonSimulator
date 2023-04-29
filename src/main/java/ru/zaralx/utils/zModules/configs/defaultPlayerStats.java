package ru.zaralx.utils.zModules.configs;


import ru.zaralx.utils.zModules.comboList;

import java.util.ArrayList;

public class defaultPlayerStats {
    public static ArrayList<comboList.arrayClass> defaults = new ArrayList<>();
    public static void setup() {
        defaults.add(new comboList.arrayClass("Balance", 0.0));
        defaults.add(new comboList.arrayClass("TotalGained", 0.0));
        defaults.add(new comboList.arrayClass("Gaining", 1.0));
        defaults.add(new comboList.arrayClass("Rebirths", 1.0));

    }
}
