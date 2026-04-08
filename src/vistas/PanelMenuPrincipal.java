package vistas;

import gui.MenuPrincipal;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PanelMenuPrincipal extends javax.swing.JPanel {

    public PanelMenuPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAzulIzquierdo = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();
        btnGusuarios1 = new javax.swing.JButton();
        btnGproductos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        panelAzulIzquierdo.setBackground(new java.awt.Color(0, 56, 78));
        panelAzulIzquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrarSesion.setBackground(new java.awt.Color(0, 56, 78));
        btnCerrarSesion.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);
        panelAzulIzquierdo.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 750, 216, 70));

        btnGusuarios1.setBackground(new java.awt.Color(0, 56, 78));
        btnGusuarios1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnGusuarios1.setForeground(new java.awt.Color(255, 255, 255));
        btnGusuarios1.setText("Ir a Reportes");
        btnGusuarios1.setBorder(null);
        btnGusuarios1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGusuarios1.addActionListener(this::btnGusuarios1ActionPerformed);
        panelAzulIzquierdo.add(btnGusuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, 216, 70));

        btnGproductos.setBackground(new java.awt.Color(0, 56, 78));
        btnGproductos.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnGproductos.setForeground(new java.awt.Color(255, 255, 255));
        btnGproductos.setText("Ir a Gestiones");
        btnGproductos.setBorder(null);
        btnGproductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGproductos.addActionListener(this::btnGproductosActionPerformed);
        panelAzulIzquierdo.add(btnGproductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 216, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/apoloMenu.png"))); // NOI18N
        panelAzulIzquierdo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuPrincipal.png"))); // NOI18N
        panelAzulIzquierdo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 400, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelAzulIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelAzulIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGproductosActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            PanelGestiones pG = new PanelGestiones ();
            menuBase.cambiarMenuIzquierdo(pG);
        }
    }//GEN-LAST:event_btnGproductosActionPerformed

    private void btnGusuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGusuarios1ActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            PanelReportes pR = new PanelReportes ();
            menuBase.cambiarMenuIzquierdo(pR);
        }
    }//GEN-LAST:event_btnGusuarios1ActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        
        javax.swing.JLabel mensaje = new javax.swing.JLabel("¿Desea cerrar la sesión actual?");
        mensaje.setFont(new java.awt.Font("Franklin Gothic Book", java.awt.Font.BOLD, 18));
        // LÍNEA MÁGICA: Le obligamos a medir 300 pixeles de ancho
        mensaje.setPreferredSize(new java.awt.Dimension(300, 30)); 
        
        int opcion = javax.swing.JOptionPane.showConfirmDialog(window, mensaje, "Cerrar Sesión", javax.swing.JOptionPane.YES_NO_OPTION);
        
        if(opcion == javax.swing.JOptionPane.YES_OPTION){
            gui.LoginModerno regresar = new gui.LoginModerno(); 
            regresar.setVisible(true);
            
            if (window != null) {
                window.dispose();
            }
            
            javax.swing.JLabel mensajeExito = new javax.swing.JLabel("Sesión cerrada exitosamente.");
            mensajeExito.setFont(new java.awt.Font("Franklin Gothic Book", java.awt.Font.PLAIN, 18));
            // También aseguramos el ancho del mensaje de éxito
            mensajeExito.setPreferredSize(new java.awt.Dimension(260, 30)); 
            
            javax.swing.JOptionPane.showMessageDialog(null, mensajeExito, "Aviso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnGproductos;
    private javax.swing.JButton btnGusuarios1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelAzulIzquierdo;
    // End of variables declaration//GEN-END:variables
}
