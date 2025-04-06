import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardPage extends JFrame {
    private JTextField weightField, heightField, bmiField;
    private JButton updateButton, viewChartButton;

    public DashboardPage() {
        // Cấu hình cửa sổ
        setTitle("Growth Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(4, 2));

        // Các thành phần giao diện
        JLabel weightLabel = new JLabel("Weight:");
        JLabel heightLabel = new JLabel("Height:");
        JLabel bmiLabel = new JLabel("BMI:");

        weightField = new JTextField();
        heightField = new JTextField();
        bmiField = new JTextField();

        updateButton = new JButton("Update Growth Data");
        viewChartButton = new JButton("View Growth Chart");

        // Thêm các thành phần vào cửa sổ
        add(weightLabel);
        add(weightField);
        add(heightLabel);
        add(heightField);
        add(bmiLabel);
        add(bmiField);
        add(updateButton);
        add(viewChartButton);

        // Lắng nghe sự kiện cho nút cập nhật
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Giả sử cập nhật thành công
                System.out.println("Growth data updated");
            }
        });

        // Lắng nghe sự kiện cho nút xem biểu đồ
        viewChartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi API lấy dữ liệu biểu đồ và hiển thị
                System.out.println("View growth chart clicked");
            }
        });
    }
}
