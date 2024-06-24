import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Laporan extends JFrame {

    public Laporan() {
        // Set up the frame
        setTitle("Laporan");
        setSize(800, 600);
        setDefaultCloseOperation(HIDE_ON_CLOSE); // To hide the window when closed
        setLayout(new BorderLayout());

        // Create the sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.setBackground(Color.DARK_GRAY);

        String[] sidebarItems = {"DASHBOARD", "DATA TRANSAKSI", "DATA PENGGUNA", "LAPORAN", "KELUAR"};
        for (String item : sidebarItems) {
            JButton button = new JButton(item);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(45, 45, 45));
            button.setFocusPainted(false);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            button.setBorderPainted(false);
            button.setHorizontalAlignment(SwingConstants.LEFT);
        }

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the report panel
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));

        // Create the filters panel
        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new GridLayout(2, 4, 10, 10));
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        filtersPanel.add(new JLabel("Mulai Tanggal"));
        filtersPanel.add(new JTextField());
        filtersPanel.add(new JLabel("Sampai Tanggal"));
        filtersPanel.add(new JTextField());
        filtersPanel.add(new JLabel("Kategori"));
        filtersPanel.add(new JTextField());

        reportPanel.add(filtersPanel);

        // Create the report summary panel
        JPanel summaryPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        summaryPanel.add(new JLabel("Dari Tanggal:"));
        summaryPanel.add(new JLabel("2024/03/01"));
        summaryPanel.add(new JLabel("Sampai Tanggal:"));
        summaryPanel.add(new JLabel("2024/03/30"));
        summaryPanel.add(new JLabel("Kategori:"));
        summaryPanel.add(new JLabel("Semua Kategori"));

        reportPanel.add(summaryPanel);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel();
        JButton pdfButton = new JButton("Cetak PDF");
        JButton printButton = new JButton("Print");
        buttonsPanel.add(pdfButton);
        buttonsPanel.add(printButton);

        reportPanel.add(buttonsPanel);

        // Create the table
        String[] columnNames = {"NO", "TANGGAL", "KATEGORI", "KETERANGAN", "PEMASUKAN", "PENGELUARAN"};
        Object[][] data = {};  // Empty data

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane tableScrollPane = new JScrollPane(table);
        reportPanel.add(tableScrollPane);

        mainPanel.add(reportPanel, BorderLayout.CENTER);

        // Add sidebar and main panel to the frame
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }
}
