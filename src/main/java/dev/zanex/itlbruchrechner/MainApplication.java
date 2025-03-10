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
        Scene scene = new Scene(root, 500, 420);

        primaryStage.setTitle("Bruchrechner");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Integer[] calcFraction(int num1, int den1, int num2, int den2, char op) {
        int resultNum = 0;
        int resultDen = 0;

        switch (op) {
            case '+':
                resultNum = num1 * den2 + num2 * den1;
                resultDen = den1 * den2;
                break;
            case '-':
                resultNum = num1 * den2 - num2 * den1;
                resultDen = den1 * den2;
                break;
            case '*':
                resultNum = num1 * num2;
                resultDen = den1 * den2;
                break;
            case '/':
                resultNum = num1 * den2;
                resultDen = den1 * num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }

        // Simplify the fraction
        int gcd = gcd(resultNum, resultDen);
        resultNum /= gcd;
        resultDen /= gcd;

        return new Integer[]{resultNum, resultDen};
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
