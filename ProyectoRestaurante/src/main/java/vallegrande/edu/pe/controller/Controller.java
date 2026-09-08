package vallegrande.edu.pe.controller;

import javafx.scene.control.Alert;

public class Controller {

    public void handleIniciarSesion() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Iniciar Sesión");
        alert.setHeaderText(null);
        alert.setContentText("Redirigiendo al formulario de inicio de sesión...");
        alert.showAndWait();
    }

    public void handleVerEstadoMesas() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Estado de Mesas");
        alert.setHeaderText(null);
        alert.setContentText("Cargando la vista del plano general de mesas...");
        alert.showAndWait();
    }
}