import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.File;

public class LoginForm extends JFrame {
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private JTextField tfEmail;
    private JPasswordField pfPassword;

    public void initialize() {
        // Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel();
        String imagePath = "PBO CAKU2/lib/1231.png";
        File imageFile = new File(imagePath);
        System.out.println("Image path: " + imageFile.getAbsolutePath());
        ImageIcon logo = new ImageIcon(imageFile.getAbsolutePath());

        if (logo.getIconWidth() == -1) {
            System.out.println("Image not found at path: " + imageFile.getAbsolutePath());
        } else {
            logoLabel.setIcon(logo);
        }
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        JLabel lbLoginForm = new JLabel("CAKU", SwingConstants.CENTER);
        lbLoginForm.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbLoginForm.setForeground(Color.BLACK);
        logoPanel.add(lbLoginForm, BorderLayout.SOUTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(35, 97, 143));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);
        lbEmail.setForeground(Color.WHITE);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);
        lbPassword.setForeground(Color.WHITE);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        // Center position calculations
        int formWidth = 400;
        int centerX = (formWidth + 850) / 2;

        // Add components to the form panel
        lbEmail.setBounds(centerX, 30, 100, 30);
        formPanel.add(lbEmail);

        tfEmail.setBounds(centerX + 110, 30, 190, 30);
        formPanel.add(tfEmail);

        lbPassword.setBounds(centerX, 80, 100, 30);
        formPanel.add(lbPassword);

        pfPassword.setBounds(centerX + 110, 80, 190, 30);
        formPanel.add(pfPassword);

        // Create the buttons and position them
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.setBackground(new Color(244, 122, 32));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBounds(centerX, 130, 300, 40);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                User user = getAuthenticatedUser(email, password);

                if (user != null) {
                    FinancialDashboard dashboard = new FinancialDashboard();
                    dashboard.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(btnLogin);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.setBounds(centerX, 180, 300, 40);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        formPanel.add(btnCancel);

        JLabel forgotPasswordLabel = new JLabel("Lupa Password?");
        forgotPasswordLabel.setForeground(Color.WHITE);
        forgotPasswordLabel.setBounds(centerX, 230, 300, 25);
        formPanel.add(forgotPasswordLabel);

        JLabel registrationLabel = new JLabel("Tidak punya Akun?");
        registrationLabel.setForeground(Color.WHITE);
        registrationLabel.setBounds(centerX, 260, 300, 25);
        formPanel.add(registrationLabel);

        JLabel registrationLink = new JLabel("Buat disini!");
        registrationLink.setForeground(Color.WHITE);
        registrationLink.setBounds(centerX, 290, 300, 25);
        registrationLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registrationLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
            }
        });
        formPanel.add(registrationLink);

        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/login";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM users WHERE email =? AND password =?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    }
}
