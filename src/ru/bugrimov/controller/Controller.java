package ru.bugrimov.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.bugrimov.model.Editor;
import ru.bugrimov.model.NumberBaseConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField inputDisplay;
    public TextField outputDisplay;
    public TextField ccFromDisplay;
    public TextField ccToDisplay;
    public Slider sliderTo;
    public Slider sliderFrom;
    public Button button_A;
    public Button button_B;
    public Button button_C;
    public Button button_D;
    public Button button_E;
    public Button button_F;
    public Button button_0;
    public Button button_1;
    public Button button_2;
    public Button button_3;
    public Button button_4;
    public Button button_5;
    public Button button_6;
    public Button button_7;
    public Button button_8;
    public Button button_9;
    public MenuItem menuExit;

    private Editor editor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editor = new Editor("0", 10, 10);
        inputDisplay.setEditable(false);
        outputDisplay.setEditable(false);
        ccFromDisplay.setEditable(false);
        ccToDisplay.setEditable(false);

        sliderFrom.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ccFromDisplay.setText(String.valueOf(newValue.intValue()));
            }
        });

        sliderTo.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ccToDisplay.setText(String.valueOf(newValue.intValue()));
            }
        });
    }

    @FXML
    public void sliderToAction() {
        ccToDisplay.setText((long) sliderTo.getValue() + "");
        outputDisplay.setText(editor.clear());
    }

    @FXML
    public void buttonAction(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        String current = editor.addNumber(value);
        inputDisplay.setText(current);
    }

    @FXML
    public void revAction() {
        inputDisplay.setText(editor.addSign());
    }

    @FXML
    public void clearAction() {
        inputDisplay.setText(editor.clear());
        outputDisplay.setText("0");
    }

    @FXML
    public void backspaceAction() {
        inputDisplay.setText(editor.backspace());
    }

    @FXML
    public void separatorAction() {
        if (!editor.findSeparator()) {
            inputDisplay.setText(editor.addSeparator());
        }
    }

    @FXML
    public void runAction() {
        editor.setCcFrom(Long.parseLong(ccFromDisplay.getText()));
        editor.setCcTo(Long.parseLong(ccToDisplay.getText()));
        String zn = "";
        String result;
        String out;
        if (inputDisplay.getText().contains("-")) {
            zn = "-";
            result = inputDisplay.getText().substring(1);
        } else {
            result = inputDisplay.getText();
        }

        if (result.contains(".")) {
            int index = result.indexOf(".");
            String a = result.substring(0, index);
            String b = result.substring(index + 1);

            String c = NumberBaseConverter.numConvertBase(a, (int) editor.getCcFrom(), (int) editor.getCcTo());
            String d = NumberBaseConverter.numConvertBase(b, (int) editor.getCcFrom(), (int) editor.getCcTo());

            out = zn + c + "." + d;
        } else {
            out = zn + NumberBaseConverter.numConvertBase(result, (int)editor.getCcFrom(), (int)editor.getCcTo());
        }
        outputDisplay.setText(out);
        editor.history.write("Исходное: " + inputDisplay.getText() + " (" + Long.parseLong((ccFromDisplay.getText())) + ") --> Результат: " + out + " (" + Long.parseLong((ccToDisplay.getText())) + ")\n");
    }

    @FXML
    public void exitAction() throws IOException {
        Stage stage = (Stage) button_0.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void sliderFromAction() {
        long value = (long)sliderFrom.getValue();
        String string = value + "";
        inputDisplay.setText("0");
        editor.clear();
        ccFromDisplay.setText(string);

    }

    public void showAbout(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/about.fxml"));
            stage.setTitle("О программе...");
            stage.setMaxHeight(320);
            stage.setMinWidth(400);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHistory(ActionEvent event) {
        try {
            ControllerHistory.initHistory(editor.getHistory());
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/history.fxml"));
            stage.setTitle("История");
            stage.setMaxHeight(320);
            stage.setMinWidth(400);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
