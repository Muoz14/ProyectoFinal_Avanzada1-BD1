package vistasViejas;

import gui.MenuPrincipal;
import java.awt.Window;
import javax.swing.SwingUtilities;
import vistas.PanelBienvenida;
import vistas.ReporteComprasCliente;
import vistas.ReporteKardex;
import vistas.ReporteVentasFecha;


public class PanelReportes extends javax.swing.JPanel {

    public PanelReportes() {
        initComponents();
    }
    
        private void activarBoton(javax.swing.JButton botonActivo) {
        
        //Encendemos TODOS los botones primero
        btnBajoI.setEnabled(true);
        btnVentasPorF.setEnabled(true);
        btnKardexI.setEnabled(true);
        btnComprasC.setEnabled(true);

        //Apagamos (deshabilitamos) SOLO el botón que acabamos de presionar
        botonActivo.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAzulIzquierdo = new javax.swing.JPanel();
        btnBajoI = new javax.swing.JButton();
        btnKardexI = new javax.swing.JButton();
        btnVentasPorF = new javax.swing.JButton();
        btnComprasC = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        panelAzulIzquierdo.setBackground(new java.awt.Color(0, 56, 78));
        panelAzulIzquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBajoI.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnBajoI.setForeground(new java.awt.Color(0, 56, 78));
        btnBajoI.setText("Bajo Inventario");
        btnBajoI.setBorder(null);
        btnBajoI.addActionListener(this::btnBajoIActionPerformed);
        panelAzulIzquierdo.add(btnBajoI, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 216, 62));

        btnKardexI.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnKardexI.setForeground(new java.awt.Color(0, 56, 78));
        btnKardexI.setText("Kardex de Inventario");
        btnKardexI.setBorder(null);
        btnKardexI.addActionListener(this::btnKardexIActionPerformed);
        panelAzulIzquierdo.add(btnKardexI, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 216, 62));

        btnVentasPorF.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnVentasPorF.setForeground(new java.awt.Color(0, 56, 78));
        btnVentasPorF.setText("Ventas por Fecha");
        btnVentasPorF.setBorder(null);
        btnVentasPorF.addActionListener(this::btnVentasPorFActionPerformed);
        panelAzulIzquierdo.add(btnVentasPorF, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 216, 62));

        btnComprasC.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnComprasC.setForeground(new java.awt.Color(0, 56, 78));
        btnComprasC.setText("Compras por Cliente");
        btnComprasC.setBorder(null);
        btnComprasC.addActionListener(this::btnComprasCActionPerformed);
        panelAzulIzquierdo.add(btnComprasC, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 660, 216, 62));

        btnRegresar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 56, 78));
        btnRegresar.setText("Regresar");
        btnRegresar.setBorder(null);
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelAzulIzquierdo.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 820, 216, 62));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/reportes.png"))); // NOI18N
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

    private void btnBajoIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajoIActionPerformed
        // TODO add your handling code here:
//        Window window = SwingUtilities.getWindowAncestor(this);
//        if (window instanceof MenuPrincipal) {
//            MenuPrincipal menuBase = (MenuPrincipal) window;
//            
//            ReporteBajoStock rBi = new ReporteBajoStock();
//            menuBase.mostrarContenidoP(rBi);
//            
//            activarBoton(btnBajoI);
//        }
    }//GEN-LAST:event_btnBajoIActionPerformed

    private void btnKardexIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKardexIActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            ReporteKardex rK = new ReporteKardex();
            menuBase.mostrarContenidoP(rK);
            
            activarBoton(btnKardexI);
        }
    }//GEN-LAST:event_btnKardexIActionPerformed

    private void btnVentasPorFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasPorFActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            ReporteVentasFecha rVf = new ReporteVentasFecha();
            menuBase.mostrarContenidoP(rVf);
            
            activarBoton(btnVentasPorF);
        }
    }//GEN-LAST:event_btnVentasPorFActionPerformed

    private void btnComprasCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasCActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            ReporteComprasCliente rCc = new ReporteComprasCliente();
            menuBase.mostrarContenidoP(rCc);
            
            activarBoton(btnComprasC);
        }
    }//GEN-LAST:event_btnComprasCActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBajoI;
    private javax.swing.JButton btnComprasC;
    private javax.swing.JButton btnKardexI;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVentasPorF;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelAzulIzquierdo;
    // End of variables declaration//GEN-END:variables
}
