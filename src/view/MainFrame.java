package view;
import java.awt.BorderLayout;
import javax.swing.*;
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

        productTable = new JTable(new ProductTableModel(productService.get()));
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(25, 25, 25, 25),
            BorderFactory.createLineBorder(java.awt.Color.GRAY, 1)
        ));
        add(scrollPane, BorderLayout.CENTER);
    }
}
