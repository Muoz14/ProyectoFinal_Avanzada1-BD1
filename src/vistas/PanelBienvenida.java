package vistas;

import dao.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PanelBienvenida extends JPanel {
    
    private JLabel lblHora;
    private JLabel lblFecha;
    
    // Variables para las métricas
    private JLabel lblTotalProductos;
    private JLabel lblVentasHoy;
    private JLabel lblClientes;
    private JLabel lblVentaMaximaHoy; 
    private JLabel lblAlertaStock;
    private JLabel lblVentasMes;
    
    private PanelGraficoVentas grafico;

    // Paleta de colores Soft UI
    private final Color bgApp = new Color(240, 244, 248); // Gris azulado ultra claro
    private final Color textDark = new Color(30, 41, 59); // Texto principal oscuro
    private final Color textMuted = new Color(100, 116, 139); // Texto secundario
    private final Color brandDarkBlue = Color.decode("#00384E");

    public PanelBienvenida() {
        initComponents(); 
        construirVista(); 
        iniciarReloj();   
        cargarDatosRapidos(); 
    }
    
    private void construirVista() {
        setBackground(bgApp);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50)); 

        // ==========================================
        // 1. ENCABEZADO (Títulos y Reloj Cápsula)
        // ==========================================
        JPanel pnlEncabezado = new JPanel(new BorderLayout());
        pnlEncabezado.setBackground(bgApp);

        JPanel pnlTitulos = new JPanel();
        pnlTitulos.setLayout(new BoxLayout(pnlTitulos, BoxLayout.Y_AXIS));
        pnlTitulos.setBackground(bgApp);

        JLabel lblTitulo = new JLabel("Panel de Control");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38)); 
        lblTitulo.setForeground(textDark); 

        JLabel lblSubtitulo = new JLabel("Centro Informático Apolo - Vista General");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(textMuted);

        pnlTitulos.add(lblTitulo);
        pnlTitulos.add(Box.createVerticalStrut(5));
        pnlTitulos.add(lblSubtitulo);

        // Reloj en estilo "Cápsula Flotante"
        JPanel pnlRelojCapsula = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
            }
        };
        pnlRelojCapsula.setOpaque(false);
        pnlRelojCapsula.setLayout(new BoxLayout(pnlRelojCapsula, BoxLayout.Y_AXIS));
        pnlRelojCapsula.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        lblHora = new JLabel("00:00:00");
        lblHora.setFont(new Font("Segoe UI", Font.BOLD, 28)); 
        lblHora.setForeground(brandDarkBlue);
        lblHora.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblFecha = new JLabel("Día, 00 de Mes");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFecha.setForeground(textMuted);
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlRelojCapsula.add(lblHora);
        pnlRelojCapsula.add(lblFecha);

        pnlEncabezado.add(pnlTitulos, BorderLayout.WEST);
        pnlEncabezado.add(pnlRelojCapsula, BorderLayout.EAST);

        add(pnlEncabezado, BorderLayout.NORTH);

        // ==========================================
        // 2. CUERPO (Tarjetas Soft UI y Gráfico)
        // ==========================================
        JPanel pnlContenidoCentral = new JPanel();
        pnlContenidoCentral.setLayout(new BoxLayout(pnlContenidoCentral, BoxLayout.Y_AXIS));
        pnlContenidoCentral.setBackground(bgApp);
        pnlContenidoCentral.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // --- GRID DE TARJETAS (2x3) ---
        JPanel pnlTarjetas = new JPanel(new GridLayout(2, 3, 30, 30)); // Mayor espaciado
        pnlTarjetas.setBackground(bgApp);

        lblTotalProductos = new JLabel("0");
        lblVentasHoy = new JLabel("L. 0.00");
        lblClientes = new JLabel("0");
        lblVentaMaximaHoy = new JLabel("L. 0.00");
        lblAlertaStock = new JLabel("0");
        lblVentasMes = new JLabel("L. 0.00");

        // Fila 1 (Colores Pastel / Vibrantes)
        pnlTarjetas.add(new TarjetaMetrica("Productos en Inventario", lblTotalProductos, new Color(59, 130, 246))); // Azul
        pnlTarjetas.add(new TarjetaMetrica("Ventas del Día", lblVentasHoy, new Color(16, 185, 129)));              // Verde
        pnlTarjetas.add(new TarjetaMetrica("Clientes Registrados", lblClientes, new Color(139, 92, 246)));         // Morado
        
        // Fila 2
        pnlTarjetas.add(new TarjetaMetrica("Mayor Venta de Hoy", lblVentaMaximaHoy, new Color(245, 158, 11)));     // Naranja
        pnlTarjetas.add(new TarjetaMetrica("Alertas Bajo Stock", lblAlertaStock, new Color(239, 68, 68)));         // Rojo
        pnlTarjetas.add(new TarjetaMetrica("Ventas Totales (Mes)", lblVentasMes, brandDarkBlue));                  // Azul Apolo

        pnlContenidoCentral.add(pnlTarjetas);
        pnlContenidoCentral.add(Box.createVerticalStrut(40)); 

        // --- GRÁFICO DE TENDENCIA ---
        JPanel pnlGraficoWrapper = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Curvas iguales a las tarjetas
                g2.dispose();
            }
        };
        pnlGraficoWrapper.setOpaque(false);
        pnlGraficoWrapper.setLayout(new BorderLayout());
        pnlGraficoWrapper.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel lblTituloGrafico = new JLabel("Dinámica de Ventas (Últimos 6 meses)");
        lblTituloGrafico.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloGrafico.setForeground(textDark);
        lblTituloGrafico.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        pnlGraficoWrapper.add(lblTituloGrafico, BorderLayout.NORTH);
        
        grafico = new PanelGraficoVentas();
        grafico.setPreferredSize(new Dimension(800, 250)); 
        pnlGraficoWrapper.add(grafico, BorderLayout.CENTER);

        pnlContenidoCentral.add(pnlGraficoWrapper);

        // Scroll invisible
        JScrollPane scrollPane = new JScrollPane(pnlContenidoCentral);
        scrollPane.setBorder(null); 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.setBackground(bgApp);
        scrollPane.getViewport().setBackground(bgApp);

        add(scrollPane, BorderLayout.CENTER);
    }

    // ==========================================
    // CLASE INTERNA: TARJETA "SOFT UI" PERSONALIZADA
    // ==========================================
    class TarjetaMetrica extends JPanel {
        private final Color colorAcento;

        public TarjetaMetrica(String titulo, JLabel lblValor, Color colorAcento) {
            this.colorAcento = colorAcento;
            setOpaque(false); // Necesario para que las esquinas redondeadas se vean bien
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 20)); // Más padding izquierdo por la barra

            JLabel lblTit = new JLabel("<html><div style='text-align: left; width: 100%;'>" + titulo.toUpperCase() + "</div></html>");
            lblTit.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lblTit.setForeground(textMuted); 

            lblValor.setFont(new Font("Segoe UI", Font.BOLD, 36)); 
            lblValor.setForeground(textDark); 
            lblValor.setHorizontalAlignment(SwingConstants.LEFT);
            lblValor.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

            add(lblTit, BorderLayout.NORTH);
            add(lblValor, BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo Blanco redondeado
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));

            // Barra de acento a la izquierda (Borde redondeado suave)
            g2.setColor(colorAcento);
            g2.fill(new RoundRectangle2D.Double(0, 0, 10, getHeight(), 30, 30));
            // Cuadramos la parte derecha de la barra para que se integre al borde
            g2.fillRect(5, 0, 5, getHeight()); 

            g2.dispose();
        }
    }

    // ==========================================
    // MOTOR DEL RELOJ
    // ==========================================
    private void iniciarReloj() {
        Timer timer = new Timer(1000, e -> {
            Date ahora = new Date();
            SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE, dd MMM yyyy");
            
            lblHora.setText(sdfHora.format(ahora));
            
            String fechaStr = sdfFecha.format(ahora);
            fechaStr = fechaStr.substring(0, 1).toUpperCase() + fechaStr.substring(1);
            lblFecha.setText(fechaStr);
        });
        timer.start();
    }

    // ==========================================
    // CARGA DE DATOS (Intacta)
    // ==========================================
    private void cargarDatosRapidos() {
        try {
            ProductoDAO daoProductos = new ProductoDAO();
            ClienteDAO daoClientes = new ClienteDAO();
            VentaDAO daoVentas = new VentaDAO();

            lblTotalProductos.setText(String.valueOf(daoProductos.searchProductos().size()));
            lblClientes.setText(String.valueOf(daoClientes.searchClientes().size()));
            lblAlertaStock.setText(String.valueOf(daoProductos.contarProductosBajoStock()));
            
            lblVentasHoy.setText("L. " + String.format("%,.2f", daoVentas.obtenerTotalVentasHoy()));
            lblVentaMaximaHoy.setText("L. " + String.format("%,.2f", daoVentas.obtenerVentaMaximaHoy()));
            lblVentasMes.setText("L. " + String.format("%,.2f", daoVentas.obtenerVentasMesActual()));
            
            java.util.List<Object[]> datosBD = daoVentas.obtenerVentasUltimos6Meses();
            grafico.actualizarDatos(datosBD);
            
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    // ==========================================
    // CLASE INTERNA: GRÁFICO DE PÍLDORAS
    // ==========================================
    class PanelGraficoVentas extends JPanel {
        private String[] meses = {"Sin Datos"};
        private double[] ventas = {0.0};
        
        public PanelGraficoVentas() {
            setOpaque(false); // Fondo transparente para heredar el blanco redondeado del wrapper
        }

        public void actualizarDatos(java.util.List<Object[]> datosBD){
            if (datosBD == null || datosBD.isEmpty()) return; 
            
            String[] nombresMeses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
            this.meses = new String[datosBD.size()];
            this.ventas = new double[datosBD.size()];

            for (int i = 0; i < datosBD.size(); i++) {
                Object[] fila = datosBD.get(i);
                int numMes = (int) fila[0];
                this.meses[i] = nombresMeses[numMes - 1]; 
                this.ventas[i] = (double) fila[1];
            }
            repaint(); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int paddingX = 10;
            int paddingY = 30;
            int labelPadding = 25;

            double maxScore = 0;
            for (Double score : ventas) maxScore = Math.max(maxScore, score);
            if (maxScore == 0) maxScore = 1; 

            // Eje X sutil
            g2.setColor(new Color(226, 232, 240)); 
            g2.setStroke(new BasicStroke(2f));
            g2.drawLine(paddingX, height - labelPadding, width - paddingX, height - labelPadding); 

            // Líneas horizontales punteadas muy suaves
            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0);
            g2.setStroke(dashed);
            g2.setColor(new Color(241, 245, 249));
            for (int i = 1; i <= 4; i++) {
                int y = height - labelPadding - (i * (height - paddingY - labelPadding) / 4);
                g2.drawLine(paddingX, y, width - paddingX, y);
            }

            int numBarras = ventas.length;
            int barWidth = (width - (paddingX * 2)) / numBarras - 40; 
            FontMetrics metrics = g2.getFontMetrics();

            for (int i = 0; i < numBarras; i++) {
                int x = paddingX + 20 + i * (barWidth + 40);
                int barHeight = (int) ((ventas[i] / maxScore) * (height - paddingY - labelPadding));
                int y = height - labelPadding - barHeight;

                // Barra estilo "Píldora" (Relleno redondeado arriba y cuadrado abajo)
                g2.setColor(new Color(59, 130, 246, 220)); // Azul corporativo vibrante
                g2.fill(new RoundRectangle2D.Double(x, y, barWidth, barHeight, 15, 15));
                // Tapamos el redondeo inferior para que nazca plana desde el piso
                g2.fillRect(x, height - labelPadding - 15, barWidth, 15);

                // Etiquetas del Eje X (Meses)
                g2.setColor(textMuted);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
                int labelWidth = metrics.stringWidth(meses[i]);
                g2.drawString(meses[i], x + (barWidth - labelWidth) / 2, height - labelPadding + 20);
                
                // Valores sobre las barras
                g2.setColor(textDark);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                String valorStr = String.format("%,.0f", ventas[i]);
                int valWidth = metrics.stringWidth(valorStr);
                g2.drawString(valorStr, x + (barWidth - valWidth) / 2, y - 10);
            }
            g2.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
