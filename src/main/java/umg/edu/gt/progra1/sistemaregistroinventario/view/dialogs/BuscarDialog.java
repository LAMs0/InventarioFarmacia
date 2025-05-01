package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import java.awt.*;

public class BuscarDialog extends JDialog {
    private InventarioFarmacia inventario;
    private FarmaciaGUI parent;

    public BuscarDialog(FarmaciaGUI parent, InventarioFarmacia inventario) {
        super(parent, "Buscar Producto", true);
        this.parent = parent;
        this.inventario = inventario;
        initComponents();
    }

    private void initComponents() {
        setSize(500, 300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtCodigo = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");

        // Área de resultados
        JTextArea areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        btnBuscar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (!codigo.isEmpty()) {
                ProductoDTO producto = inventario.buscarProductoPorCodigo(codigo);
                if (producto != null) {
                    areaResultado.setText(formatProductoDetallado(producto));
                    parent.agregarMensaje("🔍 Producto encontrado: " + producto.getNombre());
                } else {
                    areaResultado.setText("❌ Producto no encontrado");
                    parent.agregarMensaje("⚠ Búsqueda sin resultados para código: " + codigo);
                }
            }
        });

        searchPanel.add(new JLabel("Código de Barras:"));
        searchPanel.add(txtCodigo);
        searchPanel.add(btnBuscar);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollResultado, BorderLayout.CENTER);
        add(panel);
    }

    private String formatProductoDetallado(ProductoDTO producto) {
        return "═".repeat(40) + "\n" +
                "  NOMBRE: " + producto.getNombre() + "\n" +
                "  CÓDIGO: " + producto.getCodigoBarras() + "\n" +
                "  PRECIO: $" + String.format("%.2f", producto.getPrecioUnitario()) + "\n" +
                "  STOCK:  " + producto.getCantidadStock() + " unidades\n" +
                "═".repeat(40);
    }
}