package vistas;

import dao.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ReporteVentasFecha extends javax.swing.JPanel {

    // Componentes de la vista
    private JSpinner txtDesde;
    private JSpinner txtHasta;
    private JButton btnGenerar;
    private JTable tablaVentas;
    private JLabel lblTotal;

    // Paleta de colores Soft UI
    private final Color bgApp = new Color(240, 244, 248); // Gris azulado ultra claro
    private final Color textDark = new Color(30, 41, 59); // Texto principal oscuro
    private final Color textMuted = new Color(100, 116, 139); // Texto secundario
    private final Color brandDarkBlue = Color.decode("#00384E");
    private final Color accentBlue = new Color(59, 130, 246); // Azul vibrante

    public ReporteVentasFecha() {
        initComponentsPremium();
    }

    private void initComponentsPremium() {
        setBackground(bgApp);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50)); // Márgenes amplios

        // ==========================================
        // 1. ENCABEZADO (Títulos)
        // ==========================================
        JPanel pnlTitulos = new JPanel();
        pnlTitulos.setLayout(new BoxLayout(pnlTitulos, BoxLayout.Y_AXIS));
        pnlTitulos.setBackground(bgApp);
        pnlTitulos.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JLabel lblTitulo = new JLabel("Corte de Caja");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setForeground(textDark);

        JLabel lblSubtitulo = new JLabel("Reporte de ingresos y ventas filtrado por rango de fechas");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(textMuted);

        pnlTitulos.add(lblTitulo);
        pnlTitulos.add(Box.createVerticalStrut(5));
        pnlTitulos.add(lblSubtitulo);

        add(pnlTitulos, BorderLayout.NORTH);

        // ==========================================
        // 2. CONTENIDO CENTRAL (Controles + Tabla)
        // ==========================================
        JPanel pnlCentro = new JPanel(new BorderLayout(0, 20)); // Espacio vertical de 20px
        pnlCentro.setBackground(bgApp);

        // --- TARJETA DE CONTROLES (Filtros de Fecha) ---
        PanelRedondeado pnlControles = new PanelRedondeado(25);
        pnlControles.setLayout(new GridBagLayout());
        pnlControles.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Etiqueta Desde
        JLabel lblDesde = new JLabel("Desde la fecha:");
        lblDesde.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDesde.setForeground(textMuted);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        pnlControles.add(lblDesde, gbc);

        // Spinner Desde
        txtDesde = new JSpinner(new SpinnerDateModel());
        txtDesde.setEditor(new JSpinner.DateEditor(txtDesde, "dd/MM/yyyy"));
        estilizarSpinner(txtDesde);
        gbc.gridx = 1; gbc.weightx = 1.0;
        pnlControles.add(txtDesde, gbc);

        // Etiqueta Hasta
        JLabel lblHasta = new JLabel("Hasta la fecha:");
        lblHasta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblHasta.setForeground(textMuted);
        gbc.gridx = 2; gbc.weightx = 0.0;
        pnlControles.add(lblHasta, gbc);

        // Spinner Hasta
        txtHasta = new JSpinner(new SpinnerDateModel());
        txtHasta.setEditor(new JSpinner.DateEditor(txtHasta, "dd/MM/yyyy"));
        estilizarSpinner(txtHasta);
        gbc.gridx = 3; gbc.weightx = 1.0;
        pnlControles.add(txtHasta, gbc);

        // Separador
        gbc.gridx = 4; gbc.weightx = 0.5;
        pnlControles.add(Box.createHorizontalGlue(), gbc);

        // Botón Generar (Estilo Moderno)
        btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGenerar.setBackground(brandDarkBlue);
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        btnGenerar.setBorderPainted(false);
        btnGenerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGenerar.setPreferredSize(new Dimension(180, 40));
        btnGenerar.putClientProperty("JButton.buttonType", "roundRect"); // Si usas FlatLaf lo redondea
        btnGenerar.addActionListener(this::btnGenerarActionPerformed);
        gbc.gridx = 5; gbc.weightx = 0.0;
        pnlControles.add(btnGenerar, gbc);

        pnlCentro.add(pnlControles, BorderLayout.NORTH);

        // --- TARJETA DE LA TABLA Y TOTAL ---
        PanelRedondeado pnlTablaContenedor = new PanelRedondeado(25);
        pnlTablaContenedor.setLayout(new BorderLayout());
        pnlTablaContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializamos y estilizamos la tabla
        tablaVentas = new JTable();
        estilizarTabla(tablaVentas);

        // Datos en blanco por defecto al abrir
        String[] columnas = {"No. Factura", "Fecha y Hora", "Cliente", "Cajero", "Total (L.)"};
        DefaultTableModel modeloInicial = new DefaultTableModel(null, columnas);
        tablaVentas.setModel(modeloInicial);

        JScrollPane scrollTabla = new JScrollPane(tablaVentas);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder()); 
        scrollTabla.getViewport().setBackground(Color.WHITE);
        
        pnlTablaContenedor.add(scrollTabla, BorderLayout.CENTER);

        // Franja del Total (Parte inferior de la tabla)
        JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlTotal.setBackground(Color.WHITE);
        pnlTotal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(241, 245, 249)), // Línea superior suave
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        lblTotal = new JLabel("Total de Ingresos: L. 0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 26)); // Letra grande para destacar el dinero
        lblTotal.setForeground(accentBlue); // Color vibrante para el dinero

        pnlTotal.add(lblTotal);
        pnlTablaContenedor.add(pnlTotal, BorderLayout.SOUTH);

        pnlCentro.add(pnlTablaContenedor, BorderLayout.CENTER);

        add(pnlCentro, BorderLayout.CENTER);
    }

    // ==========================================
    // ESTILOS VISUALES (Spinners y Tabla)
    // ==========================================
    private void estilizarSpinner(JSpinner spinner) {
        spinner.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        spinner.setPreferredSize(new Dimension(180, 35));
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField txt = ((JSpinner.DefaultEditor) editor).getTextField();
            txt.setForeground(textDark);
            txt.setFont(new Font("Segoe UI", Font.BOLD, 15));
            txt.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private void estilizarTabla(JTable tabla) {
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(textDark);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(40); 
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(new Color(241, 245, 249)); 
        tabla.setSelectionBackground(new Color(239, 246, 255));
        tabla.setSelectionForeground(brandDarkBlue);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(textMuted);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(226, 232, 240)));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.setDefaultRenderer(Object.class, centerRenderer); // Centramos todo por estética
    }

    // ==========================================
    // LÓGICA DE BASE DE DATOS (Intacta)
    // ==========================================
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Capturar las fechas
        java.util.Date fechaDesde = (java.util.Date) txtDesde.getValue();
        java.util.Date fechaHasta = (java.util.Date) txtHasta.getValue();

        // Validación básica
        if (fechaDesde.after(fechaHasta)) {
            JOptionPane.showMessageDialog(this, "La Fecha de Inicio no puede ser mayor a la Fecha de Fin.", "Error en Fechas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Preparar modelo
        String[] columnas = {"No. Factura", "Fecha y Hora", "Cliente", "Cajero", "Total (L.)"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        // Consultar Base de Datos
        List<Object[]> listaVentas = new VentaDAO().reporteVentasPorFecha(fechaDesde, fechaHasta);

        double granTotal = 0.0;

        // Llenar tabla
        for (Object[] fila : listaVentas) {
            double totalFactura = (double) fila[4];
            granTotal += totalFactura; 
            
            fila[4] = "L. " + String.format("%,.2f", totalFactura); 
            modelo.addRow(fila);
        }

        tablaVentas.setModel(modelo);

        // Actualizar el Gran Total
        lblTotal.setText("Total de Ingresos: L. " + String.format("%,.2f", granTotal));
        
        if (listaVentas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron ventas en este rango de fechas.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }                                          

    // ==========================================
    // CLASE AUXILIAR: PANEL CON ESQUINAS REDONDEADAS
    // ==========================================
    class PanelRedondeado extends JPanel {
        private int radio;

        public PanelRedondeado(int radio) {
            this.radio = radio;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radio, radio));
            
            g2.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGap(0, 1374, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1053, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1374, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1053, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
