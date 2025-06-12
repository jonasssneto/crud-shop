import javax.swing.*;
import services.ProductService;
import services.UserService;
import util.Database;
import view.MainFrame;
import view.LoginFrame;
import view.Theme;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Theme.apply();
            
            Database db = new Database();
            UserService us = new UserService(db);

            LoginFrame login = new LoginFrame(us);
            login.setVisible(true);
            if (!login.isAuthenticated()) {
                return;
            }

            ProductService ps = new ProductService(db);
            MainFrame frame = new MainFrame(ps);
            frame.setVisible(true);
        });
    }
}