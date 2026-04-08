package gui;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import vistas.*;

public class MenuPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuPrincipal.class.getName());

    public MenuPrincipal() {
        initComponents();
        
        // 1. Maximizamos la ventana
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        // 2. Cargamos el logo de la barra superior
        try {
            java.awt.Image icon = java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/LogoBarra.png"));
            this.setIconImage(icon);
        } catch (Exception e) {
            System.out.println("No se encontró el logo: " + e.getMessage());
        }

        // 3. Inyectamos el NUEVO panel lateral (PanelMPrincipal)
        panelAzulIzquierdo.setLayout(new BorderLayout());
        cambiarMenuIzquierdo(new PanelMPrincipal());
        
        // 4. Inyectamos el panel central de Bienvenida usando tu propio método
        vistas.PanelBienvenida bienvenida = new vistas.PanelBienvenida();
        mostrarContenidoP(bienvenida);
    }
    
    public void cambiarMenuIzquierdo(JPanel p){
        p.setSize(390, 1040); // El tamaño exacto de tu barra azul
        p.setLocation(0, 0);
        
        panelAzulIzquierdo.removeAll();
        panelAzulIzquierdo.add(p, BorderLayout.CENTER);
        panelAzulIzquierdo.revalidate();
        panelAzulIzquierdo.repaint();
    }

    public void mostrarContenidoP(JPanel panelNuevo) {
        // 1. Limpiar el panel central blanco por completo
        contenido.removeAll(); 
        
        // 2. Ajustar el tamaño y posición del nuevo panel
        panelNuevo.setSize(contenido.getWidth(), contenido.getHeight());
        panelNuevo.setLocation(0, 0);
        
        // 3. Agregarlo al centro
        contenido.add(panelNuevo, java.awt.BorderLayout.CENTER); 
        
        // 4. Refrescar la pantalla para que los cambios se vean
        contenido.revalidate();
        contenido.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAzulIzquierdo = new javax.swing.JPanel();
        contenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAzulIzquierdo.setBackground(new java.awt.Color(0, 56, 78));

        javax.swing.GroupLayout panelAzulIzquierdoLayout = new javax.swing.GroupLayout(panelAzulIzquierdo);
        panelAzulIzquierdo.setLayout(panelAzulIzquierdoLayout);
        panelAzulIzquierdoLayout.setHorizontalGroup(
            panelAzulIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        panelAzulIzquierdoLayout.setVerticalGroup(
            panelAzulIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );

        getContentPane().add(panelAzulIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 1040));

        contenido.setBackground(new java.awt.Color(255, 255, 255));
        contenido.setLayout(new java.awt.BorderLayout());
        getContentPane().add(contenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 1350, 1040));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
            try {
                javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            } catch (Exception ex) {
                System.err.println("Falló al inicializar FlatLaf");
            }

            java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel panelAzulIzquierdo;
    // End of variables declaration//GEN-END:variables
}
