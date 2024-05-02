import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginInterface extends JFrame {
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel forgotPasswordLabel;
    private JLabel createAccountLabel;

    public LoginInterface() {
        // Mengatur layout frame menggunakan BorderLayout
        setLayout(new BorderLayout());

        // Membuat dan mengatur logo
        ImageIcon logoIcon = new ImageIcon("1231.png"); // Ganti dengan path ke logo Anda
        JLabel logoLabel = new JLabel(logoIcon);

        // Creating a panel for the header and adding a logo
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(logoLabel);

        // Creating a panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Adding padding

        // Creating and setting up the username field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        // Creating and setting up the password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // Creating and setting up the login button
        loginButton = new JButton("Login");

        // Creating and setting up the "Forgot Password?" label
        forgotPasswordLabel = new JLabel("Lupa Password?");

        // Membuat dan mengatur label "Tidak punya akun? Buat disini!"
        createAccountLabel = new JLabel("Tidak punya akun? Buat disini!");

        // Menambahkan komponen ke panel login
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi antara field
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spasi antara field
        loginPanel.add(loginButton);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spasi antara button dan label
        loginPanel.add(forgotPasswordLabel);
        loginPanel.add(createAccountLabel);

        // Menambahkan panel header dan login ke frame
        add(headerPanel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);

        // Menambahkan action listener untuk tombol login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logika untuk proses login
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                // Validasi username dan password
                if (isValid(username, password)) {
                    // Tambahkan logika untuk login yang berhasil
                    JOptionPane.showMessageDialog(LoginInterface.this, "Login berhasil!");
                    dispose(); // Tutup jendela login
                    // Beralih ke dashboard setelah menutup jendela login
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new FinancialDashboard().setVisible(true);
                        }
                    });
                } else {
                    // Tambahkan logika untuk login yang gagal
                    JOptionPane.showMessageDialog(LoginInterface.this, "Username atau password salah!");
                }
            }
        });

        // Pengaturan frame
        pack(); // Agar ukuran frame menyesuaikan dengan komponen di dalamnya
        setLocationRelativeTo(null); // Agar frame muncul di tengah layar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Keluar aplikasi saat window ditutup
        setVisible(true); // Menampilkan frame
    }

    // Metode untuk melakukan validasi login
    private boolean isValid(String username, char[] password) {
        // Mengubah array karakter password menjadi string
        String inputPassword = new String(password);
        // Memeriksa apakah username dan password sesuai
        return "1".equals(username) && "1".equals(inputPassword);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new LoginInterface();
    }
}
