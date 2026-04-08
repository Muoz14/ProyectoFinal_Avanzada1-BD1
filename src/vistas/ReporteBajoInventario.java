package vistas;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class ReporteBajoInventario extends JPanel {

    // Componentes de la vista
    private JTextField txtBuscar;
    private JSpinner txtLimite;
    private JTable tablaReporte;
    private DefaultTableModel modelo;

    // Paleta de colores Soft UI
    private final Color bgApp = new Color(240, 244, 248); // Gris azulado ultra claro
    private final Color textDark = new Color(30, 41, 59); // Texto principal oscuro
    private final Color textMuted = new Color(100, 116, 139); // Texto secundario
    private final Color brandDarkBlue = Color.decode("#00384E");
    private final Color accentBlue = new Color(59, 130, 246); // Azul vibrante para detalles

    public ReporteBajoInventario() {
        initComponentsPremium();
        cargarTabla();
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

        JLabel lblTitulo = new JLabel("Stock Crítico");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setForeground(textDark);

        JLabel lblSubtitulo = new JLabel("Monitoreo de productos con bajo nivel de inventario");
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

        // --- TARJETA DE CONTROLES (Búsqueda y Filtro) ---
        PanelRedondeado pnlControles = new PanelRedondeado(25);
        pnlControles.setLayout(new GridBagLayout());
        pnlControles.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Etiqueta Buscar
        JLabel lblBuscar = new JLabel("Buscar Producto:");
        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBuscar.setForeground(textMuted);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        pnlControles.add(lblBuscar, gbc);

        // Campo de Texto Buscar
        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtBuscar.setForeground(textDark);
        txtBuscar.setPreferredSize(new Dimension(300, 35));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        gbc.gridx = 1; gbc.weightx = 1.0;
        pnlControles.add(txtBuscar, gbc);

        // Separador visual
        gbc.gridx = 2; gbc.weightx = 0.5;
        pnlControles.add(Box.createHorizontalGlue(), gbc);

        // Etiqueta Límite
        JLabel lblLimite = new JLabel("Mostrar stock menor o igual a:");
        lblLimite.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblLimite.setForeground(textMuted);
        gbc.gridx = 3; gbc.weightx = 0.0;
        pnlControles.add(lblLimite, gbc);

        // Spinner Límite
        txtLimite = new JSpinner(new SpinnerNumberModel(15, 0, 1000, 1));
        txtLimite.setFont(new Font("Segoe UI", Font.BOLD, 15));
        txtLimite.setPreferredSize(new Dimension(100, 35));
        // Estilo básico para el spinner
        JComponent editor = txtLimite.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor).getTextField().setForeground(accentBlue);
            ((JSpinner.DefaultEditor) editor).getTextField().setFont(new Font("Segoe UI", Font.BOLD, 16));
        }
        txtLimite.addChangeListener(evt -> txtLimiteStateChanged());
        gbc.gridx = 4;
        pnlControles.add(txtLimite, gbc);

        pnlCentro.add(pnlControles, BorderLayout.NORTH);

        // --- TARJETA DE LA TABLA ---
        PanelRedondeado pnlTablaContenedor = new PanelRedondeado(25);
        pnlTablaContenedor.setLayout(new BorderLayout());
        pnlTablaContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializamos la tabla
        tablaReporte = new JTable();
        estilizarTabla(tablaReporte); // ¡Aquí aplicamos la magia visual!

        JScrollPane scrollTabla = new JScrollPane(tablaReporte);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder()); // Quitamos el borde feo del scroll
        scrollTabla.getViewport().setBackground(Color.WHITE);
        
        pnlTablaContenedor.add(scrollTabla, BorderLayout.CENTER);

        pnlCentro.add(pnlTablaContenedor, BorderLayout.CENTER);

        add(pnlCentro, BorderLayout.CENTER);
    }

    // ==========================================
    // MÉTODO PARA EMBELLECER LA TABLA (DISEÑO MODERNO)
    // ==========================================
    private void estilizarTabla(JTable tabla) {
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(textDark);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Altura de las filas para que no se vea apretado
        tabla.setRowHeight(40); 
        
        // Quitar líneas verticales y poner líneas horizontales muy suaves
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(new Color(241, 245, 249)); 
        
        // Color al seleccionar una fila (Azul claro con texto azul oscuro)
        tabla.setSelectionBackground(new Color(239, 246, 255));
        tabla.setSelectionForeground(brandDarkBlue);

        // Estilizar el encabezado (Header)
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(textMuted);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        // Línea divisoria debajo del encabezado
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(226, 232, 240)));

        // Alinear texto (Opcional, centrar algunas columnas)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.setDefaultRenderer(String.class, centerRenderer);
    }

    // ==========================================
    // LÓGICA DE BASE DE DATOS (Intacta)
    // ==========================================
    private void cargarTabla() {
        int limite = (int) txtLimite.getValue();

        String[] columnas = {"ID", "Producto", "Precio", "Stock Actual"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        // Tu código de base de datos
        try {
            java.util.List<entity.Producto> lista = new dao.ProductoDAO().reporteBajoStock(limite);
            for (entity.Producto p : lista) {
                modelo.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombreProducto(),
                    "L. " + String.format("%,.2f", p.getPrecio()),
                    p.getStockActual()
                });
            }
        } catch (Exception e) {
            System.out.println("Error al cargar tabla: " + e.getMessage());
        }

        tablaReporte.setModel(modelo);
        
        // Reaplicamos el filtro si había algo escrito
        txtBuscarKeyReleased(null);
    }

    // ==========================================
    // EVENTOS
    // ==========================================
    private void txtBuscarKeyReleased(KeyEvent evt) {
        if (modelo == null) return;
        
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(modelo);
        tablaReporte.setRowSorter(trs);

        String textoBusqueda = txtBuscar.getText().trim();

        if (textoBusqueda.isEmpty()) {
            tablaReporte.setRowSorter(null);
        } else {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }

    private void txtLimiteStateChanged() {
        cargarTabla();
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
            
            // Fondo de la tarjeta (Blanco)
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radio, radio));
            
            g2.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
