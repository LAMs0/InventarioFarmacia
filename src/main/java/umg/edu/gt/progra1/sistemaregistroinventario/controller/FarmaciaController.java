package umg.edu.gt.progra1.sistemaregistroinventario.controller;

import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.ActualizarPrecioDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.MultiplesProductosDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.VentaDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.BuscarDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.panels.InventarioPanel;
public class FarmaciaController {
    private InventarioFarmacia inventario;
    private static final String ARCHIVO = "inventario.txt";

    public FarmaciaController() {
        inventario = new InventarioFarmacia();
        inventario.cargarInventarioDesdeArchivo(ARCHIVO);
    }

    public void registrarProducto(String nombre, String codigo, double precio, int stock) {
        inventario.agregarProducto(new ProductoDTO(nombre, codigo, precio, stock));
    }

    public ProductoDTO consultarProducto(String codigo) {
        return inventario.buscarProductoPorCodigo(codigo);
    }

    public ProductoDTO[] listarProductos() {
        return inventario.getProductos();
    }

    public boolean venderProducto(String codigo, int cantidad) {
        return inventario.venderProducto(codigo, cantidad);
    }

    public boolean actualizarPrecio(String codigo, double precio) {
        return inventario.actualizarPrecio(codigo, precio);
    }

    public void guardar() {
        inventario.guardarInventarioEnArchivo(ARCHIVO);
    }
}
