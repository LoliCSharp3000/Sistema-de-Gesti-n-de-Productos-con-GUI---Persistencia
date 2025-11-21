package sistema;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{


    public Main(){
        setTitle("Sistema de Gestion de Productos");
        setSize(600, 400);               
        setLocationRelativeTo(null);     
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Gestión de Productos"));

        DefaultListModel<Producto> model = new DefaultListModel<>();
        JList<Producto> list = new JList<>(model);
        JScrollPane scroll = new JScrollPane(list);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton añadir = new JButton("Añadir");
        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");

        panelInferior.add(añadir);
        panelInferior.add(editar);
        panelInferior.add(eliminar);

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

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
        editar.addActionListener(e ->{
            String name = JOptionPane.showInputDialog("Cual es el nombre del producto");
            // completar
        });
        // añadir eliminar
        setVisible(true);
    }


    public static void main(String[] args) {
        new Main();
    }
}