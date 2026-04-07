package vistas;

import dao.UsuarioDAO;

public class GestionUsuarios extends javax.swing.JPanel {
    
    private int idUsuarioSeleccionado = -1;

    public GestionUsuarios() {
        initComponents();
        cargarTabla();
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtUsuario.setText("");
        txtPass.setText("");
    }
    
    private void cargarTabla() {
        // Definimos las columnas (Ocultaremos el ID de la contraseña real por seguridad)
        String[] columnas = {"ID", "Nombre", "Apellido", "Usuario", "Contraseña"};
        
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        // Llamamos al DAO
        dao.UsuarioDAO dao = new dao.UsuarioDAO();
        java.util.List<entity.Usuario> lista = dao.searchUsuarios();

        // Llenamos la tabla
        for (entity.Usuario u : lista) {
            Object[] fila = new Object[5];
            fila[0] = u.getIdUsuario();
            fila[1] = u.getNombre_empleado();
            fila[2] = u.getApellido_empleado();
            fila[3] = u.getUser();
            fila[4] = "********"; //Mostramos asteriscos fijos en la tabla, no la contraseña real
            
            modelo.addRow(fila);
        }

        tablaUsuarios.setModel(modelo); 
    }
    
    private void cargarEliminados() {
        String[] columnas = {"ID", "Nombre", "Apellido", "Usuario", "Contraseña"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        dao.UsuarioDAO dao = new dao.UsuarioDAO();
        java.util.List<entity.Usuario> lista = dao.searchUsuariosEliminados(); 

        for (entity.Usuario u : lista) {
            Object[] fila = new Object[5];
            fila[0] = u.getIdUsuario();
            fila[1] = u.getNombre_empleado();
            fila[2] = u.getApellido_empleado();
            fila[3] = u.getUser();
            fila[4] = "********"; // Mantenemos la seguridad aquí también
            modelo.addRow(fila);
        }
        tablaUsuarios.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnLimpiar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        chkEliminados = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1350, 1040));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 56, 78));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtNombre.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        txtUsuario.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario:");

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña:");

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

        txtPass.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        btnLimpiar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 56, 78));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Agrega Nuevos Usuarios");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(47, 47, 47)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtPass))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(49, 49, 49)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardar))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnModificar))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnLimpiar))
                .addGap(37, 37, 37))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 660, 410));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 56, 78));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GESTION DE USUARIOS");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 730, -1));

        jPanel3.setBackground(new java.awt.Color(0, 56, 78));
        jPanel3.setForeground(new java.awt.Color(0, 56, 78));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Buscar:");

        txtBuscar.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        chkEliminados.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        chkEliminados.setForeground(new java.awt.Color(255, 255, 255));
        chkEliminados.setText("Mostrar Eliminados");
        chkEliminados.addActionListener(this::chkEliminadosActionPerformed);

        jScrollPane1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N

        tablaUsuarios.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEliminados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkEliminados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(242, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 630, 1040));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1362, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        //Validamos que haya seleccionado a alguien en la tabla
        if (idUsuarioSeleccionado == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario de la tabla.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();

        //Verificamos en qué modo estamos usando el CheckBox
        if (chkEliminados.isSelected()) {
            // ================= MODO RESTAURAR =================
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas RESTAURAR a este usuario?",
                "Confirmar restauración",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {

                dao.restaurarUsuario(idUsuarioSeleccionado);

                javax.swing.JOptionPane.showMessageDialog(this, "Usuario restaurado correctamente!");

                limpiarCampos();
                idUsuarioSeleccionado = -1;
                cargarEliminados(); // Recargamos la tabla de eliminados para que desaparezca de ahí
            }

        } else {
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que desea ELIMINAR a este usuario?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                dao.deleteUsuario(idUsuarioSeleccionado);

                javax.swing.JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente!");

                limpiarCampos();
                idUsuarioSeleccionado = -1;
                cargarTabla(); // Recargamos la tabla normal
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
       if (idUsuarioSeleccionado == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario de la tabla.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPass.getPassword()); // Extraemos la contraseña

        // Exigimos que la contraseña no esté vacía
        if (nombre.isEmpty() || apellido.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Para modificar, ningún campo puede estar vacío. (Ingresa la contraseña actual o una nueva).", "Campos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Llenamos la entidad
        entity.Usuario usuModificado = new entity.Usuario();
        usuModificado.setIdUsuario(idUsuarioSeleccionado);
        usuModificado.setNombre_empleado(nombre);
        usuModificado.setApellido_empleado(apellido);
        usuModificado.setUser(user);
        usuModificado.setPass(pass);

        // Mandamos al DAO
        dao.UsuarioDAO dao = new dao.UsuarioDAO();
        dao.updateUsuario(usuModificado);
        
        javax.swing.JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");

        limpiarCampos();
        idUsuarioSeleccionado = -1; 
        cargarTabla();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String user = txtUsuario.getText().trim();
        
        // Forma correcta de sacar el texto de un JPasswordField en Java
        String pass = new String(txtPass.getPassword()); 

        //Validación de campos vacíos
        if(nombre.isEmpty() || apellido.isEmpty() || user.isEmpty() || pass.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios para crear un usuario.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();

        //Validación de duplicados (Usamos el método que ya tienes en tu DAO)
        if(dao.existeUsuario(user)){
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre de usuario '" + user + "' ya está en uso. Por favor, elige otro.", "Usuario duplicado", javax.swing.JOptionPane.ERROR_MESSAGE);
            return; 
        }

        //Llenamos la entidad
        entity.Usuario nuevoUsuario = new entity.Usuario();
        nuevoUsuario.setNombre_empleado(nombre);
        nuevoUsuario.setApellido_empleado(apellido);
        nuevoUsuario.setUser(user);
        nuevoUsuario.setPass(pass);

        //Mandamos a guardar
        dao.insertUsuario(nuevoUsuario);

        //Limpiamos y recargamos
        limpiarCampos();
        cargarTabla();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        //Obtenemos el modelo actual de tu tabla
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaUsuarios.getModel();

        //Creamos el "Filtro"
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> trs = new javax.swing.table.TableRowSorter<>(modelo);
        tablaUsuarios.setRowSorter(trs);

        //Capturamos lo que el usuario va escribiendo
        String textoBusqueda = txtBuscar.getText().trim();

        //Aplicamos el filtro.
        // El "(?i)" es un truco de programación (Expresión Regular) para que ignore mayúsculas y minúsculas.
        // Si el usuario borra todo, la tabla vuelve a mostrar todos los registros.
        if (textoBusqueda.isEmpty()) {
            tablaUsuarios.setRowSorter(null);
        } else {
            trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        // TODO add your handling code here:

        int fila = tablaUsuarios.getSelectedRow();

        if (fila != -1) {
            // Guardamos el ID invisible
            idUsuarioSeleccionado = Integer.parseInt(tablaUsuarios.getValueAt(fila, 0).toString());
            
            // Pasamos los datos de texto
            txtNombre.setText(tablaUsuarios.getValueAt(fila, 1).toString());
            txtApellido.setText(tablaUsuarios.getValueAt(fila, 2).toString());
            txtUsuario.setText(tablaUsuarios.getValueAt(fila, 3).toString());
            
            //Vaciamos la caja de contraseña. 
            // Si el admin quiere cambiarla, que escriba una nueva. Si la deja en blanco, la ignoraremos al modificar.
            txtPass.setText("");
            
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void chkEliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEliminadosActionPerformed
        // TODO add your handling code here:

        // Si la casilla esta marcada, mostramos los eliminados
        if (chkEliminados.isSelected()) {
            cargarEliminados();

            //Desactivamos los botones de Guardar y Modificar para no alterar registros borrados
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setText("RESTAURAR"); // Cambiamos el texto del botón Eliminar a Restaurar

        } else {
            // Si la desmarcan, volvemos a mostrar los clientes normales
            cargarTabla();

            // Volvemos a activar todo a la normalidad
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(true);
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
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
