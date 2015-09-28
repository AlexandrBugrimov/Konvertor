package ru.bugrimov.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ru.bugrimov.model.History;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHistory implements Initializable {

    public TextArea textArea;
    public static History hist;
    public Button btnRun;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void actionClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    public static void initHistory(History history) {
        hist = history;
    }

    public void runHistory(ActionEvent actionEvent) {
        List<String> list = hist.getStringList();
        for (int i = 0; i < list.size(); i++) {
            textArea.appendText(list.get(i));
            System.out.print(list.get(i));
        }

    }
}
