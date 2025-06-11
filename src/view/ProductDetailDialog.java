package view;

import model.ProductModel;
import services.ProductService;
import util.money;

import javax.swing.*;
import java.awt.*;

public class ProductDetailDialog extends JDialog {
    private final JTextField nameField = new JTextField(20);
    private final JTextField priceField = new JTextField(10);
    private final JTextField quantityField = new JTextField(10);
    private boolean updated = false;
    private boolean deleted = false;

    public ProductDetailDialog(JFrame parent, ProductService productService, ProductModel product) {
        super(parent, "Detalhes do Produto", true);
        setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        nameField.setText(product.getName());
        priceField.setText(money.format(product.getPrice_in_cents()));
        quantityField.setText(String.valueOf(product.getQuantity()));

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Preço:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Quantidade:"));
        formPanel.add(quantityField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Remover");
        JButton closeButton = new JButton("Fechar");
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> {
            if (validateFields()) {
                product.setName(nameField.getText().trim());
                product.setPrice_in_cents(money.parse(priceField.getText().trim()));
                product.setQuantity(Integer.parseInt(quantityField.getText().trim()));
                productService.update(product);
                updated = true;
                dispose();
            }
        });
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este produto?", "Confirmar remoção", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                productService.delete(product.getId());
                deleted = true;
                dispose();
            }
        });
        closeButton.addActionListener(e -> dispose());

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

    public boolean isUpdated() {
        return updated;
    }
    public boolean isDeleted() {
        return deleted;
    }
}
