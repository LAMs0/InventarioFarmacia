package umg.edu.gt.progra1.sistemaregistroinventario.view.panels;


import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class InventarioPanel {
    private JDialog dialog;
    private InventarioFarmacia inventario;
    private FarmaciaGUI parent;

    public InventarioPanel(FarmaciaGUI parent, InventarioFarmacia inventario) {
        this.parent = parent;
        this.inventario = inventario;
    }

    public void mostrar() {
        dialog = new JDialog(parent, "Inventario Completo", true);
        dialog.setSize(700, 500);
        dialog.setLayout(new BorderLayout());

        // Modelo de tabla
        String[] columnas = {"Código", "Nombre", "Precio", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(model);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(25);

        // Llenar tabla
        for (int i = 0; i < inventario.getContadorProductos(); i++) {
            ProductoDTO p = inventario.getProductos()[i];
            model.addRow(new Object[]{
                    p.getCodigoBarras(),
                    p.getNombre(),
                    String.format("$%.2f", p.getPrecioUnitario()),
                    p.getCantidadStock()
            });
        }

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de resumen
        JPanel resumenPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel lblResumen = new JLabel("Total productos: " + inventario.getContadorProductos());
        lblResumen.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnExportar = new JButton("Exportar a CSV");
        btnExportar.addActionListener(e -> exportarACSV());

        resumenPanel.add(lblResumen);
        resumenPanel.add(btnExportar);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(resumenPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void exportarACSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar inventario como CSV");

        if (fileChooser.showSaveDialog(dialog) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile() + ".csv")) {
                writer.println("Código,Nombre,Precio,Stock");

                for (int i = 0; i < inventario.getContadorProductos(); i++) {
                    ProductoDTO p = inventario.getProductos()[i];
                    writer.printf("\"%s\",\"%s\",%.2f,%d%n",
                            p.getCodigoBarras(),
                            p.getNombre(),
                            p.getPrecioUnitario(),
                            p.getCantidadStock());
                }

                JOptionPane.showMessageDialog(dialog,
                        "Inventario exportado exitosamente",
                        "Exportación completada",
                        JOptionPane.INFORMATION_MESSAGE);
                parent.agregarMensaje("📊 Inventario exportado a CSV");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Error al exportar: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}