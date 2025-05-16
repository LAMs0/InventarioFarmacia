package umg.edu.gt.progra1.sistemaregistroinventario.view;
import umg.edu.gt.progra1.sistemaregistroinventario.controller.FarmaciaController;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.ActualizarPrecioDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.BuscarDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.MultiplesProductosDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs.VentaDialog;
import umg.edu.gt.progra1.sistemaregistroinventario.view.panels.InventarioPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FarmaciaGUI extends JFrame {
    private FarmaciaController ctrl = new FarmaciaController();
    private InventarioPanel panelInv = new InventarioPanel();

    public FarmaciaGUI() {
        super("Gestión de Farmacia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel botones = new JPanel();
        JButton b1 = new JButton("Registrar");
        JButton b2 = new JButton("Buscar");
        JButton b3 = new JButton("Carga Masiva");
        JButton b4 = new JButton("Vender");
        JButton b5 = new JButton("Actualizar Precio");

        b1.addActionListener(e -> new MultiplesProductosDialog(this, ctrl).setVisible(true));
        b2.addActionListener(e -> new BuscarDialog(this, ctrl).setVisible(true));
        b3.addActionListener(e -> new MultiplesProductosDialog(this, ctrl).setVisible(true));
        b4.addActionListener(e -> new VentaDialog(this, ctrl).setVisible(true));
        b5.addActionListener(e -> new ActualizarPrecioDialog(this, ctrl).setVisible(true));

        botones.add(b1); botones.add(b2); botones.add(b3); botones.add(b4); botones.add(b5);

        add(botones, BorderLayout.NORTH);
        add(panelInv, BorderLayout.CENTER);

        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                panelInv.actualizarTabla(ctrl.listarProductos());
            }
            public void windowClosing(java.awt.event.WindowEvent e) {
                ctrl.guardar();
            }
        });

        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FarmaciaGUI::new);
    }
}