package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        topPanel.setBorder(new EmptyBorder(15, 25, 15, 25));

        JLabel titleLabel = new JLabel("Sistema de Gestão de Produtos");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.WEST);

        JButton addButton = new JButton("Adicionar Produto");
        addButton.setFocusPainted(false);
        addButton.setBackground(new Color(59, 89, 182));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
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

        // Tabela com produtos
        productTable = new JTable(new ProductTableModel(productService.get()));
        productTable.setRowHeight(28);
        productTable.setShowGrid(true);
        productTable.setGridColor(Color.LIGHT_GRAY);
        productTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        productTable.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(15, 25, 15, 25),
            BorderFactory.createLineBorder(Color.GRAY, 1)
        ));
        add(scrollPane, BorderLayout.CENTER);
    }
}
