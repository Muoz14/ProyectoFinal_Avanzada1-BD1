package vistas;

import entity.*;
import dao.*;

public class GestionVentas extends javax.swing.JPanel {
    
    // Variables globales para la memoria temporal de la factura
    private javax.swing.table.DefaultTableModel modeloCarrito;
    private java.util.List<entity.DetalleVenta> carritoCompras = new java.util.ArrayList<>();
    private double totalFactura = 0.0;

    // --- VARIABLES FANTASMAS PARA CLIENTE ---
    private javax.swing.JPopupMenu popupCliente;
    private javax.swing.JTable tablaPopupCli;
    private javax.swing.table.DefaultTableModel modeloPopupCli;
    private java.util.List<entity.Cliente> listaCliCache;
    private entity.Cliente clienteSeleccionado = null;

    // --- VARIABLES FANTASMAS PARA EMPLEADO ---
    private javax.swing.JPopupMenu popupEmpleado;
    private javax.swing.JTable tablaPopupEmp;
    private javax.swing.table.DefaultTableModel modeloPopupEmp;
    private java.util.List<entity.Usuario> listaEmpCache;
    private entity.Usuario empleadoSeleccionado = null;

    // --- VARIABLES FANTASMAS PARA PRODUCTO ---
    private javax.swing.JPopupMenu popupProducto;
    private javax.swing.JTable tablaPopupProd;
    private javax.swing.table.DefaultTableModel modeloPopupProd;
    private java.util.List<entity.Producto> listaProdCache;
    private entity.Producto productoSeleccionado = null;

    public GestionVentas() {
        initComponents();
        prepararTablaCarrito();
        
        prepararBuscadorCliente();
        prepararBuscadorEmpleado();
        prepararBuscadorProducto();
    }
    
    private void prepararTablaCarrito() {
        String[] columnas = {"ID Prod", "Producto", "Precio", "Cantidad", "Subtotal"};
        modeloCarrito = new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tablaCarrito.setModel(modeloCarrito);
    }

    // 1. MOTOR CLIENTE (Conectado a txtBuscarEmpleado1)
    private void prepararBuscadorCliente() {
        popupCliente = new javax.swing.JPopupMenu();
        popupCliente.setFocusable(false);
        modeloPopupCli = new javax.swing.table.DefaultTableModel(null, new String[]{"ID", "Nombre Cliente"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPopupCli = new javax.swing.JTable(modeloPopupCli);
        
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
                break;
            }
        }
    }

