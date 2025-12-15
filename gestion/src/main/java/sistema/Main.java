package sistema;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    //falta JSON

    public Main(){
        setTitle("Sistema de Gestión de Productos");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 250));

        // ---------- PANEL SUPERIOR ----------
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBackground(new Color(70,130,180));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel titulo = new JLabel("Gestión de Productos");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);

        panelSuperior.add(titulo);

        // ---------- LISTA ----------
        DefaultListModel<Producto> model = new DefaultListModel<>();
        JList<Producto> list = new JList<>(model);
        list.setFont(new Font("Monospaced", Font.PLAIN, 16));
        list.setBackground(Color.WHITE);
        list.setBorder(BorderFactory.createLineBorder(new Color(180,180,180), 1));

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(245,245,250));
        panelCentral.add(scroll, BorderLayout.CENTER);

        // ---------- BOTONES ----------
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 4, 10, 10));
        panelInferior.setBackground(new Color(245,245,250));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JButton añadir = crearBoton("Añadir");
        JButton editar = crearBoton("Editar");
        JButton eliminar = crearBoton("Eliminar");
        JButton calculos = crearBoton("Calcular");

        panelInferior.add(añadir);
        panelInferior.add(editar);
        panelInferior.add(eliminar);
        panelInferior.add(calculos);

        añadir.addActionListener(e ->{
            String name = "";
            double price = 0.0;
            int cantidad = 0;
            float weight = 0f;
            try {
                name = JOptionPane.showInputDialog("Dime el nombre del producto");
                if (name.trim().isEmpty()||name == null) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío");
                }
                price = Double.parseDouble(JOptionPane.showInputDialog("Dime el precio del producto"));
                if (price <= 0) {
                    throw new IllegalArgumentException("No puede ser grati ni negativo");
                }
                cantidad = Integer.parseInt(JOptionPane.showInputDialog("Pon la cantidad del producto"));
                if (cantidad <= 0) {
                    throw new IllegalArgumentException("No puede ser grati ni negativo");
                }
                weight = Float.parseFloat(JOptionPane.showInputDialog("Pon el peso del producto"));
                if (weight <= 0) {
                    throw new IllegalArgumentException("No puede ser grati ni negativo");
                }
            } catch (NumberFormatException n) {
                JOptionPane.showInputDialog("No pongas texto en donde debe estar el numero");
            } catch (IllegalArgumentException i) {
                JOptionPane.showMessageDialog(null, "❌ Error: " + i.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "❌ Error inesperado: " + ex.getMessage());
            }
            String category = JOptionPane.showInputDialog("Pon la categoria del producto");
            Producto producto = new Producto(name, price, cantidad, weight, category);
            model.addElement(producto);
        });
        editar.addActionListener(e ->{
            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para editar");
                return;
            }
            Producto prod = model.getElementAt(index);
            String name = JOptionPane.showInputDialog("Dime el nombre del producto");
            double price = 0.0;
            int cantidad = 0;
            float weight = 0f;
            try {
                price = Double.parseDouble(JOptionPane.showInputDialog("Dime el precio del producto"));
                cantidad = Integer.parseInt(JOptionPane.showInputDialog("Pon la cantidad del producto"));
                weight = Float.parseFloat(JOptionPane.showInputDialog("Pon el peso del producto"));
            } catch (NumberFormatException n) {
                JOptionPane.showInputDialog("No pongas texto en donde debe estar el numero");
            }
            String category = JOptionPane.showInputDialog("Pon la categoria del producto");
            prod.SetAll(name, price, cantidad, weight, category);
            model.setElementAt(prod, index);
            
        });
        eliminar.addActionListener(e->{
            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar");
                return;
            }
            model.remove(index);
        });
        
        calculos.addActionListener(e->{
            if (model.isEmpty()) {
                JOptionPane.showMessageDialog(null, "❗ No hay productos para calcular", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            new VentanaCalculos(model);
        });
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        setVisible(true);
    }
    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(new Color(100, 149, 237));
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createLineBorder(new Color(70,130,180), 2));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    public static void main(String[] args) {
        new Main();
    }
}