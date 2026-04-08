package vistas;

import dao.ClienteDAO;

public class GestionClientes extends javax.swing.JPanel {
    
    private int idClienteSeleccionado = -1;

    public GestionClientes() {
        initComponents();
        cargarTabla();
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        txtNombre.putClientProperty("JTextField.placeholderText", "Nombre del cliente");
        txtApellido.putClientProperty("JTextField.placeholderText", "Apellido del cliente");
        txtTelefono.putClientProperty("JTextField.placeholderText", "Ej: 96007914");
        txtCorreo.putClientProperty("JTextField.placeholderText", "Ej: ejemplo@gmail.com");
        txtDireccion.putClientProperty("JTextField.placeholderText", "Ej: B. La Curva");
    }
    
    void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
    }
    
    private void cargarTabla() {
        //Definimos los nombres de las columnas
        String[] columnas = {"ID", "Nombre", "Apellido", "Teléfono", "Correo", "Dirección"};
        
        // Creamos el modelo de la tabla (y bloqueamos la edición manual de celdas)
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que el usuario edite dando doble clic en la tabla
            }
        };

        ClienteDAO dao = new ClienteDAO();
        java.util.List<entity.Cliente> lista = dao.searchClientes();

        //Recorremos la lista y agregamos cada cliente como una fila
        for (entity.Cliente c : lista) {
            Object[] fila = new Object[6];
            fila[0] = c.getIdCliente();
            fila[1] = c.getNombreCliente();
            fila[2] = c.getApellidoCliente();
            fila[3] = c.getTelefono();
            fila[4] = c.getCorreo();
            fila[5] = c.getDireccion();
            
            modelo.addRow(fila); // Agregamos la fila al modelo
        }

        tablaClientes.setModel(modelo); 
    }
    
    private void cargarEliminados() {
        String[] columnas = {"ID", "Nombre", "Apellido", "Teléfono", "Correo", "Dirección"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        ClienteDAO dao = new ClienteDAO();
        java.util.List<entity.Cliente> lista = dao.searchClientesEliminados(); 

        for (entity.Cliente c : lista) {
            Object[] fila = new Object[6];
            fila[0] = c.getIdCliente();
            fila[1] = c.getNombreCliente();
            fila[2] = c.getApellidoCliente();
            fila[3] = c.getTelefono();
            fila[4] = c.getCorreo();
            fila[5] = c.getDireccion();
            modelo.addRow(fila);
        }
        tablaClientes.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        chkEliminados = new javax.swing.JCheckBox();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1350, 1040));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 56, 78));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtNombre.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        txtTelefono.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtTelefono.addActionListener(this::txtTelefonoActionPerformed);

        txtCorreo.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        txtDireccion.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Telefono:");

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Correo:");

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Direccion:");

        btnEliminar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 56, 78));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnModificar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 56, 78));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(this::btnModificarActionPerformed);

        btnGuardar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 56, 78));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellido:");

        txtApellido.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        btnLimpiar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 56, 78));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Agrega Nuevos Clientes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(txtCorreo)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre)
                    .addComponent(txtTelefono))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnGuardar))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnModificar))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnLimpiar))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(36, 36, 36))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 660, 490));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 56, 78));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GESTION DE CLIENTES");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 720, -1));

        jPanel3.setBackground(new java.awt.Color(0, 56, 78));

        jScrollPane1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N

        tablaClientes.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        txtBuscar.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Buscar:");

        chkEliminados.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        chkEliminados.setForeground(new java.awt.Color(255, 255, 255));
        chkEliminados.setText("Mostrar Eliminados");
        chkEliminados.addActionListener(this::chkEliminadosActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEliminados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkEliminados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 630, 1040));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        //Obtenemos el modelo actual de tu tabla
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaClientes.getModel();
        
        //Creamos el "Filtro"
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> trs = new javax.swing.table.TableRowSorter<>(modelo);
        tablaClientes.setRowSorter(trs);
        
        //Capturamos lo que el usuario va escribiendo
        String textoBusqueda = txtBuscar.getText().trim();
        
        //Aplicamos el filtro. 
        // El "(?i)" es un truco de programación (Expresión Regular) para que ignore mayúsculas y minúsculas.
        // Si el usuario borra todo, la tabla vuelve a mostrar todos los registros.
        if (textoBusqueda.isEmpty()) {
            tablaClientes.setRowSorter(null);
        } else {
            trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + textoBusqueda));
        }        
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        // TODO add your handling code here:
        
        int fila = tablaClientes.getSelectedRow();

        if(fila != -1){

            idClienteSeleccionado = Integer.parseInt(tablaClientes.getValueAt(fila, 0).toString());
            
            txtNombre.setText(tablaClientes.getValueAt(fila, 1).toString());
            txtApellido.setText(tablaClientes.getValueAt(fila, 2).toString());
            txtTelefono.setText(tablaClientes.getValueAt(fila, 3).toString());
            txtCorreo.setText(tablaClientes.getValueAt(fila, 4).toString());
            txtDireccion.setText(tablaClientes.getValueAt(fila, 5).toString());
            
            btnGuardar.setEnabled(false);
            btnEliminar.setEnabled(true);
        }else{
            btnEliminar.setEnabled(false);
        }
        

    }//GEN-LAST:event_tablaClientesMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        //Capturamos los datos
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String direccion = txtDireccion.getText().trim();

        //Validación de campos no vacios
        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, llena al menos el nombre, apellido y teléfono.", "Campos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Llenamos nuestra entidad
        entity.Cliente nuevoCliente = new entity.Cliente();
        nuevoCliente.setNombreCliente(nombre);
        nuevoCliente.setApellidoCliente(apellido);
        nuevoCliente.setTelefono(telefono);
        nuevoCliente.setCorreo(correo);
        nuevoCliente.setDireccion(direccion);

        //Mandamos a guardar a la base de datos
        ClienteDAO dao = new ClienteDAO();
        dao.insertCliente(nuevoCliente); 

        //Limpiamos las cajas de texto para el siguiente registro
        limpiarCampos();
        
        //Actualizamos la tabla visualmente
        cargarTabla();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        //Verificamos que el usuario sí haya seleccionado a alguien en la tabla
        if (idClienteSeleccionado == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente de la tabla para modificarlo.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Capturamos los datos (que posiblemente el usuario ya edito)
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String direccion = txtDireccion.getText().trim();

        //Validación básica para que no dejen campos importantes vacíos al editar
        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre, apellido y teléfono son obligatorios.", "Campos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Llenamos nuestra entidad INCLUYENDO EL ID que guardamos al hacer clic
        entity.Cliente clienteModificado = new entity.Cliente();
        clienteModificado.setIdCliente(idClienteSeleccionado); // Esto es lo que le dice a la BD a quién actualizar!
        clienteModificado.setNombreCliente(nombre);
        clienteModificado.setApellidoCliente(apellido);
        clienteModificado.setTelefono(telefono);
        clienteModificado.setCorreo(correo);
        clienteModificado.setDireccion(direccion);

        //Mandamos a actualizar a la base de datos
        dao.ClienteDAO dao = new dao.ClienteDAO();
        dao.updateCliente(clienteModificado);
        
        javax.swing.JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente!");

        //Vaciamos las cajas, reseteamos el ID invisible a -1 y recargamos la tabla
        limpiarCampos();
        
        idClienteSeleccionado = -1; // Lo regresamos a -1 para que no se quede guardado por error
        cargarTabla(); // Actualizamos la vista
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        //Validamos que haya seleccionado a alguien en la tabla
        if (idClienteSeleccionado == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente de la tabla.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        ClienteDAO dao = new ClienteDAO();

        //Verificamos en qué modo estamos usando el CheckBox
        if (chkEliminados.isSelected()) {
            // ================= MODO RESTAURAR =================
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de que deseas RESTAURAR a este cliente?", 
                    "Confirmar restauración", 
                    javax.swing.JOptionPane.YES_NO_OPTION, 
                    javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                
                dao.restaurarCliente(idClienteSeleccionado);
                
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente restaurado correctamente!");

                limpiarCampos();
                idClienteSeleccionado = -1;
                cargarEliminados(); // Recargamos la tabla de eliminados para que desaparezca de ahí
            }
            
        } else {
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de que desea ELIMINAR a este cliente?", 
                    "Confirmar eliminación", 
                    javax.swing.JOptionPane.YES_NO_OPTION, 
                    javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                dao.deleteCliente(idClienteSeleccionado);
                
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente!");

                limpiarCampos();
                idClienteSeleccionado = -1; 
                cargarTabla(); // Recargamos la tabla normal
            }
        }      
    }//GEN-LAST:event_btnEliminarActionPerformed

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

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}