    // 2. MOTOR EMPLEADO (Conectado a txtBuscarEmpleado)
    private void prepararBuscadorEmpleado() {
        popupEmpleado = new javax.swing.JPopupMenu();
        popupEmpleado.setFocusable(false);
        modeloPopupEmp = new javax.swing.table.DefaultTableModel(null, new String[]{"ID", "Nombre Empleado"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPopupEmp = new javax.swing.JTable(modeloPopupEmp);
        
        tablaPopupEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int fila = tablaPopupEmp.getSelectedRow();
                if (fila != -1) seleccionarEmpleado((int) tablaPopupEmp.getValueAt(fila, 0));
            }
        });

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tablaPopupEmp);
        scroll.setPreferredSize(new java.awt.Dimension(300, 150));
        popupEmpleado.add(scroll);
        listaEmpCache = new dao.UsuarioDAO().searchUsuarios();
    }

    private void filtrarEmpleado(String busqueda) {
        modeloPopupEmp.setRowCount(0);
        for (entity.Usuario u : listaEmpCache) {
            String nombreCompleto = u.getNombre_empleado() + " " + u.getApellido_empleado();
            if (nombreCompleto.toLowerCase().contains(busqueda.toLowerCase())) {
                modeloPopupEmp.addRow(new Object[]{u.getIdUsuario(), nombreCompleto});
            }
        }
    }

    private void seleccionarEmpleado(int id) {
        for (entity.Usuario u : listaEmpCache) {
            if (u.getIdUsuario() == id) {
                empleadoSeleccionado = u;
                txtBuscarEmpleado.setText(u.getNombre_empleado() + " " + u.getApellido_empleado());
                popupEmpleado.setVisible(false);
                break;
            }
        }
    }

    // 3. MOTOR PRODUCTO (Conectado a txtBuscarProductos)
    private void prepararBuscadorProducto() {
        popupProducto = new javax.swing.JPopupMenu();
        popupProducto.setFocusable(false);
        modeloPopupProd = new javax.swing.table.DefaultTableModel(null, new String[]{"ID", "Producto", "Stock"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPopupProd = new javax.swing.JTable(modeloPopupProd);
        
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnFacturar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnAgregarCarrito = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtBuscarProductos = new javax.swing.JTextField();
        txtBuscarEmpleado = new javax.swing.JTextField();
        txtBuscarCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnEliminarCarrito = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1350, 1040));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 56, 78));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtCantidad.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cliente:");

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Producto:");

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cantidad:");

        btnFacturar.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnFacturar.setForeground(new java.awt.Color(0, 56, 78));
        btnFacturar.setText("Facturar Venta");
        btnFacturar.addActionListener(this::btnFacturarActionPerformed);

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Empleado:");

        btnAgregarCarrito.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnAgregarCarrito.setForeground(new java.awt.Color(0, 56, 78));
        btnAgregarCarrito.setText("Agregar al Carrito");
        btnAgregarCarrito.addActionListener(this::btnAgregarCarritoActionPerformed);

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total:");

        lblTotal.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("L. 0.00");

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

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Realiza una Nueva Venta");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAgregarCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel8))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(lblTotal))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3))
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblTotal))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarCarrito)
                    .addComponent(btnFacturar))
                .addGap(33, 33, 33))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 580, 530));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 56, 78));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GESTION DE VENTAS");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 680, -1));

        btnEliminarCarrito.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        btnEliminarCarrito.setForeground(new java.awt.Color(0, 56, 78));
        btnEliminarCarrito.setText("Eliminar Producto del Carrito");
        btnEliminarCarrito.addActionListener(this::btnEliminarCarritoActionPerformed);
        jPanel2.add(btnEliminarCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 820, 340, -1));

        jPanel4.setBackground(new java.awt.Color(0, 56, 78));

        jScrollPane1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N

        tablaCarrito.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCarritoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCarrito);

        jLabel10.setFont(new java.awt.Font("Franklin Gothic Book", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Carrito de la Venta");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 670, 1040));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        // TODO add your handling code here:
        
        //Validar que el carrito no este vacio
        if (carritoCompras.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "El carrito esta vacio. Agrega productos primero.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (clienteSeleccionado == null || empleadoSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un Cliente y un Empleado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        Venta nuevaVenta = new Venta();
        nuevaVenta.setIdCliente(clienteSeleccionado.getIdCliente());
        nuevaVenta.setIdUsuario(empleadoSeleccionado.getIdUsuario());

        VentaDAO daoVentas = new VentaDAO();
        try {
            boolean exito = daoVentas.registrarVenta(nuevaVenta, carritoCompras);
            
            if (exito) {
                // TICKET
                StringBuilder ticket = new StringBuilder();
                ticket.append("==========================================\n");
                ticket.append("         CENTRO INFORMATICO APOLO         \n");
                ticket.append("==========================================\n");
                
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                ticket.append("Fecha: ").append(sdf.format(new java.util.Date())).append("\n");
                ticket.append("Cliente: ").append(clienteSeleccionado.getNombreCliente()).append(" ").append(clienteSeleccionado.getApellidoCliente()).append("\n");
                ticket.append("Cajero: ").append(empleadoSeleccionado.getNombre_empleado()).append(" ").append(empleadoSeleccionado.getApellido_empleado()).append("\n");
                ticket.append("------------------------------------------\n");
                ticket.append(String.format("%-5s %-15s %-8s %-8s\n", "CANT", "PRODUCTO", "PRECIO", "SUBTOT"));
                ticket.append("------------------------------------------\n");

                for (entity.DetalleVenta d : carritoCompras) {
                    String nombreProd = d.getNombreProducto();
                    if (nombreProd.length() > 15) nombreProd = nombreProd.substring(0, 15);
                    ticket.append(String.format("%-5d %-15s L.%-6.2f L.%-6.2f\n", 
                        d.getCantidad(), nombreProd, d.getPrecioUnitario(), d.getSubTotal()));
                }

                ticket.append("------------------------------------------\n");
                ticket.append(String.format("TOTAL A PAGAR:                  L. %,.2f\n", totalFactura));
                ticket.append("==========================================\n");
                ticket.append("         ¡Gracias por su compra!          \n");

                javax.swing.JTextArea txtTicket = new javax.swing.JTextArea(ticket.toString());
                txtTicket.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));
                txtTicket.setEditable(false);
                txtTicket.setBackground(new java.awt.Color(240, 240, 240));

                javax.swing.JOptionPane.showMessageDialog(this, txtTicket, "Ticket de Venta", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                
                // LIMPIEZA TOTAL
                carritoCompras.clear();
                modeloCarrito.setRowCount(0); 
                totalFactura = 0.0;
                lblTotal.setText("L. 0.00");
                
                // Reiniciar campos y memorias
                txtBuscarCliente.setText("");
                txtBuscarEmpleado.setText("");
                txtBuscarProductos.setText("");
                clienteSeleccionado = null;
                empleadoSeleccionado = null;
                productoSeleccionado = null;
                
                txtBuscarCliente.setEnabled(true);
                txtBuscarEmpleado.setEnabled(true);
                actualizarCacheProductos(); // Recargar stock real
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrio un error al facturar: " + e.getMessage(), "Error Critico", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void tablaCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCarritoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaCarritoMouseClicked

    private void btnAgregarCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCarritoActionPerformed
        // TODO add your handling code here:
        
       //Validar que se ingreso una cantidad valida
        String cantStr = txtCantidad.getText().trim();
        if (cantStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingresa una cantidad.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantidadAAgregar = 0;
        try {
            cantidadAAgregar = Integer.parseInt(cantStr);
            if (cantidadAAgregar <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Cantidad invalida.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (productoSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Busca y selecciona un producto.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean productoExiste = false;
        int indiceFilaExistente = -1;

        for (int i = 0; i < carritoCompras.size(); i++) {
            if (carritoCompras.get(i).getIdProducto() == productoSeleccionado.getIdProducto()) {
                productoExiste = true;
                indiceFilaExistente = i;
                break; 
            }
        }

        if (productoExiste) {
            entity.DetalleVenta detalleExistente = carritoCompras.get(indiceFilaExistente);
            int nuevaCantidadTotal = detalleExistente.getCantidad() + cantidadAAgregar;

            if (nuevaCantidadTotal > productoSeleccionado.getStockActual()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Stock insuficiente! Ya tienes " + detalleExistente.getCantidad() + " en el carrito y solo quedan " + productoSeleccionado.getStockActual() + " en inventario.", "Stock", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            detalleExistente.setCantidad(nuevaCantidadTotal);
            detalleExistente.setSubTotal(nuevaCantidadTotal * productoSeleccionado.getPrecio());
            modeloCarrito.setValueAt(detalleExistente.getCantidad(), indiceFilaExistente, 3);
            modeloCarrito.setValueAt(detalleExistente.getSubTotal(), indiceFilaExistente, 4);
            totalFactura += (cantidadAAgregar * productoSeleccionado.getPrecio());

        } else {
            if (cantidadAAgregar > productoSeleccionado.getStockActual()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Stock insuficiente! Solo quedan " + productoSeleccionado.getStockActual() + " unidades.", "Stock", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            double subTotal = cantidadAAgregar * productoSeleccionado.getPrecio();
            entity.DetalleVenta detalle = new entity.DetalleVenta();
            detalle.setIdProducto(productoSeleccionado.getIdProducto());
            detalle.setNombreProducto(productoSeleccionado.getNombreProducto());
            detalle.setCantidad(cantidadAAgregar);
            detalle.setPrecioUnitario(productoSeleccionado.getPrecio());
            detalle.setSubTotal(subTotal);
            carritoCompras.add(detalle);

            Object[] fila = new Object[5];
            fila[0] = detalle.getIdProducto();
            fila[1] = detalle.getNombreProducto();
            fila[2] = detalle.getPrecioUnitario();
            fila[3] = detalle.getCantidad();
            fila[4] = detalle.getSubTotal();
            modeloCarrito.addRow(fila);

            totalFactura += subTotal;
        }

        lblTotal.setText("L. " + String.format("%,.2f", totalFactura)); 
        txtCantidad.setText("");
        txtBuscarProductos.setText("");
        productoSeleccionado = null;
        
        // Bloqueamos cliente y empleado para no cambiarlos a mitad de venta
        txtBuscarCliente.setEnabled(false);
        txtBuscarEmpleado.setEnabled(false);
    }//GEN-LAST:event_btnAgregarCarritoActionPerformed

    private void btnEliminarCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCarritoActionPerformed
        // TODO add your handling code here:

        int fila = tablaCarrito.getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la tabla para eliminar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        entity.DetalleVenta detalle = carritoCompras.get(fila);
        int cantidadActual = detalle.getCantidad();
        int cantidadAEliminar = cantidadActual; // Por defecto asumimos que quiere borrar todo

        //Si hay mas de 1, le preguntamos cuantos quiere eliminar
        if (cantidadActual > 1) {
            String input = javax.swing.JOptionPane.showInputDialog(this, 
                "Tienes " + cantidadActual + " unidades de este producto en el carrito.\n¿Cuántas deseas eliminar?", 
                "Eliminar cantidad", 
                javax.swing.JOptionPane.QUESTION_MESSAGE);
                
            //Si el usuario da a Cancelar o cierra la ventana, abortamos
            if (input == null) {
                return;
            }
            
            try {
                cantidadAEliminar = Integer.parseInt(input.trim());
                
                if (cantidadAEliminar <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, "La cantidad a eliminar debe ser mayor a 0.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cantidadAEliminar > cantidadActual) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No puedes eliminar más unidades de las que hay en el carrito.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingresa un número entero válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        //Si decidio eliminar todos los que hay (o si solo había 1)
        if (cantidadAEliminar == cantidadActual) {
            totalFactura -= detalle.getSubTotal();
            carritoCompras.remove(fila);
            modeloCarrito.removeRow(fila);
        } else {
            //Solo restamos una parte de la cantidad
            int nuevaCantidad = cantidadActual - cantidadAEliminar;
            double dineroARestar = cantidadAEliminar * detalle.getPrecioUnitario();
            
            detalle.setCantidad(nuevaCantidad);
            detalle.setSubTotal(detalle.getSubTotal() - dineroARestar);
            
            //Actualizamos la tabla visual (Columnas de Cantidad y Subtotal)
            modeloCarrito.setValueAt(nuevaCantidad, fila, 3);
            modeloCarrito.setValueAt(detalle.getSubTotal(), fila, 4);
            
            totalFactura -= dineroARestar;
        }

        //Ajuste de seguridad por si los decimales de Java hacen cosas raras
        if (totalFactura < 0) totalFactura = 0.0; 
        lblTotal.setText("L. " + String.format("%,.2f", totalFactura));

        //Si el carrito quedó vacío, volvemos a habilitar Cliente y Empleado
        if (carritoCompras.isEmpty()) {
            txtBuscarCliente.setEnabled(true);
            txtBuscarEmpleado.setEnabled(true);
        }
    }//GEN-LAST:event_btnEliminarCarritoActionPerformed

    private void txtBuscarProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarProductosMouseClicked
        // TODO add your handling code here:
        String texto = txtBuscarProductos.getText().trim();
        filtrarProducto(texto); 
        if (modeloPopupProd.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
        }
    }//GEN-LAST:event_txtBuscarProductosMouseClicked

    private void txtBuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductosActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtBuscarProductosActionPerformed

    private void txtBuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosKeyReleased
        // TODO add your handling code here:
        String texto = txtBuscarProductos.getText().trim();
        if(texto.isEmpty()){ productoSeleccionado = null; }
        filtrarProducto(texto);
        if (modeloPopupProd.getRowCount() > 0) {
            popupProducto.show(txtBuscarProductos, 0, txtBuscarProductos.getHeight());
            txtBuscarProductos.requestFocus();
        } else {
            popupProducto.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarProductosKeyReleased

    private void txtBuscarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoMouseClicked
        // TODO add your handling code here:
        if(!txtBuscarEmpleado.isEnabled()) return;
        String texto = txtBuscarEmpleado.getText().trim();
        filtrarEmpleado(texto);
        if (modeloPopupEmp.getRowCount() > 0) {
            popupEmpleado.show(txtBuscarEmpleado, 0, txtBuscarEmpleado.getHeight());
        }
    }//GEN-LAST:event_txtBuscarEmpleadoMouseClicked

    private void txtBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpleadoActionPerformed

    private void txtBuscarEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyReleased
        // TODO add your handling code here:
        if(!txtBuscarEmpleado.isEnabled()) return;
        String texto = txtBuscarEmpleado.getText().trim();
        if(texto.isEmpty()){ empleadoSeleccionado = null; }
        filtrarEmpleado(texto);
        if (modeloPopupEmp.getRowCount() > 0) {
            popupEmpleado.show(txtBuscarEmpleado, 0, txtBuscarEmpleado.getHeight());
            txtBuscarEmpleado.requestFocus();
        } else {
            popupEmpleado.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarEmpleadoKeyReleased

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
        if(texto.isEmpty()){ clienteSeleccionado = null; }
        filtrarCliente(texto);
        if (modeloPopupCli.getRowCount() > 0) {
            popupCliente.show(txtBuscarCliente, 0, txtBuscarCliente.getHeight());
            txtBuscarCliente.requestFocus();
        } else {
            popupCliente.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscarClienteKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCarrito;
    private javax.swing.JButton btnEliminarCarrito;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaCarrito;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarEmpleado;
    private javax.swing.JTextField txtBuscarProductos;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables
}
