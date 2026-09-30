package vallegrande.edu.pe.webpageyalpa.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import vallegrande.edu.pe.webpageyalpa.controller.MainController;
import vallegrande.edu.pe.webpageyalpa.model.Producto;

public class MainView {
    private BorderPane root;
    private StackPane contentArea;

    private VBox inicioView;
    private VBox productosView;

    private TableView<Producto> table;
    private MainController controller;

    public MainView() {
        controller = new MainController();
        root = new BorderPane();

        // 1. Crear la barra de navegación superior
        HBox navBar = crearBarraNavegacion();
        root.setTop(navBar);

        // 2. Área central donde cambiarán las vistas
        contentArea = new StackPane();
        contentArea.setPadding(new Insets(20));

        // 3. Generar las vistas de Inicio y Productos
        inicioView = crearVistaInicio();
        productosView = crearVistaProductos();

        // Establecer "Inicio" como la pantalla inicial
        contentArea.getChildren().add(inicioView);

        root.setCenter(contentArea);
    }

    private HBox crearBarraNavegacion() {
        HBox nav = new HBox(15);
        nav.setPadding(new Insets(12, 20, 12, 20));
        nav.setStyle("-fx-background-color: #1b4965; -fx-alignment: center-left;");

        Label title = new Label("COOPERATIVA YALPA");
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnInicio = new Button("🏠 Inicio");
        Button btnProductos = new Button("📦 Productos");

        // Estilos para los botones del menú
        String btnStyle = "-fx-background-color: #2b6cb0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand; -fx-background-radius: 5px;";
        btnInicio.setStyle(btnStyle);
        btnProductos.setStyle(btnStyle);

        // Acciones de navegación
        btnInicio.setOnAction(e -> cambiarVista(inicioView));
        btnProductos.setOnAction(e -> cambiarVista(productosView));

        nav.getChildren().addAll(title, spacer, btnInicio, btnProductos);
        return nav;
    }

    private void cambiarVista(VBox vista) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(vista);
    }

    // --- VISTA 1: INICIO ---
    private VBox crearVistaInicio() {
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("¡Bienvenido al Sistema de Gestión!");
        welcomeLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1b4965;");

        Label subLabel = new Label("Cooperativa Agraria Yalpa Limitada");
        subLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #4a5568;");

        // Tarjeta informativa del sistema
        VBox card = new VBox(12);
        card.setPadding(new Insets(20));
        card.setMaxWidth(520);
        card.setStyle("-fx-background-color: #f7fafc; -fx-border-color: #e2e8f0; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");

        Label cardTitle = new Label("📌 Información del Sistema");
        cardTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #2d3748;");

        Label cardDesc = new Label("Aplicación Desktop integrada con la Base de Datos MySQL del proyecto Web.\n\n" +
                "• Haz clic en el botón '📦 Productos' en el menú superior para consultar la lista de productos registrados.");
        cardDesc.setWrapText(true);
        cardDesc.setStyle("-fx-font-size: 13px; -fx-text-fill: #4a5568;");

        card.getChildren().addAll(cardTitle, cardDesc);

        box.getChildren().addAll(welcomeLabel, subLabel, card);
        return box;
    }

    // --- VISTA 2: PRODUCTOS (TABLA) ---
    private VBox crearVistaProductos() {
        VBox box = new VBox(15);

        Label titleLabel = new Label("Catálogo de Productos Registrados");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1b4965;");

        table = new TableView<>();

        TableColumn<Producto, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto, String> colCategoria = new TableColumn<>("Categoría");
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio (S/)");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().addAll(colId, colNombre, colCategoria, colPrecio, colStock);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        cargarDatos();

        box.getChildren().addAll(titleLabel, table);
        VBox.setVgrow(table, Priority.ALWAYS);
        return box;
    }

    private void cargarDatos() {
        ObservableList<Producto> productos = FXCollections.observableArrayList(controller.obtenerProductos());
        table.setItems(productos);
    }

    public BorderPane getRoot() {
        return root;
    }
}