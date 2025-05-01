package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import java.awt.*;

public class VentaDialog extends JDialog {
    private InventarioFarmacia inventario;
    private FarmaciaGUI parent;

    public VentaDialog(FarmaciaGUI parent, InventarioFarmacia inventario) {
        super(parent, "Agregar Producto", true);
        this.parent = parent;
        this.inventario = inventario;
        initComponents();
    }

    private void initComponents() {
        setSize(400, 250);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtNombre = new JTextField();
        JTextField txtCodigo = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Código de Barras:"));
        panel.add(txtCodigo);
        panel.add(new JLabel("Precio Unitario (Q):"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Cantidad en Stock:"));
        panel.add(txtStock);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String codigo = txtCodigo.getText().trim();

                if(nombre.isEmpty() || codigo.isEmpty()) {
                    throw new IllegalArgumentException("Nombre y código son obligatorios");
                }

                double precio = Double.parseDouble(txtPrecio.getText());
                int stock = Integer.parseInt(txtStock.getText());

                ProductoDTO nuevoProducto = new ProductoDTO(nombre, codigo, precio, stock);
                inventario.agregarProducto(nuevoProducto);
                parent.agregarMensaje("✅ Producto agregado: " + nombre + " (Código: " + codigo + ")");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error: " + ex.getMessage(),
                        "Datos inválidos", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnAgregar);

        add(panel);
    }
}