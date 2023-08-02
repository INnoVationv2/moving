package com.citi.move_move_;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Moving mouse!");
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            minimizeToTray(stage);
        });
        stage.show();
    }

    // 最小化到系统托盘
    private void minimizeToTray(Stage stage) {
        if (SystemTray.isSupported()) {
            try {
                // 加载托盘图标
                InputStream iconStream = HelloApplication.class.getResourceAsStream("icon.png");
                BufferedImage image = ImageIO.read(iconStream);
//                BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                TrayIcon trayIcon = new TrayIcon(image);

                // 设置托盘图标的提示文本
                trayIcon.setToolTip("JavaFX Application");

                // 设置点击托盘图标时的事件处理
                PopupMenu menu = new PopupMenu();
                MenuItem showMenu = new MenuItem("Main menu");
                MenuItem exitMenu = new MenuItem("Exit");
                showMenu.addActionListener(event -> {
                    stage.setIconified(false); // 还原窗口
                    stage.show();
                    stage.toFront(); // 将窗口显示在最前面
                });
                exitMenu.addActionListener(event -> System.exit(0));
                menu.add(showMenu);
                menu.add(exitMenu);
                trayIcon.setPopupMenu(menu);
                // 获取系统托盘对象
                SystemTray tray = SystemTray.getSystemTray();

                // 添加托盘图标到系统托盘
                tray.add(trayIcon);

                // 隐藏主窗口
                stage.hide();
            } catch (AWTException | IOException e) {
                e.printStackTrace();
            }
        } else {
            // 系统不支持系统托盘，你可以在这里处理其他方式的最小化操作
            stage.setIconified(true);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}