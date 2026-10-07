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

    // --- MÓDULO PRODUCTOS ---
    public List<Producto> obtenerProductos() { return productoDAO.listar(); }
    public boolean agregarProducto(Producto p) { return productoDAO.insertar(p); }
    public boolean actualizarProducto(Producto p) { return productoDAO.actualizar(p); }
    public boolean eliminarProducto(int id) { return productoDAO.eliminar(id); }

    // --- MÓDULO SOCIOS ---
    public List<Socio> obtenerSocios() { return socioDAO.listar(); }
    public boolean agregarSocio(Socio s) { return socioDAO.insertar(s); }
    public boolean actualizarSocio(Socio s) { return socioDAO.actualizar(s); }
    public boolean eliminarSocio(int id) { return socioDAO.eliminar(id); }
}