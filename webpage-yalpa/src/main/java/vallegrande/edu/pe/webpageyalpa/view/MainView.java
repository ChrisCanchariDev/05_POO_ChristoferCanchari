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
import vallegrande.edu.pe.webpageyalpa.model.Socio;

public class MainView {
    private BorderPane root;
    private StackPane contentArea;

    private VBox inicioView;
    private VBox productosView;
    private VBox sociosView;

    private TableView<Producto> tableProductos;
    private TableView<Socio> tableSocios;
    private MainController controller;

    // Campos Formulario Productos
    private TextField txtProdNombre;
    private TextField txtProdCategoria;
    private TextField txtProdPrecio;
    private TextField txtProdStock;

    // Campos Formulario Socios (Mockup S10)
    private TextField txtSocioNombre;
    private TextField txtSocioApellido;
    private TextField txtSocioCorreo;
    private TextField txtSocioEstado;

    public MainView() {
        controller = new MainController();
        root = new BorderPane();

        // 1. Barra de navegación superior
        HBox navBar = crearBarraNavegacion();
        root.setTop(navBar);

        // 2. Área central
        contentArea = new StackPane();
        contentArea.setPadding(new Insets(20));

        inicioView = crearVistaInicio();
        productosView = crearVistaProductos();
        sociosView = crearVistaSocios();

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
        Button btnSocios = new Button("👤 Formulario Cliente");

        String btnStyle = "-fx-background-color: #2b6cb0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand; -fx-background-radius: 5px;";
        btnInicio.setStyle(btnStyle);
        btnProductos.setStyle(btnStyle);
        btnSocios.setStyle(btnStyle);

        btnInicio.setOnAction(e -> cambiarVista(inicioView));
        btnProductos.setOnAction(e -> cambiarVista(productosView));
        btnSocios.setOnAction(e -> cambiarVista(sociosView));

        nav.getChildren().addAll(title, spacer, btnInicio, btnProductos, btnSocios);
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

        VBox card = new VBox(12);
        card.setPadding(new Insets(20));
        card.setMaxWidth(520);
        card.setStyle("-fx-background-color: #f7fafc; -fx-border-color: #e2e8f0; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");

        Label cardTitle = new Label("📌 Módulos del Sistema");
        cardTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #2d3748;");

        Label cardDesc = new Label("• Módulo 📦 Productos: Gestión del catálogo de la cooperativa.\n" +
                "• Módulo 👤 Formulario S10: Registro e Inserción de usuarios según el mockup de clase.");
        cardDesc.setWrapText(true);
        cardDesc.setStyle("-fx-font-size: 13px; -fx-text-fill: #4a5568;");

        card.getChildren().addAll(cardTitle, cardDesc);
        box.getChildren().addAll(welcomeLabel, subLabel, card);
        return box;
    }

    // --- VISTA 2: PRODUCTOS ---
    private VBox crearVistaProductos() {
        VBox box = new VBox(15);

        Label titleLabel = new Label("Gestión de Productos");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1b4965;");

        // Formulario
        TitledPane formPane = new TitledPane();
        formPane.setText("➕ Registrar Nuevo Producto");
        formPane.setCollapsible(false);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        txtProdNombre = new TextField();
        txtProdCategoria = new TextField();
        txtProdPrecio = new TextField();
        txtProdStock = new TextField();

        Button btnRegistrar = new Button("💾 Registrar Producto");
        btnRegistrar.setStyle("-fx-background-color: #2b8a3e; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnRegistrar.setOnAction(e -> registrarProducto());

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(txtProdNombre, 1, 0);
        grid.add(new Label("Categoría:"), 2, 0);
        grid.add(txtProdCategoria, 3, 0);
        grid.add(new Label("Precio (S/):"), 0, 1);
        grid.add(txtProdPrecio, 1, 1);
        grid.add(new Label("Stock:"), 2, 1);
        grid.add(txtProdStock, 3, 1);
        grid.add(btnRegistrar, 3, 2);

        formPane.setContent(grid);

        // Tabla
        tableProductos = new TableView<>();
        TableColumn<Producto, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto, String> colCat = new TableColumn<>("Categoría");
        colCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio (S/)");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableProductos.getColumns().addAll(colId, colNombre, colCat, colPrecio, colStock);
        tableProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        cargarDatosProductos();

        box.getChildren().addAll(titleLabel, formPane, tableProductos);
        VBox.setVgrow(tableProductos, Priority.ALWAYS);
        return box;
    }

    // --- VISTA 3: MOCKUP SLIDE S10 (SOCIOS / USUARIOS) ---
    private VBox crearVistaSocios() {
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);

        // Tarjeta estilo Mockup exacto de la diapositiva S10
        VBox formCard = new VBox(12);
        formCard.setMaxWidth(500);
        formCard.setPadding(new Insets(20));
        formCard.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cbd5e1; -fx-border-width: 1.5px; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER_LEFT);

