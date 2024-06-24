import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DataPenggunaForm extends JFrame {
    private JTextField usernameField, passwordField;
    private JButton saveButton, closeButton;

    public DataPenggunaForm() {
        // Membuat frame
        super("Data Pengguna");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(300, 200);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1, 0, 1)); // Mengatur jarak antara tombol menjadi 1 piksel
        sidebar.setBackground(Color.DARK_GRAY);
        addSidebarButton(sidebar, "DASHBOARD");
        addSidebarButton(sidebar, "DATA TRANSAKSI");
        addSidebarButton(sidebar, "DATA PENGGUNA");
        addSidebarButton(sidebar, "LAPORAN");
        addSidebarButton(sidebar, "KELUAR");

        add(sidebar, BorderLayout.WEST);

        // Panel untuk form
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        // Username
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        // Password
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> saveUser());
        buttonPanel.add(saveButton);

        closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveUser() {
        // Logika untuk menyimpan data pengguna
        System.out.println("Data pengguna disimpan.");
    }

    private void addSidebarButton(JPanel sidebar, String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10)); // Mengatur padding tombol menjadi 1 piksel
        sidebar.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataPenggunaForm::new);
    }
}
