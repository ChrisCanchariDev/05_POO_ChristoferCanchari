package vallegrande.edu.pe.webpageyalpa.controller;

import vallegrande.edu.pe.webpageyalpa.model.Producto;
import vallegrande.edu.pe.webpageyalpa.model.ProductoDAO;

import java.util.List;

public class MainController {
    private ProductoDAO productoDAO;

    public MainController() {
        this.productoDAO = new ProductoDAO();
    }

    public List<Producto> obtenerProductos() {
        return productoDAO.listar();
    }
}