
-- 1. TẠO DATABASE
CREATE DATABASE ChildGrowthTracker;

USE ChildGrowthTracker;

-- 2. USERS - Quản lý thông tin người dùng với các vai trò (Guest, Member, Doctor, Admin)
CREATE TABLE Users (
    Id INT IDENTITY PRIMARY KEY,
    FullName NVARCHAR(100),
    Email NVARCHAR(100) UNIQUE NOT NULL,
    PasswordHash NVARCHAR(255) NOT NULL,
    Role NVARCHAR(20) CHECK (Role IN ('guest', 'member', 'doctor', 'admin')) NOT NULL,  -- Cập nhật các vai trò người dùng
    Status NVARCHAR(20) CHECK (Status IN ('active', 'inactive', 'pending')) DEFAULT 'active',
    CreatedAt DATETIME DEFAULT GETDATE(),
    ExpiryDate DATETIME NULL
);

-- 3. CHILDREN - Quản lý thông tin về trẻ em
CREATE TABLE Children (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    FullName NVARCHAR(100),
    Gender NVARCHAR(10) CHECK (Gender IN ('male', 'female')),
    Birthday DATE,
    CreatedAt DATETIME DEFAULT GETDATE(),
    IsActive BIT DEFAULT 1 -- Trạng thái hoạt động của trẻ
);

-- 4. GROWTH RECORDS - Lưu trữ các chỉ số tăng trưởng của trẻ
CREATE TABLE GrowthRecords (
    Id INT IDENTITY PRIMARY KEY,
    ChildId INT FOREIGN KEY REFERENCES Children(Id),
    RecordDate DATE NOT NULL,
    AgeMonths INT, -- Tuổi tháng của trẻ
    HeightCm FLOAT,
    WeightKg FLOAT,
    BMI AS (WeightKg / POWER(HeightCm / 100.0, 2)),
    GrowthType NVARCHAR(50) CHECK (GrowthType IN ('height', 'weight', 'bmi')), -- Loại phát triển
    WarningFlag BIT DEFAULT 0, -- Cảnh báo phát triển bất thường
    WarningType NVARCHAR(50) -- Loại cảnh báo như 'underweight', 'overweight', v.v.
);

