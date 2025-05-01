package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ActualizarPrecioDialog extends JDialog {
    private InventarioFarmacia inventario;
    private FarmaciaGUI parent;

    public ActualizarPrecioDialog(FarmaciaGUI parent, InventarioFarmacia inventario) {
        super(parent, "Actualizar Precio", true);
        this.parent = parent;
        this.inventario = inventario;
        initComponents();
    }

    private void initComponents() {
        setSize(500, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(5, 5));

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JTextField txtCodigo = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");

        // Panel de detalles
        JPanel detailPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        detailPanel.setBorder(BorderFactory.createTitledBorder("Información Actual"));
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblNombreVal = new JLabel("");
        JLabel lblPrecioActual = new JLabel("Precio actual:");
        JLabel lblPrecioActualVal = new JLabel("");
        JLabel lblNuevoPrecio = new JLabel("Nuevo precio:");
        JFormattedTextField txtNuevoPrecio = new JFormattedTextField(NumberFormat.getNumberInstance());
        txtNuevoPrecio.setColumns(10);

        detailPanel.add(lblNombre);
        detailPanel.add(lblNombreVal);
        detailPanel.add(lblPrecioActual);
        detailPanel.add(lblPrecioActualVal);
        detailPanel.add(lblNuevoPrecio);
        detailPanel.add(txtNuevoPrecio);

        // Panel de acciones
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnActualizar = new JButton("Actualizar Precio");
        btnActualizar.setEnabled(false);

        // Lógica de búsqueda
        btnBuscar.addActionListener(e -> {
            ProductoDTO producto = inventario.buscarProductoPorCodigo(txtCodigo.getText().trim());
            if (producto != null) {
                lblNombreVal.setText(producto.getNombre());
                lblPrecioActualVal.setText(String.format("$%.2f", producto.getPrecioUnitario()));
                txtNuevoPrecio.setValue(producto.getPrecioUnitario());
                btnActualizar.setEnabled(true);
                parent.agregarMensaje("🔍 Producto encontrado para actualización: " + producto.getNombre());
            } else {
                JOptionPane.showMessageDialog(this,
                        "Producto no encontrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Lógica de actualización
        btnActualizar.addActionListener(e -> {
            try {
                double nuevoPrecio = ((Number)txtNuevoPrecio.getValue()).doubleValue();
                if (nuevoPrecio <= 0) {
                    throw new IllegalArgumentException("El precio debe ser mayor a cero");
                }

                boolean exito = inventario.actualizarPrecio(txtCodigo.getText().trim(), nuevoPrecio);
                if (exito) {
                    parent.agregarMensaje(String.format("✏️ Precio actualizado: %s - Nuevo precio: $%.2f",
                            lblNombreVal.getText(), nuevoPrecio));
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Precio inválido: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        searchPanel.add(new JLabel("Código de Producto:"));
        searchPanel.add(txtCodigo);
        searchPanel.add(btnBuscar);
        actionPanel.add(btnActualizar);

        add(searchPanel, BorderLayout.NORTH);
        add(detailPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
    }
}