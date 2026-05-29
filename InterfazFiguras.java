package figuras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

abstract class FiguraGeometrica {
    private double volumen;
    private double superficie;

    public void setVolumen(double volumen) { this.volumen = volumen; }
    public double getVolumen() { return volumen; }
    public void setSuperficie(double superficie) { this.superficie = superficie; }
    public double getSuperficie() { return superficie; }

    public abstract void calcularVolumen();
    public abstract void calcularSuperficie();
    public abstract String getNombre();
}

class Cilindro extends FiguraGeometrica {
    private double radio, altura;
    public Cilindro(double radio, double altura) { this.radio = radio; this.altura = altura; }
    public double getRadio() { return radio; }
    public void setRadio(double radio) { this.radio = radio; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public void calcularVolumen() { setVolumen(Math.PI * Math.pow(radio, 2) * altura); }
    public void calcularSuperficie() { setSuperficie(2 * Math.PI * radio * (radio + altura)); }
    public String getNombre() { return "Cilindro"; }
}

class Esfera extends FiguraGeometrica {
    private double radio;
    public Esfera(double radio) { this.radio = radio; }
    public double getRadio() { return radio; }
    public void setRadio(double radio) { this.radio = radio; }
    public void calcularVolumen() { setVolumen((4.0 / 3.0) * Math.PI * Math.pow(radio, 3)); }
    public void calcularSuperficie() { setSuperficie(4 * Math.PI * Math.pow(radio, 2)); }
    public String getNombre() { return "Esfera"; }
}

class Piramide extends FiguraGeometrica {
    private double base, apotema;
    public Piramide(double base, double apotema) { this.base = base; this.apotema = apotema; }
    public double getBase() { return base; }
    public void setBase(double base) { this.base = base; }
    public double getApotema() { return apotema; }
    public void setApotema(double apotema) { this.apotema = apotema; }
    public void calcularVolumen() {
        double altura = Math.sqrt(Math.pow(apotema, 2) - Math.pow(base / 2, 2));
        setVolumen((1.0 / 3.0) * Math.pow(base, 2) * altura);
    }
    public void calcularSuperficie() { setSuperficie(Math.pow(base, 2) + 2 * base * apotema); }
    public String getNombre() { return "Pirámide"; }
}

class Cubo extends FiguraGeometrica {
    private double lado;
    public Cubo(double lado) { this.lado = lado; }
    public double getLado() { return lado; }
    public void setLado(double lado) { this.lado = lado; }
    public void calcularVolumen() { setVolumen(Math.pow(lado, 3)); }
    public void calcularSuperficie() { setSuperficie(6 * Math.pow(lado, 2)); }
    public String getNombre() { return "Cubo"; }
}

class Prisma extends FiguraGeometrica {
    private double base, altura, longitudLateral;
    public Prisma(double base, double altura, double longitudLateral) {
        this.base = base; this.altura = altura; this.longitudLateral = longitudLateral;
    }
    public double getBase() { return base; }
    public void setBase(double base) { this.base = base; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public double getLongitudLateral() { return longitudLateral; }
    public void setLongitudLateral(double l) { this.longitudLateral = l; }
    public void calcularVolumen() { setVolumen(base * altura * longitudLateral); }
    public void calcularSuperficie() { setSuperficie(2 * (base * altura + base * longitudLateral + altura * longitudLateral)); }
    public String getNombre() { return "Prisma"; }
}

class InterfazFiguras extends JFrame implements ActionListener {

    private JComboBox<String> selectorFigura;
    private JPanel panelEntradas;
    private JPanel panelImagen;
    private JLabel labelImagen;
    private JLabel labelResultadoVolumen;
    private JLabel labelResultadoSuperficie;
    private JButton btnCalcular;

    private JTextField txtRadio, txtAltura, txtBase, txtApotema, txtLado, txtLongitudLateral;
    private JLabel lblRadio, lblAltura, lblBase, lblApotema, lblLado, lblLongitudLateral;

    private String[] figuras = {"Cilindro", "Esfera", "Pirámide", "Cubo", "Prisma"};

