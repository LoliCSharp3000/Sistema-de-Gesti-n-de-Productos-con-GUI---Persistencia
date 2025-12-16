package sistema;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Main extends JFrame{
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

        cargarProductos(model);

        añadir.addActionListener(e ->{
            Producto producto = new Producto();
            try {
                String name = JOptionPane.showInputDialog("Dime el nombre del producto");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Dime el precio del producto"));
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Pon la cantidad del producto"));
                float weight = Float.parseFloat(JOptionPane.showInputDialog("Pon el peso del producto"));
                String category = JOptionPane.showInputDialog("Pon la categoria del producto");
                producto = new Producto(name, price, cantidad, weight, category);
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número válido");
                return;
            } catch (IllegalArgumentException i) {
                JOptionPane.showMessageDialog(null, "❌ Error: " + i.getMessage());
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "❌ Error inesperado: " + ex.getMessage());
                return;
            }
            
            model.addElement(producto);
            guardarProductos(model);
        });

        editar.addActionListener(e ->{
            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para editar");
                return;
            }
            Producto prod = model.getElementAt(index);
            try {
                String name = JOptionPane.showInputDialog("Dime el nombre del producto");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Dime el precio del producto"));
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Pon la cantidad del producto"));
                float weight = Float.parseFloat(JOptionPane.showInputDialog("Pon el peso del producto"));
                String category = JOptionPane.showInputDialog("Pon la categoria del producto");
                prod.setAll(name, price, cantidad, weight, category);
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número válido");
                return;
            } catch (IllegalArgumentException i) {
                JOptionPane.showMessageDialog(null, "❌ Error: " + i.getMessage());
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "❌ Error inesperado: " + ex.getMessage());
                return;
            }
            model.setElementAt(prod, index);
            guardarProductos(model);
        });
        eliminar.addActionListener(e->{
            int index = list.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar");
                return;
            }
            model.remove(index);
            guardarProductos(model);
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

    public static void guardarProductos(DefaultListModel<Producto> model){
        ArrayList<Producto> lista = new ArrayList<>();
        for (int index = 0; index < model.size(); index++) {
            lista.add(model.getElementAt(index));
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("productos.json")) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error guardando productos: " + e.getMessage());
        }
    }
    public static void cargarProductos(DefaultListModel<Producto> model){
        Gson gson = new Gson();
        File file = new File("productos.json");
        if (!file.exists()) {
            return;
        }
        try(FileReader reader = new FileReader(file)) {
            ArrayList<Producto> lista = gson.fromJson(reader, new TypeToken<ArrayList<Producto>>(){}.getType()); 
            model.clear();
            if (lista != null) {
                for (Producto p : lista) {
                    model.addElement(p);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar productos: " + e.getMessage());
        }
    }
}