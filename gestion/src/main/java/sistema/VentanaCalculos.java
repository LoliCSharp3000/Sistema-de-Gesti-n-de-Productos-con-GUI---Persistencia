package sistema;

import javax.swing.*;
import java.awt.*;

public class VentanaCalculos extends JFrame {
    public VentanaCalculos(){
        setTitle("Calculos");
        setSize(600, 400);               
        setLocationRelativeTo(null);     
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Calculo de parametros"));

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        JScrollPane scroll = new JScrollPane(list);
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton precioMedio = new JButton("Calcular Precio Medio");
        JButton cantidadTotal = new JButton("Calcular cantidad total");
        JButton pesoTotal = new JButton("Calcular el peso total");
        JButton comparar = new JButton("comparar 2 productos");
        //añadir a la ventana
    }
}
