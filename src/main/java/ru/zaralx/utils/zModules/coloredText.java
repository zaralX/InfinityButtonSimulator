package ru.zaralx.utils.zModules;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class coloredText {

    /**
     * Colorize a string %>#FFFFFF#<%
     * @param text - text for colorize
     * @return colored text
     */
    public static String colorize(String text) {
        StringBuilder ex = new StringBuilder(text);

        String[] exe = ex.toString().split("%>");
        List<String> exe2 = new ArrayList<>();
        for (String s : exe) {
            if (s.equals("")) {
                continue;
            }
            if (s.charAt(0) == '#') {
                String[] exe1 = s.split("#<%");
                if (exe1.length > 1) {
                    exe2.add(exe1[0]);
                    exe2.add(exe1[1]);
                } else {
                    exe2.add(exe1[0]);
                }

            } else {
                exe2.add(s);
            }
        }

        ex = new StringBuilder();
        for (String s : exe2) {
            if (s.charAt(0) == '#') {
                ex.append(ChatColor.of(s));
            } else {
                ex.append(s);
            }
        }

        return ex.toString();
    }

    public static String random() {
        StringBuilder ex = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int random = new Random().nextInt(16);
            switch (random) {
                case 0: ex.append("0"); break;
                case 1: ex.append("1"); break;
                case 2: ex.append("2"); break;
                case 3: ex.append("3"); break;
                case 4: ex.append("4"); break;
                case 5: ex.append("5"); break;
                case 6: ex.append("6"); break;
                case 7: ex.append("7"); break;
                case 8: ex.append("8"); break;
                case 9: ex.append("9"); break;
                case 10: ex.append("A"); break;
                case 11: ex.append("B"); break;
                case 12: ex.append("C"); break;
                case 13: ex.append("D"); break;
                case 14: ex.append("E"); break;
                case 15: ex.append("F"); break;
            }
        }
        return ex.toString();
    }
}
