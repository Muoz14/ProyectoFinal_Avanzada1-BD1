package vistas;

public class Inventario extends javax.swing.JPanel {
    
    //Buscador fantasma de producto
    private javax.swing.JPopupMenu popupProducto;
    private javax.swing.JTable tablaPopup;
    private javax.swing.table.DefaultTableModel modeloPopup;
    private java.util.List<entity.Producto> listaProductosCache; 
    private entity.Producto productoSeleccionado = null;
    
    //Buscador fantasma de empleados
    private javax.swing.JPopupMenu popupEmpleado;
    private javax.swing.JTable tablaPopupEmpleado;
    private javax.swing.table.DefaultTableModel modeloPopupEmpleado;
    private java.util.List<entity.Usuario> listaUsuariosCache; 
    private entity.Usuario empleadoSeleccionado = null;
    
    private javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorterTablaPrincipal;

    public Inventario() {
        initComponents();
        prepararBuscadorFantasma();
        prepararBuscadorFantasmaEmpleado();
        cargarTabla();
        
        txtBuscarProductos.putClientProperty("JTextField.placeholderText", "Seleccione producto");
        txtBuscarEmpleado.putClientProperty("JTextField.placeholderText", "Seleccione un empleado");
        txtCantidad.putClientProperty("JTextField.placeholderText", "Ej: 10");
        
    }

