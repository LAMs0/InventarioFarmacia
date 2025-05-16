package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.controller.FarmaciaController;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ActualizarPrecioDialog extends JDialog {
    public ActualizarPrecioDialog(JFrame parent, FarmaciaController ctrl) {
        super(parent, "Actualizar Precio", true);
        setLayout(new GridLayout(3,2,10,10));

        JTextField txtCodigo = new JTextField();
        JTextField txtPrecio = new JTextField();
        JButton btn = new JButton("Actualizar");

        btn.addActionListener(e -> {
            String code = txtCodigo.getText();
            try {
                double price = Double.parseDouble(txtPrecio.getText());
                boolean ok = ctrl.actualizarPrecio(code, price);
                JOptionPane.showMessageDialog(this, ok ? "Precio actualizado" : "Producto no encontrado");
                if(ok) dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio inválido");
            }
        });

        add(new JLabel("Código de Barras:")); add(txtCodigo);
        add(new JLabel("Nuevo Precio:")); add(txtPrecio);
        add(btn);

        pack(); setLocationRelativeTo(parent);
    }
}
