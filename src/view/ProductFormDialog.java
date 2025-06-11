package view;

import model.ProductModel;
import services.ProductService;
import util.money;

import javax.swing.*;
import java.awt.*;

public class ProductFormDialog extends JDialog {
    private final JTextField nameField = new JTextField(20);
    private final JTextField priceField = new JTextField(10);
    private final JTextField quantityField = new JTextField(10);
    private boolean saved = false;

    public ProductFormDialog(JFrame parent, ProductService productService) {
        super(parent, "Adicionar Produto", true);
        setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(Theme.WINDOW_PADDING);

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Preço:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Quantidade:"));
        formPanel.add(quantityField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Salvar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.setFont(Theme.BUTTON_FONT);
        saveButton.setBackground(Theme.PRIMARY_COLOR);
        saveButton.setForeground(Theme.CONTRAST_COLOR);
        cancelButton.setFont(Theme.BUTTON_FONT);
        cancelButton.setBackground(Theme.PRIMARY_COLOR);
        cancelButton.setForeground(Theme.CONTRAST_COLOR);
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            if (validateFields()) {
                ProductModel product = new ProductModel();

                product.setName(nameField.getText().trim());
                product.setPrice_in_cents(money.parse(priceField.getText().trim()));
                product.setQuantity(Integer.parseInt(quantityField.getText().trim()));
                productService.save(product);
                saved = true;
                dispose();
            }
        });
        cancelButton.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(parent);
    }

    private boolean validateFields() {
        if (nameField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() || quantityField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            if (quantity < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser números inteiros positivos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean isSaved() {
        return saved;
    }
}
