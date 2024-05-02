import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate; // Import kelas LocalDate

public class CaruTransactionForm {
    private JFrame frame;
    private JPanel panel;
    private JLabel tanggalLabel, jenisLabel, kategoriLabel, nominalLabel, keteranganLabel;
    private JTextField tanggalField, nominalField;
    private JTextArea keteranganArea;
    private JComboBox<String> jenisComboBox, kategoriComboBox;
    private JButton tutupButton, simpanButton;

    public CaruTransactionForm() {
        // Membuat frame
        frame = new JFrame("Formulir Transaksi CARU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Membuat panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Membuat dan menambahkan komponen
        tanggalLabel = new JLabel("Tanggal:");
        // Mengatur nilai awal tanggalField menjadi tanggal hari ini
        tanggalField = new JTextField(LocalDate.now().toString());
        jenisLabel = new JLabel("Jenis:");
        jenisComboBox = new JComboBox<>(new String[] { "Pilih", "Pemasukan", "Pengeluaran" });
        kategoriLabel = new JLabel("Kategori:");
        kategoriComboBox = new JComboBox<>(new String[] { "Pilih", "Makanan", "Transportasi", "Lainnya" });
        nominalLabel = new JLabel("Nominal:");
        nominalField = new JTextField();
        keteranganLabel = new JLabel("Keterangan:");
        keteranganArea = new JTextArea(3, 20);

        tutupButton = new JButton("Tutup");
        tutupButton.addActionListener(e -> frame.dispose());
        simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(e -> saveTransaction());

        panel.add(tanggalLabel);
        panel.add(tanggalField);
        panel.add(jenisLabel);
        panel.add(jenisComboBox);
        panel.add(kategoriLabel);
        panel.add(kategoriComboBox);
        panel.add(nominalLabel);
        panel.add(nominalField);
        panel.add(keteranganLabel);
        panel.add(new JScrollPane(keteranganArea));
        panel.add(tutupButton);
        panel.add(simpanButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void saveTransaction() {
        // Implementasi penyimpanan data transaksi
        System.out.println("Transaksi disimpan.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CaruTransactionForm::new);
    }
}
