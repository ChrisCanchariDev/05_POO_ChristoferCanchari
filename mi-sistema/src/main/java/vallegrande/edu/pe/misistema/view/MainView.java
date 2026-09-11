package vallegrande.edu.pe.misistema.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class MainView extends BorderPane {
    private Button bntInicio;
    private Button btnUsuario;
    private Button btnProductos;
    private Button btnVentas;
    private Button btnReportes;
    private Button btnConfiguracion;

    public MainView(){
        crearMenu();
        mostrarInicio();
    }
    private void crearMenu(){
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(25));
        menu.setPrefWidth(220);
        Label titulo = new Label("MI SISTEMA");
        titulo.setStyle(
                "-fx-font-size:20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill:#ffffff;"
        );
        bntInicio = crearBoton("Inicio");
        btnUsuario = crearBoton("Usuario");
        btnProductos = crearBoton("Productos");
        btnVentas = crearBoton("Ventas");
        btnReportes = crearBoton("Reportes");
        btnConfiguracion = crearBoton("Configuración");
        menu.getChildren().addAll(
                titulo,
                bntInicio,
                btnUsuario,
                btnProductos,
                btnVentas,
                btnReportes,
                btnConfiguracion
        );
        menu.setStyle("-fx-background-color: #830f1a;");
        setLeft(menu);
    }
    private Button crearBoton(String texto){
        Button boton = new Button(texto);
        boton.setPrefWidth(170);
        boton.setPrefHeight(40);
        boton.setStyle("-fx-background-color: white;" +
                "-fx-text-fill: #1E3A8A;" +
                "-fx-text-size: 14px;" +
                "-fx-background-radius: 8;"
        );
        return boton;
    }
    public void mostrarInicio(){
        VBox contenido = new VBox(10);
        contenido.setAlignment(Pos.CENTER);
        Label titulo = new Label("BIENVENIDO ");
        titulo.setStyle("-fx-font-size: 28px;" +
                "-fx-font-weight: bold;");
        Label texto = new Label("Panel principal de mi sistema");
        contenido.getChildren().addAll(
                titulo,
                texto
        );
        setCenter(contenido);
    }
    public void mostrarUsuario(){
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("USUARIO");
        titulo.setStyle("-fx-font-size: 26ps;" +
                "-fx-font-weight: bold;");
        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Carlos Perez", "Administrador"),
                crearTarjeta("Maria Lopes", "Vendedora"),
                crearTarjeta("Chris Luyo", "Supervisor")
        );
        contenido.getChildren().addAll(
                titulo,
                tarjetas
        );
        setCenter(contenido);
    }
    public void mostrarProductos(){
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("PRODUCTOS");
        titulo.setStyle("-fx-font-size: 26;" +
                "-fx-font-weight: bold;");
        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Laptop Lenovo", "S/ 2500"),
                crearTarjeta("Mouse Ergonómico", "S/ 300"),
                crearTarjeta("Mackbook", "S/ 6000")
        );
        contenido.getChildren().addAll(
                titulo,
                tarjetas
        );
        setCenter(contenido);
    }
    public void mostrarVentas() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("REGISTRO DE VENTAS");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Venta #001", "S/ 2500.00 - Finalizado"),
                crearTarjeta("Venta #002", "S/ 300.00 - Pendiente"),
                crearTarjeta("Venta #003", "S/ 6000.00 - Finalizado")
        );
        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }

    public void mostrarReportes() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("REPORTES GENERALES");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("Ventas Mensuales", "+15% respecto al mes previo"),
                crearTarjeta("Stock Crítico", "5 productos con bajo stock"),
                crearTarjeta("Usuarios Activos", "12 usuarios conectados")
        );
        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }

    public void mostrarConfiguracion() {
        VBox contenido = new VBox(20);
        contenido.setPadding(new Insets(30));
        Label titulo = new Label("CONFIGURACIÓN DEL SISTEMA");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        HBox tarjetas = new HBox(15);
        tarjetas.getChildren().addAll(
                crearTarjeta("General", "Ajustes del sistema y moneda"),
                crearTarjeta("Seguridad", "Permisos y contraseñas"),
                crearTarjeta("Base de Datos", "Copias de seguridad")
        );
        contenido.getChildren().addAll(titulo, tarjetas);
        setCenter(contenido);
    }
    private VBox crearTarjeta(String titulo, String detalle){
        VBox tarjeta = new VBox(8);
        tarjeta.setPadding(new Insets(20));
        tarjeta.setPrefWidth(1800);
        tarjeta.setStyle("-fx-background-color: #EAF2FF;" +
                "-fx-background-radius: 12");
        Label nombre = new Label(titulo);
        nombre.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;");
        Label info = new Label(detalle);
        tarjeta.getChildren().addAll(
                nombre,
                info
        );
        return tarjeta;
    }
    public Button getBntInicio(){
        return bntInicio;
    }
    public Button getBtnUsuario(){
        return btnUsuario;
    }
    public Button getBtnProductos() {
        return btnProductos;
    }
    public Button getBtnVentas() { return btnVentas; }
    public Button getBtnReportes() { return btnReportes; }
    public Button getBtnConfiguracion() { return btnConfiguracion; }
}
