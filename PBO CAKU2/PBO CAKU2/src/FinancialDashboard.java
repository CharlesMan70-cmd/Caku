import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinancialDashboard extends JFrame {

    public FinancialDashboard() {
        // Set title and default close operation
        setTitle("Financial Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create the sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JButton btnDashboard = new JButton("Dashboard");
        JButton btnDataTransaksi = new JButton("Data Transaksi");
        JButton btnDataPengguna = new JButton("Data Pengguna");
        JButton btnLaporan = new JButton("Laporan");
        JButton btnKeluar = new JButton("Keluar");

        sidebar.add(btnDashboard);
        sidebar.add(btnDataTransaksi);
        sidebar.add(btnDataPengguna);
        sidebar.add(btnLaporan);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnKeluar);

        // Create the main panel
        JPanel mainPanel = new JPanel(new CardLayout());

        // Create dashboard panel
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
        
        JLabel lblTitle = new JLabel("Dashboard");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        dashboardPanel.add(lblTitle);

        JLabel lblDailyIncome = new JLabel("Pemasukan Harian: ");
        JLabel lblMonthlyIncome = new JLabel("Pemasukan Bulanan: ");
        JLabel lblYearlyIncome = new JLabel("Pemasukan Tahunan: ");
        JLabel lblTotalIncome = new JLabel("Total Pemasukan: ");

        JLabel lblDailyExpense = new JLabel("Pengeluaran Harian: ");
        JLabel lblMonthlyExpense = new JLabel("Pengeluaran Bulanan: ");
        JLabel lblYearlyExpense = new JLabel("Pengeluaran Tahunan: ");
        JLabel lblTotalExpense = new JLabel("Total Pengeluaran: ");

        dashboardPanel.add(lblDailyIncome);
        dashboardPanel.add(lblMonthlyIncome);
        dashboardPanel.add(lblYearlyIncome);
        dashboardPanel.add(lblTotalIncome);
        dashboardPanel.add(lblDailyExpense);
        dashboardPanel.add(lblMonthlyExpense);
        dashboardPanel.add(lblYearlyExpense);
        dashboardPanel.add(lblTotalExpense);

        // Create charts panel
        JPanel chartsPanel = new JPanel();
        chartsPanel.setLayout(new GridLayout(1, 2));
        chartsPanel.add(createChartPanel("Pemasukan"));
        chartsPanel.add(createChartPanel("Pengeluaran"));
        
        dashboardPanel.add(chartsPanel);

        // Add all panels to main panel
        mainPanel.add(dashboardPanel, "Dashboard");
        mainPanel.add(new JPanel(), "Data Transaksi");
        mainPanel.add(new JPanel(), "Data Pengguna");
        mainPanel.add(new JPanel(), "Laporan");

        // Add listeners to sidebar buttons
        btnDashboard.addActionListener(e -> showPanel(mainPanel, "Dashboard"));
        btnDataTransaksi.addActionListener(e -> showPanel(mainPanel, "Data Transaksi"));
        btnDataPengguna.addActionListener(e -> showPanel(mainPanel, "Data Pengguna"));
        btnLaporan.addActionListener(e -> showPanel(mainPanel, "Laporan"));
        btnKeluar.addActionListener(e -> System.exit(0));

        // Add sidebar and main panel to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void showPanel(JPanel mainPanel, String panelName) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, panelName);
    }

    private JPanel createChartPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        // Placeholder for chart, replace with actual chart implementation
        panel.add(new JLabel("Chart Placeholder"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinancialDashboard frame = new FinancialDashboard();
            frame.setVisible(true);
        });
    }
}
