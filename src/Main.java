import javax.swing.*;
import services.ProductService;
import util.Database;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {  
         SwingUtilities.invokeLater(() -> {
             Database db = new Database();
             ProductService ps = new ProductService(db);
             MainFrame frame = new MainFrame(ps);
             frame.setVisible(true);
         });
    }
}