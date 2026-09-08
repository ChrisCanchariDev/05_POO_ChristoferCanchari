package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class View {
    private final VBox layout;
    private final Controller controller;

    public View() {
        this.controller = new Controller();
        this.layout = new VBox(20);
        buildUI();
    }

    private void buildUI() {
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #FAF8F5; -fx-padding: 40;");

        // Logo
        Label logoLabel = new Label("🍽️");
        logoLabel.setFont(Font.font("System", 64));

        // Título
        Label titleLabel = new Label("GourmetFlow");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 36));
        titleLabel.setStyle("-fx-text-fill: #212121;");

        // Subtítulo
        Label subtitleLabel = new Label("Sistema inteligente de gestión de comandas y mesas");
        subtitleLabel.setFont(Font.font("System", 16));
        subtitleLabel.setStyle("-fx-text-fill: #757575;");

        // Botones
        Button btnLogin = new Button("Iniciar Sesión");
        btnLogin.setPrefWidth(240);
        btnLogin.setPrefHeight(45);
        btnLogin.setStyle("-fx-background-color: #E65100; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand;");
        btnLogin.setOnAction(e -> controller.handleIniciarSesion());

        Button btnMesas = new Button("Ver Estado de Mesas");
        btnMesas.setPrefWidth(240);
        btnMesas.setPrefHeight(45);
        btnMesas.setStyle("-fx-background-color: transparent; -fx-text-fill: #E65100; -fx-border-color: #E65100; -fx-border-width: 2; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 8; -fx-cursor: hand;");
        btnMesas.setOnAction(e -> controller.handleVerEstadoMesas());

        layout.getChildren().addAll(logoLabel, titleLabel, subtitleLabel, btnLogin, btnMesas);
    }

    public VBox getView() {
        return layout;
    }
}