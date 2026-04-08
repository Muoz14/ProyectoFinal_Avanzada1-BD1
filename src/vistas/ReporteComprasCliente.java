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

public class ReporteComprasCliente extends javax.swing.JPanel {
    
    // --- VARIABLES FANTASMAS PARA CLIENTE ---
    private JPopupMenu popupCliente;
    private JTable tablaPopupCli;
    private DefaultTableModel modeloPopupCli;
    private java.util.List<entity.Cliente> listaCliCache;
    private entity.Cliente clienteSeleccionado = null;

    // --- COMPONENTES VISUALES ---
    private JTextField txtBuscarCliente;
    private JTable tablaCompras;
    private JLabel lblTotalInversion;

    // Paleta de colores Soft UI
    private final Color bgApp = new Color(240, 244, 248); 
    private final Color textDark = new Color(30, 41, 59); 
    private final Color textMuted = new Color(100, 116, 139); 
    private final Color brandDarkBlue = Color.decode("#00384E");
    private final Color accentBlue = new Color(59, 130, 246);

    public ReporteComprasCliente() {
        initComponentsPremium();
        prepararBuscadorCliente(); 
        actualizarTablaCompras(); 
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

        JLabel lblTitulo = new JLabel("Historial de Compras");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setForeground(textDark);

        JLabel lblSubtitulo = new JLabel("Consulta la inversión total y el detalle de facturas por cliente");
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

        // --- TARJETA DE BÚSQUEDA ---
        PanelRedondeado pnlControles = new PanelRedondeado(25);
        pnlControles.setLayout(new GridBagLayout());
        pnlControles.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblBuscar = new JLabel("Buscar Cliente:");
        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBuscar.setForeground(textMuted);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        pnlControles.add(lblBuscar, gbc);

        txtBuscarCliente = new JTextField();
        txtBuscarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtBuscarCliente.setForeground(textDark);
        txtBuscarCliente.setPreferredSize(new Dimension(400, 40));
        txtBuscarCliente.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                txtBuscarClienteMouseClicked(evt);
            }
        });
        txtBuscarCliente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });
        gbc.gridx = 1; gbc.weightx = 1.0;
        pnlControles.add(txtBuscarCliente, gbc);

        pnlCentro.add(pnlControles, BorderLayout.NORTH);

        // --- TARJETA DE LA TABLA Y TOTAL ---
        PanelRedondeado pnlTablaContenedor = new PanelRedondeado(25);
        pnlTablaContenedor.setLayout(new BorderLayout());
        pnlTablaContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tablaCompras = new JTable();
        estilizarTabla(tablaCompras);

        JScrollPane scrollTabla = new JScrollPane(tablaCompras);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder()); 
        scrollTabla.getViewport().setBackground(Color.WHITE);
        
        pnlTablaContenedor.add(scrollTabla, BorderLayout.CENTER);

        // Franja del Total
        JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlTotal.setBackground(Color.WHITE);
        pnlTotal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(241, 245, 249)), 
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        lblTotalInversion = new JLabel("Inversión Total del Cliente: L. 0.00");
        lblTotalInversion.setFont(new Font("Segoe UI", Font.BOLD, 26)); 
        lblTotalInversion.setForeground(accentBlue); 

        pnlTotal.add(lblTotalInversion);
        pnlTablaContenedor.add(pnlTotal, BorderLayout.SOUTH);

        pnlCentro.add(pnlTablaContenedor, BorderLayout.CENTER);

        add(pnlCentro, BorderLayout.CENTER);
    }

    // ==========================================
    // ESTILOS VISUALES
    // ==========================================
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
        tabla.setDefaultRenderer(Object.class, centerRenderer); 
    }

    // ==========================================
    // MOTOR DE BUSQUEDA FANTASMA (CLIENTES)
    // ==========================================
    private void prepararBuscadorCliente() {
        popupCliente = new JPopupMenu();
        popupCliente.setFocusable(false);
        popupCliente.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        
        modeloPopupCli = new DefaultTableModel(null, new String[]{"ID", "Nombre Cliente"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPopupCli = new JTable(modeloPopupCli);
        
        // Estilo rápido a la tabla fantasma
        tablaPopupCli.setRowHeight(30);
        tablaPopupCli.setShowVerticalLines(false);
        tablaPopupCli.setSelectionBackground(new Color(239, 246, 255));
        tablaPopupCli.setSelectionForeground(brandDarkBlue);
        tablaPopupCli.getTableHeader().setBackground(Color.WHITE);
        
        tablaPopupCli.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                int fila = tablaPopupCli.getSelectedRow();
                if (fila != -1) seleccionarCliente((int) tablaPopupCli.getValueAt(fila, 0));
            }
        });

        JScrollPane scroll = new JScrollPane(tablaPopupCli);
        scroll.setPreferredSize(new Dimension(400, 150));
        scroll.setBorder(BorderFactory.createEmptyBorder());
        popupCliente.add(scroll);
        
        actualizarCacheClientes();
    }

    private void actualizarCacheClientes() {
        listaCliCache = new dao.ClienteDAO().searchClientes();
    }

    private void filtrarCliente(String busqueda) {
        modeloPopupCli.setRowCount(0);
        for (entity.Cliente c : listaCliCache) {
            String nombreCompleto = c.getNombreCliente() + " " + c.getApellidoCliente();
            if (nombreCompleto.toLowerCase().contains(busqueda.toLowerCase())) {
                modeloPopupCli.addRow(new Object[]{c.getIdCliente(), nombreCompleto});
            }
        }
    }

    private void seleccionarCliente(int id) {
        for (entity.Cliente c : listaCliCache) {
            if (c.getIdCliente() == id) {
                clienteSeleccionado = c;
                txtBuscarCliente.setText(c.getNombreCliente() + " " + c.getApellidoCliente());
                popupCliente.setVisible(false);
                
                // ACTUALIZAMOS EL REPORTE AUTOMÁTICAMENTE
                actualizarTablaCompras();
                break;
            }
        }
    }

    private void txtBuscarClienteMouseClicked(MouseEvent evt) {                                              
        if(!txtBuscarCliente.isEnabled()) return;
        String texto = txtBuscarCliente.getText().trim();
        filtrarCliente(texto);
        if (modeloPopupCli.getRowCount() > 0) {
            popupCliente.show(txtBuscarCliente, 0, txtBuscarCliente.getHeight());
        }
    }                                             

    private void txtBuscarClienteKeyReleased(KeyEvent evt) {                                             
        if(!txtBuscarCliente.isEnabled()) return;
        String texto = txtBuscarCliente.getText().trim();
        
        if(texto.isEmpty()){ 
            clienteSeleccionado = null; 
            actualizarTablaCompras(); 
        }
        
        filtrarCliente(texto);
        if (modeloPopupCli.getRowCount() > 0) {
            popupCliente.show(txtBuscarCliente, 0, txtBuscarCliente.getHeight());
            txtBuscarCliente.requestFocus();
        } else {
            popupCliente.setVisible(false);
        }
    }

    // ==========================================
    // LOGICA DEL REPORTE
    // ==========================================
    private void actualizarTablaCompras() {
        String[] cols = {"No. Factura", "Fecha", "Cajero", "Total (L.)"};
        DefaultTableModel modelo = new DefaultTableModel(null, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        if (clienteSeleccionado == null) {
            tablaCompras.setModel(modelo);
            lblTotalInversion.setText("Inversión Total del Cliente: L. 0.00");
            return;
        }

        java.util.List<Object[]> datos = new dao.ClienteDAO().obtenerHistorialCompras(clienteSeleccionado.getIdCliente());
        double acumulado = 0;

        for (Object[] fila : datos) {
            double totalRow = (double) fila[3];
            acumulado += totalRow;
            fila[3] = "L. " + String.format("%,.2f", totalRow);
            modelo.addRow(fila);
        }

        tablaCompras.setModel(modelo);
        lblTotalInversion.setText("Inversión Total del Cliente: L. " + String.format("%,.2f", acumulado));
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

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1374, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
