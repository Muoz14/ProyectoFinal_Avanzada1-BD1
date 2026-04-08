package vistas;

import vistas.ReporteBajoInventario;
import gui.MenuPrincipal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PanelR extends javax.swing.JPanel {
    
    // Componentes del Menú de Reportes
    private JButton btnBajoI;
    private JButton btnVentasPorF;
    private JButton btnKardexI;
    private JButton btnComprasC;
    private JButton btnRegresar;

    // Colores base de la marca
    private final Color brandDarkBlue = Color.decode("#00384E");
    private final Color brandLight = Color.WHITE;
    private final Color hoverBlue = Color.decode("#005475");

    public PanelR() {
        initComponentsPremium();
    }
    
    // =========================================================
    // 1. EL REINICIO VISUAL BLINDADO
    // =========================================================
   private void activarBoton(JButton botonActivo) {
        JButton[] botones = {btnBajoI, btnVentasPorF, btnKardexI, btnComprasC};
        
        for (JButton b : botones) {
            b.setEnabled(true);
            b.putClientProperty("isHovered", false); 
            b.setIconTextGap(20); // Regresa el texto a su lugar
            b.setBorderPainted(false);
            b.setContentAreaFilled(false);
        }

        botonActivo.setEnabled(false);
        botonActivo.putClientProperty("isHovered", false);
        botonActivo.setIconTextGap(20);
    }

    private void initComponentsPremium() {
        setLayout(new GridBagLayout()); 
        setBackground(brandDarkBlue);
        setPreferredSize(new Dimension(400, 1040));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.weightx = 1.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        // =========================================================
        // 1. HEADER: TÍTULO DE REPORTES
        // =========================================================
        JPanel pnlHeader = new JPanel(new GridBagLayout());
        pnlHeader.setBackground(brandDarkBlue);
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.anchor = GridBagConstraints.CENTER;

        JLabel lblTitulo = new JLabel("REPORTES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42)); 
        lblTitulo.setForeground(brandLight);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbcHeader.insets = new Insets(60, 0, 10, 0); 
        pnlHeader.add(lblTitulo, gbcHeader);

        JLabel lblSub = new JLabel("MÓDULOS DE ANÁLISIS");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(255, 255, 255, 180));
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        gbcHeader.insets = new Insets(0, 0, 40, 0); 
        pnlHeader.add(lblSub, gbcHeader);

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(pnlHeader, gbc);

        // =========================================================
        // 2. LISTA DE BOTONES DE REPORTES (Puro Texto)
        // =========================================================
        JPanel pnlMenu = new JPanel(new GridBagLayout());
        pnlMenu.setBackground(brandDarkBlue);
        GridBagConstraints gbcMenu = new GridBagConstraints();
        gbcMenu.gridx = 0;
        gbcMenu.weightx = 1.0;
        gbcMenu.fill = GridBagConstraints.HORIZONTAL;

        btnBajoI = crearBotonMenu("Bajo Inventario");
        btnBajoI.addActionListener(this::btnBajoIActionPerformed);
        gbcMenu.insets = new Insets(0, 20, 10, 20);
        gbcMenu.gridy = 0;
        pnlMenu.add(btnBajoI, gbcMenu);

        btnVentasPorF = crearBotonMenu("Ventas por Fecha");
        btnVentasPorF.addActionListener(this::btnVentasPorFActionPerformed);
        gbcMenu.gridy = 1;
        pnlMenu.add(btnVentasPorF, gbcMenu);

        btnKardexI = crearBotonMenu("Kardex de Inventario");
        btnKardexI.addActionListener(this::btnKardexIActionPerformed);
        gbcMenu.gridy = 2;
        pnlMenu.add(btnKardexI, gbcMenu);

        btnComprasC = crearBotonMenu("Compras por Cliente");
        btnComprasC.addActionListener(this::btnComprasCActionPerformed);
        gbcMenu.gridy = 3;
        pnlMenu.add(btnComprasC, gbcMenu);

        gbc.gridy = 1;
        add(pnlMenu, gbc);

        // =========================================================
        // 3. ESPACIADOR
        // =========================================================
        gbc.gridy = 2;
        gbc.weighty = 1.0; 
        add(javax.swing.Box.createVerticalGlue(), gbc);

        // =========================================================
        // 4. BOTÓN REGRESAR AL MENÚ PRINCIPAL
        // =========================================================
        btnRegresar = crearBotonMenu("Regresar al Menú");
        btnRegresar.setForeground(new Color(255, 200, 100)); 
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        
        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 20, 100, 20); // Subido a 100px
        add(btnRegresar, gbc);
    }

    // =========================================================
    // 2. MOTOR DE ANIMACIÓN (SIN IMÁGENES EXTERNAS)
    // =========================================================
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        
        // TRUCO: Creamos una imagen invisible por código para que el 
        // efecto IconTextGap funcione perfecto sin pedir archivos PNG.
        BufferedImage imgInvisible = new BufferedImage(1, 24, BufferedImage.TYPE_INT_ARGB);
        boton.setIcon(new ImageIcon(imgInvisible));

        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        boton.setForeground(brandLight);
        boton.setHorizontalAlignment(SwingConstants.LEADING); 
        boton.setHorizontalTextPosition(SwingConstants.RIGHT); 
        
        boton.setIconTextGap(20); 

        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boton.putClientProperty("JTextField.padding", new Insets(10, 20, 10, 20));

        Timer animTimer = new Timer(10, null);
        animTimer.addActionListener(e -> {
            int currentGap = boton.getIconTextGap();
            boolean hovered = boton.getClientProperty("isHovered") != null && (boolean) boton.getClientProperty("isHovered");
            int targetGap = hovered ? 40 : 20;

            if (currentGap < targetGap) {
                boton.setIconTextGap(currentGap + 2); 
            } else if (currentGap > targetGap) {
                boton.setIconTextGap(currentGap - 2); 
            } else {
                animTimer.stop(); 
            }
        });

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!boton.isEnabled()) return; 
                
                boton.putClientProperty("isHovered", true);
                boton.setBorderPainted(true);
                boton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, hoverBlue)); 
                boton.setContentAreaFilled(true);
                boton.setBackground(new Color(255, 255, 255, 15)); 
                animTimer.start(); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.putClientProperty("isHovered", false); 
                
                if (!boton.isEnabled()) return; 
                
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
                animTimer.start(); 
            }
        });

        return boton;
    }

    // =========================================================
    // TU LÓGICA DE EVENTOS ORIGINAL ADAPTADA
    // =========================================================

    private void btnBajoIActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            ReporteBajoInventario rBi = new ReporteBajoInventario();
            menuBase.mostrarContenidoP(rBi);
            activarBoton(btnBajoI);
        }
    }                                        

    private void btnKardexIActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            ReporteKardex rK = new ReporteKardex();
            menuBase.mostrarContenidoP(rK);
            activarBoton(btnKardexI);
        }
    }                                          

    private void btnVentasPorFActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            ReporteVentasFecha rVf = new ReporteVentasFecha();
            menuBase.mostrarContenidoP(rVf);
            activarBoton(btnVentasPorF);
        }
    }                                             

    private void btnComprasCActionPerformed(java.awt.event.ActionEvent evt) {                                            
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            ReporteComprasCliente rCc = new ReporteComprasCliente();
            menuBase.mostrarContenidoP(rCc);
            activarBoton(btnComprasC);
        }
    }                                           

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            // Regresamos al Menú Principal Moderno
            PanelMPrincipal menuInicio = new PanelMPrincipal();
            menuBase.cambiarMenuIzquierdo(menuInicio); 
            
            PanelBienvenida bienvenida = new PanelBienvenida();
            menuBase.mostrarContenidoP(bienvenida);
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
