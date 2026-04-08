package gui;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import dao.UsuarioDAO;

public class LoginModerno extends javax.swing.JFrame {
    
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JButton btnIniciar;
    private JLabel lblCrear;
    private JPanel backgroundPanel;
    private JPanel pnlCard;
    
    private boolean animacionTerminada = false;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginModerno.class.getName());

    public LoginModerno() {
       setTitle("Sistema Apolo - Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Colores base
        Color brandDarkBlue = Color.decode("#00384E");
        Color brandLight = Color.WHITE;
        Color hoverBlue = Color.decode("#005475"); 

        // 1. PANEL DE FONDO (Layout Nulo para animaciones a 60fps)
        backgroundPanel = new JPanel(null); 
        backgroundPanel.setBackground(brandDarkBlue);

        // 2. LA TARJETA FLOTANTE
        pnlCard = new JPanel();
        pnlCard.setLayout(null); 
        pnlCard.setBackground(brandLight);
        // Tamaño fijo de la tarjeta
        pnlCard.setSize(420, 550);
        pnlCard.putClientProperty("FlatLaf.style", "arc: 30");

        // --- CONTENIDO DE LA TARJETA ---
        JLabel lblLogo = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/recursos/apolo - Editado (1).png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            lblLogo.setText("APOLO");
            lblLogo.setFont(new Font("Arial Black", Font.BOLD, 30));
            lblLogo.setForeground(brandDarkBlue);
        }
        lblLogo.setBounds(150, 40, 120, 120);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCard.add(lblLogo);

        JLabel lblBienvenida = new JLabel("Bienvenido de nuevo");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBienvenida.setForeground(brandDarkBlue);
        lblBienvenida.setBounds(0, 180, 420, 30);
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCard.add(lblBienvenida);

        JLabel lblSub = new JLabel("Ingresa tus credenciales para continuar");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);
        lblSub.setBounds(0, 215, 420, 20);
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCard.add(lblSub);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsuario.setBounds(40, 270, 340, 45);
        txtUsuario.putClientProperty("JTextField.placeholderText", "Usuario");
        txtUsuario.putClientProperty("JComponent.roundRect", true);
        txtUsuario.putClientProperty("JTextField.padding", new Insets(5, 10, 5, 10));
        txtUsuario.putClientProperty("JComponent.focusPainted", true);
        pnlCard.add(txtUsuario);

        txtPass = new JPasswordField();
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass.setBounds(40, 335, 340, 45);
        txtPass.putClientProperty("JTextField.placeholderText", "Contraseña");
        txtPass.putClientProperty("PasswordField.showRevealButton", true);
        txtPass.putClientProperty("JComponent.roundRect", true);
        txtPass.putClientProperty("JTextField.padding", new Insets(5, 10, 5, 10));
        txtPass.putClientProperty("JComponent.focusPainted", true);
        pnlCard.add(txtPass);

        btnIniciar = new JButton("Iniciar Sesión");
        btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBackground(brandDarkBlue);
        btnIniciar.setBounds(40, 410, 340, 45);
        btnIniciar.putClientProperty("JButton.buttonType", "roundRect");
        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btnIniciar.setBackground(hoverBlue); }
            @Override
            public void mouseExited(MouseEvent e) { btnIniciar.setBackground(brandDarkBlue); }
        });
        
        btnIniciar.addActionListener(e -> procesarLogin());
        
        pnlCard.add(btnIniciar);

        lblCrear = new JLabel("¿No tienes cuenta? Crea una aquí");
        lblCrear.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCrear.setForeground(brandDarkBlue);
        lblCrear.setBounds(0, 475, 420, 20);
        lblCrear.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        lblCrear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { lblCrear.setForeground(Color.decode("#00A2E8")); }
            @Override
            public void mouseExited(MouseEvent e) { lblCrear.setForeground(brandDarkBlue); }
            @Override
            public void mouseClicked(MouseEvent e) {
                Registro crearCuenta = new Registro();
                crearCuenta.setVisible(true);
                dispose(); // Cierra esta ventana de login
            }
            
        });

        pnlCard.add(lblCrear);

        // Agregamos la tarjeta al fondo
        backgroundPanel.add(pnlCard);
        setContentPane(backgroundPanel);
        UIManager.put("Component.focusColor", brandDarkBlue);

        // --- PREPARACIÓN DE LA ANIMACIÓN ---
        // Calculamos posiciones exactas basadas en la resolución de tu pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int xCentro = (pantalla.width - pnlCard.getWidth()) / 2;
        int yFinal = (pantalla.height - pnlCard.getHeight()) / 2;
        int yInicio = pantalla.height; // Empieza oculta hasta abajo de la pantalla

        // Colocamos la tarjeta en la posición de inicio
        pnlCard.setLocation(xCentro, yInicio);

        // Sensor para auto-centrar si cambias el tamaño de la ventana
        backgroundPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (animacionTerminada) {
                    pnlCard.setLocation((backgroundPanel.getWidth() - pnlCard.getWidth()) / 2, 
                                        (backgroundPanel.getHeight() - pnlCard.getHeight()) / 2);
                }
            }
        });

        // Lanzamos el motor de animación
        iniciarAnimacionFluida(xCentro, yFinal, yInicio);
    }

    private void iniciarAnimacionFluida(int xCentro, int yDestino, int yInicio) {
        Timer timer = new Timer(10, new ActionListener() {
            int currentY = yInicio;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Matemática para un frenado súper suave
                int velocidad = Math.max(2, (currentY - yDestino) / 12); 
                currentY -= velocidad; 
                
                if (currentY <= yDestino) {
                    currentY = yDestino;
                    animacionTerminada = true;
                    ((Timer) e.getSource()).stop(); // Apagamos el motor
                }
                
                // Movimiento absoluto directo (No se traba)
                pnlCard.setLocation(xCentro, currentY);
            }
        });
        
        timer.setInitialDelay(500); // Espera medio segundo y entra a escena
        timer.start();
    }
    
    private void procesarLogin() {
        String user = txtUsuario.getText();
        String pass = new String(txtPass.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Por favor, ingresa tu usuario y contraseña.", 
                "Campos vacíos", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Llamamos a tu clase DAO
        dao.UsuarioDAO dao = new dao.UsuarioDAO();
        
        if (dao.validarLogin(user, pass)) {
            // Entró con éxito, abrimos el Menú Principal
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose(); // Destruimos el login
        } else {
            // Falló la validación
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Credenciales incorrectas o usuario dado de baja.", 
                "Error de Login", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
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
        java.awt.EventQueue.invokeLater(() -> new LoginModerno().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
