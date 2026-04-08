package vistasViejas;

import vistas.*;
import dao.*;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ReporteVentasFecha_Antiguo extends javax.swing.JPanel {

    public ReporteVentasFecha_Antiguo() {
        initComponents();
        txtDesde.setEditor(new javax.swing.JSpinner.DateEditor(txtDesde, "dd/MM/yyyy"));
        txtHasta.setEditor(new javax.swing.JSpinner.DateEditor(txtHasta, "dd/MM/yyyy"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtDesde = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        txtHasta = new javax.swing.JSpinner();
        btnGenerar = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 56, 78));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("REPORTE DE VENTAS (CORTE DE CAJA)");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaVentas);

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 56, 78));
        jLabel9.setText("Dedes:");

        txtDesde.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtDesde.setModel(new javax.swing.SpinnerDateModel());
        txtDesde.addChangeListener(this::txtDesdeStateChanged);

        jLabel10.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 56, 78));
        jLabel10.setText("Hasta:");

        txtHasta.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtHasta.setModel(new javax.swing.SpinnerDateModel());
        txtHasta.addChangeListener(this::txtHastaStateChanged);

        btnGenerar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(0, 56, 78));
        btnGenerar.setText("Generar Reporte");
        btnGenerar.addActionListener(this::btnGenerarActionPerformed);

        lblTotal.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 56, 78));
        lblTotal.setText("Total de Ingresos: L. 0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(135, 135, 135))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotal)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1374, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1053, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDesdeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtDesdeStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDesdeStateChanged

    private void txtHastaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtHastaStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHastaStateChanged

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // TODO add your handling code here:
        
        //Capturar las fechas de los JSpinner
        java.util.Date fechaDesde = (java.util.Date) txtDesde.getValue();
        java.util.Date fechaHasta = (java.util.Date) txtHasta.getValue();

        // Validación básica: "Desde" no puede ser mayor que "Hasta"
        if (fechaDesde.after(fechaHasta)) {
            javax.swing.JOptionPane.showMessageDialog(this, "La Fecha de Inicio no puede ser mayor a la Fecha de Fin.", "Error en Fechas", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Preparar el modelo de la tabla
        String[] columnas = {"No. Factura", "Fecha y Hora", "Cliente", "Cajero", "Total (L.)"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        //Llamar al motor de la base de datos
        List<Object[]> listaVentas = new VentaDAO().reporteVentasPorFecha(fechaDesde, fechaHasta);

        double granTotal = 0.0;

        //Llenar la tabla visual y sumar el dinero
        for (Object[] fila : listaVentas) {
            // Re-formatear el dinero para que se vea bonito en la tabla
            double totalFactura = (double) fila[4];
            granTotal += totalFactura; // Vamos sumando al acumulado general
            
            fila[4] = "L. " + String.format("%,.2f", totalFactura); 
            modelo.addRow(fila);
        }

        tablaVentas.setModel(modelo);

        //Mostrar el Gran Total en la etiqueta gigante de abajo
        lblTotal.setText("Total de Ingresos: L. " + String.format("%,.2f", granTotal));
        
        if (listaVentas.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron ventas en este rango de fechas.", "Sin Resultados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JSpinner txtDesde;
    private javax.swing.JSpinner txtHasta;
    // End of variables declaration//GEN-END:variables
}
