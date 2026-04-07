package gui;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import vistas.*;

public class MenuPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuPrincipal.class.getName());

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        try {
            java.awt.Image icon = java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/LogoBarra.png"));
            this.setIconImage(icon);
        } catch (Exception e) {
            System.out.println("No se encontró el logo: " + e.getMessage());
        }

        this.setExtendedState(MAXIMIZED_BOTH);
        panelAzulIzquierdo.setLayout(new BorderLayout());
        cambiarMenuIzquierdo(new PanelMenuPrincipal());
        
        panelAzulIzquierdo.setLayout(new BorderLayout());
        cambiarMenuIzquierdo(new PanelMenuPrincipal());
        
        // 1. Instanciamos el nuevo panel de bienvenida
        vistas.PanelBienvenida bienvenida = new vistas.PanelBienvenida();

        // 2. Asignamos el tamaño y posición (ajusta estos números al tamaño de tu panel central blanco)
        // Supongamos que tu panel central se llama "pnlCentro" o algo similar
        bienvenida.setSize(1350, 1040); // El tamaño que vi que usaste en otros paneles
        bienvenida.setLocation(0, 0);

        // 3. Limpiamos el panel central y le agregamos el de bienvenida
        contenido.removeAll(); // Cambia pnlCentro por el nombre de tu panel blanco en NetBeans
        contenido.add(bienvenida, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
        
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel panelAzulIzquierdo;
    // End of variables declaration//GEN-END:variables
}
