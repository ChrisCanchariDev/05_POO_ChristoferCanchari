package vallegrande.edu.pe.webpageyalpa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vallegrande.edu.pe.webpageyalpa.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView.getRoot(), 750, 480);

        primaryStage.setTitle("Cooperativa Agraria Yalpa Limitada - JavaFX Desktop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}