    //Metodo para cargar el historial en la tabla
    private void cargarTabla() {
        String[] columnas = {"ID Mov.", "Producto", "Usuario", "Movimiento", "Cantidad", "Stock Actual", "Fecha"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        dao.InventarioDAO daoInv = new dao.InventarioDAO();
        java.util.List<entity.Inventario> lista = daoInv.listarMovimientos();

        for (entity.Inventario i : lista) {
            Object[] fila = new Object[7];
            fila[0] = i.getIdMovimiento();
            fila[1] = i.getNombreProducto(); 
            fila[2] = i.getNombreUsuario();  
            fila[3] = i.getTipoMovimiento();
            fila[4] = i.getCantidad();
            fila[5] = i.getStockActualProducto();
            fila[6] = i.getFechaMovimiento();
            
            modelo.addRow(fila);
        }
        
        tablaInventario.setModel(modelo);

        sorterTablaPrincipal = new javax.swing.table.TableRowSorter<>(modelo);
        tablaInventario.setRowSorter(sorterTablaPrincipal);
    }
    
    private void prepararBuscadorFantasmaEmpleado() {
        popupEmpleado = new javax.swing.JPopupMenu();
        popupEmpleado.setFocusable(false); 

        String[] columnas = {"ID", "Nombre del Empleado"};
        modeloPopupEmpleado = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaPopupEmpleado = new javax.swing.JTable(modeloPopupEmpleado);
        
        // EVENTO CLIC EN LA TABLA
        tablaPopupEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int fila = tablaPopupEmpleado.getSelectedRow();
                if (fila != -1) {
                    int idUsu = (int) tablaPopupEmpleado.getValueAt(fila, 0);
                    seleccionarEmpleadoDesdePopup(idUsu);
                }
            }
        });

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tablaPopupEmpleado);
        scroll.setPreferredSize(new java.awt.Dimension(350, 150)); 
        popupEmpleado.add(scroll);

        // Cargamos todos los usuarios a la memoria
        listaUsuariosCache = new dao.UsuarioDAO().searchUsuarios(); 
    }

    private void filtrarTablaPopupEmpleado(String busqueda) {
        modeloPopupEmpleado.setRowCount(0); // Limpiar tabla
        for (entity.Usuario u : listaUsuariosCache) {
            // Unimos nombre y apellido para una mejor búsqueda
            String nombreCompleto = u.getNombre_empleado() + " " + u.getApellido_empleado();
            
            if (nombreCompleto.toLowerCase().contains(busqueda.toLowerCase())) {
                modeloPopupEmpleado.addRow(new Object[]{u.getIdUsuario(), nombreCompleto});
            }
        }
    }

    private void seleccionarEmpleadoDesdePopup(int idUsuario) {
        for (entity.Usuario u : listaUsuariosCache) {
            if (u.getIdUsuario() == idUsuario) {
                empleadoSeleccionado = u;
                // Mostramos el nombre completo en la caja de texto
                txtBuscarEmpleado.setText(u.getNombre_empleado() + " " + u.getApellido_empleado()); 
                popupEmpleado.setVisible(false); 
                break;
            }
        }
    }
    
    private void actualizarCacheProductos() {
        listaProductosCache = new dao.ProductoDAO().searchProductos();
    }
    
   private void prepararBuscadorFantasma() {
        popupProducto = new javax.swing.JPopupMenu();
        popupProducto.setFocusable(false); // Para que no te robe el cursor mientras escribes

        String[] columnas = {"ID", "Producto", "Stock"};
        modeloPopup = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaPopup = new javax.swing.JTable(modeloPopup);
        
        // EVENTO: ¿Qué pasa cuando el usuario hace clic en la tabla fantasma?
        tablaPopup.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int fila = tablaPopup.getSelectedRow();
                if (fila != -1) {
                    int idProd = (int) tablaPopup.getValueAt(fila, 0);
                    seleccionarProductoDesdePopup(idProd);
                }
            }
        });

        // Metemos la tabla en un Scroll y luego al Popup
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tablaPopup);
        scroll.setPreferredSize(new java.awt.Dimension(350, 150)); // Ancho y alto de tu tabla fantasma
        popupProducto.add(scroll);

        // Cargamos todos los productos a la memoria
        actualizarCacheProductos(); 
    }
    
    private void filtrarTablaPopup(String busqueda) {
        modeloPopup.setRowCount(0); // Limpiar tabla
        for (entity.Producto p : listaProductosCache) {
            // Busca coincidencias ignorando mayúsculas/minúsculas
            if (p.getNombreProducto().toLowerCase().contains(busqueda.toLowerCase())) {
                modeloPopup.addRow(new Object[]{p.getIdProducto(), p.getNombreProducto(), p.getStockActual()});
            }
        }
    }
    
    private void seleccionarProductoDesdePopup(int idProducto) {
        // Buscamos el objeto completo y lo guardamos
        for (entity.Producto p : listaProductosCache) {
            if (p.getIdProducto() == idProducto) {
                
                // MAGIA: ¿Quién abrió el fantasma? ¿La caja izquierda o la derecha?
                if (popupProducto.getInvoker() == txtBuscar) {
                    // Si lo abrió el buscador principal (derecha)
                    txtBuscar.setText(p.getNombreProducto());
                    
                    // Filtramos la tabla gigante para mostrar solo ese producto
                    if (sorterTablaPrincipal != null) {
                        sorterTablaPrincipal.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + p.getNombreProducto()));
                    }
                    
                } else {
                    // Si lo abrió el formulario de ingreso (izquierda)
                    productoSeleccionado = p;
                    txtBuscarProductos.setText(p.getNombreProducto()); 
                }
                
                popupProducto.setVisible(false); // Ocultamos al fantasma
                break;
            }
        }
    }
    
    // Método para limpiar 
    private void limpiarCampos() {
        txtCantidad.setText("");
        btGRbE_S.clearSelection(); 
        txtBuscarEmpleado.setText("");
        empleadoSeleccionado = null;
        txtBuscarProductos.setText(""); // Limpiamos el buscador
        productoSeleccionado = null; // Reseteamos la memoria del producto seleccionado
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGRbE_S = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnProcesar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        rbEntrada = new javax.swing.JRadioButton();
        rbSalida = new javax.swing.JRadioButton();
        txtBuscarProductos = new javax.swing.JTextField();
        txtBuscarEmpleado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1350, 1040));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 56, 78));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtCantidad.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Producto:");

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Movimiento:");

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cantidad:");

        btnProcesar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnProcesar.setForeground(new java.awt.Color(0, 56, 78));
        btnProcesar.setText("Procesar Movimiento");
        btnProcesar.addActionListener(this::btnProcesarActionPerformed);

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Empleado:");

        btGRbE_S.add(rbEntrada);
        rbEntrada.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        rbEntrada.setForeground(new java.awt.Color(255, 255, 255));
        rbEntrada.setText("Entrada");

        btGRbE_S.add(rbSalida);
        rbSalida.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        rbSalida.setForeground(new java.awt.Color(255, 255, 255));
        rbSalida.setText("Salida");

        txtBuscarProductos.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscarProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarProductosMouseClicked(evt);
            }
        });
        txtBuscarProductos.addActionListener(this::txtBuscarProductosActionPerformed);
        txtBuscarProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductosKeyReleased(evt);
            }
        });

        txtBuscarEmpleado.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarEmpleadoMouseClicked(evt);
            }
        });
        txtBuscarEmpleado.addActionListener(this::txtBuscarEmpleadoActionPerformed);
        txtBuscarEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEmpleadoKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Realiza un Movimiento en el Inventario");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(38, 38, 38)
                                .addComponent(txtCantidad))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rbEntrada)
                                        .addGap(27, 27, 27)
                                        .addComponent(rbSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtBuscarProductos)
                                    .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rbEntrada)
                    .addComponent(rbSalida))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(52, 52, 52)
                .addComponent(btnProcesar)
                .addGap(35, 35, 35))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 450, 430));

        txtBuscar.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarMouseClicked(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 320, -1));

        jScrollPane1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N

        tablaInventario.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 740, 590));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Buscar:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 56, 78));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GESTION DE INVENTARIO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 530, -1));

        jPanel3.setBackground(new java.awt.Color(0, 56, 78));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 820, 1040));

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        // TODO add your handling code here:
        //Validar que haya seleccionado una opción de Entada/Salida
        if (!rbEntrada.isSelected() && !rbSalida.isSelected()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona si es una Entrada o una Salida.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Validar que la cantidad no este vacia y sea un numero válido
        String cantStr = txtCantidad.getText().trim();
        if (cantStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingresa la cantidad.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(cantStr);
            if (cantidad <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Extraer el objeto desde nuestra variable global de la tabla fantasma
        entity.Producto prodSeleccionado = this.productoSeleccionado;
        entity.Usuario usuSeleccionado = this.empleadoSeleccionado;

        if (prodSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un Producto de la lista.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (usuSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un Empleado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoMovimiento = rbEntrada.isSelected() ? "Entrada" : "Salida";

        //Si es salida, validar que haya suficiente stock
        if (tipoMovimiento.equals("Salida")) {
            if (cantidad > prodSeleccionado.getStockActual()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Stock insuficiente!\n" +
                    "Producto: " + prodSeleccionado.getNombreProducto() + "\n" +
                    "Stock Actual: " + prodSeleccionado.getStockActual() + "\n" +
                    "Intentas sacar: " + cantidad, 
                    "Error de Inventario", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        //Llenar la entidad Inventario
        entity.Inventario nuevoMovimiento = new entity.Inventario();
        nuevoMovimiento.setIdProducto(prodSeleccionado.getIdProducto());
        nuevoMovimiento.setIdUsuario(usuSeleccionado.getIdUsuario());
        nuevoMovimiento.setTipoMovimiento(tipoMovimiento);
        nuevoMovimiento.setCantidad(cantidad);

        //Procesar en Base de Datos (Esto hace el INSERT y el UPDATE mágico)
        dao.InventarioDAO daoInv = new dao.InventarioDAO();
        daoInv.insertMovimiento(nuevoMovimiento);

        limpiarCampos();
        cargarTabla(); 
        actualizarCacheProductos(); // Recargamos para que el fantasma tenga el nuevo stock actualizado
       
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        String texto = txtBuscar.getText().trim();
        
        // Si borran todo el texto, mostramos toda la tabla grande de nuevo
        if (texto.isEmpty() && sorterTablaPrincipal != null) {
            sorterTablaPrincipal.setRowFilter(null);
        }

        // Filtramos la lista del popup
        filtrarTablaPopup(texto);

        // Si hay resultados, mostramos el fantasma anclado al txtBuscar
        if (modeloPopup.getRowCount() > 0) {
            popupProducto.show(txtBuscar, 0, txtBuscar.getHeight());
            txtBuscar.requestFocus(); // Mantenemos el cursor para seguir escribiendo
        } else {
            popupProducto.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        // TODO add your handling code here:

      
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void txtBuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosKeyReleased
        // TODO add your handling code here:
        String texto = txtBuscarProductos.getText().trim();
        
        // Si borran todo, simplemente "olvidamos" el producto, pero NO ocultamos la tabla
        if(texto.isEmpty()){
            productoSeleccionado = null;
        }

        filtrarTablaPopup(texto);

        // Si hay resultados, mostramos la tabla fantasma
        if (modeloPopup.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
            txtBuscarProductos.requestFocus(); 
        } else {
            popupProducto.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarProductosKeyReleased

    private void txtBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductosActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtBuscarProductosActionPerformed

    private void txtBuscarProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarProductosMouseClicked
        // TODO add your handling code here:
        String texto = txtBuscarProductos.getText().trim();
        
        filtrarTablaPopup(texto); // Si esta vacio, el contains("") de tu metodo traera TODOS los productos
        
        if (modeloPopup.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
        }
    }//GEN-LAST:event_txtBuscarProductosMouseClicked

    private void txtBuscarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoMouseClicked
        // TODO add your handling code here:
        String texto = txtBuscarEmpleado.getText().trim();
        filtrarTablaPopupEmpleado(texto); 
        if (modeloPopupEmpleado.getRowCount() > 0) {
            popupEmpleado.show(txtBuscarEmpleado, 0, txtBuscarEmpleado.getHeight());
        }
    }//GEN-LAST:event_txtBuscarEmpleadoMouseClicked

    private void txtBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpleadoActionPerformed

    private void txtBuscarEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyReleased
        // TODO add your handling code here:
        String texto = txtBuscarEmpleado.getText().trim();
        
        if(texto.isEmpty()){
            empleadoSeleccionado = null;
        }

        filtrarTablaPopupEmpleado(texto);

        if (modeloPopupEmpleado.getRowCount() > 0) {
            popupEmpleado.show(txtBuscarEmpleado, 0, txtBuscarEmpleado.getHeight());
            txtBuscarEmpleado.requestFocus(); 
        } else {
            popupEmpleado.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarEmpleadoKeyReleased

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        // TODO add your handling code here:
        String texto = txtBuscar.getText().trim();
        filtrarTablaPopup(texto);
        if (modeloPopup.getRowCount() > 0) {
            popupProducto.show(txtBuscar, 0, txtBuscar.getHeight());
        }
    }//GEN-LAST:event_txtBuscarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btGRbE_S;
    private javax.swing.JButton btnProcesar;
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
    private javax.swing.JRadioButton rbEntrada;
    private javax.swing.JRadioButton rbSalida;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarEmpleado;
    private javax.swing.JTextField txtBuscarProductos;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables
}
