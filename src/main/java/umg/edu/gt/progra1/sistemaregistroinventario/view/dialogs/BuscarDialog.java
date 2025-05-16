package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.controller.FarmaciaController;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import java.awt.*;

public class BuscarDialog extends JDialog {
    public BuscarDialog(JFrame parent, FarmaciaController ctrl) {
        super(parent, "Buscar Producto", true);
        setLayout(new GridLayout(2,2,10,10));

        JTextField txt = new JTextField();
        JLabel lbl = new JLabel("Ingrese código");
        JButton btn = new JButton("Buscar");

        btn.addActionListener(e -> {
            ProductoDTO p = ctrl.consultarProducto(txt.getText());
            lbl.setText(p != null ? p.toString() : "No encontrado");
        });

        add(new JLabel("Código de Barras:")); add(txt);
        add(btn); add(lbl);

        pack(); setLocationRelativeTo(parent);
    }
}
