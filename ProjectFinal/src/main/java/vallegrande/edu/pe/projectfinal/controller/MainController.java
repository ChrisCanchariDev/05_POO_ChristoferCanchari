package vallegrande.edu.pe.projectfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import vallegrande.edu.pe.projectfinal.model.Producto;
import vallegrande.edu.pe.projectfinal.model.ProductoDAO;
import vallegrande.edu.pe.projectfinal.view.MainView;

public class MainController {

    private MainView view;
    private ProductoDAO dao;
    private ObservableList<Producto> listaObservable;

    public MainController(MainView view) {
        this.view = view;
        this.dao = new ProductoDAO();
        initController();
    }

    private void initController() {
        cargarTabla();

        // Evento de selección de fila en la tabla
        view.getTablaProductos().getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                view.getTxtId().setText(String.valueOf(newSel.getId()));
                view.getTxtNombre().setText(newSel.getNombre());
                view.getTxtCategoria().setText(newSel.getCategoria());
                view.getTxtCantidad().setText(String.valueOf(newSel.getCantidad()));
                view.getTxtPrecio().setText(String.valueOf(newSel.getPrecio()));
            }
        });

        // Eventos de botones
        view.getBtnRegistrar().setOnAction(e -> registrar());
        view.getBtnActualizar().setOnAction(e -> actualizar());
        view.getBtnEliminar().setOnAction(e -> eliminar());
    }

    private void cargarTabla() {
        listaObservable = FXCollections.observableArrayList(dao.listar());
        view.getTablaProductos().setItems(listaObservable);
    }

    private void registrar() {
        try {
            String nombre = view.getTxtNombre().getText();
            String categoria = view.getTxtCategoria().getText();
            int cantidad = Integer.parseInt(view.getTxtCantidad().getText());
            double precio = Double.parseDouble(view.getTxtPrecio().getText());

            Producto p = new Producto(nombre, categoria, cantidad, precio);
            if (dao.registrar(p)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto registrado correctamente.");
                limpiarCampos();
                cargarTabla();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el producto.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "Verifique los valores numéricos ingresados.");
        }
    }

    private void actualizar() {
        try {
            if (view.getTxtId().getText().isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un producto para actualizar.");
                return;
            }
            int id = Integer.parseInt(view.getTxtId().getText());
            String nombre = view.getTxtNombre().getText();
            String categoria = view.getTxtCategoria().getText();
            int cantidad = Integer.parseInt(view.getTxtCantidad().getText());
            double precio = Double.parseDouble(view.getTxtPrecio().getText());

            Producto p = new Producto(id, nombre, categoria, cantidad, precio);
            if (dao.actualizar(p)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto actualizado correctamente.");
                limpiarCampos();
                cargarTabla();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el producto.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "Verifique los datos de entrada.");
        }
    }

    private void eliminar() {
        if (view.getTxtId().getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un producto para eliminar.");
            return;
        }
        int id = Integer.parseInt(view.getTxtId().getText());
        if (dao.eliminar(id)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto eliminado correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el producto.");
        }
    }

    private void limpiarCampos() {
        view.getTxtId().clear();
        view.getTxtNombre().clear();
        view.getTxtCategoria().clear();
        view.getTxtCantidad().clear();
        view.getTxtPrecio().clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}