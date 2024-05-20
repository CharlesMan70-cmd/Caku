import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FinancialTransactionApp extends JFrame {
    private DefaultTableModel model;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinancialTransactionApp app = new FinancialTransactionApp();
            app.createAndShowGUI();
        });
    }

    protected void createAndShowGUI() {
        JFrame frame = new JFrame("Financial Transaction App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JLabel profileLabel = new JLabel("Profil");
        JMenu usernameMenu = new JMenu("Nama Pengguna");
        JMenuItem editProfileItem = new JMenuItem("Edit Profil");
        usernameMenu.add(editProfileItem);
        menuBar.add(profileLabel);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(usernameMenu);

        // Tambah menu tambah transaksi
        JMenu transactionMenu = new JMenu("Transaksi");
        JMenuItem addTransactionItem = new JMenuItem("Tambah Transaksi", new ImageIcon("add_icon.png"));
        addTransactionItem.addActionListener(e -> {
            CaruTransactionForm transactionForm = new CaruTransactionForm(this);
            transactionForm.setVisible(true);
        });
        transactionMenu.add(addTransactionItem);
        menuBar.add(transactionMenu);

        frame.setJMenuBar(menuBar);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1));
        sidebar.setBackground(Color.DARK_GRAY);
        addSidebarButton(sidebar, "DASHBOARD");
        addSidebarButton(sidebar, "DATA TRANSAKSI");
        addSidebarButton(sidebar, "DATA PENGGUNA");
        addSidebarButton(sidebar, "LAPORAN");
        addSidebarButton(sidebar, "KELUAR");

        // Tambahkan ActionListener untuk tombol "DATA TRANSAKSI"
        // Tabel
        String[] columnNames = { "NO", "TANGGAL", "KATEGORI", "KETERANGAN", "JENIS", "PEMASUKAN", "PENGELUARAN",
                "OPSI" };
        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Tambahkan tombol edit dan hapus di kolom OPSI
        TableColumn opsiColumn = table.getColumnModel().getColumn(7);
        opsiColumn.setCellRenderer(new ButtonRenderer());
        opsiColumn.setCellEditor(new ButtonEditor(new JTextField(), table));

        // Mengatur JComboBox untuk kolom "KATEGORI"
        JComboBox<String> kategoriComboBox = new JComboBox<>();
        kategoriComboBox.addItem("Pemasukan");
        kategoriComboBox.addItem("Pengeluaran");
        TableColumn kategoriColumn = table.getColumnModel().getColumn(2);
        kategoriColumn.setCellEditor(new DefaultCellEditor(kategoriComboBox));

        // Mengatur JComboBox untuk kolom "JENIS"
        JComboBox<String> jenisComboBox = new JComboBox<>();
        jenisComboBox.addItem("Cash");
        jenisComboBox.addItem("Rekening");
        TableColumn jenisColumn = table.getColumnModel().getColumn(4);
        jenisColumn.setCellEditor(new DefaultCellEditor(jenisComboBox));

        // Mengatur JComboBox untuk kolom "TANGGAL"
        TableColumn tanggalColumn = table.getColumnModel().getColumn(1);
        tanggalColumn.setCellEditor(new DateCellEditor());

        // Panel atas
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(menuBar, BorderLayout.CENTER);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buat panel baru untuk sidebar
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.add(sidebar, BorderLayout.CENTER);

        // Tambahkan sidebarPanel ke utama di sebelah kiri
        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // Tambahkan panel utama ke frame
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton addSidebarButton(JPanel sidebar, String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidebar.add(button);
        return button;
    }

    // Method to show FinancialTransactionApp
    private void showTransactionApp() {
        // Dispose the current frame
        dispose();

        // Show FinancialTransactionApp
        FinancialTransactionApp transactionApp = new FinancialTransactionApp();
        transactionApp.createAndShowGUI();
    }

    static class ButtonRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton gantiButton;
        private JButton hapusButton;

        public ButtonRenderer() {
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
            gantiButton = new JButton("Ganti", new ImageIcon("it.png"));
            hapusButton = new JButton("Hapus", new ImageIcon(".png"));
            panel.add(gantiButton);
            panel.add(hapusButton);
            panel.setBackground(Color.WHITE);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object valueObject, boolean isSelected,
                boolean hasFocus,
                int row, int column) {
            return panel;
        }
    }

    static class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        protected JPanel panel;
        private JButton gantiButton;
        private JButton hapusButton;
        private JTable table;
        private Object[] currentRowData; // Menyimpan data baris saat ini

        public ButtonEditor(JTextField textField, JTable table) {
            super();
            this.table = table;
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
            gantiButton = new JButton("Ganti", new ImageIcon(".png"));
            hapusButton = new JButton("Hapus", new ImageIcon("h.png"));
            panel.add(gantiButton);
            panel.add(hapusButton);
            panel.setBackground(Color.WHITE);

            gantiButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.convertRowIndexToModel(table.getEditingRow());
                    if (selectedRow != -1) {
                        // Lakukan aksi ganti di sini, Anda bisa mengambil data dari tabel dan
                        // menampilkannya di form pengeditan
                        JOptionPane.showMessageDialog(panel, "Anda memilih Ganti pada baris " + selectedRow);
                    }
                }
            });

            hapusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.convertRowIndexToModel(table.getEditingRow());
                    if (selectedRow != -1) {
                        // Lakukan aksi hapus di sini
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(panel, "Anda memilih Hapus pada baris " + selectedRow);
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return currentRowData; // Mengembalikan data baris saat ini
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    static class DateCellEditor extends AbstractCellEditor implements TableCellEditor {
        private JSpinner spinner;

        public DateCellEditor() {
            spinner = new JSpinner(new SpinnerDateModel());
            spinner.setEditor(new JSpinner.DateEditor(spinner, "dd-MM-yyyy"));
            spinner.setValue(new Date());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }

    public void addTransactionToTable(Object[] rowData) {
        model.addRow(rowData);
    }
}

class CaruTransactionForm extends JFrame {
    private FinancialTransactionApp parent;
    private JPanel panel;
    private JLabel tanggalLabel, jenisLabel, kategoriLabel, nominalLabel, keteranganLabel;
    private JTextField tanggalField, nominalField;
    private JTextArea keteranganArea;
    private JComboBox<String> jenisComboBox, kategoriComboBox;
    private JButton tutupButton, simpanButton;

    public CaruTransactionForm(FinancialTransactionApp parent) {
        // Membuat frame
        super("Formulir Transaksi CARU");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        this.parent = parent;

        // Membuat panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Membuat dan menambahkan komponen
        tanggalLabel = new JLabel("Tanggal:");
        // Mengatur nilai awal tanggalField menjadi tanggal hari ini
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        tanggalField = new JTextField(LocalDate.now().format(formatter));
        jenisLabel = new JLabel("Jenis:");
        jenisComboBox = new JComboBox<>(new String[] { "Pilih", "Pemasukan", "Pengeluaran" });
        kategoriLabel = new JLabel("Kategori:");
        kategoriComboBox = new JComboBox<>(new String[] { "Pilih", "Makanan", "Transportasi", "Lainnya" });
        nominalLabel = new JLabel("Nominal:");
        nominalField = new JTextField();
        keteranganLabel = new JLabel("Keterangan:");
        keteranganArea = new JTextArea(3, 20);

        tutupButton = new JButton("Tutup");
        tutupButton.addActionListener(e -> dispose());
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

        add(panel);
        setLocationRelativeTo(null);
    }

    private void saveTransaction() {
        // Mengambil nilai dari setiap inputan
        String tanggal = tanggalField.getText();
        String jenis = (String) jenisComboBox.getSelectedItem();
        String kategori = (String) kategoriComboBox.getSelectedItem();
        String nominal = nominalField.getText();
        String keterangan = keteranganArea.getText();

        // Contoh: menyimpan data ke dalam ArrayList
        Transaksi transaksi = new Transaksi(tanggal, jenis, kategori, nominal, keterangan);
        Object[] rowData = { transaksi.getTanggal(), transaksi.getJenis(), transaksi.getKategori(),
                transaksi.getNominal(), transaksi.getKeterangan() };

        // Memanggil method dari parent untuk menambahkan data transaksi ke dalam tabel
        parent.addTransactionToTable(rowData);

        // Menutup form setelah data disimpan
        dispose();
    }
}

class Transaksi {
    private String tanggal;
    private String jenis;
    private String kategori;
    private String nominal;
    private String keterangan;

    public Transaksi(String tanggal, String jenis, String kategori, String nominal, String keterangan) {
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.kategori = kategori;
        this.nominal = nominal;
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public String getKategori() {
        return kategori;
    }

    public String getNominal() {
        return nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
