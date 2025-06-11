import model.ProductModel;
import services.ProductService;
import util.Database;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        ProductService ps = new ProductService(db);

        ProductModel product = new ProductModel();
        product.setId(1);
        product.setName("Caneta");
        product.setPrice_in_cents(250);
        product.setQuantity(100);

        ps.save(product);

    }
}