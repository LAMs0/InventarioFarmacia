package umg.edu.gt.progra1.sistemaregistroinventario.view;
import umg.edu.gt.progra1.sistemaregistroinventario.controller.FarmaciaController;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FarmaciaGUI extends JFrame {
    private FarmaciaController controller;
    private JTextArea textArea;

    public FarmaciaGUI() {
        super("Sistema de Inventario - Farmacia");
        controller = new FarmaciaController(this);
        initializeUI();
        cargarProductosEjemplo();
    }

    private void initializeUI() {
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de botones con estilo mejorado
        JPanel buttonPanel = createButtonPanel();

        // Área de texto con scroll
        textArea = new JTextArea();
        configureTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registro de Operaciones"));

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botones con iconos y tooltips
        panel.add(createButton("Agregar", "➕", "Agregar nuevo producto", e -> controller.mostrarDialogoAgregar()));
        panel.add(createButton("Buscar", "🔍", "Buscar producto por código", e -> controller.mostrarDialogoBuscar()));
        panel.add(createButton("Inventario", "📋", "Mostrar inventario completo", e -> controller.mostrarInventario()));
        panel.add(createButton("Vender", "💰", "Registrar venta de producto", e -> controller.mostrarDialogoVender()));
        panel.add(createButton("Actualizar", "✏️", "Actualizar precio de producto", e -> controller.mostrarDialogoActualizar()));
        panel.add(createButton("Ingresar Varios", "📦", "Ingresar múltiples productos", e -> controller.mostrarDialogoMultiplesProductos()));

        return panel;
    }

    private JButton createButton(String text, String icon, String tooltip, ActionListener action) {
        JButton button = new JButton(text + " " + icon);
        button.setToolTipText(tooltip);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(action);
        return button;
    }

    private void configureTextArea() {
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setMargin(new Insets(5, 5, 5, 5));
    }

    private void cargarProductosEjemplo() {
        controller.cargarProductosEjemplo();
    }

    public void agregarMensaje(String mensaje) {
        textArea.append(mensaje + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Establecer el look and feel del sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // Configuración adicional para mejorar la apariencia
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);

                // Mostrar la ventana principal
                FarmaciaGUI gui = new FarmaciaGUI();
                gui.setLocationRelativeTo(null); // Centrar en pantalla
                gui.setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error al iniciar la aplicación: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}