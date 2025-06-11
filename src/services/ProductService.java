package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ProductModel;
import util.Database;

public class ProductService {
    private final Database db;

    public ProductService(Database db) {
        this.db = db;
    }

    // CREATE
    public void save(ProductModel product) {
        String query = "INSERT INTO product (name, price_in_cents, quantity) VALUES (?, ?, ?)";

        try (Connection conn = db.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice_in_cents());
            ps.setInt(3, product.getQuantity());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    public List<ProductModel> get() {
        String query = "SELECT * FROM product";
        List<ProductModel> products = new ArrayList<>();

        try (Connection conn = db.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice_in_cents(rs.getInt("price_in_cents"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }

        return products;
    }

    public void update(ProductModel product) {
        String query = "UPDATE product SET name = ?, price_in_cents = ?, quantity = ? WHERE id = ?";

        try (Connection conn = db.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice_in_cents());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Produto não encontrado para atualizar.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM product WHERE id = ?";

        try (Connection conn = db.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Produto não encontrado para deletar.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }
}
