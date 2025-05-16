package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.controller.FarmaciaController;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import java.awt.*;

public class VentaDialog extends JDialog {
    public VentaDialog(JFrame parent, FarmaciaController ctrl) {
        super(parent, "Registrar Venta", true);
        setLayout(new GridLayout(3,2,10,10));

        JTextField txtCod = new JTextField();
        JSpinner spn = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
        JButton btn = new JButton("Vender");
        JLabel lbl = new JLabel(" ");

        btn.addActionListener(e -> {
            boolean ok = ctrl.venderProducto(txtCod.getText(), (int)spn.getValue());
            lbl.setText(ok ? "Venta registrada" : "Stock insuficiente");
            if(ok) dispose();
        });

        add(new JLabel("Código:")); add(txtCod);
        add(new JLabel("Cantidad:")); add(spn);
        add(btn); add(lbl);

        pack(); setLocationRelativeTo(parent);
    }
}