import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginPage() {
        // Cấu hình cửa sổ
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Đặt cửa sổ ở trung tâm màn hình

        // Layout
        setLayout(new GridLayout(3, 2));

        // Các thành phần giao diện
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        
        emailField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Thêm các thành phần vào cửa sổ
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        // Lắng nghe sự kiện cho nút login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Giả sử đăng nhập thành công
                System.out.println("Login successful");
                // Mở trang Dashboard
                DashboardPage dashboard = new DashboardPage();
                dashboard.setVisible(true);
                dispose(); // Đóng trang đăng nhập
            }
        });

        // Lắng nghe sự kiện cho nút register
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mở trang đăng ký
                RegisterPage registerPage = new RegisterPage();
                registerPage.setVisible(true);
                dispose(); // Đóng trang đăng nhập
            }
        });
    }
}