-- 5. GROWTH ALERTS - Cảnh báo về sự phát triển bất thường của trẻ
CREATE TABLE GrowthAlerts (
    Id INT IDENTITY PRIMARY KEY,
    GrowthRecordId INT FOREIGN KEY REFERENCES GrowthRecords(Id),
    AlertType NVARCHAR(50),
    Severity NVARCHAR(20),
    Description NVARCHAR(MAX),
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 6. MEMBERSHIPS - Quản lý các gói thành viên
CREATE TABLE Memberships (
    Id INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(100),
    Price DECIMAL(10,2),
    DurationDays INT,
    Description NVARCHAR(MAX)
);

-- 7. USER MEMBERSHIPS - Lịch sử sử dụng gói của người dùng
CREATE TABLE UserMemberships (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    MembershipId INT FOREIGN KEY REFERENCES Memberships(Id),
    StartDate DATE,
    EndDate DATE,
    PaymentStatus NVARCHAR(20) CHECK (PaymentStatus IN ('pending', 'paid', 'failed'))
);

-- 8. CONSULT REQUESTS - Yêu cầu tư vấn từ người dùng (Member) đến bác sĩ (Doctor)
CREATE TABLE ConsultRequests (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id), -- Người dùng yêu cầu tư vấn (Member)
    ChildId INT FOREIGN KEY REFERENCES Children(Id),
    DoctorId INT FOREIGN KEY REFERENCES Doctors(Id), -- Thêm bác sĩ (Doctor)
    Message NVARCHAR(MAX),
    Status NVARCHAR(20) CHECK (Status IN ('pending', 'replied', 'completed')) DEFAULT 'pending',
    ReminderDate DATETIME DEFAULT NULL,  -- Thời gian nhắc nhở
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 9. CONSULT RESPONSES - Phản hồi từ bác sĩ cho yêu cầu tư vấn
CREATE TABLE ConsultResponses (
    Id INT IDENTITY PRIMARY KEY,
    ConsultId INT FOREIGN KEY REFERENCES ConsultRequests(Id),
    DoctorId INT FOREIGN KEY REFERENCES Doctors(Id),
    ResponseText NVARCHAR(MAX),
    ResponseDate DATETIME DEFAULT GETDATE()
);

-- 10. FEEDBACK - Quản lý đánh giá và phản hồi từ người dùng
CREATE TABLE Feedback (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    Message NVARCHAR(MAX),
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 11. BLOGS - Quản lý các bài viết chia sẻ kinh nghiệm
CREATE TABLE Blogs (
    Id INT IDENTITY PRIMARY KEY,
    Title NVARCHAR(255),
    Content NVARCHAR(MAX),
    AuthorId INT FOREIGN KEY REFERENCES Users(Id),
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 12. FAQ - Quản lý các câu hỏi thường gặp
CREATE TABLE FAQs (
    Id INT IDENTITY PRIMARY KEY,
    Question NVARCHAR(255),
    Answer NVARCHAR(MAX),
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 13. SHARED GROWTH DATA - Chia sẻ dữ liệu tăng trưởng giữa người dùng và bác sĩ
CREATE TABLE SharedGrowthData (
    Id INT IDENTITY PRIMARY KEY,
    ChildId INT FOREIGN KEY REFERENCES Children(Id),
    DoctorId INT FOREIGN KEY REFERENCES Users(Id),
    SharedAt DATETIME DEFAULT GETDATE(),
    CanComment BIT DEFAULT 1 -- Cho phép hoặc không cho phép bác sĩ và người dùng bình luận
);

-- 14. USER LOGS - Lịch sử hoạt động người dùng
CREATE TABLE UserLogs (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    Action NVARCHAR(255),
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 15. PAYMENTS - Lưu trữ thông tin thanh toán cho các gói thành viên
CREATE TABLE Payments (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    MembershipId INT FOREIGN KEY REFERENCES Memberships(Id),
    Amount DECIMAL(10, 2),
    PaymentStatus NVARCHAR(20) CHECK (PaymentStatus IN ('pending', 'paid', 'failed')),
    PaymentDate DATETIME DEFAULT GETDATE(),
    TransactionId NVARCHAR(100)
);

-- 16. HEALTH REPORTS - Lưu trữ báo cáo sức khỏe tổng hợp
CREATE TABLE HealthReports (
    Id INT IDENTITY PRIMARY KEY,
    ChildId INT FOREIGN KEY REFERENCES Children(Id),
    ReportDate DATETIME DEFAULT GETDATE(),
    HeightCm FLOAT,
    WeightKg FLOAT,
    BMI FLOAT,
    Status NVARCHAR(50) CHECK (Status IN ('Normal', 'Underweight', 'Overweight', 'Obese')),
    ReportSummary NVARCHAR(MAX)
);

-- 17. DOCTORS - Quản lý bác sĩ
CREATE TABLE Doctors (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    Specialty NVARCHAR(100),
    LicenseNumber NVARCHAR(100),
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 18. SERVICES - Dịch vụ và gói bổ sung
CREATE TABLE Services (
    Id INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(100),
    Description NVARCHAR(MAX),
    Price DECIMAL(10, 2),
    ServiceType NVARCHAR(50) CHECK (ServiceType IN ('consultation', 'checkup', 'nutrition'))
);

-- 19. NOTIFICATIONS - Thông báo và nhắc nhở bác sĩ
CREATE TABLE Notifications (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    Message NVARCHAR(MAX),
    NotificationType NVARCHAR(50) CHECK (NotificationType IN ('alert', 'reminder', 'update')),
    Status NVARCHAR(20) CHECK (Status IN ('sent', 'pending', 'failed')),
    CreatedAt DATETIME DEFAULT GETDATE(),
    SentAt DATETIME NULL
);

-- 20. GROWTH MILESTONES - Mốc phát triển chuẩn
CREATE TABLE GrowthMilestones (
    Id INT IDENTITY PRIMARY KEY,
    AgeMonths INT,  -- Độ tuổi (tháng)
    HeightMin FLOAT, -- Chiều cao tối thiểu
    HeightMax FLOAT, -- Chiều cao tối đa
    WeightMin FLOAT, -- Cân nặng tối thiểu
    WeightMax FLOAT, -- Cân nặng tối đa
    BMI_Min FLOAT,   -- BMI tối thiểu
    BMI_Max FLOAT,   -- BMI tối đa
    Category NVARCHAR(50) CHECK (Category IN ('Normal', 'Underweight', 'Overweight', 'Obese'))
);

-- 21. SESSION LOGS - Lịch sử đăng nhập người dùng
CREATE TABLE SessionLogs (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    LoginTime DATETIME DEFAULT GETDATE(),
    LogoutTime DATETIME NULL,
    SessionDuration INT DEFAULT 0,  -- Số phút đã đăng nhập
    IPAddress NVARCHAR(50)
);

-- 22. DASHBOARD STATS - Thống kê tổng hợp cho Dashboard
CREATE TABLE DashboardStats (
    Id INT IDENTITY PRIMARY KEY,
    UserId INT FOREIGN KEY REFERENCES Users(Id),
    TotalChildren INT DEFAULT 0,
    TotalConsultRequests INT DEFAULT 0,
    TotalActiveMemberships INT DEFAULT 0,
    TotalAlerts INT DEFAULT 0,
    CreatedAt DATETIME DEFAULT GETDATE()
);

-- 23. DASHBOARD REPORTS - Báo cáo tổng hợp theo ngày/tháng
CREATE TABLE DashboardReports (
    Id INT IDENTITY PRIMARY KEY,
    TotalConsultRequests INT DEFAULT 0, -- Tổng số yêu cầu tư vấn
    TotalGrowthAlerts INT DEFAULT 0,    -- Tổng số cảnh báo tăng trưởng
    TotalUsers INT DEFAULT 0,          -- Tổng số người dùng
    TotalChildren INT DEFAULT 0,       -- Tổng số trẻ em
    TotalActiveMemberships INT DEFAULT 0,  -- Tổng số gói thành viên hoạt động
    ReportDate DATETIME DEFAULT GETDATE()  -- Ngày báo cáo
);
