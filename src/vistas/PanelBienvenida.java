package vistas;

import dao.*;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PanelBienvenida extends javax.swing.JPanel {
    
    private JLabel lblHora;
    private JLabel lblFecha;
    
    // Variables para las métricas (1ra Fila)
    private JLabel lblTotalProductos;
    private JLabel lblVentasHoy;
    private JLabel lblClientes;
    
    // Variables para las nuevas métricas estratégicas (2da Fila)
    private JLabel lblVentaMaximaHoy; // <-- ¡Nueva!
    private JLabel lblAlertaStock;
    private JLabel lblVentasMes;
    
    private PanelGraficoVentas grafico;

    public PanelBienvenida() {
        initComponents(); // 1. Llama al método bloqueado de NetBeans
        construirVista(); // 2. Llama a nuestro método de diseño visual
        iniciarReloj();   // 3. Arranca la hora
        cargarDatosRapidos(); // 4. Consulta la Base de Datos
    }
    
    private void construirVista() {
        // Configuración principal del panel
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40)); // Márgenes reducidos ligeramente para pantallas de laptop

        // ==========================================
        // ENCABEZADO (Títulos y Reloj)
        // ==========================================
        JPanel pnlEncabezado = new JPanel(new BorderLayout());
        pnlEncabezado.setBackground(Color.WHITE);

        JPanel pnlTitulos = new JPanel();
        pnlTitulos.setLayout(new BoxLayout(pnlTitulos, BoxLayout.Y_AXIS));
        pnlTitulos.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("CENTRO INFORMÁTICO APOLO");
        lblTitulo.setFont(new Font("Franklin Gothic Book", Font.BOLD, 32)); // Tamaño ajustado
        lblTitulo.setForeground(new Color(0, 51, 76)); 

        JLabel lblSubtitulo = new JLabel("Bienvenido al Sistema Integral de Gestión");
        lblSubtitulo.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 18));
        lblSubtitulo.setForeground(Color.GRAY);

        pnlTitulos.add(lblTitulo);
        pnlTitulos.add(Box.createVerticalStrut(5));
        pnlTitulos.add(lblSubtitulo);

        // Panel del Reloj
        JPanel pnlReloj = new JPanel();
        pnlReloj.setLayout(new BoxLayout(pnlReloj, BoxLayout.Y_AXIS));
        pnlReloj.setBackground(Color.WHITE);

        lblHora = new JLabel("00:00:00");
        lblHora.setFont(new Font("Franklin Gothic Book", Font.BOLD, 42)); // Tamaño ajustado
        lblHora.setForeground(new Color(0, 51, 76));
        lblHora.setAlignmentX(Component.RIGHT_ALIGNMENT);

        lblFecha = new JLabel("Día, 00 de Mes de 0000");
        lblFecha.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 16));
        lblFecha.setForeground(Color.DARK_GRAY);
        lblFecha.setAlignmentX(Component.RIGHT_ALIGNMENT);

        pnlReloj.add(lblHora);
        pnlReloj.add(lblFecha);

        pnlEncabezado.add(pnlTitulos, BorderLayout.WEST);
        pnlEncabezado.add(pnlReloj, BorderLayout.EAST);

        add(pnlEncabezado, BorderLayout.NORTH);

        // ==========================================
        // CUERPO (Tarjetas y Gráfico)
        // ==========================================
        // Creamos un panel central que contenga todo y le ponemos un Scroll por si el monitor es muy pequeño
        JPanel pnlContenidoCentral = new JPanel();
        pnlContenidoCentral.setLayout(new BoxLayout(pnlContenidoCentral, BoxLayout.Y_AXIS));
        pnlContenidoCentral.setBackground(Color.WHITE);
        pnlContenidoCentral.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // --- GRID DE TARJETAS (2 Filas x 3 Columnas) ---
        JPanel pnlTarjetas = new JPanel(new GridLayout(2, 3, 20, 20)); // Espaciado de 20px
        pnlTarjetas.setBackground(Color.WHITE);

        // Inicializar etiquetas
        lblTotalProductos = new JLabel("0");
        lblVentasHoy = new JLabel("L. 0.00");
        lblClientes = new JLabel("0");
        lblVentaMaximaHoy = new JLabel("L. 0.00");
        lblAlertaStock = new JLabel("0");
        lblVentasMes = new JLabel("L. 0.00");

        // Fila 1
        pnlTarjetas.add(crearTarjeta("Productos en Inventario", lblTotalProductos, new Color(41, 128, 185))); // Azul
        pnlTarjetas.add(crearTarjeta("Ventas del Día", lblVentasHoy, new Color(39, 174, 96)));             // Verde
        pnlTarjetas.add(crearTarjeta("Clientes Registrados", lblClientes, new Color(142, 68, 173)));       // Morado
        
        // Fila 2
        // Agregamos la tarjeta de "Mayor Venta de Hoy"
        pnlTarjetas.add(crearTarjeta("Mayor Venta de Hoy", lblVentaMaximaHoy, new Color(243, 156, 18))); 
        pnlTarjetas.add(crearTarjeta("Alertas Bajo Stock", lblAlertaStock, new Color(231, 76, 60)));        
        pnlTarjetas.add(crearTarjeta("Ventas Totales (Mes)", lblVentasMes, new Color(44, 62, 80)));

        pnlContenidoCentral.add(pnlTarjetas);
        pnlContenidoCentral.add(Box.createVerticalStrut(40)); // Espacio entre tarjetas y gráfico

        // --- GRÁFICO DE TENDENCIA ---
        JLabel lblTituloGrafico = new JLabel("Tendencia de Ventas (Últimos 6 meses)");
        lblTituloGrafico.setFont(new Font("Franklin Gothic Book", Font.BOLD, 20));
        lblTituloGrafico.setForeground(Color.DARK_GRAY);
        lblTituloGrafico.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        pnlContenidoCentral.add(lblTituloGrafico);
        pnlContenidoCentral.add(Box.createVerticalStrut(10));
        
        grafico = new PanelGraficoVentas();
        // Le damos un alto preferido al gráfico, pero el ancho será responsivo
        grafico.setPreferredSize(new Dimension(800, 250)); 
        grafico.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        pnlContenidoCentral.add(grafico);

        // Añadimos el contenido central a un JScrollPane para laptops con pantallas pequeñas
        JScrollPane scrollPane = new JScrollPane(pnlContenidoCentral);
        scrollPane.setBorder(null); // Sin borde feo
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll más suave
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);
    }

    // ==========================================
    // MÉTODO CREADOR DE TARJETAS RESPONSIVAS
    // ==========================================
    private JPanel crearTarjeta(String titulo, JLabel lblValor, Color colorBorde) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout()); // Cambiamos a BorderLayout para mejor responsividad
        tarjeta.setBackground(Color.WHITE);
        
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 2, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15) // Padding interno
        ));
        
        // ELIMINAMOS el setPreferredSize. Ahora Swing decide el tamaño basado en el GridLayout

        // TRUCO HTML: Permite que el texto se envuelva en varias líneas si el panel se encoge
        JLabel lblTit = new JLabel("<html><div style='text-align: center; width: 100%;'>" + titulo + "</div></html>", SwingConstants.CENTER);
        lblTit.setFont(new Font("Franklin Gothic Book", Font.BOLD, 15));
        lblTit.setForeground(Color.DARK_GRAY);

        lblValor.setFont(new Font("Franklin Gothic Book", Font.BOLD, 30)); // Ligeramente más pequeño para evitar cortes
        lblValor.setForeground(colorBorde);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);

        tarjeta.add(lblTit, BorderLayout.NORTH);
        tarjeta.add(lblValor, BorderLayout.CENTER);

        return tarjeta;
    }

    // ==========================================
    // MOTOR DEL RELOJ
    // ==========================================
    private void iniciarReloj() {
        Timer timer = new Timer(1000, e -> {
            Date ahora = new Date();
            SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdfFecha = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");
            
            lblHora.setText(sdfHora.format(ahora));
            
            String fechaStr = sdfFecha.format(ahora);
            fechaStr = fechaStr.substring(0, 1).toUpperCase() + fechaStr.substring(1);
            lblFecha.setText(fechaStr);
        });
        timer.start();
    }

    // ==========================================
    // CARGA DE DATOS 
    // ==========================================
    private void cargarDatosRapidos() {
        try {
            ProductoDAO daoProductos = new ProductoDAO();
            ClienteDAO daoClientes = new ClienteDAO();
            VentaDAO daoVentas = new VentaDAO();

            // 1. Catálogos
            lblTotalProductos.setText(String.valueOf(daoProductos.searchProductos().size()));
            lblClientes.setText(String.valueOf(daoClientes.searchClientes().size()));
            lblAlertaStock.setText(String.valueOf(daoProductos.contarProductosBajoStock()));
            
            // 2. Ventas y Dinero (Con formato monetario)
            lblVentasHoy.setText("L. " + String.format("%,.2f", daoVentas.obtenerTotalVentasHoy()));
            lblVentaMaximaHoy.setText("L. " + String.format("%,.2f", daoVentas.obtenerVentaMaximaHoy()));
            lblVentasMes.setText("L. " + String.format("%,.2f", daoVentas.obtenerVentasMesActual()));
            
            // 3. Alimentar Gráfica
            java.util.List<Object[]> datosBD = daoVentas.obtenerVentasUltimos6Meses();
            grafico.actualizarDatos(datosBD);
            
        } catch (Exception e) {
            System.out.println("Error al cargar los datos reales: " + e.getMessage());
        }
    }

    // ==========================================
    // CLASE INTERNA: GRÁFICO DE BARRAS NATIVO
    // ==========================================
    class PanelGraficoVentas extends JPanel {
        private String[] meses = {"Sin Datos"};
        private double[] ventas = {0.0};
        
        public PanelGraficoVentas() {
            setBackground(Color.WHITE);
        }

        // Este método recibe la lista directo de MySQL y la transforma en barras
        public void actualizarDatos(java.util.List<Object[]> datosBD){
            if (datosBD == null || datosBD.isEmpty()) {
                return; // Se queda con "Sin Datos" si la BD está vacía
            }
            
            String[] nombresMeses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
            this.meses = new String[datosBD.size()];
            this.ventas = new double[datosBD.size()];

            for (int i = 0; i < datosBD.size(); i++) {
                Object[] fila = datosBD.get(i);
                int numMes = (int) fila[0];
                this.meses[i] = nombresMeses[numMes - 1]; // Convierte 10 -> "Oct"
                this.ventas[i] = (double) fila[1];
            }
            repaint(); // Obliga a repintar el gráfico con los datos frescos
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int padding = 40;
            int labelPadding = 25;

            double maxScore = 0;
            for (Double score : ventas) maxScore = Math.max(maxScore, score);
            if (maxScore == 0) maxScore = 1; // Prevenir división por cero

            // Dibujar fondo de cuadrícula
            g2.setColor(new Color(230, 230, 230));
            int numGridLines = 5;
            for (int i = 0; i <= numGridLines; i++) {
                int y = height - padding - labelPadding - (i * (height - padding * 2 - labelPadding) / numGridLines);
                g2.drawLine(padding, y, width - padding, y);
            }

            // Ejes X y Y
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(2f));
            g2.drawLine(padding, height - padding - labelPadding, width - padding, height - padding - labelPadding); 
            g2.drawLine(padding, height - padding - labelPadding, padding, padding); 

            // Barras
            int barWidth = (width - padding * 2) / ventas.length - 20;
            FontMetrics metrics = g2.getFontMetrics();

            for (int i = 0; i < ventas.length; i++) {
                int x = padding + 10 + i * (barWidth + 20);
                int barHeight = (int) ((ventas[i] / maxScore) * (height - padding * 2 - labelPadding));
                int y = height - padding - labelPadding - barHeight;

                g2.setColor(new Color(39, 174, 96, 200)); 
                g2.fillRect(x, y, barWidth, barHeight);
                g2.setColor(new Color(39, 174, 96));
                g2.drawRect(x, y, barWidth, barHeight);

                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 14));
                int labelWidth = metrics.stringWidth(meses[i]);
                g2.drawString(meses[i], x + (barWidth - labelWidth) / 2, height - padding + 10);
                
                String valorStr = String.format("%,.0f", ventas[i]);
                int valWidth = metrics.stringWidth(valorStr);
                g2.drawString(valorStr, x + (barWidth - valWidth) / 2, y - 10);
            }
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
