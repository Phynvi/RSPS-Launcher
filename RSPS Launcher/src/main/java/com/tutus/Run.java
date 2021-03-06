package com.tutus;

import com.tutus.audio.AudioManager;
import com.tutus.versioncontrol.CacheVersionTask;
import com.tutus.versioncontrol.client.ClientVersionTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Run extends Application implements EventHandler<ActionEvent> {

    Button launchButton;
    AudioManager audioManager = new AudioManager();
    CacheVersionTask cacheVersionTask = new CacheVersionTask();
    ClientVersionTask clientVersionTask = new ClientVersionTask();

    public static void main(String[] args) {
        System.out.println("Launched the program");

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tutus Launcher");
        launchButton = new Button();
        launchButton.setText("Download File Test");
        launchButton.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(launchButton);

        Scene mainScene = new Scene(layout, Configuration.appWidth, Configuration.appHeight);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        if (Configuration.enableAudio == true) {
            audioManager.playMusic();
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == launchButton){
            System.out.println("Button Pressed");
            try {
                cacheVersionTask.checkCacheVersionTask();
                clientVersionTask.checkClientVersionTask();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
