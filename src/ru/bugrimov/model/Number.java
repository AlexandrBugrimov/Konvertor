package ru.bugrimov.model;

import javafx.beans.property.SimpleStringProperty;

public class Number {
    private SimpleStringProperty number;

    public Number(String number) {
        this.number =  new SimpleStringProperty(number);
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getNumber() {
        return number.get();
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
