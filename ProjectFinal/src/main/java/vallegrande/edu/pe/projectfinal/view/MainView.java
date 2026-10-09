package vallegrande.edu.pe.projectfinal.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import vallegrande.edu.pe.projectfinal.model.Producto;

public class MainView extends BorderPane {

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtCategoria;
    private TextField txtCantidad;
    private TextField txtPrecio;

    private Button btnRegistrar;
    private Button btnActualizar;
    private Button btnEliminar;

    private TableView<Producto> tablaProductos;
    private TableColumn<Producto, Integer> colId;
    private TableColumn<Producto, String> colNombre;
    private TableColumn<Producto, String> colCategoria;
    private TableColumn<Producto, Integer> colCantidad;
    private TableColumn<Producto, Double> colPrecio;

    public MainView() {
        setPadding(new Insets(15));

        // Título Superior
        Label lblTitulo = new Label("Sistema de Gestión de Productos");
        lblTitulo.setStyle("-font-size: 18pt; -fx-font-weight: bold; -fx-text-fill: #1A365D;");
        HBox topBox = new HBox(lblTitulo);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(0, 0, 15, 0));
        setTop(topBox);

        // Formulario de Entrada (Izquierda)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        txtId = new TextField();
        txtId.setDisable(true);
        txtNombre = new TextField();
        txtCategoria = new TextField();
        txtCantidad = new TextField();
        txtPrecio = new TextField();

        grid.add(new Label("ID:"), 0, 0);
        grid.add(txtId, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(txtNombre, 1, 1);
        grid.add(new Label("Categoría:"), 0, 2);
        grid.add(txtCategoria, 1, 2);
        grid.add(new Label("Cantidad:"), 0, 3);
        grid.add(txtCantidad, 1, 3);
        grid.add(new Label("Precio:"), 0, 4);
        grid.add(txtPrecio, 1, 4);

        // Botones
        btnRegistrar = new Button("Registrar");
        btnActualizar = new Button("Actualizar");
        btnEliminar = new Button("Eliminar");

        btnRegistrar.setStyle("-fx-background-color: #2B6CB0; -fx-text-fill: white;");
        btnActualizar.setStyle("-fx-background-color: #319795; -fx-text-fill: white;");
        btnEliminar.setStyle("-fx-background-color: #E53E3E; -fx-text-fill: white;");

        HBox boxBotones = new HBox(10, btnRegistrar, btnActualizar, btnEliminar);
        boxBotones.setPadding(new Insets(10, 0, 0, 0));

        VBox leftBox = new VBox(10, grid, boxBotones);
        leftBox.setPadding(new Insets(0, 15, 0, 0));
        setLeft(leftBox);

        // Tabla Central
        tablaProductos = new TableView<>();

        colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(50);

        colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(150);

        colCategoria = new TableColumn<>("Categoría");
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colCategoria.setPrefWidth(120);

        colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCantidad.setPrefWidth(80);

        colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecio.setPrefWidth(80);

        tablaProductos.getColumns().addAll(colId, colNombre, colCategoria, colCantidad, colPrecio);
        setCenter(tablaProductos);
    }

    // Getters para controles
    public TextField getTxtId() { return txtId; }
    public TextField getTxtNombre() { return txtNombre; }
    public TextField getTxtCategoria() { return txtCategoria; }
    public TextField getTxtCantidad() { return txtCantidad; }
    public TextField getTxtPrecio() { return txtPrecio; }
    public Button getBtnRegistrar() { return btnRegistrar; }
    public Button getBtnActualizar() { return btnActualizar; }
    public Button getBtnEliminar() { return btnEliminar; }
    public TableView<Producto> getTablaProductos() { return tablaProductos; }
}