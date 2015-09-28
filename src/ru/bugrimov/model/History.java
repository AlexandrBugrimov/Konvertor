package ru.bugrimov.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class History {
    public List<String> getStringList() {
        return stringList;
    }

    private ObservableList<String> stringList = FXCollections.observableArrayList();


    private String getStringList(final int index) {
        return stringList.get(index);
    }

    private void setStringList(final String string) {
        this.stringList.add(string);
    }

    public boolean write(final String message) {
        if (!(message.length() == 0)) {
            this.setStringList(message);
            return true;
        } else {
            return false;
        }
    }

    public String read(final int index) {
        String message;
        if (!stringList.isEmpty()) {
            message = this.getStringList(index);
        } else {
            message = "";
        }
        return message;
    }

    public void clear() {
        this.stringList.clear();
    }

    public int size() {
        return this.stringList.size();
    }
}