        Label lblFormTitle = new Label("Formulario de Registro (JavaFX Desktop)");
        lblFormTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #1e293b;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label lblMockupTag = new Label("UI Mockup S10");
        lblMockupTag.setStyle("-fx-font-size: 11px; -fx-text-fill: #64748b;");

        headerBox.getChildren().addAll(lblFormTitle, spacer, lblMockupTag);

        String labelStyle = "-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #334155; -fx-min-width: 90px;";
        String inputStyle = "-fx-background-color: #f8fafc; -fx-border-color: #cbd5e1; -fx-border-radius: 6px; -fx-background-radius: 6px; -fx-padding: 8px; -fx-font-size: 13px;";

        // Campos exactos de la diapositiva
        HBox rowNombre = crearFilaInput("Nombre:", txtSocioNombre = new TextField(), "Carlos", labelStyle, inputStyle);
        HBox rowApellido = crearFilaInput("Apellido:", txtSocioApellido = new TextField(), "Pérez", labelStyle, inputStyle);
        HBox rowCorreo = crearFilaInput("Correo:", txtSocioCorreo = new TextField(), "carlos@gmail.com", labelStyle, inputStyle);
        HBox rowEstado = crearFilaInput("Estado:", txtSocioEstado = new TextField(), "Activo", labelStyle, inputStyle);

        Button btnRegistrar = new Button("[ REGISTRAR ]");
        btnRegistrar.setMaxWidth(Double.MAX_VALUE);
        btnRegistrar.setStyle("-fx-background-color: #0284c7; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 8px; -fx-cursor: hand;");
        btnRegistrar.setOnAction(e -> registrarSocio());

        formCard.getChildren().addAll(headerBox, rowNombre, rowApellido, rowCorreo, rowEstado, btnRegistrar);

        // Tabla de Socios
        tableSocios = new TableView<>();
        TableColumn<Socio, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Socio, String> colNom = new TableColumn<>("Nombre");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Socio, String> colApe = new TableColumn<>("Apellido");
        colApe.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        TableColumn<Socio, String> colCor = new TableColumn<>("Correo");
        colCor.setCellValueFactory(new PropertyValueFactory<>("correo"));

        TableColumn<Socio, String> colEst = new TableColumn<>("Estado");
        colEst.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tableSocios.getColumns().addAll(colId, colNom, colApe, colCor, colEst);
        tableSocios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        cargarDatosSocios();

        mainContainer.getChildren().addAll(formCard, tableSocios);
        VBox.setVgrow(tableSocios, Priority.ALWAYS);
        return mainContainer;
    }

    private HBox crearFilaInput(String labelText, TextField tf, String placeholder, String labelStyle, String inputStyle) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        Label lbl = new Label(labelText);
        lbl.setStyle(labelStyle);
        tf.setPromptText(placeholder);
        tf.setStyle(inputStyle);
        HBox.setHgrow(tf, Priority.ALWAYS);
        row.getChildren().addAll(lbl, tf);
        return row;
    }

    // Lógicas de Registro
    private void registrarProducto() {
        if (txtProdNombre.getText().isEmpty() || txtProdCategoria.getText().isEmpty() ||
                txtProdPrecio.getText().isEmpty() || txtProdStock.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Complete todos los campos del producto.");
            return;
        }

        try {
            double precio = Double.parseDouble(txtProdPrecio.getText().trim());
            int stock = Integer.parseInt(txtProdStock.getText().trim());

            if (controller.registrarProducto(txtProdNombre.getText().trim(), txtProdCategoria.getText().trim(), precio, stock)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto insertado en MySQL.");
                txtProdNombre.clear(); txtProdCategoria.clear(); txtProdPrecio.clear(); txtProdStock.clear();
                cargarDatosProductos();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Precio y stock deben ser numéricos.");
        }
    }

    private void registrarSocio() {
        String nom = txtSocioNombre.getText().trim();
        String ape = txtSocioApellido.getText().trim();
        String cor = txtSocioCorreo.getText().trim();
        String est = txtSocioEstado.getText().trim();

        if (nom.isEmpty() || ape.isEmpty() || cor.isEmpty() || est.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Complete todos los campos del formulario.");
            return;
        }

        if (controller.registrarSocio(nom, ape, cor, est)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Registro almacenado de forma permanente en MySQL.");
            txtSocioNombre.clear(); txtSocioApellido.clear(); txtSocioCorreo.clear(); txtSocioEstado.clear();
            cargarDatosSocios();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo realizar la inserción.");
        }
    }

    private void cargarDatosProductos() {
        ObservableList<Producto> lista = FXCollections.observableArrayList(controller.obtenerProductos());
        tableProductos.setItems(lista);
    }

    private void cargarDatosSocios() {
        ObservableList<Socio> lista = FXCollections.observableArrayList(controller.obtenerSocios());
        tableSocios.setItems(lista);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public BorderPane getRoot() {
        return root;
    }
}