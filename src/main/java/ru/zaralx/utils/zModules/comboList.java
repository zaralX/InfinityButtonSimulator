package ru.zaralx.utils.zModules;
public class comboList {
    public static class arrayClass {
        Object first;
        Object second;
        public arrayClass(Object first, Object second) { this.first = first; this.second = second; }

        public void set(Object first_to, Object second_to) { this.first = first_to; this.second = second_to; }
        public void setFirst(Object first_to) { this.first = first_to; }
        public void setSecond(Object second_to) { this.second = second_to; }

        public Object getFirst() { return this.first; }
        public Object getSecond() { return this.second; }
    }
}