import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// @version 1.0/2026

public class InterfazNotas extends JFrame implements ActionListener {

    private JLabel[] etiquetasNotas;
    private JTextField[] camposNotas;
    private JButton botonCalcular;
    private JButton botonLimpiar;
    private JLabel etiquetaPromedio;
    private JLabel etiquetaDesviacion;
    private JLabel etiquetaMayor;
    private JLabel etiquetaMenor;
    private JLabel valorPromedio;
    private JLabel valorDesviacion;
    private JLabel valorMayor;
    private JLabel valorMenor;

    private Notas notas;


    public InterfazNotas() {
        notas = new Notas();
        inicializarComponentes();
    }


    private void inicializarComponentes() {
        setTitle("Gestión de Notas");
        setSize(500, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout(10, 10));
        contenedor.setBackground(new Color(245, 247, 250));


        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 48, 135)); // Azul UNAL
        JLabel titulo = new JLabel("Ingreso de Notas");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        panelTitulo.add(titulo);
        contenedor.add(panelTitulo, BorderLayout.NORTH);


        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(245, 247, 250));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        etiquetasNotas = new JLabel[5];
        camposNotas = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            etiquetasNotas[i] = new JLabel("Nota " + (i + 1) + ":");
            etiquetasNotas[i].setFont(new Font("SansSerif", Font.PLAIN, 13));
            camposNotas[i] = new JTextField(10);
            camposNotas[i].setFont(new Font("SansSerif", Font.PLAIN, 13));
            camposNotas[i].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 190, 210)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
            ));

            gbc.gridx = 0; gbc.gridy = i; gbc.weightx = 0.3;
            panelCentral.add(etiquetasNotas[i], gbc);
            gbc.gridx = 1; gbc.weightx = 0.7;
            panelCentral.add(camposNotas[i], gbc);
        }


        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotones.setBackground(new Color(245, 247, 250));

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBackground(new Color(0, 48, 135));
        botonCalcular.setForeground(Color.WHITE);
        botonCalcular.setFont(new Font("SansSerif", Font.BOLD, 13));
        botonCalcular.setFocusPainted(false);
        botonCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonCalcular.addActionListener(this);

        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBackground(new Color(180, 30, 30));
        botonLimpiar.setForeground(Color.WHITE);
        botonLimpiar.setFont(new Font("SansSerif", Font.BOLD, 13));
        botonLimpiar.setFocusPainted(false);
        botonLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonLimpiar.addActionListener(this);

        panelBotones.add(botonCalcular);
        panelBotones.add(botonLimpiar);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panelCentral.add(panelBotones, gbc);

        contenedor.add(panelCentral, BorderLayout.CENTER);


        JPanel panelResultados = new JPanel(new GridLayout(4, 2, 10, 8));
        panelResultados.setBackground(new Color(230, 235, 245));
        panelResultados.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(0, 48, 135)),
            BorderFactory.createEmptyBorder(15, 30, 15, 30)
        ));

        Font fuenteEtiqueta = new Font("SansSerif", Font.BOLD, 13);
        Font fuenteValor = new Font("SansSerif", Font.PLAIN, 13);
        Color colorEtiqueta = new Color(0, 48, 135);

        etiquetaPromedio   = crearEtiqueta("Promedio:", fuenteEtiqueta, colorEtiqueta);
        valorPromedio      = crearEtiqueta("---", fuenteValor, Color.DARK_GRAY);
        etiquetaDesviacion = crearEtiqueta("Desviación estándar:", fuenteEtiqueta, colorEtiqueta);
        valorDesviacion    = crearEtiqueta("---", fuenteValor, Color.DARK_GRAY);
        etiquetaMayor      = crearEtiqueta("Nota mayor:", fuenteEtiqueta, colorEtiqueta);
        valorMayor         = crearEtiqueta("---", fuenteValor, Color.DARK_GRAY);
        etiquetaMenor      = crearEtiqueta("Nota menor:", fuenteEtiqueta, colorEtiqueta);
        valorMenor         = crearEtiqueta("---", fuenteValor, Color.DARK_GRAY);

        panelResultados.add(etiquetaPromedio);   panelResultados.add(valorPromedio);
        panelResultados.add(etiquetaDesviacion); panelResultados.add(valorDesviacion);
        panelResultados.add(etiquetaMayor);      panelResultados.add(valorMayor);
        panelResultados.add(etiquetaMenor);      panelResultados.add(valorMenor);

        contenedor.add(panelResultados, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel crearEtiqueta(String texto, Font fuente, Color color) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(fuente);
        lbl.setForeground(color);
        return lbl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();

        if (fuente == botonCalcular) {
            try {
                for (int i = 0; i < 5; i++) {
                    double nota = Double.parseDouble(camposNotas[i].getText().trim());
                    if (nota < 0 || nota > 5) {
                        JOptionPane.showMessageDialog(this,
                            "La nota " + (i + 1) + " debe estar entre 0.0 y 5.0",
                            "Valor inválido", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    notas.listaNotas[i] = nota;
                }
                valorPromedio.setText(String.format("%.2f", notas.calcularPromedio()));
                valorDesviacion.setText(String.format("%.2f", notas.calcularDesviacion()));
                valorMayor.setText(String.format("%.2f", notas.notaMayor()));
                valorMenor.setText(String.format("%.2f", notas.notaMenor()));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Por favor ingrese valores numéricos válidos en todos los campos.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            }

        } else if (fuente == botonLimpiar) {
            for (JTextField campo : camposNotas) {
                campo.setText("");
            }
            valorPromedio.setText("---");
            valorDesviacion.setText("---");
            valorMayor.setText("---");
            valorMenor.setText("---");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazNotas());
    }
}
