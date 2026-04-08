package vistas;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ReporteKardex extends javax.swing.JPanel {
    
    // Lógica Original
    private java.util.List<Object[]> todoElHistorial = new java.util.ArrayList<>();
    
    // Variables Fantasmas
    private JPopupMenu popupProducto;
    private JTable tablaPopupProd;
    private DefaultTableModel modeloPopupProd;
    private java.util.List<entity.Producto> listaProdCache;
    private entity.Producto productoSeleccionado = null;

    // Componentes Visuales
    private JTextField txtBuscarProductos;
    private JCheckBox chkEntradas;
    private JCheckBox chkSalidas;
    private JButton btnGenerarKardex;
    private JTable tablaKardex;

    // Paleta de colores Soft UI
    private final Color bgApp = new Color(240, 244, 248); 
    private final Color textDark = new Color(30, 41, 59); 
    private final Color textMuted = new Color(100, 116, 139); 
    private final Color brandDarkBlue = Color.decode("#00384E");

    public ReporteKardex() {
        initComponentsPremium();
        prepararBuscadorProducto();
    }

    private void initComponentsPremium() {
        setBackground(bgApp);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50)); 

        // ==========================================
        // 1. ENCABEZADO
        // ==========================================
        JPanel pnlTitulos = new JPanel();
        pnlTitulos.setLayout(new BoxLayout(pnlTitulos, BoxLayout.Y_AXIS));
        pnlTitulos.setBackground(bgApp);
        pnlTitulos.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JLabel lblTitulo = new JLabel("Kardex de Producto");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setForeground(textDark);

        JLabel lblSubtitulo = new JLabel("Historial detallado de entradas y salidas de inventario");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(textMuted);

        pnlTitulos.add(lblTitulo);
        pnlTitulos.add(Box.createVerticalStrut(5));
        pnlTitulos.add(lblSubtitulo);

        add(pnlTitulos, BorderLayout.NORTH);

        // ==========================================
        // 2. CONTENIDO CENTRAL
        // ==========================================
        JPanel pnlCentro = new JPanel(new BorderLayout(0, 20)); 
        pnlCentro.setBackground(bgApp);

        // --- TARJETA DE CONTROLES ---
        PanelRedondeado pnlControles = new PanelRedondeado(25);
        pnlControles.setLayout(new GridBagLayout());
        pnlControles.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Etiqueta
        JLabel lblProductos = new JLabel("Buscar Producto:");
        lblProductos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblProductos.setForeground(textMuted);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        pnlControles.add(lblProductos, gbc);

        // Buscador
        txtBuscarProductos = new JTextField();
        txtBuscarProductos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtBuscarProductos.setForeground(textDark);
        txtBuscarProductos.setPreferredSize(new Dimension(300, 40));
        txtBuscarProductos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtBuscarProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarProductosMouseClicked(evt);
            }
        });
        txtBuscarProductos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                txtBuscarProductosKeyReleased(evt);
            }
        });
        gbc.gridx = 1; gbc.weightx = 1.0;
        pnlControles.add(txtBuscarProductos, gbc);

        // Separador
        gbc.gridx = 2; gbc.weightx = 0.5;
        pnlControles.add(Box.createHorizontalGlue(), gbc);

        // Checkboxes
        chkEntradas = new JCheckBox("Entradas");
        estilizarCheckbox(chkEntradas);
        chkEntradas.setSelected(true); // Seleccionado por defecto
        chkEntradas.addActionListener(e -> filtrarTablaKardex());
        gbc.gridx = 3; gbc.weightx = 0.0;
        pnlControles.add(chkEntradas, gbc);

        chkSalidas = new JCheckBox("Salidas");
        estilizarCheckbox(chkSalidas);
        chkSalidas.setSelected(true); // Seleccionado por defecto
        chkSalidas.addActionListener(e -> filtrarTablaKardex());
        gbc.gridx = 4;
        pnlControles.add(chkSalidas, gbc);

        // Botón Ver Historial
        btnGenerarKardex = new JButton("Ver Historial");
        btnGenerarKardex.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGenerarKardex.setBackground(brandDarkBlue);
        btnGenerarKardex.setForeground(Color.WHITE);
        btnGenerarKardex.setFocusPainted(false);
        btnGenerarKardex.setBorderPainted(false);
        btnGenerarKardex.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGenerarKardex.setPreferredSize(new Dimension(160, 40));
        btnGenerarKardex.addActionListener(e -> btnGenerarKardexActionPerformed());
        gbc.gridx = 5; gbc.insets = new Insets(0, 30, 0, 10);
        pnlControles.add(btnGenerarKardex, gbc);

        pnlCentro.add(pnlControles, BorderLayout.NORTH);

        // --- TARJETA DE LA TABLA ---
        PanelRedondeado pnlTablaContenedor = new PanelRedondeado(25);
        pnlTablaContenedor.setLayout(new BorderLayout());
        pnlTablaContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tablaKardex = new JTable();
        estilizarTabla(tablaKardex);
        
        // Iniciamos la tabla vacía con los títulos
        String[] columnas = {"Fecha y Hora", "Ref/Factura", "Tipo Movimiento", "Cant.", "Responsable"};
        tablaKardex.setModel(new DefaultTableModel(null, columnas));

        JScrollPane scrollTabla = new JScrollPane(tablaKardex);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder()); 
        scrollTabla.getViewport().setBackground(Color.WHITE);
        
        pnlTablaContenedor.add(scrollTabla, BorderLayout.CENTER);

        pnlCentro.add(pnlTablaContenedor, BorderLayout.CENTER);

        add(pnlCentro, BorderLayout.CENTER);
    }

    // ==========================================
    // ESTILOS VISUALES
    // ==========================================
    private void estilizarCheckbox(JCheckBox chk) {
        chk.setFont(new Font("Segoe UI", Font.BOLD, 14));
        chk.setForeground(textDark);
        chk.setBackground(Color.WHITE);
        chk.setFocusPainted(false);
        chk.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        // Renderer Personalizado para pintar "Entradas" de Verde y "Salidas" de Rojo
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Centramos el texto
                setHorizontalAlignment(SwingConstants.CENTER);
                
                if (!isSelected) {
                    String tipoMovimiento = table.getValueAt(row, 2).toString().toUpperCase();
                    if (tipoMovimiento.contains("ENTRADA")) {
                        c.setForeground(new Color(16, 185, 129)); // Verde
                    } else if (tipoMovimiento.contains("VENTA") || tipoMovimiento.contains("SALIDA")) {
                        c.setForeground(new Color(239, 68, 68)); // Rojo
                    } else {
                        c.setForeground(textDark);
                    }
                }
                return c;
            }
        };

        tabla.setDefaultRenderer(Object.class, customRenderer);
    }

    // ==========================================
    // LÓGICA DE BUSCADOR FANTASMA
    // ==========================================
    private void prepararBuscadorProducto() {
        popupProducto = new JPopupMenu();
        popupProducto.setFocusable(false);
        popupProducto.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        
        modeloPopupProd = new DefaultTableModel(null, new String[]{"ID", "Producto", "Stock"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPopupProd = new JTable(modeloPopupProd);
        
        // Estilo rápido a la tabla fantasma
        tablaPopupProd.setRowHeight(30);
        tablaPopupProd.setShowVerticalLines(false);
        tablaPopupProd.setSelectionBackground(new Color(239, 246, 255));
        tablaPopupProd.setSelectionForeground(brandDarkBlue);
        tablaPopupProd.getTableHeader().setBackground(Color.WHITE);
        
        tablaPopupProd.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int fila = tablaPopupProd.getSelectedRow();
                if (fila != -1) seleccionarProducto((int) tablaPopupProd.getValueAt(fila, 0));
            }
        });

        JScrollPane scroll = new JScrollPane(tablaPopupProd);
        scroll.setPreferredSize(new Dimension(350, 150));
        scroll.setBorder(BorderFactory.createEmptyBorder());
        popupProducto.add(scroll);
        
        actualizarCacheProductos();
    }

    private void actualizarCacheProductos() {
        listaProdCache = new dao.ProductoDAO().searchProductos();
    }

    private void filtrarProducto(String busqueda) {
        modeloPopupProd.setRowCount(0);
        for (entity.Producto p : listaProdCache) {
            if (p.getNombreProducto().toLowerCase().contains(busqueda.toLowerCase())) {
                modeloPopupProd.addRow(new Object[]{p.getIdProducto(), p.getNombreProducto(), p.getStockActual()});
            }
        }
    }

    private void seleccionarProducto(int id) {
        for (entity.Producto p : listaProdCache) {
            if (p.getIdProducto() == id) {
                productoSeleccionado = p;
                txtBuscarProductos.setText(p.getNombreProducto());
                popupProducto.setVisible(false);
                break;
            }
        }
    }

    private void txtBuscarProductosMouseClicked(MouseEvent evt) {                                                
        String texto = txtBuscarProductos.getText().trim();
        filtrarProducto(texto); 
        if (modeloPopupProd.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
        }
    }                                               

    private void txtBuscarProductosKeyReleased(KeyEvent evt) {                                               
        String texto = txtBuscarProductos.getText().trim();
        if(texto.isEmpty()){ 
            productoSeleccionado = null; 
        }
        filtrarProducto(texto);
        if (modeloPopupProd.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
            txtBuscarProductos.requestFocus();
        } else {
            popupProducto.setVisible(false);
        }
    }

    // ==========================================
    // LÓGICA DE REPORTE (Intacta)
    // ==========================================
    private void btnGenerarKardexActionPerformed() {                                                 
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un producto primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        todoElHistorial = new dao.ProductoDAO().historialKardexProducto(productoSeleccionado.getIdProducto());
        filtrarTablaKardex();
        
        if (todoElHistorial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este producto no tiene movimientos registrados.", "Sin Datos", JOptionPane.INFORMATION_MESSAGE);
        }
    } 

    private void filtrarTablaKardex() {
        String[] columnas = {"Fecha y Hora", "Ref/Factura", "Tipo Movimiento", "Cant.", "Responsable"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        boolean verEntradas = chkEntradas.isSelected();
        boolean verSalidas = chkSalidas.isSelected();

        for (Object[] fila : todoElHistorial) {
            String tipo = fila[2].toString().toUpperCase(); 

            boolean esEntrada = tipo.contains("ENTRADA");
            boolean esSalida = tipo.contains("VENTA") || tipo.contains("SALIDA");

            if ((esEntrada && verEntradas) || (esSalida && verSalidas)) {
                modelo.addRow(fila);
            }
        }
        tablaKardex.setModel(modelo);
    }

    // ==========================================
    // CLASE AUXILIAR
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

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1374, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1048, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
