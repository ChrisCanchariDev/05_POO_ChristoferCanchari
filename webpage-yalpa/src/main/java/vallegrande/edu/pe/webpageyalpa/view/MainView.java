package vallegrande.edu.pe.webpageyalpa.view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import vallegrande.edu.pe.webpageyalpa.controller.MainController;
import vallegrande.edu.pe.webpageyalpa.model.Producto;
import vallegrande.edu.pe.webpageyalpa.model.Socio;

import java.util.Optional;

public class MainView {
    private BorderPane root;
    private StackPane contentArea;

    private VBox inicioView;
    private VBox sociosView;
    private VBox productosView;

    private MainController controller;

    // Campos Socios
    private TableView<Socio> tableSocios;
    private TextField txtSocId, txtSocNombre, txtSocApellido, txtSocDni, txtSocTelefono, txtSocEstado;

    // Campos Productos
    private TableView<Producto> tableProductos;
    private TextField txtProdId, txtProdNombre, txtProdCategoria, txtProdPrecio, txtProdStock;

    public MainView() {
        controller = new MainController();
        root = new BorderPane();

        HBox navBar = crearBarraNavegacion();
        root.setTop(navBar);

        contentArea = new StackPane();
        contentArea.setPadding(new Insets(15));

        inicioView = crearVistaInicio();
        sociosView = crearVistaSocios();
        productosView = crearVistaProductos();

        contentArea.getChildren().add(inicioView);
        root.setCenter(contentArea);
    }

    private HBox crearBarraNavegacion() {
        HBox nav = new HBox(12);
        nav.setPadding(new Insets(12, 20, 12, 20));
        nav.setStyle("-fx-background-color: #1b4965; -fx-alignment: center-left;");

        Label title = new Label("COOPERATIVA YALPA");
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnInicio = new Button("🏠 Inicio");
        Button btnSocios = new Button("👨‍🌾 Socios");
        Button btnProductos = new Button("📦 Productos");

        String btnStyle = "-fx-background-color: #2b6cb0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand; -fx-background-radius: 5px;";
        btnInicio.setStyle(btnStyle);
        btnSocios.setStyle(btnStyle);
        btnProductos.setStyle(btnStyle);

        btnInicio.setOnAction(e -> cambiarVista(inicioView));
        btnSocios.setOnAction(e -> {
            cargarSocios();
            cambiarVista(sociosView);
        });
        btnProductos.setOnAction(e -> {
            cargarProductos();
            cambiarVista(productosView);
        });

        nav.getChildren().addAll(title, spacer, btnInicio, btnSocios, btnProductos);
        return nav;
    }

