package view;

import services.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final UserService userService;
    private final JTextField userField = new JTextField(15);
    private final JPasswordField passField = new JPasswordField(15);
    private boolean authenticated = false;

    public LoginFrame(UserService userService) {
        super("Login");
        this.userService = userService;
        initializeComponents();
    }

    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 180);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBorder(Theme.WINDOW_PADDING);
        form.add(new JLabel("Usuário:"));
        form.add(userField);
        form.add(new JLabel("Senha:"));
        form.add(passField);
        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton loginButton = new JButton("Entrar");
        JButton cancelButton = new JButton("Cancelar");
        buttons.add(loginButton);
        buttons.add(cancelButton);
        add(buttons, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword());
            if (userService.authenticate(user, pass)) {
                authenticated = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        cancelButton.addActionListener(e -> System.exit(0));
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
