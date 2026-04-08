package vistasViejas;

import vistas.*;

public class ReporteKardex_Viejo extends javax.swing.JPanel {
    
    // Esta lista guardara todos los movimientos (entradas y salidas) originales
    private java.util.List<Object[]> todoElHistorial = new java.util.ArrayList<>();
    
    // --- VARIABLES FANTASMAS PARA PRODUCTO ---
    private javax.swing.JPopupMenu popupProducto;
    private javax.swing.JTable tablaPopupProd;
    private javax.swing.table.DefaultTableModel modeloPopupProd;
    private java.util.List<entity.Producto> listaProdCache;
    private entity.Producto productoSeleccionado = null;

    public ReporteKardex_Viejo() {
        initComponents();
        prepararBuscadorProducto();
    }
    
    // MOTOR DE BUSQUEDA FANTASMA (PRODUCTOS)
    private void prepararBuscadorProducto() {
        popupProducto = new javax.swing.JPopupMenu();
        popupProducto.setFocusable(false);
        
        modeloPopupProd = new javax.swing.table.DefaultTableModel(null, new String[]{"ID", "Producto", "Stock"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPopupProd = new javax.swing.JTable(modeloPopupProd);
        
        // EVENTO CLIC EN LA TABLA FANTASMA
        tablaPopupProd.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int fila = tablaPopupProd.getSelectedRow();
                if (fila != -1) seleccionarProducto((int) tablaPopupProd.getValueAt(fila, 0));
            }
        });

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tablaPopupProd);
        scroll.setPreferredSize(new java.awt.Dimension(350, 150));
        popupProducto.add(scroll);
        
        // Cargamos la cache
        actualizarCacheProductos();
    }

    private void actualizarCacheProductos() {
        listaProdCache = new dao.ProductoDAO().searchProductos();
    }

    private void filtrarProducto(String busqueda) {
        modeloPopupProd.setRowCount(0);
        for (entity.Producto p : listaProdCache) {
            if (p.getNombreProducto().toLowerCase().contains(busqueda.toLowerCase())) {
                modeloPopupProd.addRow(new Object[]{p.getIdProducto(), p.getNombreProducto(), p.getStockActual()});
            }
        }
    }

    private void seleccionarProducto(int id) {
        for (entity.Producto p : listaProdCache) {
            if (p.getIdProducto() == id) {
                productoSeleccionado = p;
                txtBuscarProductos.setText(p.getNombreProducto());
                popupProducto.setVisible(false);
                break;
            }
        }
    }
    
    // LOGICA DEL REPORTE KARDEX
    private void filtrarTablaKardex() {
        String[] columnas = {"Fecha y Hora", "Ref/Factura", "Tipo Movimiento", "Cant.", "Responsable"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        // Leemos el estado de los Checkbox
        boolean verEntradas = chkEntradas.isSelected();
        boolean verSalidas = chkSalidas.isSelected();

        for (Object[] fila : todoElHistorial) {
            String tipo = fila[2].toString().toUpperCase(); // Columna de "Tipo Movimiento"

            // Verificamos si la fila es Entrada o Salida (Venta)
            boolean esEntrada = tipo.contains("ENTRADA");
            boolean esSalida = tipo.contains("VENTA") || tipo.contains("SALIDA");

            // Logica de visualizacion
            if ((esEntrada && verEntradas) || (esSalida && verSalidas)) {
                modelo.addRow(fila);
            }
        }
        tablaKardex.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaKardex = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnGenerarKardex = new javax.swing.JButton();
        chkSalidas = new javax.swing.JCheckBox();
        chkEntradas = new javax.swing.JCheckBox();
        txtBuscarProductos = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 56, 78));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("KARDEX / HISTORIAL DE PRODUCTO");

        tablaKardex.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaKardex);

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 56, 78));
        jLabel9.setText("Productos: ");

        btnGenerarKardex.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnGenerarKardex.setForeground(new java.awt.Color(0, 56, 78));
        btnGenerarKardex.setText("Ver Historial");
        btnGenerarKardex.addActionListener(this::btnGenerarKardexActionPerformed);

        chkSalidas.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        chkSalidas.setForeground(new java.awt.Color(0, 56, 78));
        chkSalidas.setText("Mostrar Salidas");
        chkSalidas.addActionListener(this::chkSalidasActionPerformed);

        chkEntradas.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        chkEntradas.setForeground(new java.awt.Color(0, 56, 78));
        chkEntradas.setText("Mostrar Entradas");
        chkEntradas.addActionListener(this::chkEntradasActionPerformed);

        txtBuscarProductos.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscarProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarProductosMouseClicked(evt);
            }
        });
        txtBuscarProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductosKeyReleased(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(chkEntradas)
                .addGap(18, 18, 18)
                .addComponent(chkSalidas)
                .addGap(209, 209, 209)
                .addComponent(btnGenerarKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnGenerarKardex)
                    .addComponent(chkSalidas)
                    .addComponent(chkEntradas)
                    .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarKardexActionPerformed
        // TODO add your handling code here:
        if (productoSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un producto primero.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Consultar la base de datos y guardar en nuestra variable global usando la entidad
        todoElHistorial = new dao.ProductoDAO().historialKardexProducto(productoSeleccionado.getIdProducto());

        // Llamar al filtro para que pinte la tabla por primera vez
        filtrarTablaKardex();

        // Si no hay datos, mostramos un aviso
        if (todoElHistorial.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Este producto no tiene movimientos registrados.", "Sin Datos", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarKardexActionPerformed

    private void chkSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSalidasActionPerformed
        // TODO add your handling code here:
        filtrarTablaKardex();
    }//GEN-LAST:event_chkSalidasActionPerformed

    private void chkEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEntradasActionPerformed
        // TODO add your handling code here:
        filtrarTablaKardex();
    }//GEN-LAST:event_chkEntradasActionPerformed

    private void txtBuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosKeyReleased
        // TODO add your handling code here:
        String texto = txtBuscarProductos.getText().trim();
        if(texto.isEmpty()){
            productoSeleccionado = null;
        }
        filtrarProducto(texto);
        if (modeloPopupProd.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
            txtBuscarProductos.requestFocus();
        } else {
            popupProducto.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarProductosKeyReleased

    private void txtBuscarProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarProductosMouseClicked
        // TODO add your handling code here:
        String texto = txtBuscarProductos.getText().trim();
        filtrarProducto(texto);
        if (modeloPopupProd.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
        }
    }//GEN-LAST:event_txtBuscarProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarKardex;
    private javax.swing.JCheckBox chkEntradas;
    private javax.swing.JCheckBox chkSalidas;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaKardex;
    private javax.swing.JTextField txtBuscarProductos;
    // End of variables declaration//GEN-END:variables
}
