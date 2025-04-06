import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends JFrame {
    private JTextField emailField, nameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterPage() {
        // Cấu hình cửa sổ
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Đặt cửa sổ ở trung tâm màn hình

        // Layout
        setLayout(new GridLayout(4, 2));

        // Các thành phần giao diện
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        
        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");

        // Thêm các thành phần vào cửa sổ
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);

        // Lắng nghe sự kiện cho nút đăng ký
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Giả sử đăng ký thành công
                System.out.println("Registration successful");
                // Mở trang đăng nhập sau khi đăng ký
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose(); // Đóng trang đăng ký
            }
        });
    }
}
