import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.File;

public class RegistrationForm extends JFrame {
    final private Font mainFont = new Font("Segoe UI", Font.PLAIN, 18);
    JTextField tfName, tfUsername, tfEmail, tfPhone;
    JPasswordField pfPassword;

    public RegistrationForm() {
        initialize();
    }

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

        JLabel lbRegistrationForm = new JLabel("CAKU - Registration Form", SwingConstants.CENTER);
        lbRegistrationForm.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbRegistrationForm.setForeground(Color.BLACK);
        logoPanel.add(lbRegistrationForm, BorderLayout.SOUTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(35, 97, 143));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel lbName = new JLabel("Name");
        lbName.setFont(mainFont);
        lbName.setForeground(Color.WHITE);

        tfName = new JTextField();
        tfName.setFont(mainFont);

        JLabel lbUsername = new JLabel("Username");
        lbUsername.setFont(mainFont);
        lbUsername.setForeground(Color.WHITE);

        tfUsername = new JTextField();
        tfUsername.setFont(mainFont);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);
        lbEmail.setForeground(Color.WHITE);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPhone = new JLabel("Phone");
        lbPhone.setFont(mainFont);
        lbPhone.setForeground(Color.WHITE);

        tfPhone = new JTextField();
        tfPhone.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);
        lbPassword.setForeground(Color.WHITE);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        // Center position calculations
        int formWidth = 400;
        int centerX = (formWidth + 850) / 2;

        // Add components to the form panel
        int yPosition = 1;
        int spacing = 50;

        lbName.setBounds(centerX, yPosition, 100, 30);
        formPanel.add(lbName);

        tfName.setBounds(centerX + 110, yPosition, 190, 30);
        formPanel.add(tfName);

        yPosition += spacing;
        lbUsername.setBounds(centerX, yPosition, 100, 30);
        formPanel.add(lbUsername);

        tfUsername.setBounds(centerX + 110, yPosition, 190, 30);
        formPanel.add(tfUsername);

        yPosition += spacing;
        lbEmail.setBounds(centerX, yPosition, 100, 30);
        formPanel.add(lbEmail);

        tfEmail.setBounds(centerX + 110, yPosition, 190, 30);
        formPanel.add(tfEmail);

        yPosition += spacing;
        lbPhone.setBounds(centerX, yPosition, 150, 30);
        formPanel.add(lbPhone);

        tfPhone.setBounds(centerX + 110, yPosition, 190, 30);
        formPanel.add(tfPhone);

        yPosition += spacing;
        lbPassword.setBounds(centerX, yPosition, 100, 30);
        formPanel.add(lbPassword);

        pfPassword.setBounds(centerX + 110, yPosition, 190, 30);
        formPanel.add(pfPassword);

        yPosition += spacing;

        // Create the buttons and position them
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(mainFont);
        btnRegister.setBackground(new Color(244, 122, 32));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBounds(centerX, yPosition, 300, 40);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String username = tfUsername.getText();
                String email = tfEmail.getText();
                String phone = tfPhone.getText();
                String password = String.valueOf(pfPassword.getPassword());

                if (registerUser(name, username, email, phone, password)) {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            "Registration Successful",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            "Registration Failed",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(btnRegister);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.setBounds(centerX, yPosition + 50, 300, 40);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        formPanel.add(btnCancel);

        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        setTitle("Registration Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    private boolean registerUser(String name, String username, String email, String phone, String password) {
        final String DB_URL = "jdbc:mysql://localhost:3306/login";
        final String DB_USERNAME = "root";
        final String DB_PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "INSERT INTO users (name, username, email, phone, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, password);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setVisible(true);
    }
}
