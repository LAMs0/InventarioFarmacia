package umg.edu.gt.progra1.sistemaregistroinventario;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class FarmaciaGUI {

    private InventarioFarmacia inventario;
    private JFrame frame;
    private JTextArea textArea;

    public FarmaciaGUI(){
        inventario = new InventarioFarmacia();


    }

    private void cargarProductosEjemplo() {
        //Productos de Ejemplo
        ProductoDTO[] ejemplos = {
                new ProductoDTO("Paracetamol 500mg", "P001", 5.99, 150),
                new ProductoDTO("Ibuprofeno 400mg", "P002", 7.50, 80),
                new ProductoDTO("Amoxicilina 250mg", "P003", 12.75, 45),
                new ProductoDTO("Omeprazol 20mg", "P004", 9.25, 120),
                new ProductoDTO("Aspirina 100mg", "P005", 4.50, 200)
        };

        for (ProductoDTO producto : ejemplos) {
            inventario.agregarProducto(producto);
        }
    }

    private void initializa(){
        //Configuracion de las ventanas
        frame = new JFrame("Sistema de Inventario - Farmacia ");
        frame.setSize(900,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Panel de Botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Botones
        JButton btnAgregar = crearBoton("Agregar", "➕", "Agregar nuevo producto");
        JButton btnBuscar = crearBoton("Buscar", "🔍", "Buscar producto por codigo");
        JButton btnInventario = crearBoton("Inventario", "📋", "Mostrar inventario completo");
        JButton btnVender = crearBoton("Vender", "💰", "Registrar venta de producto");
        JButton btnActualizar = crearBoton("Actualizar", "✏️", "Actualizar precio de producto");
        JButton btnNuevos = crearBoton("Ingresar Varios", "📦", "Ingresar multiples productos");

        //Area para el texto 
    }


}
