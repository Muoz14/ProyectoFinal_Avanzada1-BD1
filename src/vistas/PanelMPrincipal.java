package vistas;

import gui.MenuPrincipal;
import gui.LoginModerno; // Usamos el Login final que creamos
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PanelMPrincipal extends javax.swing.JPanel {
    
    // Componentes del Menú
    private JButton btnGestiones;
    private JButton btnReportes;
    private JButton btnCerrarSesion;

    // Colores base de la marca
    private final Color brandDarkBlue = Color.decode("#00384E");
    private final Color brandLight = Color.WHITE;
    private final Color hoverBlue = Color.decode("#005475");

    public PanelMPrincipal() {
        initComponents();
        initComponentsPremium();
    }
    private void initComponentsPremium() {
        setLayout(new GridBagLayout()); 
        setBackground(brandDarkBlue);
        setPreferredSize(new Dimension(400, 1040));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.weightx = 1.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.anchor = GridBagConstraints.NORTH; 

        // =========================================================
        // 1. SECCIÓN SUPERIOR: ÁREA DE MARCA
        // =========================================================
        JPanel pnlHeader = new JPanel(new GridBagLayout());
        pnlHeader.setBackground(brandDarkBlue);
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.anchor = GridBagConstraints.CENTER;
        
        JLabel lblLogo = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/recursos/apoloMenu.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            lblLogo.setText("APOLO");
            lblLogo.setFont(new Font("Arial Black", Font.BOLD, 26));
            lblLogo.setForeground(brandLight);
        }
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        gbcHeader.insets = new Insets(50, 0, 15, 0); 
        pnlHeader.add(lblLogo, gbcHeader);
        
        JLabel lblApolo = new JLabel("APOLO");
        lblApolo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblApolo.setForeground(brandLight);
        lblApolo.setHorizontalAlignment(SwingConstants.CENTER);
        gbcHeader.insets = new Insets(0, 0, 10, 0);
        pnlHeader.add(lblApolo, gbcHeader);

        JLabel lblSistema = new JLabel("SISTEMA DE GESTIÓN");
        lblSistema.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSistema.setForeground(new Color(255, 255, 255, 180)); 
        lblSistema.setHorizontalAlignment(SwingConstants.CENTER);
        gbcHeader.insets = new Insets(0, 0, 40, 0); 
        pnlHeader.add(lblSistema, gbcHeader);

        gbc.gridy = 0;
        gbc.weighty = 0.0; 
        gbc.insets = new Insets(0, 0, 0, 0);
        add(pnlHeader, gbc);

        // =========================================================
        // 2. SECCIÓN CENTRAL: NAVEGACIÓN
        // =========================================================
        JPanel pnlMenu = new JPanel(new GridBagLayout());
        pnlMenu.setBackground(brandDarkBlue);
        GridBagConstraints gbcMenu = new GridBagConstraints();
        gbcMenu.gridx = 0;
        gbcMenu.weightx = 1.0;
        gbcMenu.fill = GridBagConstraints.HORIZONTAL;
        
        Font sectionFont = new Font("Segoe UI", Font.BOLD, 14);
        
        // --- NAVEGACIÓN PRINCIPAL ---
        JLabel lblSectionMain = new JLabel("MENÚ PRINCIPAL");
        lblSectionMain.setFont(sectionFont);
        lblSectionMain.setForeground(new Color(255, 255, 255, 150));
        lblSectionMain.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 255, 255, 100))); 
        gbcMenu.insets = new Insets(0, 20, 15, 20); 
        gbcMenu.gridy = 0;
        pnlMenu.add(lblSectionMain, gbcMenu);
        
        // Botón Gestiones (Tu lógica conectada aquí)
        btnGestiones = crearBotonMenu("Ir a Gestiones", "/recursos/icon_gestiones.png");
        btnGestiones.addActionListener(this::btnGproductosActionPerformed);
        gbcMenu.insets = new Insets(0, 20, 10, 20);
        gbcMenu.gridy = 1;
        pnlMenu.add(btnGestiones, gbcMenu);

        // Botón Reportes (Tu lógica conectada aquí)
        btnReportes = crearBotonMenu("Ir a Reportes", "/recursos/icon_reportes.png");
        btnReportes.addActionListener(this::btnGusuarios1ActionPerformed);
        gbcMenu.gridy = 2;
        pnlMenu.add(btnReportes, gbcMenu);

        // Espaciador grande entre secciones
        gbcMenu.gridy = 3;
        gbcMenu.weighty = 0.0;
        pnlMenu.add(javax.swing.Box.createVerticalStrut(30), gbcMenu);

        // --- CUENTA ---
        JLabel lblSectionAccount = new JLabel("CUENTA");
        lblSectionAccount.setFont(sectionFont);
        lblSectionAccount.setForeground(new Color(255, 255, 255, 150));
        lblSectionAccount.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 255, 255, 100)));
        gbcMenu.insets = new Insets(0, 20, 15, 20);
        gbcMenu.gridy = 4;
        pnlMenu.add(lblSectionAccount, gbcMenu);

        // Botón Cerrar Sesión (Tu lógica conectada aquí)
        btnCerrarSesion = crearBotonMenu("Cerrar Sesión", "/recursos/icon_logout.png");
        btnCerrarSesion.setForeground(new Color(255, 90, 90)); 
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);
        gbcMenu.insets = new Insets(0, 20, 10, 20);
        gbcMenu.gridy = 5;
        pnlMenu.add(btnCerrarSesion, gbcMenu);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        add(pnlMenu, gbc);

        // Espaciador para copyright al fondo
        gbc.gridy = 2;
        gbc.weighty = 1.0; 
        add(javax.swing.Box.createVerticalGlue(), gbc);

        // =========================================================
        // 3. COPYRIGHT
        // =========================================================
        JLabel lblCR = new JLabel("Copyright © Centro Apolo All rights reserved.");
        lblCR.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCR.setForeground(new Color(255, 255, 255, 100)); 
        lblCR.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 0, 30, 0); 
        add(lblCR, gbc);
    }

    // --- FUNCIÓN AUXILIAR PARA BOTONES PREMIUM ---
    private JButton crearBotonMenu(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(rutaIcono));
            Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            // Falla silenciosa si no encuentra el icono, solo muestra texto
        }

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
        boton.putClientProperty("JTextField.padding", new Insets(10, 15, 10, 15));

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBorderPainted(true);
                boton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, hoverBlue)); 
                boton.setContentAreaFilled(true);
                boton.setBackground(new Color(255, 255, 255, 15)); 
            }
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
            }
        });

        return boton;
    }

    // =========================================================
    // TU LÓGICA DE NAVEGACIÓN Y ACCIONES EXACTAMENTE IGUAL
    // =========================================================

    private void btnGproductosActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            PanelG pG = new PanelG();
            menuBase.cambiarMenuIzquierdo(pG);
        }
    }                                             

    private void btnGusuarios1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            PanelReportes pR = new PanelReportes();
            menuBase.cambiarMenuIzquierdo(pR);
        }
    }                                             

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {                                                
        Window window = SwingUtilities.getWindowAncestor(this);
        
        JLabel mensaje = new JLabel("¿Desea cerrar la sesión actual?");
        mensaje.setFont(new Font("Segoe UI", Font.BOLD, 18));
        mensaje.setPreferredSize(new Dimension(300, 30)); 
        
        int opcion = JOptionPane.showConfirmDialog(window, mensaje, "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        
        if(opcion == JOptionPane.YES_OPTION){
            // Te devuelve al nuevo LoginAnimado premium que creamos
            LoginModerno regresar = new LoginModerno(); 
            regresar.setVisible(true);
            
            if (window != null) {
                window.dispose();
            }
            
            JLabel mensajeExito = new JLabel("Sesión cerrada exitosamente.");
            mensajeExito.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            mensajeExito.setPreferredSize(new Dimension(260, 30)); 
            
            JOptionPane.showMessageDialog(null, mensajeExito, "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
