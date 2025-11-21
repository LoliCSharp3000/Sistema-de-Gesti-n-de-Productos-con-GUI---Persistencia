package sistema;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{


    public Main(){
        setTitle("Sistema de Gestion de Productos");
        setSize(600, 400);               // Tamaño
        setLocationRelativeTo(null);     // Centrar ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        DefaultListModel<Producto> model = new DefaultListModel<>();
        JList<Producto> list = new JList<>(model);
        add(panelSuperior);
        add(list);
        JPanel panelCentral = new JPanel();
        JButton añadir = new JButton("Añadir");
        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");
        add(panelCentral);
        add(añadir);
        add(editar);
        add(eliminar);
        añadir.addActionListener(e ->{
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
            Producto producto = new Producto(name, price, cantidad, weight, category);
            model.addElement(producto);
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        new Main();
    }
}