package vistas;

import gui.MenuPrincipal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PanelG extends javax.swing.JPanel {
    
    // Componentes del Menú
    private JButton btnGclientes;
    private JButton btnGproductos;
    private JButton btnGventas;
    private JButton btnInventario;
    private JButton btnGusuarios;
    private JButton btnRegresar;

    // Colores base de la marca
    private final Color brandDarkBlue = Color.decode("#00384E");
    private final Color brandLight = Color.WHITE;
    private final Color hoverBlue = Color.decode("#005475");

    public PanelG() {
        initComponentsPremium();
    }
    
    // =========================================================
    // 1. EL REINICIO VISUAL FORZADO (Mata el efecto fantasma)
    // =========================================================
    private void activarBoton(JButton botonActivo) {
        // Metemos todos los botones de navegación en un arreglo
        JButton[] botones = {btnGclientes, btnGproductos, btnGventas, btnInventario, btnGusuarios};
        
        for (JButton b : botones) {
            b.setEnabled(true);
            // FORZAMOS EL REINICIO VISUAL a la posición 25 original
            b.putClientProperty("JTextField.padding", new Insets(10, 25, 10, 20)); 
            b.setBorderPainted(false);
            b.setContentAreaFilled(false);
        }

        // Apagamos SOLO el botón presionado
        botonActivo.setEnabled(false);
    }

    private void initComponentsPremium() {
        setLayout(new GridBagLayout()); 
        setBackground(brandDarkBlue);
        setPreferredSize(new Dimension(400, 1040));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.weightx = 1.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        // =========================================================
        // 1. HEADER: TÍTULO DE GESTIONES
        // =========================================================
        JPanel pnlHeader = new JPanel(new GridBagLayout());
        pnlHeader.setBackground(brandDarkBlue);
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.anchor = GridBagConstraints.CENTER;

        JLabel lblTitulo = new JLabel("GESTIONES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42)); // Título monumental
        lblTitulo.setForeground(brandLight);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbcHeader.insets = new Insets(60, 0, 10, 0); // Margen superior
        pnlHeader.add(lblTitulo, gbcHeader);

        JLabel lblSub = new JLabel("MÓDULOS DEL SISTEMA");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(255, 255, 255, 180));
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        gbcHeader.insets = new Insets(0, 0, 40, 0); 
        pnlHeader.add(lblSub, gbcHeader);

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(pnlHeader, gbc);

        // =========================================================
        // 2. LISTA DE BOTONES DE GESTIÓN
        // =========================================================
        JPanel pnlMenu = new JPanel(new GridBagLayout());
        pnlMenu.setBackground(brandDarkBlue);
        GridBagConstraints gbcMenu = new GridBagConstraints();
        gbcMenu.gridx = 0;
        gbcMenu.weightx = 1.0;
        gbcMenu.fill = GridBagConstraints.HORIZONTAL;

        // --- BOTONES ---
        btnGclientes = crearBotonMenu("Gestión de Clientes", "/recursos/icon_clientes.png");
        btnGclientes.addActionListener(this::btnGclientesActionPerformed);
        gbcMenu.insets = new Insets(0, 20, 10, 20);
        gbcMenu.gridy = 0;
        pnlMenu.add(btnGclientes, gbcMenu);

        btnGproductos = crearBotonMenu("Gestión de Productos", "/recursos/icon_productos.png");
        btnGproductos.addActionListener(this::btnGproductosActionPerformed);
        gbcMenu.gridy = 1;
        pnlMenu.add(btnGproductos, gbcMenu);

        btnGventas = crearBotonMenu("Gestión de Ventas", "/recursos/icon_ventas.png");
        btnGventas.addActionListener(this::btnGventasActionPerformed);
        gbcMenu.gridy = 2;
        pnlMenu.add(btnGventas, gbcMenu);

        btnInventario = crearBotonMenu("Inventario", "/recursos/icon_inventario.png");
        btnInventario.addActionListener(this::btnInventarioActionPerformed);
        gbcMenu.gridy = 3;
        pnlMenu.add(btnInventario, gbcMenu);

        btnGusuarios = crearBotonMenu("Gestión de Usuarios", "/recursos/icon_usuarios.png");
        btnGusuarios.addActionListener(this::btnGusuariosActionPerformed);
        gbcMenu.gridy = 4;
        pnlMenu.add(btnGusuarios, gbcMenu);

        gbc.gridy = 1;
        add(pnlMenu, gbc);

        // =========================================================
        // 3. ESPACIADOR (Empuja el botón regresar al fondo)
        // =========================================================
        gbc.gridy = 2;
        gbc.weighty = 1.0; 
        add(javax.swing.Box.createVerticalGlue(), gbc);

        // =========================================================
        // 4. BOTÓN REGRESAR AL MENÚ PRINCIPAL
        // =========================================================
        btnRegresar = crearBotonMenu("Regresar al Menú", "/recursos/icon_back.png");
        btnRegresar.setForeground(new Color(255, 200, 100)); // Naranja/dorado
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        
        gbc.gridy = 3;
        gbc.weighty = 0.0;
        // Botón subido a 100px para que no pegue abajo
        gbc.insets = new Insets(0, 20, 100, 20); 
        add(btnRegresar, gbc);
    }

    // =========================================================
    // 2. EL MOTOR DE ANIMACIÓN BLINDADO
    // =========================================================
    private JButton crearBotonMenu(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(rutaIcono));
            Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {}

        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        boton.setForeground(brandLight);
        boton.setHorizontalAlignment(SwingConstants.LEADING); 
        boton.setHorizontalTextPosition(SwingConstants.RIGHT); 
        boton.setIconTextGap(20); 

        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.putClientProperty("JButton.buttonType", "roundRect");
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Posición original de inicio (25px a la izquierda)
        boton.putClientProperty("JTextField.padding", new Insets(10, 25, 10, 20));

        // Animación "Bulletproof" (A prueba de movimientos rápidos)
        boton.addMouseListener(new MouseAdapter() {
            Timer animTimer;
            int paddingIzq = 25;
            boolean isHovered = false;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!boton.isEnabled()) return; // Si está apagado, lo ignoramos por completo
                
                isHovered = true;
                boton.setBorderPainted(true);
                boton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, hoverBlue)); 
                boton.setContentAreaFilled(true);
                boton.setBackground(new Color(255, 255, 255, 15)); 
                iniciarAnimacion();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!boton.isEnabled()) return; // Si está apagado, lo ignoramos
                
                isHovered = false;
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
                iniciarAnimacion();
            }

            private void iniciarAnimacion() {
                if (animTimer != null && animTimer.isRunning()) return; // Previene choques de timers
                
                animTimer = new Timer(15, ae -> {
                    if (isHovered && paddingIzq < 45) { // Va hacia la derecha
                        paddingIzq += 2;
                    } else if (!isHovered && paddingIzq > 25) { // Regresa hacia la izquierda
                        paddingIzq -= 2;
                    } else {
                        animTimer.stop(); // Llegó a su destino
                    }
                    boton.putClientProperty("JTextField.padding", new Insets(10, paddingIzq, 10, 20));
                    boton.revalidate();
                });
                animTimer.start();
            }
        });

        return boton;
    }

    // =========================================================
    // TU LÓGICA DE EVENTOS (Intacta)
    // =========================================================

    private void btnGclientesActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            GestionClientes gC = new GestionClientes();
            menuBase.mostrarContenidoP(gC);
            activarBoton(btnGclientes);
        }
    }                                             

    private void btnGproductosActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            GestionProductos gP = new GestionProductos();
            menuBase.mostrarContenidoP(gP);
            activarBoton(btnGproductos);
        }
    }                                             

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            Inventario gI = new Inventario();
            menuBase.mostrarContenidoP(gI);
            activarBoton(btnInventario);
        }
    }                                             

    private void btnGusuariosActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            GestionUsuarios gU = new GestionUsuarios();
            menuBase.mostrarContenidoP(gU);
            activarBoton(btnGusuarios);
        }
    }                                             

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            
            PanelMPrincipal menuInicio = new PanelMPrincipal();
            menuBase.cambiarMenuIzquierdo(menuInicio); 
            
            PanelBienvenida bienvenida = new PanelBienvenida();
            menuBase.mostrarContenidoP(bienvenida);
        }
    }                                            

    private void btnGventasActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof MenuPrincipal) {
            MenuPrincipal menuBase = (MenuPrincipal) window;
            GestionVentas gV = new GestionVentas();
            menuBase.mostrarContenidoP(gV);
            activarBoton(btnGventas);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
