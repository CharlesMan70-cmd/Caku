import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionForm extends JDialog {
    private JTextField dateField;
    private JComboBox<String> categoryField;
    private JTextField descriptionField;
    private JTextField amountField;
    private FinancialTransactionApp parent;

    public TransactionForm(FinancialTransactionApp parent) {
        this.parent = parent;
        setTitle("Tambah Transaksi");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Tanggal (yyyy-mm-dd):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Kategori:"));
        categoryField = new JComboBox<>(new String[]{"Pemasukan", "Pengeluaran"});
        add(categoryField);

        add(new JLabel("Keterangan:"));
        descriptionField = new JTextField();
        add(descriptionField);

        add(new JLabel("Jumlah:"));
        amountField = new JTextField();
        add(amountField);

        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String category = (String) categoryField.getSelectedItem();
                String description = descriptionField.getText();
                double amount = Double.parseDouble(amountField.getText());

                parent.addTransaction(date, category, description, amount);
                dispose();
            }
        });
        add(saveButton);

        setVisible(true);
    }
}
