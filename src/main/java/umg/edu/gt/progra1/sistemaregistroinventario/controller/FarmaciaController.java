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
    private FarmaciaGUI view;
    private InventarioFarmacia model;

    public FarmaciaController(FarmaciaGUI view) {
        this.view = view;
        this.model = new InventarioFarmacia();
    }

    public void cargarProductosEjemplo() {
        ProductoDTO[] ejemplos = {
                new ProductoDTO("Paracetamol 500mg", "P001", 5.99, 150),
                new ProductoDTO("Ibuprofeno 400mg", "P002", 7.50, 80),
                new ProductoDTO("Amoxicilina 250mg", "P003", 12.75, 45),
                new ProductoDTO("Omeprazol 20mg", "P004", 9.25, 120),
                new ProductoDTO("Aspirina 100mg", "P005", 4.50, 200)
        };

        for (ProductoDTO producto : ejemplos) {
            model.agregarProducto(producto);
        }
        view.agregarMensaje("✅ Sistema iniciado con 5 productos de ejemplo");
    }

    public void mostrarDialogoAgregar() {
        new VentaDialog(view, model).setVisible(true);
    }

    public void mostrarDialogoBuscar() {
        new BuscarDialog(view, model).setVisible(true);
    }

    public void mostrarInventario() {
        new InventarioPanel(view, model).mostrar();
    }

    public void mostrarDialogoVender() {
        new VentaDialog(view, model).setVisible(true);
    }

    public void mostrarDialogoActualizar() {
        new ActualizarPrecioDialog(view, model).setVisible(true);
    }

    public void mostrarDialogoMultiplesProductos() {
        new MultiplesProductosDialog(view, model).setVisible(true);
    }
}
