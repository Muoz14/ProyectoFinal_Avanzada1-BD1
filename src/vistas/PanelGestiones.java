package vistas;

import gui.MenuPrincipal;
import java.awt.Window;
import javax.swing.SwingUtilities;
import vistas.*;

public class PanelGestiones extends javax.swing.JPanel {

    public PanelGestiones() {
        initComponents();
    }
    
    private void activarBoton(javax.swing.JButton botonActivo) {
        
        //Encendemos TODOS los botones primero
        btnGclientes.setEnabled(true);
        btnGproductos.setEnabled(true);
        btnGventas.setEnabled(true);
        btnInventario.setEnabled(true);
        btnGusuarios.setEnabled(true);

        //Apagamos (deshabilitamos) SOLO el botón que acabamos de presionar
        botonActivo.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAzulIzquierdo = new javax.swing.JPanel();
        btnGclientes = new javax.swing.JButton();
        btnGproductos = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnGusuarios = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnGventas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        panelAzulIzquierdo.setBackground(new java.awt.Color(0, 56, 78));
        panelAzulIzquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGclientes.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnGclientes.setForeground(new java.awt.Color(0, 56, 78));
        btnGclientes.setText("Gestión de Clientes");
        btnGclientes.setBorder(null);
        btnGclientes.addActionListener(this::btnGclientesActionPerformed);
        panelAzulIzquierdo.add(btnGclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 216, 62));

        btnGproductos.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnGproductos.setForeground(new java.awt.Color(0, 56, 78));
        btnGproductos.setText("Gestion de Productos");
        btnGproductos.setBorder(null);
        btnGproductos.addActionListener(this::btnGproductosActionPerformed);
        panelAzulIzquierdo.add(btnGproductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 216, 62));

        btnInventario.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnInventario.setForeground(new java.awt.Color(0, 56, 78));
        btnInventario.setText("Inventario");
        btnInventario.setBorder(null);
        btnInventario.addActionListener(this::btnInventarioActionPerformed);
        panelAzulIzquierdo.add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 600, 216, 62));

        btnGusuarios.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnGusuarios.setForeground(new java.awt.Color(0, 56, 78));
        btnGusuarios.setText("Gestion de Usuarios");
        btnGusuarios.setBorder(null);
        btnGusuarios.addActionListener(this::btnGusuariosActionPerformed);
        panelAzulIzquierdo.add(btnGusuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 740, 216, 62));

        btnRegresar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 56, 78));
        btnRegresar.setText("Regresar");
        btnRegresar.setBorder(null);
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelAzulIzquierdo.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 880, 216, 62));

        btnGventas.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnGventas.setForeground(new java.awt.Color(0, 56, 78));
        btnGventas.setText("Gestión de Ventas");
        btnGventas.setBorder(null);
        btnGventas.addActionListener(this::btnGventasActionPerformed);
        panelAzulIzquierdo.add(btnGventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 216, 61));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/gestiones.png"))); // NOI18N
        panelAzulIzquierdo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 400, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelAzulIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelAzulIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGclientesActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            GestionClientes gC = new GestionClientes();
            menuBase.mostrarContenidoP(gC);
            
            activarBoton(btnGclientes);
            
        }
    }//GEN-LAST:event_btnGclientesActionPerformed

    private void btnGproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGproductosActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            GestionProductos gP = new GestionProductos();
            menuBase.mostrarContenidoP(gP);
            
            activarBoton(btnGproductos);
        }
    }//GEN-LAST:event_btnGproductosActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            Inventario gI = new Inventario();
            menuBase.mostrarContenidoP(gI);
            
            activarBoton(btnInventario);
        }
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnGusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGusuariosActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            GestionUsuarios gU = new GestionUsuarios();
            menuBase.mostrarContenidoP(gU);
            
            activarBoton(btnGusuarios);
        }
    }//GEN-LAST:event_btnGusuariosActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:

        Window window = SwingUtilities.getWindowAncestor(this);
        
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            // Instanciamos el menú principal y le decimos al JFrame que lo cambie
            PanelMenuPrincipal menuInicio = new PanelMenuPrincipal();
            menuBase.cambiarMenuIzquierdo(menuInicio); 
            
            PanelBienvenida bienvenida = new PanelBienvenida();
           
            menuBase.mostrarContenidoP(bienvenida);
            
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGventasActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            GestionVentas gV = new GestionVentas();
            menuBase.mostrarContenidoP(gV);
            
            activarBoton(btnGventas);
        }
    }//GEN-LAST:event_btnGventasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGclientes;
    private javax.swing.JButton btnGproductos;
    private javax.swing.JButton btnGusuarios;
    private javax.swing.JButton btnGventas;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelAzulIzquierdo;
    // End of variables declaration//GEN-END:variables
}
