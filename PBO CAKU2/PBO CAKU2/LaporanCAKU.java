import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LaporanCAKU {
    private JFrame frame;
    private JTextField startDateField, endDateField;
    private JComboBox<String> categoryComboBox;
    private JButton printButton, pdfButton, closeButton;

    public LaporanCAKU() {
        // Membuat frame dan mengatur layout
        frame = new JFrame("LaporanCAKU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 400);

        // Membuat Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JLabel profileLabel = new JLabel("Profil");
        JMenu usernameMenu = new JMenu("Nama Pengguna");
        JMenuItem editProfileItem = new JMenuItem("Edit Profil");
        usernameMenu.add(editProfileItem);
        menuBar.add(profileLabel);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(usernameMenu);
        frame.setJMenuBar(menuBar);

        // Membuat Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1, 0, 1)); // Mengatur jarak antara tombol menjadi 1 piksel
        sidebar.setBackground(Color.DARK_GRAY);
        addSidebarButton(sidebar, "DASHBOARD");
        addSidebarButton(sidebar, "DATA TRANSAKSI");
        addSidebarButton(sidebar, "DATA PENGGUNA");
        addSidebarButton(sidebar, "LAPORAN");
        addSidebarButton(sidebar, "KELUAR");
        frame.add(sidebar, BorderLayout.WEST);

        // Membuat Panel untuk form
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("Mulai Tanggal:"));
        startDateField = new JTextField();
        formPanel.add(startDateField);
        formPanel.add(new JLabel("Sampai Tanggal:"));
        endDateField = new JTextField();
        formPanel.add(endDateField);
        formPanel.add(new JLabel("Kategori:"));
        categoryComboBox = new JComboBox<>(new String[] { "Pemasukan", "Pengeluaran" }); // Mengubah opsi kategori
        formPanel.add(categoryComboBox);
        frame.add(formPanel, BorderLayout.CENTER);

        // Membuat Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        printButton = new JButton("Print");
        buttonPanel.add(printButton);
        pdfButton = new JButton("Cetak PDF");
        buttonPanel.add(pdfButton);
        closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> frame.dispose());
        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Menampilkan frame
        frame.setVisible(true);
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
        SwingUtilities.invokeLater(LaporanCAKU::new);
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}
