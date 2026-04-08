package vistas;

import dao.ProductoDAO;

public class GestionProductos extends javax.swing.JPanel {
    
    private int idProductoSeleccionado = -1;

    public GestionProductos() {
        initComponents();
        cargarTabla();
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        txtNombre.putClientProperty("JTextField.placeholderText", "Nombre del nuevo producto");
        txtPrecio.putClientProperty("JTextField.placeholderText", "Ej: 2500");
        txtStockA.putClientProperty("JTextField.placeholderText", "Ej: 5");
        txtDescrip.putClientProperty("JTextField.placeholderText", "Ej: Celular Gama Premium");
    }
    
    void limpiarCampos(){
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStockA.setText("");
        txtDescrip.setText("");
    }
    
    private void cargarTabla() {
        //Definimos los nombres de las columnas
        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Descripcion"};
        
        // Creamos el modelo de la tabla (y bloqueamos la edición manual de celdas)
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que el usuario edite dando doble clic en la tabla
            }
        };

        ProductoDAO dao = new ProductoDAO();
        java.util.List<entity.Producto> lista = dao.searchProductos();

        //Recorremos la lista y agregamos cada cliente como una fila
        for (entity.Producto c : lista) {
            Object[] fila = new Object[5];
            fila[0] = c.getIdProducto();
            fila[1] = c.getNombreProducto();
            fila[2] = c.getPrecio();
            fila[3] = c.getStockActual();
            fila[4] = c.getDescripcion();
            
            modelo.addRow(fila); // Agregamos la fila al modelo
        }

        tablaProductos.setModel(modelo); 
    }
    
    private void cargarEliminados() {
        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Descripcion"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        ProductoDAO dao = new ProductoDAO();
        java.util.List<entity.Producto> lista = dao.searchProductosEliminados(); 

        for (entity.Producto c : lista) {
            Object[] fila = new Object[5];
            fila[0] = c.getIdProducto();
            fila[1] = c.getNombreProducto();
            fila[2] = c.getPrecio();
            fila[3] = c.getStockActual();
            fila[4] = c.getDescripcion();
            modelo.addRow(fila);
        }
        tablaProductos.setModel(modelo);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtStockA = new javax.swing.JTextField();
        txtDescrip = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        chkEliminados = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1350, 1040));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 56, 78));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 290, -1));

        txtStockA.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtStockA.addActionListener(this::txtStockAActionPerformed);
        jPanel2.add(txtStockA, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 290, -1));

        txtDescrip.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jPanel2.add(txtDescrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 290, 90));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Stock Actual:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripcion:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 56, 78));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 130, -1));

        btnModificar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 56, 78));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(this::btnModificarActionPerformed);
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 130, -1));

        btnGuardar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 56, 78));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 130, -1));

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Precio:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        txtPrecio.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 290, -1));

        btnLimpiar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 56, 78));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 130, -1));

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Agrega Nuevos Productos");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 660, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 660, 460));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 56, 78));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GESTION DE PRODUCTOS");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, -1));

        jPanel3.setBackground(new java.awt.Color(0, 56, 78));
        jPanel3.setForeground(new java.awt.Color(0, 56, 78));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N

        tablaProductos.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 530, 590));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Buscar:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        txtBuscar.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel3.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 290, -1));

        chkEliminados.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        chkEliminados.setForeground(new java.awt.Color(255, 255, 255));
        chkEliminados.setText("Mostrar Eliminados");
        chkEliminados.addActionListener(this::chkEliminadosActionPerformed);
        jPanel3.add(chkEliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 170, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 630, 1040));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockAActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        //Validamos que haya seleccionado a alguien en la tabla
        if (idProductoSeleccionado == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la tabla.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProductoDAO dao = new ProductoDAO();

        //Verificamos en qué modo estamos usando el CheckBox
        if (chkEliminados.isSelected()) {
            // ================= MODO RESTAURAR =================
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas RESTAURAR a este producto?",
                "Confirmar restauración",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {

                dao.restaurarProducto(idProductoSeleccionado);

                javax.swing.JOptionPane.showMessageDialog(this, "Producto restaurado correctamente!");

                limpiarCampos();
                idProductoSeleccionado = -1;
                cargarEliminados(); // Recargamos la tabla de eliminados para que desaparezca de ahí
            }

        } else {
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que desea ELIMINAR a este producto?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                dao.deleteProducto(idProductoSeleccionado);

                javax.swing.JOptionPane.showMessageDialog(this, "Producto eliminado correctamente!");

                limpiarCampos();
                idProductoSeleccionado = -1;
                cargarTabla(); // Recargamos la tabla normal
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
       //Verificamos que haya seleccionado un producto
        if (idProductoSeleccionado == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la tabla.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescrip.getText().trim();
        
        if (nombre.isEmpty() || txtPrecio.getText().trim().isEmpty() || txtStockA.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nombre, precio y stock son obligatorios.", "Campos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        double precio = 0.0;
        int stock = 0;

        //Protegemos el programa de errores de escritura
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim());
            stock = Integer.parseInt(txtStockA.getText().trim());
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El precio y el stock deben ser números válidos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        if (precio <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0.", "Error de validación", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (stock < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "El stock no puede ser un número negativo.", "Error de validación", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Llenamos la entidad INCLUYENDO EL ID
        entity.Producto prodModificado = new entity.Producto();
        prodModificado.setIdProducto(idProductoSeleccionado); // ¡Clave para actualizar!
        prodModificado.setNombreProducto(nombre);
        prodModificado.setDescripcion(descripcion);
        prodModificado.setPrecio(precio);
        prodModificado.setStockActual(stock);

        //Mandamos al DAO
        ProductoDAO dao = new ProductoDAO();
        dao.updateProducto(prodModificado);
        
        javax.swing.JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");

        limpiarCampos();
        idProductoSeleccionado = -1; 
        cargarTabla();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescrip.getText().trim();
        
        if (nombre.isEmpty() || txtPrecio.getText().trim().isEmpty() || txtStockA.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nombre, precio y stock son obligatorios.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Variables numericas
        double precio = 0.0;
        int stock = 0;

        try {
            precio = Double.parseDouble(txtPrecio.getText().trim());
            stock = Integer.parseInt(txtStockA.getText().trim());
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos en Precio y Stock.", "Error de formato", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (precio <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0.", "Error de validación", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (stock < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "El stock no puede ser un número negativo.", "Error de validación", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llenamos la entidad
        entity.Producto nuevoProducto = new entity.Producto();
        nuevoProducto.setNombreProducto(nombre);
        nuevoProducto.setDescripcion(descripcion);
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setStockActual(stock);

        // Guardamos
        dao.ProductoDAO dao = new dao.ProductoDAO();
        dao.insertProducto(nuevoProducto);

        limpiarCampos();
        cargarTabla();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        //Obtenemos el modelo actual de tu tabla
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaProductos.getModel();

        //Creamos el "Filtro"
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> trs = new javax.swing.table.TableRowSorter<>(modelo);
        tablaProductos.setRowSorter(trs);

        //Capturamos lo que el usuario va escribiendo
        String textoBusqueda = txtBuscar.getText().trim();

        //Aplicamos el filtro.
        // El "(?i)" es un truco de programación (Expresión Regular) para que ignore mayúsculas y minúsculas.
        // Si el usuario borra todo, la tabla vuelve a mostrar todos los registros.
        if (textoBusqueda.isEmpty()) {
            tablaProductos.setRowSorter(null);
        } else {
            trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        // TODO add your handling code here:

        int fila = tablaProductos.getSelectedRow();

        if(fila != -1){

            idProductoSeleccionado = Integer.parseInt(tablaProductos.getValueAt(fila, 0).toString());

            txtNombre.setText(tablaProductos.getValueAt(fila, 1).toString());
            txtPrecio.setText(tablaProductos.getValueAt(fila, 2).toString());
            txtStockA.setText(tablaProductos.getValueAt(fila, 3).toString());
            txtDescrip.setText(tablaProductos.getValueAt(fila, 4).toString());
            
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(true);
        }else{
            btnEliminar.setEnabled(false);
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void chkEliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEliminadosActionPerformed
        // TODO add your handling code here:

        // Si la casilla esta marcada, mostramos los eliminados
        if (chkEliminados.isSelected()) {
            cargarEliminados();

            //Desactivamos los botones de Guardar y Modificar para no alterar registros borrados
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(true);
            btnEliminar.setText("RESTAURAR"); // Cambiamos el texto del botón Eliminar a Restaurar

        } else {
            // Si la desmarcan, volvemos a mostrar los clientes normales
            cargarTabla();

            // Volvemos a activar todo a la normalidad
            btnGuardar.setEnabled(true);
            btnEliminar.setEnabled(false);
            btnEliminar.setText("ELIMINAR");
        }
    }//GEN-LAST:event_chkEliminadosActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox chkEliminados;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescrip;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStockA;
    // End of variables declaration//GEN-END:variables
}
