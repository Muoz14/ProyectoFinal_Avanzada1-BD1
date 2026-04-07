package vistas;

public class ReporteBajoInventario extends javax.swing.JPanel {

    public ReporteBajoInventario() {
        initComponents();
        cargarTabla();
    }
    
    private void cargarTabla() {
        //Obtenemos el valor actual del JSpinner
        int limite = (int) txtLimite.getValue();

        //Preparamos los títulos de la tabla
        String[] columnas = {"ID", "Producto", "Precio", "Stock Actual"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Bloqueamos la edición manual
            }
        };

        //Llamamos al motor DAO
        java.util.List<entity.Producto> lista = new dao.ProductoDAO().reporteBajoStock(limite);

        //Llenamos la tabla fila por fila
        for (entity.Producto p : lista) {
            modelo.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombreProducto(),
                "L. " + String.format("%,.2f", p.getPrecio()), // Formato de moneda
                p.getStockActual()
            });
        }

        tablaReporte.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReporte = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLimite = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 56, 78));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("REPORTE DE STOCK CRÍTICO");

        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaReporte);

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 56, 78));
        jLabel8.setText("Buscar:");

        txtBuscar.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 56, 78));
        jLabel9.setText("Mostrar productos con stock menor o igual a: ");

        txtLimite.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtLimite.setModel(new javax.swing.SpinnerNumberModel(15, 0, 1000, 1));
        txtLimite.addChangeListener(this::txtLimiteStateChanged);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaReporte.getModel();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> trs = new javax.swing.table.TableRowSorter<>(modelo);
        tablaReporte.setRowSorter(trs);

        String textoBusqueda = txtBuscar.getText().trim();

        if (textoBusqueda.isEmpty()) {
            tablaReporte.setRowSorter(null);
        } else {
            // Filtra ignorando mayusculas y minusculas
            trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtLimiteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtLimiteStateChanged
        // TODO add your handling code here:
        //Cada vez que el usuario suba o baje el numero, la tabla se recarga sola
        cargarTabla();
    }//GEN-LAST:event_txtLimiteStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReporte;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JSpinner txtLimite;
    // End of variables declaration//GEN-END:variables
}