    public InterfazFiguras() {
        setTitle("Cálculo de Figuras Geométricas");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(240, 248, 255));

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setBackground(new Color(240, 248, 255));
        JLabel lblFigura = new JLabel("Seleccione figura:");
        lblFigura.setFont(new Font("Arial", Font.BOLD, 14));
        selectorFigura = new JComboBox<>(figuras);
        selectorFigura.setFont(new Font("Arial", Font.PLAIN, 13));
        selectorFigura.addActionListener(this);
        panelTop.add(lblFigura);
        panelTop.add(selectorFigura);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 0));
        panelCentro.setBackground(new Color(240, 248, 255));

        panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));
        labelImagen = new JLabel("", SwingConstants.CENTER);
        panelImagen.add(labelImagen, BorderLayout.CENTER);

        panelEntradas = new JPanel();
        panelEntradas.setLayout(new BoxLayout(panelEntradas, BoxLayout.Y_AXIS));
        panelEntradas.setBackground(new Color(240, 248, 255));
        panelEntradas.setBorder(BorderFactory.createTitledBorder("Datos de entrada"));

        lblRadio = new JLabel("Radio (cm):");
        txtRadio = new JTextField(10);
        lblAltura = new JLabel("Altura (cm):");
        txtAltura = new JTextField(10);
        lblBase = new JLabel("Base (cm):");
        txtBase = new JTextField(10);
        lblApotema = new JLabel("Apotema (cm):");
        txtApotema = new JTextField(10);
        lblLado = new JLabel("Lado (cm):");
        txtLado = new JTextField(10);
        lblLongitudLateral = new JLabel("Longitud lateral (cm):");
        txtLongitudLateral = new JTextField(10);

        panelCentro.add(panelImagen);
        panelCentro.add(panelEntradas);

        JPanel panelBottom = new JPanel(new GridLayout(3, 1, 5, 5));
        panelBottom.setBackground(new Color(240, 248, 255));

        btnCalcular = new JButton("Calcular");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalcular.setBackground(new Color(70, 130, 180));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCalcular.addActionListener(this);

        labelResultadoVolumen = new JLabel("Volumen: -");
        labelResultadoVolumen.setFont(new Font("Arial", Font.BOLD, 13));
        labelResultadoSuperficie = new JLabel("Superficie: -");
        labelResultadoSuperficie.setFont(new Font("Arial", Font.BOLD, 13));

        panelBottom.add(btnCalcular);
        panelBottom.add(labelResultadoVolumen);
        panelBottom.add(labelResultadoSuperficie);

        panelPrincipal.add(panelTop, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBottom, BorderLayout.SOUTH);

        add(panelPrincipal);
        actualizarFormulario("Cilindro");
        setVisible(true);
    }

    private void agregarCampo(JLabel label, JTextField field) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila.setBackground(new Color(240, 248, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        fila.add(label);
        fila.add(field);
        panelEntradas.add(fila);
    }

    private void actualizarFormulario(String figura) {
        panelEntradas.removeAll();
        labelImagen.setIcon(obtenerIcono(figura));
        labelImagen.setText(figura);
        labelImagen.setVerticalTextPosition(SwingConstants.BOTTOM);
        labelImagen.setHorizontalTextPosition(SwingConstants.CENTER);
        labelImagen.setFont(new Font("Arial", Font.BOLD, 13));

        switch (figura) {
            case "Cilindro":
                agregarCampo(lblRadio, txtRadio);
                agregarCampo(lblAltura, txtAltura);
                break;
            case "Esfera":
                agregarCampo(lblRadio, txtRadio);
                break;
            case "Pirámide":
                agregarCampo(lblBase, txtBase);
                agregarCampo(lblApotema, txtApotema);
                break;
            case "Cubo":
                agregarCampo(lblLado, txtLado);
                break;
            case "Prisma":
                agregarCampo(lblBase, txtBase);
                agregarCampo(lblAltura, txtAltura);
                agregarCampo(lblLongitudLateral, txtLongitudLateral);
                break;
        }

        labelResultadoVolumen.setText("Volumen: -");
        labelResultadoSuperficie.setText("Superficie: -");
        panelEntradas.revalidate();
        panelEntradas.repaint();
    }

    private ImageIcon obtenerIcono(String figura) {
        int w = 120, h = 120;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(70, 130, 180));

        switch (figura) {
            case "Cilindro":
                g2.fillOval(20, 10, 80, 30);
                g2.fillRect(20, 25, 80, 60);
                g2.setColor(new Color(100, 160, 210));
                g2.fillOval(20, 70, 80, 30);
                g2.setColor(new Color(50, 100, 150));
                g2.drawOval(20, 10, 80, 30);
                g2.drawOval(20, 70, 80, 30);
                g2.drawLine(20, 25, 20, 85);
                g2.drawLine(100, 25, 100, 85);
                break;
            case "Esfera":
                g2.fillOval(10, 10, 100, 100);
                g2.setColor(new Color(100, 160, 210, 120));
                g2.fillOval(30, 20, 40, 20);
                break;
            case "Pirámide":
                int[] xPts = {60, 10, 110};
                int[] yPts = {10, 100, 100};
                g2.fillPolygon(xPts, yPts, 3);
                g2.setColor(new Color(50, 100, 150));
                g2.drawPolygon(xPts, yPts, 3);
                break;
            case "Cubo":
                g2.fillRect(20, 30, 60, 60);
                g2.setColor(new Color(50, 100, 150));
                int[] xCube = {20, 45, 105, 80};
                int[] yCube = {30, 5, 5, 30};
                g2.fillPolygon(xCube, yCube, 4);
                int[] xCube2 = {80, 105, 105, 80};
                int[] yCube2 = {30, 5, 65, 90};
                g2.fillPolygon(xCube2, yCube2, 4);
                g2.setColor(new Color(30, 70, 120));
                g2.drawRect(20, 30, 60, 60);
                g2.drawPolygon(xCube, yCube, 4);
                g2.drawPolygon(xCube2, yCube2, 4);
                break;
            case "Prisma":
                g2.fillRect(15, 40, 70, 55);
                g2.setColor(new Color(50, 100, 150));
                int[] xPrisma = {15, 40, 110, 85};
                int[] yPrisma = {40, 10, 10, 40};
                g2.fillPolygon(xPrisma, yPrisma, 4);
                int[] xPrisma2 = {85, 110, 110, 85};
                int[] yPrisma2 = {40, 10, 65, 95};
                g2.fillPolygon(xPrisma2, yPrisma2, 4);
                g2.setColor(new Color(30, 70, 120));
                g2.drawRect(15, 40, 70, 55);
                g2.drawPolygon(xPrisma, yPrisma, 4);
                g2.drawPolygon(xPrisma2, yPrisma2, 4);
                break;
        }
        g2.dispose();
        return new ImageIcon(img);
    }

    private void calcular() {
        String figura = (String) selectorFigura.getSelectedItem();
        try {
            FiguraGeometrica fg = null;
            switch (figura) {
                case "Cilindro":
                    fg = new Cilindro(Double.parseDouble(txtRadio.getText()), Double.parseDouble(txtAltura.getText()));
                    break;
                case "Esfera":
                    fg = new Esfera(Double.parseDouble(txtRadio.getText()));
                    break;
                case "Pirámide":
                    fg = new Piramide(Double.parseDouble(txtBase.getText()), Double.parseDouble(txtApotema.getText()));
                    break;
                case "Cubo":
                    fg = new Cubo(Double.parseDouble(txtLado.getText()));
                    break;
                case "Prisma":
                    fg = new Prisma(Double.parseDouble(txtBase.getText()), Double.parseDouble(txtAltura.getText()), Double.parseDouble(txtLongitudLateral.getText()));
                    break;
            }
            if (fg != null) {
                fg.calcularVolumen();
                fg.calcularSuperficie();
                labelResultadoVolumen.setText(String.format("Volumen: %.2f cm³", fg.getVolumen()));
                labelResultadoSuperficie.setText(String.format("Superficie: %.2f cm²", fg.getSuperficie()));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectorFigura) {
            actualizarFormulario((String) selectorFigura.getSelectedItem());
        } else if (e.getSource() == btnCalcular) {
            calcular();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazFiguras());
    }
}
