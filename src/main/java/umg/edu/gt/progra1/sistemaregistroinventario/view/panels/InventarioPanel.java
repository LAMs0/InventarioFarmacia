package umg.edu.gt.progra1.sistemaregistroinventario.view.panels;


import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class InventarioPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public InventarioPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new DefaultTableModel(new Object[]{"Nombre", "Código", "Precio", "Stock"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table));
    }

    public void actualizarTabla(ProductoDTO[] productos) {
        model.setRowCount(0);
        for (ProductoDTO p : productos) {
            model.addRow(new Object[]{p.getNombre(), p.getCodigoBarras(), p.getPrecioUnitario(), p.getCantidadStock()});
        }
    }
}