    private void cambiarVista(VBox vista) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(vista);
    }

    // --- 1. INICIO ---
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
        card.setStyle("-fx-background-color: #f7fafc; -fx-border-color: #e2e8f0; -fx-border-width: 2px; -fx-border-radius: 8px;");

        Label cardTitle = new Label("📌 Módulos del Sistema");
        cardTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #2d3748;");

        Label cardDesc = new Label("Navega utilizando la barra superior:\n\n" +
                "• 👨‍🌾 Socios: Registro y control de socios de la cooperativa.\n" +
                "• 📦 Productos: Gestión de cosechas y derivados de la cooperativa.");
        cardDesc.setWrapText(true);
        cardDesc.setStyle("-fx-font-size: 13px; -fx-text-fill: #4a5568;");

        card.getChildren().addAll(cardTitle, cardDesc);
        box.getChildren().addAll(welcomeLabel, subLabel, card);
        return box;
    }

    // --- 2. VISTA SOCIOS ---
    private VBox crearVistaSocios() {
        VBox box = new VBox(12);

        Label titleLabel = new Label("Mantenimiento de Socios");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1b4965;");

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        form.setPadding(new Insets(10));
        form.setStyle("-fx-background-color: #f0f4f8; -fx-background-radius: 5px;");

        txtSocId = new TextField(); txtSocId.setDisable(true);
        txtSocNombre = new TextField(); txtSocNombre.setPromptText("Nombre");
        txtSocApellido = new TextField(); txtSocApellido.setPromptText("Apellido");
        txtSocDni = new TextField(); txtSocDni.setPromptText("DNI");
        txtSocTelefono = new TextField(); txtSocTelefono.setPromptText("Teléfono");
        txtSocEstado = new TextField(); txtSocEstado.setPromptText("Estado (Activo/Inactivo)");

        form.add(new Label("ID:"), 0, 0); form.add(txtSocId, 1, 0);
        form.add(new Label("Nombre:"), 0, 1); form.add(txtSocNombre, 1, 1);
        form.add(new Label("Apellido:"), 2, 0); form.add(txtSocApellido, 3, 0);
        form.add(new Label("DNI:"), 2, 1); form.add(txtSocDni, 3, 1);
        form.add(new Label("Teléfono:"), 4, 0); form.add(txtSocTelefono, 5, 0);
        form.add(new Label("Estado:"), 4, 1); form.add(txtSocEstado, 5, 1);

        HBox btnBox = new HBox(10);
        Button btnAdd = new Button("➕ Registrar"); btnAdd.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold;");
        Button btnUpd = new Button("✏️ Actualizar"); btnUpd.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
        Button btnDel = new Button("🗑️ Eliminar"); btnDel.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");
        Button btnClr = new Button("🧹 Limpiar"); btnClr.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-weight: bold;");
        btnBox.getChildren().addAll(btnAdd, btnUpd, btnDel, btnClr);

        tableSocios = new TableView<>();
        TableColumn<Socio, Integer> colId = new TableColumn<>("ID"); colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Socio, String> colNom = new TableColumn<>("Nombre"); colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Socio, String> colApe = new TableColumn<>("Apellido"); colApe.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        TableColumn<Socio, String> colDni = new TableColumn<>("DNI"); colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        TableColumn<Socio, String> colTel = new TableColumn<>("Teléfono"); colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        TableColumn<Socio, String> colEst = new TableColumn<>("Estado"); colEst.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tableSocios.getColumns().addAll(colId, colNom, colApe, colDni, colTel, colEst);
        tableSocios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableSocios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtSocId.setText(String.valueOf(newSel.getId()));
                txtSocNombre.setText(newSel.getNombre());
                txtSocApellido.setText(newSel.getApellido());
                txtSocDni.setText(newSel.getDni());
                txtSocTelefono.setText(newSel.getTelefono());
                txtSocEstado.setText(newSel.getEstado());
            }
        });

        btnAdd.setOnAction(e -> {
            Socio s = new Socio(0, txtSocNombre.getText(), txtSocApellido.getText(), txtSocDni.getText(), txtSocTelefono.getText(), txtSocEstado.getText());
            if (controller.agregarSocio(s)) { cargarSocios(); limpiarSocForm(); }
        });

        btnUpd.setOnAction(e -> {
            if (txtSocId.getText().isEmpty()) return;
            Socio s = new Socio(Integer.parseInt(txtSocId.getText()), txtSocNombre.getText(), txtSocApellido.getText(), txtSocDni.getText(), txtSocTelefono.getText(), txtSocEstado.getText());
            if (controller.actualizarSocio(s)) { cargarSocios(); limpiarSocForm(); }
        });

        btnDel.setOnAction(e -> {
            if (txtSocId.getText().isEmpty()) return;
            if (controller.eliminarSocio(Integer.parseInt(txtSocId.getText()))) { cargarSocios(); limpiarSocForm(); }
        });

        btnClr.setOnAction(e -> limpiarSocForm());

        box.getChildren().addAll(titleLabel, form, btnBox, tableSocios);
        VBox.setVgrow(tableSocios, Priority.ALWAYS);
        return box;
    }

    // --- 3. VISTA PRODUCTOS ---
    private VBox crearVistaProductos() {
        VBox box = new VBox(12);

        Label titleLabel = new Label("Mantenimiento de Productos");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1b4965;");

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        form.setPadding(new Insets(10));
        form.setStyle("-fx-background-color: #f0f4f8; -fx-background-radius: 5px;");

        txtProdId = new TextField(); txtProdId.setDisable(true);
        txtProdNombre = new TextField(); txtProdNombre.setPromptText("Nombre");
        txtProdCategoria = new TextField(); txtProdCategoria.setPromptText("Categoría");
        txtProdPrecio = new TextField(); txtProdPrecio.setPromptText("Precio");
        txtProdStock = new TextField(); txtProdStock.setPromptText("Stock");

        form.add(new Label("ID:"), 0, 0); form.add(txtProdId, 1, 0);
        form.add(new Label("Nombre:"), 0, 1); form.add(txtProdNombre, 1, 1);
        form.add(new Label("Categoría:"), 2, 0); form.add(txtProdCategoria, 3, 0);
        form.add(new Label("Precio:"), 2, 1); form.add(txtProdPrecio, 3, 1);
        form.add(new Label("Stock:"), 4, 0); form.add(txtProdStock, 5, 0);

        HBox btnBox = new HBox(10);
        Button btnAdd = new Button("➕ Registrar"); btnAdd.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold;");
        Button btnUpd = new Button("✏️ Actualizar"); btnUpd.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
        Button btnDel = new Button("🗑️ Eliminar"); btnDel.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");
        Button btnClr = new Button("🧹 Limpiar"); btnClr.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-weight: bold;");
        btnBox.getChildren().addAll(btnAdd, btnUpd, btnDel, btnClr);

        tableProductos = new TableView<>();
        TableColumn<Producto, Integer> colId = new TableColumn<>("ID"); colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Producto, String> colNom = new TableColumn<>("Nombre"); colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Producto, String> colCat = new TableColumn<>("Categoría"); colCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        TableColumn<Producto, Double> colPre = new TableColumn<>("Precio (S/)"); colPre.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<Producto, Integer> colStk = new TableColumn<>("Stock"); colStk.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableProductos.getColumns().addAll(colId, colNom, colCat, colPre, colStk);
        tableProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtProdId.setText(String.valueOf(newSel.getId()));
                txtProdNombre.setText(newSel.getNombre());
                txtProdCategoria.setText(newSel.getCategoria());
                txtProdPrecio.setText(String.valueOf(newSel.getPrecio()));
                txtProdStock.setText(String.valueOf(newSel.getStock()));
            }
        });

        btnAdd.setOnAction(e -> {
            Producto p = new Producto(0, txtProdNombre.getText(), txtProdCategoria.getText(), Double.parseDouble(txtProdPrecio.getText()), Integer.parseInt(txtProdStock.getText()));
            if (controller.agregarProducto(p)) { cargarProductos(); limpiarProdForm(); }
        });

        btnUpd.setOnAction(e -> {
            if (txtProdId.getText().isEmpty()) return;
            Producto p = new Producto(Integer.parseInt(txtProdId.getText()), txtProdNombre.getText(), txtProdCategoria.getText(), Double.parseDouble(txtProdPrecio.getText()), Integer.parseInt(txtProdStock.getText()));
            if (controller.actualizarProducto(p)) { cargarProductos(); limpiarProdForm(); }
        });

        btnDel.setOnAction(e -> {
            if (txtProdId.getText().isEmpty()) return;
            if (controller.eliminarProducto(Integer.parseInt(txtProdId.getText()))) { cargarProductos(); limpiarProdForm(); }
        });

        btnClr.setOnAction(e -> limpiarProdForm());

        box.getChildren().addAll(titleLabel, form, btnBox, tableProductos);
        VBox.setVgrow(tableProductos, Priority.ALWAYS);
        return box;
    }

    private void cargarSocios() {
        tableSocios.setItems(FXCollections.observableArrayList(controller.obtenerSocios()));
    }

    private void cargarProductos() {
        tableProductos.setItems(FXCollections.observableArrayList(controller.obtenerProductos()));
    }

    private void limpiarSocForm() {
        txtSocId.clear(); txtSocNombre.clear(); txtSocApellido.clear(); txtSocDni.clear(); txtSocTelefono.clear(); txtSocEstado.clear();
        tableSocios.getSelectionModel().clearSelection();
    }

    private void limpiarProdForm() {
        txtProdId.clear(); txtProdNombre.clear(); txtProdCategoria.clear(); txtProdPrecio.clear(); txtProdStock.clear();
        tableProductos.getSelectionModel().clearSelection();
    }

    public BorderPane getRoot() { return root; }
}