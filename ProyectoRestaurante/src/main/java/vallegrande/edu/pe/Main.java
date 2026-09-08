package vallegrande.edu.pe;

import vallegrande.edu.pe.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        View welcomeView = new View();
        Scene scene = new Scene(welcomeView.getView(), 900, 600);

        primaryStage.setTitle("GourmetFlow - Bienvenida");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}