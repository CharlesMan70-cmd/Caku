import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FinancialDashboard extends JFrame {
    private FinancialTransactionApp transactionAppInstance; // Variabel untuk menyimpan instance FinancialTransactionApp
    private DataPenggunaForm dataPenggunaFormInstance; // Variabel untuk menyimpan instance DataPenggunaForm
    private LaporanCAKU laporanCAKUInstance; // Variabel untuk menyimpan instance LaporanCAKU

    public FinancialDashboard() {
        // Set up the frame
        setTitle("CARU Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1)); // Mengubah ke 5 untuk menghapus "TENTANG"
        sidebar.setBackground(Color.DARK_GRAY);
        addSidebarButton(sidebar, "DASHBOARD");
        addSidebarButton(sidebar, "DATA TRANSAKSI");
        addSidebarButton(sidebar, "DATA PENGGUNA");
        addSidebarButton(sidebar, "LAPORAN");
        addSidebarButton(sidebar, "KELUAR");

        // Create content area
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(new Color(220, 220, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(100, 100, 100, 100); // Padding antar komponen
        content.setLayout(new GridLayout(2, 4, 100, 100));
        content.setBackground(new Color(220, 220, 220));
        addContentBox(content, "Pemasukan Hari Ini", "Rp. 0,-", new Color(100, 100, 255));
        addContentBox(content, "Pemasukan Bulan Ini", "Rp. 5.000.000,-", new Color(100, 255, 100));
        addContentBox(content, "Pemasukan Tahun Ini", "Rp. 0,-", new Color(255, 100, 100));
        addContentBox(content, "Seluruh Pemasukan", "Rp. 5.000.000,-", new Color(30, 30, 30));
        addContentBox(content, "Pengeluaran Hari Ini", "Rp. 0,-", new Color(100, 100, 255));
        addContentBox(content, "Pengeluaran Bulan Ini", "Rp. 5.000.000,-", new Color(100, 255, 100));
        addContentBox(content, "Pengeluaran Tahun Ini", "Rp. 0,-", new Color(255, 100, 100));
        addContentBox(content, "Seluruh Pengeluaran", "Rp. 5.000.000,-", new Color(30, 30, 30));
        // Add more boxes as needed...

        // Add components to frame
        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);
    }

    private void addSidebarButton(JPanel sidebar, String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (text.equals("DASHBOARD")) {
                    // Tidak lakukan apa-apa karena sudah berada di dashboard
                } else if (text.equals("DATA TRANSAKSI")) {
                    if (transactionAppInstance == null) { // Periksa apakah instance belum ada
                        transactionAppInstance = new FinancialTransactionApp();
                        transactionAppInstance.setVisible(true); // Tampilkan instance
                    } else {
                        transactionAppInstance.setVisible(true); // Jika sudah ada instance, tampilkan yang sudah ada
                    }
                } else if (text.equals("DATA PENGGUNA")) {
                    if (dataPenggunaFormInstance == null) { // Periksa apakah instance belum ada
                        dataPenggunaFormInstance = new DataPenggunaForm();
                        dataPenggunaFormInstance.setVisible(true);
                    } else {
                        dataPenggunaFormInstance.setVisible(true); // Jika sudah ada instance, tampilkan yang sudah ada
                    }
                } else if (text.equals("LAPORAN")) {
                    if (laporanCAKUInstance == null) { // Periksa apakah instance belum ada
                        laporanCAKUInstance = new LaporanCAKU();
                        laporanCAKUInstance.setVisible(true);
                    } else {
                        laporanCAKUInstance.setVisible(true); // Jika sudah ada instance, tampilkan yang sudah ada
                    }
                } else if (text.equals("KELUAR")) {
                    dispose(); // Tutup frame FinancialDashboard
                }
            }
        });
        sidebar.add(button);
    }

    private void addContentBox(JPanel content, String title, String amount, Color color) {
        JPanel box = new JPanel();
        box.setBackground(color);
        box.add(new JLabel(title));
        box.add(new JLabel(amount));
        content.add(box);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinancialDashboard dashboard = new FinancialDashboard();
            dashboard.setVisible(true);
        });
    }
}
