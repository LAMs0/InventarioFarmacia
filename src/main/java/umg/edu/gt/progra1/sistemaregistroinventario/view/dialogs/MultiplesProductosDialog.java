package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.controller.FarmaciaController;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import java.awt.*;

public class MultiplesProductosDialog extends JDialog {
    public MultiplesProductosDialog(JFrame parent, FarmaciaController ctrl) {
        super(parent, "Carga Masiva", true);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea(10,30);
        area.setText("# Nombre,Codigo,Precio,Stock\nEj: Paracetamol,P001,5.99,100");
        JLabel lbl = new JLabel(" ");
        JButton btn = new JButton("Cargar");

        btn.addActionListener(e -> {
            String[] lines = area.getText().split("\n"); int count=0;
            for(String line: lines) {
                if(line.startsWith("#")|| line.isBlank()) continue;
                try{
                    String[] d = line.split(",");
                    ctrl.registrarProducto(d[0].trim(), d[1].trim(),
                            Double.parseDouble(d[2].trim()), Integer.parseInt(d[3].trim()));
                    count++;
                } catch(Exception ex){ lbl.setText("Error en: " + line); return; }
            }
            lbl.setText("Productos cargados: " + count);
        });

        add(new JScrollPane(area), BorderLayout.CENTER);
        JPanel bot = new JPanel(new BorderLayout()); bot.add(btn, BorderLayout.NORTH); bot.add(lbl, BorderLayout.SOUTH);
        add(bot, BorderLayout.SOUTH);

        pack(); setLocationRelativeTo(parent);
    }
}
