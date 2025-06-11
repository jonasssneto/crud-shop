package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import services.ProductService;

public class MainFrame extends JFrame {
    private final ProductService productService;
    private JTable productTable;

    public MainFrame(ProductService productService) {
        super("Sistema de Gestão de Produtos");
        this.productService = productService;
        initializeComponents();
    }

    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(Theme.WINDOW_PADDING);

        JLabel titleLabel = new JLabel("Sistema de Gestão de Produtos");
        titleLabel.setFont(Theme.TITLE_FONT);
        topPanel.add(titleLabel, BorderLayout.WEST);

        JButton addButton = new JButton("Adicionar Produto");
        addButton.setFocusPainted(false);
        addButton.setBackground(Theme.PRIMARY_COLOR);
        addButton.setForeground(Theme.CONTRAST_COLOR);
        addButton.setFont(Theme.BUTTON_FONT);
        addButton.setPreferredSize(new Dimension(180, 35));
        addButton.addActionListener(e -> {
            ProductFormDialog dialog = new ProductFormDialog(this, productService);
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                ((ProductTableModel) productTable.getModel()).setProducts(productService.get());
            }
        });
        topPanel.add(addButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        productTable = new JTable(new ProductTableModel(productService.get()));
        productTable.setRowHeight(28);
        productTable.setShowGrid(true);
        productTable.setGridColor(Theme.GRID_COLOR);
        productTable.getTableHeader().setFont(Theme.TABLE_HEADER_FONT);
        productTable.setFont(Theme.TABLE_FONT);

        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.setSelectionBackground(Theme.PRIMARY_COLOR);
        productTable.setSelectionForeground(Theme.CONTRAST_COLOR);
        productTable.setDefaultEditor(Object.class, null); // Disable editing
        productTable.setAutoCreateRowSorter(true);
        productTable.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < productTable.getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = productTable.getSelectedRow();
                int id = (int) productTable.getValueAt(row, 0);
                var product = productService.getById(id);
                if (product != null) {
                    ProductDetailDialog dialog = new ProductDetailDialog(MainFrame.this, productService, product);
                    dialog.setVisible(true);
                    if (dialog.isUpdated() || dialog.isDeleted()) {
                        ((ProductTableModel) productTable.getModel()).setProducts(productService.get());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(15, 25, 15, 25),
                BorderFactory.createLineBorder(Color.GRAY, 1)));
        add(scrollPane, BorderLayout.CENTER);
    }
}
