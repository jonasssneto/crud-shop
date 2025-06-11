import javax.swing.*;
import services.ProductService;
import util.Database;
import view.MainFrame;
import view.Theme;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Theme.apply();
            
            Database db = new Database();
            
            ProductService ps = new ProductService(db);
            
            MainFrame frame = new MainFrame(ps);
            frame.setVisible(true);
        });
    }
}