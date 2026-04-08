package gui;

import dao.UsuarioDAO;
import entity.Usuario;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Registro extends javax.swing.JFrame {
    
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JButton btnCrear;
    private JLabel lblIniciar;
    private JPanel backgroundPanel;
    private JPanel pnlCard;
    
    private boolean animacionTerminada = false;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Registro.class.getName());

    public Registro() {
        setTitle("Sistema Apolo - Crear Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        
        getRootPane().putClientProperty("JRootPane.titleBarBackground", Color.decode("#1A1A1A"));
        getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
        
        //Cargamos el logo de la barra superior
        try {
            java.awt.Image icon = java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/LogoBarra.png"));
            this.setIconImage(icon);
        } catch (Exception e) {
            System.out.println("No se encontró el logo: " + e.getMessage());
        }

        // Colores base de la marca
        Color brandDarkBlue = Color.decode("#00384E");
        Color brandLight = Color.WHITE;
        Color hoverBlue = Color.decode("#005475"); 

        // 1. PANEL DE FONDO (Layout Nulo para 60fps)
        backgroundPanel = new JPanel(null); 
        backgroundPanel.setBackground(brandDarkBlue);

        // 2. LA TARJETA FLOTANTE (Más alta para los 4 campos)
        pnlCard = new JPanel();
        pnlCard.setLayout(null); 
        pnlCard.setBackground(brandLight);
        pnlCard.setSize(420, 680); // Ajustado para que quepa todo perfecto
        pnlCard.putClientProperty("FlatLaf.style", "arc: 30");

        // --- CONTENIDO DE LA TARJETA ---
        
        // Logo
        JLabel lblLogo = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/recursos/apolo - Editado (1).png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            lblLogo.setText("APOLO");
            lblLogo.setFont(new Font("Arial Black", Font.BOLD, 26));
            lblLogo.setForeground(brandDarkBlue);
        }
        lblLogo.setBounds(160, 30, 100, 100);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCard.add(lblLogo);

        // Textos
        JLabel lblTitulo = new JLabel("Crea tu cuenta");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(brandDarkBlue);
        lblTitulo.setBounds(0, 140, 420, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCard.add(lblTitulo);

        JLabel lblSub = new JLabel("Únete al Sistema Apolo para continuar");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);
        lblSub.setBounds(0, 175, 420, 20);
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCard.add(lblSub);

        // Campo: Nombre
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtNombre.setBounds(40, 220, 340, 45);
        txtNombre.putClientProperty("JTextField.placeholderText", "Tu nombre");
        txtNombre.putClientProperty("JComponent.roundRect", true);
        txtNombre.putClientProperty("JTextField.padding", new Insets(5, 10, 5, 10));
        txtNombre.putClientProperty("JComponent.focusPainted", true);
        pnlCard.add(txtNombre);

        // Campo: Apellido
        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtApellido.setBounds(40, 285, 340, 45);
        txtApellido.putClientProperty("JTextField.placeholderText", "Tu apellido");
        txtApellido.putClientProperty("JComponent.roundRect", true);
        txtApellido.putClientProperty("JTextField.padding", new Insets(5, 10, 5, 10));
        txtApellido.putClientProperty("JComponent.focusPainted", true);
        pnlCard.add(txtApellido);

        // Campo: Usuario
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsuario.setBounds(40, 350, 340, 45);
        txtUsuario.putClientProperty("JTextField.placeholderText", "Tu usuario");
        txtUsuario.putClientProperty("JComponent.roundRect", true);
        txtUsuario.putClientProperty("JTextField.padding", new Insets(5, 10, 5, 10));
        txtUsuario.putClientProperty("JComponent.focusPainted", true);
        pnlCard.add(txtUsuario);

        // Campo: Contraseña
        txtPass = new JPasswordField();
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass.setBounds(40, 415, 340, 45);
        txtPass.putClientProperty("JTextField.placeholderText", "Tu contraseña");
        txtPass.putClientProperty("PasswordField.showRevealButton", true);
        txtPass.putClientProperty("JComponent.roundRect", true);
        txtPass.putClientProperty("JTextField.padding", new Insets(5, 10, 5, 10));
        txtPass.putClientProperty("JComponent.focusPainted", true);
        pnlCard.add(txtPass);

        // Botón: Crear Cuenta
        btnCrear = new JButton("Crear Cuenta");
        btnCrear.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setBackground(brandDarkBlue);
        btnCrear.setBounds(40, 490, 340, 45);
        btnCrear.putClientProperty("JButton.buttonType", "roundRect");
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Animación Hover y Acción de Base de Datos
        btnCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btnCrear.setBackground(hoverBlue); }
            @Override
            public void mouseExited(MouseEvent e) { btnCrear.setBackground(brandDarkBlue); }
        });
        btnCrear.addActionListener(e -> procesarRegistro()); // Conectado a la BD
        pnlCard.add(btnCrear);

        // Enlace: Iniciar Sesión
        lblIniciar = new JLabel("¿Ya tienes cuenta? Inicia sesión aquí");
        lblIniciar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblIniciar.setForeground(brandDarkBlue);
        lblIniciar.setBounds(0, 555, 420, 20);
        lblIniciar.setHorizontalAlignment(SwingConstants.CENTER);
        lblIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Animación Hover y Cambio de Pantalla
        lblIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { lblIniciar.setForeground(Color.decode("#00A2E8")); }
            @Override
            public void mouseExited(MouseEvent e) { lblIniciar.setForeground(brandDarkBlue); }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Te manda de regreso al LoginAnimado moderno
                LoginModerno login = new LoginModerno();
                login.setVisible(true);
                dispose(); 
            }
        });
        pnlCard.add(lblIniciar);

        // Agregamos la tarjeta al fondo
        backgroundPanel.add(pnlCard);
        setContentPane(backgroundPanel);
        UIManager.put("Component.focusColor", brandDarkBlue);

        // --- PREPARACIÓN DE LA ANIMACIÓN FLUIDA ---
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int xCentro = (pantalla.width - pnlCard.getWidth()) / 2;
        int yFinal = (pantalla.height - pnlCard.getHeight()) / 2;
        int yInicio = pantalla.height; // Empieza oculta hasta abajo

        pnlCard.setLocation(xCentro, yInicio);

        // Sensor de Auto-centrado
        backgroundPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (animacionTerminada) {
                    pnlCard.setLocation((backgroundPanel.getWidth() - pnlCard.getWidth()) / 2, 
                                        (backgroundPanel.getHeight() - pnlCard.getHeight()) / 2);
                }
            }
        });

        iniciarAnimacionFluida(xCentro, yFinal, yInicio);
    }

    // El motor de animación súper suave
    private void iniciarAnimacionFluida(int xCentro, int yDestino, int yInicio) {
        Timer timer = new Timer(10, new ActionListener() {
            int currentY = yInicio;

            @Override
            public void actionPerformed(ActionEvent e) {
                int velocidad = Math.max(2, (currentY - yDestino) / 12); 
                currentY -= velocidad; 
                
                if (currentY <= yDestino) {
                    currentY = yDestino;
                    animacionTerminada = true;
                    ((Timer) e.getSource()).stop();
                }
                pnlCard.setLocation(xCentro, currentY);
            }
        });
        timer.setInitialDelay(500); 
        timer.start();
    }

    // --- LÓGICA DE BASE DE DATOS EXACTA A LA TUYA ---
    private void procesarRegistro() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String user = txtUsuario.getText();
        String pass = new String(txtPass.getPassword()); 

        if (nombre.isEmpty() || apellido.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();

        if (dao.existeUsuario(user)) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario '" + user + "' ya está en uso. Por favor, elige otro.", "Usuario duplicado", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre_empleado(nombre);
        nuevoUsuario.setApellido_empleado(apellido);
        nuevoUsuario.setUser(user);
        nuevoUsuario.setPass(pass);

        dao.insertUsuario(nuevoUsuario);

        txtNombre.setText("");
        txtApellido.setText("");
        txtUsuario.setText("");
        txtPass.setText("");
        
        // Opcional: Mandarlo de regreso al login automáticamente tras registro exitoso
        JOptionPane.showMessageDialog(this, "¡Cuenta creada con éxito! Inicia sesión ahora.");
        LoginModerno login = new LoginModerno();
        login.setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            UIManager.put("PasswordField.showRevealButton", true);
        } catch (Exception ex) {
            System.err.println("Falló al inicializar FlatLaf");
        }
        java.awt.EventQueue.invokeLater(() -> new Registro().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
