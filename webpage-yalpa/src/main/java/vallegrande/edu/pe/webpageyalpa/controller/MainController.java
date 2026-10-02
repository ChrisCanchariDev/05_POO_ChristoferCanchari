package vallegrande.edu.pe.webpageyalpa.controller;

import vallegrande.edu.pe.webpageyalpa.model.Producto;
import vallegrande.edu.pe.webpageyalpa.model.ProductoDAO;
import vallegrande.edu.pe.webpageyalpa.model.Socio;
import vallegrande.edu.pe.webpageyalpa.model.SocioDAO;

import java.util.List;

public class MainController {
    private ProductoDAO productoDAO;
    private SocioDAO socioDAO;

    public MainController() {
        this.productoDAO = new ProductoDAO();
        this.socioDAO = new SocioDAO();
    }

    // --- MÉTODOS DE PRODUCTOS ---
    public List<Producto> obtenerProductos() {
        return productoDAO.listar();
    }

    public boolean registrarProducto(String nombre, String categoria, double precio, int stock) {
        Producto nuevoProducto = new Producto(0, nombre, categoria, precio, stock);
        return productoDAO.insertar(nuevoProducto);
    }

    // --- MÉTODOS DE SOCIOS / REGISTROS ---
    public List<Socio> obtenerSocios() {
        return socioDAO.listar();
    }

    public boolean registrarSocio(String nombre, String apellido, String correo, String estado) {
        Socio nuevoSocio = new Socio(0, nombre, apellido, correo, estado);
        return socioDAO.insertar(nuevoSocio);
    }
}