package sesion21;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sesion21 {

    private static ArrayList<JButton> botonesDinamicos = new ArrayList<>();
    private static final int MAX_FILAS = 10;
    private static final int MAX_COLUMNAS = 3;
    private static final int MAX_BOTONES = MAX_FILAS * MAX_COLUMNAS;
    private static JPanel panelBotones = new JPanel();
    private static int margenX = 20; // Margen inicial en X
    private static int margenY = 20; // Margen inicial en Y

    public static void main(String[] args) {
        ArrayList<JComponent> componentes = getComponentes();
        JFrame pantalla = getFrame(componentes);       
    }

    private static ArrayList<JComponent> getComponentes() {
        int margen = 20;
        int margenSuperior = 20;
        JLabel label = getLabel(margen, margenSuperior);
        JButton button = getButton(margen, margenSuperior);
        JTextField textField = getTextField(margen, margenSuperior);
        
        button.addActionListener(e -> {
            String texto = textField.getText();
            if (!texto.isEmpty() && botonesDinamicos.size() < MAX_BOTONES) {
                agregarBoton(texto);
            } else if (botonesDinamicos.size() >= MAX_BOTONES) {
                JOptionPane.showMessageDialog(null, "Has alcanzado el límite de 30 botones.");
            }
        });

        ArrayList<JComponent> componentes = new ArrayList<>();
        componentes.add(label);
        componentes.add(button);
        componentes.add(textField);
        componentes.add(panelBotones);
        
        return componentes;
    }

    private static JFrame getFrame(ArrayList<JComponent> componentes) throws HeadlessException {
        JFrame pantalla = new JFrame("Mi pantallita");
        configurarPantalla(pantalla);
        agregarComponentes(componentes, pantalla);
        return pantalla;
    }

    private static void configurarPantalla(JFrame pantalla) {
        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla.setSize(600, 600);
        pantalla.setVisible(true);
        pantalla.setLayout(null);
        panelBotones.setBounds(20, 120, 550, 400);  
        panelBotones.setLayout(null);  
    }

    private static void agregarComponentes(ArrayList<JComponent> componentes, JFrame pantalla) {
        for (JComponent componente : componentes) {
            pantalla.add(componente);
        }
    }

    private static JTextField getTextField(int margen, int margenSuperior) {
        JTextField textField = new JTextField(20);
        textField.setBounds(margen, margenSuperior + 65, 150, 20);
        return textField;
    }

    private static JButton getButton(int margen, int margenSuperior) {
        JButton button = new JButton("Agregar Botón");
        button.setBounds(margen, margenSuperior + 40, 150, 20);
        return button;
    }

    private static JLabel getLabel(int margen, int margenSuperior) {
        JLabel label = new JLabel("Introduce un nombre:");
        label.setBounds(margen, margenSuperior, 150, 20);
        return label;
    }

    private static void agregarBoton(String texto) {
       
        JButton nuevoBoton = new JButton(texto);
        nuevoBoton.addActionListener(e -> {
            botonesDinamicos.remove(nuevoBoton);
            panelBotones.remove(nuevoBoton);
            reorganizarBotones();
        });

        botonesDinamicos.add(nuevoBoton);
        panelBotones.add(nuevoBoton);
        reorganizarBotones();
    }

    private static void reorganizarBotones() {
       
        for (int i = 0; i < botonesDinamicos.size(); i++) {
            JButton boton = botonesDinamicos.get(i);
            int columna = i / MAX_FILAS; 
            int fila = i % MAX_FILAS; 
            int x = margenX + (columna * 120); 
            int y = margenY + (fila * 40); 
            boton.setBounds(x, y, 100, 30);
        }

        
        panelBotones.revalidate();
        panelBotones.repaint();
    }
}
