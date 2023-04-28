package ru.zaralx.utils.zModules;

public class intFormatter {

    public String string = "";
    public intFormatter(double value) {
        String ex;
        if (value >= 1000000000000000000000000000000000000.0) {
            double math = value / 1000000000000000000000000000000000000.0;
            ex = String.format("%.2f", math) + "Un";
        } else if (value >= 1000000000000000000000000000000000.0) {
            double math = value / 1000000000000000000000000000000000.0;
            ex = String.format("%.2f", math) + "De";
        } else if (value >= 1000000000000000000000000000000.0) {
            double math = value / 1000000000000000000000000000000.0;
            ex = String.format("%.2f", math) + "No";
        } else if (value >= 1000000000000000000000000000.0) {
            double math = value / 1000000000000000000000000000.0;
            ex = String.format("%.2f", math) + "Oc";
        } else if (value >= 1000000000000000000000000.0) {
            double math = value / 1000000000000000000000000.0;
            ex = String.format("%.2f", math) + "Sp";
        } else if (value >= 1000000000000000000000.0) {
            double math = value / 1000000000000000000000.0;
            ex = String.format("%.2f", math) + "Sx";
        } else if (value >= 1000000000000000000.0) {
            double math = value / 1000000000000000000.0;
            ex = String.format("%.2f", math) + "Qu";
        } else if (value >= 1000000000000000.0) {
            double math = value / 1000000000000000.0;
            ex = String.format("%.2f", math) + "Qa";
        } else if (value >= 1000000000000.0) {
            double math = value / 1000000000000.0;
            ex = String.format("%.2f", math) + "T";
        } else if (value >= 1000000000.0) {
            double math = value / 1000000000.0;
            ex = String.format("%.2f", math) + "B";
        } else if (value >= 1000000.0) {
            double math = value / 1000000.0;
            ex = String.format("%.2f", math) + "M";
        } else if (value >= 1000.0) {
            double math = value / 1000.0;
            ex = String.format("%.2f", math) + "K";
        } else {
            ex = String.format("%.2f", value);
        }
        string = ex;
    }
}
