package dev.zanex.itlbruchrechner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        URL url = getClass().getResource("/web/index.html");

        if (url != null) {
            webView.getEngine().load(url.toExternalForm());
        } else {
            System.out.println("Error: Vue.js index.html not found!");
        }

        StackPane root = new StackPane(webView);
        Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("Bruchrechner");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
