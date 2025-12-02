package sistema;

import javax.swing.*;
import java.awt.*;

public class VentanaCalculos extends JFrame {
    private DefaultListModel<Producto> model;

    public VentanaCalculos(DefaultListModel<Producto> model){
        this.model = model;

        setTitle("Cálculos del Sistema");
        setSize(650, 450);               
        setLocationRelativeTo(null);     
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 250));

        // ---------- PANEL SUPERIOR ----------
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(70, 130, 180));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel titulo = new JLabel("Cálculo de Parámetros");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(titulo);

        // ---------- LISTA DE RESULTADOS ----------
        DefaultListModel<String> textmodel = new DefaultListModel<>();
        JList<String> list = new JList<>(textmodel);
        list.setFont(new Font("Monospaced", Font.PLAIN, 16));
        list.setBackground(new Color(255,255,255));
        list.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(245, 245, 250));
        panelCentral.add(scroll, BorderLayout.CENTER);

        // ---------- PANEL DE BOTONES ----------
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(2, 2, 10, 10));
        panelInferior.setBackground(new Color(245,245,250));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JButton precioMedio = crearBoton("Precio Medio");
        JButton cantidadTotal = crearBoton("Cantidad Total");
        JButton pesoTotal = crearBoton("Peso Total");
        JButton comparar = crearBoton("Comparar Productos");

        panelInferior.add(precioMedio);
        panelInferior.add(cantidadTotal);
        panelInferior.add(pesoTotal);
        panelInferior.add(comparar);

        precioMedio.addActionListener(e->{
            double total = 0;
            for (int i = 0; i < model.size(); i++) {
                total += model.get(i).getPrice();
            }
            total = total/model.size();
            textmodel.addElement("Precio medio: " + total);
        });
        cantidadTotal.addActionListener(e->{
            double total = 0;
            for (int i = 0; i < model.size(); i++) {
                total += model.get(i).getCantidad();
            }
            textmodel.addElement("Cantidad Total: " + total);
        });
        pesoTotal.addActionListener(e->{
            double total = 0;
            for (int i = 0; i < model.size(); i++) {
                total += model.get(i).getWeight();
            }
            textmodel.addElement("Peso Total: " + total);
        });
        comparar.addActionListener(e->{
            
        });
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        setVisible(true);
    }
    private JButton crearBoton(String texto){
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(new Color(100, 149, 237));
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createLineBorder(new Color(70,130,180), 2));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }
}
