package com.citi.move_move_;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Math.abs;

public class HelloController implements Initializable {
    public Slider slider;
    @FXML
    private Label msg;
    @FXML
    private Label intervalValue;
    private static Robot robot;
    private static int moveInterval = 5;
    private static Thread mouseMoveThread;

    @FXML
    protected void stopMove() {
        if(mouseMoveThread == null){
            msg.setText("Move mouse is not running");
        }
        mouseMoveThread.interrupt();
    }

    @FXML
    protected void startMove() throws AWTException {
        if(mouseMoveThread != null){
            msg.setText("Move mouse is running");
            return;
        }
        if(robot == null) robot = new Robot();
        mouseMoveThread = new Thread(() -> {
            try {
                while(true) {
                    Platform.runLater(() -> msg.setText("Moving mouse, Interval: " + moveInterval + "mins"));
                    smoothMoveMouse();
                    Thread.sleep(toMin());
                }
            } catch (InterruptedException e) {
                Platform.runLater(() -> msg.setText("Stop Move"));
                mouseMoveThread = null;
            }
        });
        mouseMoveThread.start();
        msg.setText("Start Move, Interval: " + moveInterval + "min");
    }

    void smoothMoveMouse() throws InterruptedException {
        Random random = new Random();
        int x = random.nextInt(400);
        int y = random.nextInt(400);
        Point location = MouseInfo.getPointerInfo().getLocation();
        int _x = location.x, _y = location.y;
        int delay = 10;

        //Move from _x to x
        int step = (x - _x) < 0 ? -10 : 10;
        while (x != _x) {
            if (abs(x - _x) > 10) _x += step;
            else _x = x;
            robot.mouseMove(_x, _y);
            Thread.sleep(delay);
        }

        //Move from _y to y
        step = (y - _y) < 0 ? -10 : 10;
        while (y != _y) {
            if (abs(y - _y) > 10) _y += step;
            else _y = y;
            robot.mouseMove(_x, _y);
            Thread.sleep(delay);
        }

    }

    private long toMin(){
        return (long) moveInterval * 1000 * 60;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setValue(moveInterval);
        slider.valueProperty().addListener((observable, oldValue, newValue) ->
                slider.setValue(Math.round(slider.getValue())));
        slider.valueProperty().addListener((observable, oldValue, newValue) ->
                moveInterval = newValue.intValue());
        intervalValue.textProperty().bindBidirectional(slider.valueProperty(), java.text.NumberFormat.getInstance());
    }
}