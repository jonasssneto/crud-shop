package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ProductModel;
import util.money;

public class ProductTableModel extends AbstractTableModel {
    private List<ProductModel> products;
    private final String[] columns = {"ID", "Nome", "Preço", "Quantidade"};

    public ProductTableModel(List<ProductModel> products) {
        this.products = products != null ? products : List.of();
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 3 -> Integer.class;
            case 2 -> String.class;
            default -> String.class;
        };
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductModel product = products.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> product.getId();
            case 1 -> product.getName();
            case 2 -> money.format(product.getPrice_in_cents());
            case 3 -> product.getQuantity();
            default -> null;
        };
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
        fireTableDataChanged();
    }
}
