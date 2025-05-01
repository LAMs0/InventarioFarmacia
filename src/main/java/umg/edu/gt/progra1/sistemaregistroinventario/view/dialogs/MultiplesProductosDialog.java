package umg.edu.gt.progra1.sistemaregistroinventario.view.dialogs;


import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;
import umg.edu.gt.progra1.sistemaregistroinventario.view.FarmaciaGUI;

import javax.swing.*;
import java.awt.*;

public class MultiplesProductosDialog extends JDialog {
    private InventarioFarmacia inventario;
    private FarmaciaGUI parent;

    public MultiplesProductosDialog(FarmaciaGUI parent, InventarioFarmacia inventario) {
        super(parent, "Ingresar Múltiples Productos", true);
        this.parent = parent;
        this.inventario = inventario;
        initComponents();
    }

    private void initComponents() {
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Área de texto para ingreso múltiple
        JTextArea areaEntrada = new JTextArea(15, 40);
        areaEntrada.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaEntrada.setText(
                "# Formato: Nombre|Código|Precio|Stock\n" +
                        "# Ejemplos:\n" +
                        "Jarabe para la tos|J001|8.75|50\n" +
                        "Curitas|C002|3.20|200\n" +
                        "Termómetro digital|T003|15.99|30\n" +
                        "Alcohol en gel 250ml|A004|5.50|150"
        );

        JScrollPane scrollPane = new JScrollPane(areaEntrada);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnProcesar = new JButton("Procesar Productos");

        btnCancelar.addActionListener(e -> dispose());
        btnProcesar.addActionListener(e -> procesarProductos(areaEntrada.getText()));

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnProcesar);

        // Panel de instrucciones
        JPanel instructionPanel = new JPanel(new BorderLayout());
        instructionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JLabel lblInstrucciones = new JLabel(
                "<html><b>Instrucciones:</b> Ingrese un producto por línea con el formato: <i>Nombre|Código|Precio|Stock</i></html>"
        );
        instructionPanel.add(lblInstrucciones, BorderLayout.NORTH);

        // Construcción final
        add(instructionPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void procesarProductos(String texto) {
        String[] lineas = texto.split("\n");
        int exitosos = 0;
        int errores = 0;

        for (String linea : lineas) {
            try {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue; // Saltar líneas vacías o comentarios
                }

                String[] partes = linea.split("\\|");
                if (partes.length != 4) {
                    throw new IllegalArgumentException("Formato incorrecto");
                }

                String nombre = partes[0].trim();
                String codigo = partes[1].trim();
                double precio = Double.parseDouble(partes[2].trim());
                int stock = Integer.parseInt(partes[3].trim());

                if (nombre.isEmpty() || codigo.isEmpty()) {
                    throw new IllegalArgumentException("Nombre y código son obligatorios");
                }

                if (precio <= 0 || stock < 0) {
                    throw new IllegalArgumentException("Precio y stock deben ser positivos");
                }

                ProductoDTO nuevo = new ProductoDTO(nombre, codigo, precio, stock);
                inventario.agregarProducto(nuevo);
                exitosos++;
            } catch (Exception e) {
                parent.agregarMensaje("⚠ Error en línea: " + linea + " - " + e.getMessage());
                errores++;
            }
        }

        parent.agregarMensaje(String.format(
                "📦 Resultado: %d productos agregados, %d errores",
                exitosos, errores
        ));

        if (errores == 0) {
            dispose(); // Cerrar automáticamente si todo fue exitoso
        } else {
            JOptionPane.showMessageDialog(this,
                    String.format("Se procesaron %d productos con éxito.\n%d líneas tuvieron errores.", exitosos, errores),
                    "Resultado del Procesamiento",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}