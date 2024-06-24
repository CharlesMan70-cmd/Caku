import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FinancialTransactionApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Transaction> transactions;

    public FinancialTransactionApp() {
        setTitle("Transaksi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure it closes only this window

        transactions = new ArrayList<>();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"NO", "TANGGAL", "KATEGORI", "KETERANGAN", "PEMASUKAN", "PENGELUARAN"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Tambah Transaksi");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TransactionForm(FinancialTransactionApp.this);
            }
        });

        panel.add(addButton, BorderLayout.NORTH);
        add(panel);
    }

    public void addTransaction(String date, String category, String description, double amount) {
        double income = 0;
        double expense = 0;

        if ("Pemasukan".equals(category)) {
            income = amount;
        } else if ("Pengeluaran".equals(category)) {
            expense = amount;
        }

        Transaction transaction = new Transaction(date, category, description, income, expense);
        transactions.add(transaction);

        Object[] row = new Object[]{tableModel.getRowCount() + 1, date, category, description, income, expense};
        tableModel.addRow(row);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public static class Transaction {
        private String date;
        private String category;
        private String description;
        private double income;
        private double expense;

        public Transaction(String date, String category, String description, double income, double expense) {
            this.date = date;
            this.category = category;
            this.description = description;
            this.income = income;
            this.expense = expense;
        }

        public String getDate() {
            return date;
        }

        public double getIncome() {
            return income;
        }

        public double getExpense() {
            return expense;
        }
    }
}