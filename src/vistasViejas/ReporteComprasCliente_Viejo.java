package vistasViejas;

import vistas.*;

public class ReporteComprasCliente_Viejo extends javax.swing.JPanel {
    
    // --- VARIABLES FANTASMAS PARA CLIENTE ---
    private javax.swing.JPopupMenu popupCliente;
    private javax.swing.JTable tablaPopupCli;
    private javax.swing.table.DefaultTableModel modeloPopupCli;
    private java.util.List<entity.Cliente> listaCliCache;
    private entity.Cliente clienteSeleccionado = null;

    public ReporteComprasCliente_Viejo() {
        initComponents();
        prepararBuscadorCliente(); // Inicia el motor fantasma
        actualizarTablaCompras(); // Carga la tabla vacía al inicio
    }

    // MOTOR DE BUSQUEDA FANTASMA (CLIENTES)
    private void prepararBuscadorCliente() {
        popupCliente = new javax.swing.JPopupMenu();
        popupCliente.setFocusable(false);
        
        modeloPopupCli = new javax.swing.table.DefaultTableModel(null, new String[]{"ID", "Nombre Cliente"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPopupCli = new javax.swing.JTable(modeloPopupCli);
        
        // EVENTO CLIC EN LA TABLA FANTASMA
        tablaPopupCli.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int fila = tablaPopupCli.getSelectedRow();
                if (fila != -1) seleccionarCliente((int) tablaPopupCli.getValueAt(fila, 0));
            }
        });

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tablaPopupCli);
        scroll.setPreferredSize(new java.awt.Dimension(300, 150));
        popupCliente.add(scroll);
        
        actualizarCacheClientes();
    }

    private void actualizarCacheClientes() {
        listaCliCache = new dao.ClienteDAO().searchClientes();
    }

    private void filtrarCliente(String busqueda) {
        modeloPopupCli.setRowCount(0);
        for (entity.Cliente c : listaCliCache) {
            String nombreCompleto = c.getNombreCliente() + " " + c.getApellidoCliente();
            if (nombreCompleto.toLowerCase().contains(busqueda.toLowerCase())) {
                modeloPopupCli.addRow(new Object[]{c.getIdCliente(), nombreCompleto});
            }
        }
    }

    private void seleccionarCliente(int id) {
        for (entity.Cliente c : listaCliCache) {
            if (c.getIdCliente() == id) {
                clienteSeleccionado = c;
                txtBuscarCliente.setText(c.getNombreCliente() + " " + c.getApellidoCliente());
                popupCliente.setVisible(false);
                
                // ACTUALIZAMOS EL REPORTE AUTOMÁTICAMENTE AL SELECCIONAR
                actualizarTablaCompras();
                break;
            }
        }
    }
    
    // LOGICA DEL REPORTE
    private void actualizarTablaCompras() {
        String[] cols = {"No. Factura", "Fecha", "Cajero", "Total (L.)"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        // Si no hay cliente seleccionado (por ejemplo, al inicio o si borran el texto), mostramos tabla vacía
        if (clienteSeleccionado == null) {
            tablaCompras.setModel(modelo);
            lblTotalInversion.setText("Inversión Total del Cliente: L. 0.00");
            return;
        }

        java.util.List<Object[]> datos = new dao.ClienteDAO().obtenerHistorialCompras(clienteSeleccionado.getIdCliente());
        double acumulado = 0;

        for (Object[] fila : datos) {
            double totalRow = (double) fila[3];
            acumulado += totalRow;
            fila[3] = "L. " + String.format("%,.2f", totalRow);
            modelo.addRow(fila);
        }

        tablaCompras.setModel(modelo);
        lblTotalInversion.setText("Inversión Total del Cliente: L. " + String.format("%,.2f", acumulado));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompras = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lblTotalInversion = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 56, 78));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("HISTORIAL DE COMPRAS POR CLIENTE");

        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaCompras);

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 56, 78));
        jLabel9.setText("Clientes:");

        lblTotalInversion.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        lblTotalInversion.setForeground(new java.awt.Color(0, 56, 78));
        lblTotalInversion.setText("Inversion Total: L. 0.00");

        txtBuscarCliente.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarClienteMouseClicked(evt);
            }
        });
        txtBuscarCliente.addActionListener(this::txtBuscarClienteActionPerformed);
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalInversion)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(135, 135, 135))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotalInversion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarClienteMouseClicked
        // TODO add your handling code here:
        if(!txtBuscarCliente.isEnabled()) return;
        String texto = txtBuscarCliente.getText().trim();
        filtrarCliente(texto);
        if (modeloPopupCli.getRowCount() > 0) {
            popupCliente.show(txtBuscarCliente, 0, txtBuscarCliente.getHeight());
        }
    }//GEN-LAST:event_txtBuscarClienteMouseClicked

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        // TODO add your handling code here:
        if(!txtBuscarCliente.isEnabled()) return;
        String texto = txtBuscarCliente.getText().trim();
        
        if(texto.isEmpty()){ 
            clienteSeleccionado = null; 
            actualizarTablaCompras(); // Limpia la tabla si borran el texto
        }
        
        filtrarCliente(texto);
        if (modeloPopupCli.getRowCount() > 0) {
            popupCliente.show(txtBuscarCliente, 0, txtBuscarCliente.getHeight());
            txtBuscarCliente.requestFocus();
        } else {
            popupCliente.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarClienteKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalInversion;
    private javax.swing.JTable tablaCompras;
    private javax.swing.JTextField txtBuscarCliente;
    // End of variables declaration//GEN-END:variables
}
