package presentation;

import business.DeliveryService;
import business.UserService;
import presentation.administratorGUI.AdministratorPanelController;
import presentation.clientGUI.Client;
import presentation.clientGUI.ClientPanelController;
import presentation.employeeGUI.EmployeePanel;

import javax.swing.*;
import java.awt.*;

/**
 * clasa principala care reprezinta panoul de log in al aplicatiei si de asemenea de aici incepe executia
 * Aserturile trebuie sa fie activate pentru ca programul sa poata functiona corespunzator
 */
@SuppressWarnings({"ConstantConditions", "AssertWithSideEffects"})
public class LogInWindow extends JFrame {

    static {
        boolean assertsEnabled = false;
        assert assertsEnabled = true; // daca asertiunile nu sunt activate, aceasta linie nu va fi executata
        if (!assertsEnabled)
            throw new RuntimeException("Asertiunile trebuie activate pentru ca programul sa poata functiona corespunzator!");
    }

    private final JTextField usernameField;
    private final JPasswordField passwordField;

    private final JButton btnLogin;
    private final JButton btnRegister;
    private final JButton btnRegisterUser;
    private final JButton btnCancelRegistration;

    public LogInWindow() {
        this.setTitle("Login Window");
        this.setBounds(100, 100, 292, 212);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(29, 45, 86, 20);
        this.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(29, 75, 86, 20);
        this.getContentPane().add(lblPassword);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBounds(111, 45, 118, 20);
        this.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(111, 75, 118, 20);
        this.getContentPane().add(passwordField);

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setBounds(125, 103, 86, 20);
        this.getContentPane().add(btnLogin);
        btnLogin.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            User u = new User(username, password);
            int status = UserService.findUser(u);
            switch (status) {
                case -1 -> showMessage("Parola introdusa este gresita");
                case 0 -> showMessage("Userul nu este inregistrat");
                case 1 -> new AdministratorPanelController();
                case 2 -> DeliveryService.getClassInstance().addObserver(new EmployeePanel());
                case 3 -> new ClientPanelController(u.getId());
            }
            clearFields();
        });

        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegister.setBounds(29, 123, 86, 20);
        this.getContentPane().add(btnRegister);
        btnRegister.addActionListener(e -> {
                    showRegistrationButtons(true);
                    clearFields();
                }
        );

        btnCancelRegistration = new JButton("Cancel Registration");
        btnCancelRegistration.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancelRegistration.setBounds(10, 133, 165, 21);
        btnCancelRegistration.setVisible(false);
        this.getContentPane().add(btnCancelRegistration);
        btnCancelRegistration.addActionListener(e -> {
                    showRegistrationButtons(false);
                    clearFields();
                }
        );

        btnRegisterUser = new JButton("Register User");
        btnRegisterUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegisterUser.setBounds(135, 103, 132, 21);
        btnRegisterUser.setVisible(false);
        this.getContentPane().add(btnRegisterUser);
        btnRegisterUser.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            User u = new Client(username, password);
            if (UserService.registerUser(u)) {
                showMessage("User inregistrat cu succes");
                showRegistrationButtons(false);
            } else
                showMessage("Userul este inregistrat deja, folositi alt username");
            clearFields();
        });

        this.setVisible(true);
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private void showRegistrationButtons(boolean flag) {
        btnLogin.setVisible(!flag);
        btnRegister.setVisible(!flag);
        btnRegisterUser.setVisible(flag);
        btnCancelRegistration.setVisible(flag);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new LogInWindow();
    }
}
