import java.net.*;
import java.io.*;

public class ApiService {

    // Phương thức đăng ký người dùng mới
    public static void registerUser(String name, String email, String password) throws IOException {
        // Địa chỉ API đăng ký người dùng
        URL url = new URL("http://localhost:8080/api/register");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Cấu hình kết nối HTTP
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        
        // Tạo chuỗi JSON chứa dữ liệu đăng ký
        String jsonInputString = "{\"name\": \"" + name + "\", \"email\": \"" + email + "\", \"password\": \"" + password + "\"}";

        // Gửi yêu cầu đến server
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length); // Gửi dữ liệu JSON tới backend
        }

        // Đọc phản hồi từ server
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());  // Gộp các dòng phản hồi lại
            }
            System.out.println("Response: " + response.toString()); // In kết quả phản hồi
        }
    }

    // Phương thức lấy thông tin người dùng
    public static String getUserData(String userId) throws IOException {
        // Địa chỉ API lấy thông tin người dùng
        URL url = new URL("http://localhost:8080/api/users/" + userId); 
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Đọc phản hồi từ server (dữ liệu thông tin người dùng)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection
