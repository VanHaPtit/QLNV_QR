E-HRM – Hệ Thống Quản Trị Nhân Sự
1. Giới thiệu
   E-HRM (Enterprise Human Resource Management) là hệ thống quản lý nhân sự được xây dựng nhằm số hóa và tự động hóa toàn bộ quy trình quản trị nhân sự trong doanh nghiệp. Hệ thống tập trung vào ba bài toán cốt lõi: chấm công chính xác, tính lương minh bạch và quản lý quỹ phép nhất quán.
   Trong thực tế, nhiều doanh nghiệp vẫn phụ thuộc vào các phương pháp thủ công như Excel hoặc các hệ thống rời rạc, dẫn đến sai sót trong tính toán, thiếu minh bạch trong xử lý vi phạm và khó kiểm soát dữ liệu theo thời gian. E-HRM được thiết kế để giải quyết triệt để các vấn đề này thông qua kiến trúc hiện đại, cơ chế cấu hình linh hoạt và các nguyên tắc đảm bảo tính đúng đắn dữ liệu như Idempotency.
2. Mục tiêu hệ thống
   Hệ thống được xây dựng với các mục tiêu :
   Tự động hóa quy trình tính lương dựa trên dữ liệu thực tế
   Đảm bảo tính chính xác tài chính trong mọi phép tính
   Cung cấp khả năng cấu hình linh hoạt mà không cần thay đổi mã nguồn
   Tăng tính minh bạch trong quản lý chấm công và xử lý vi phạm
   Đảm bảo dữ liệu luôn nhất quán khi thực hiện các thao tác tính lại
3. Phạm vi và đối tượng sử dụng
   Hệ thống phục vụ bốn nhóm người dùng chính:
   System Admin: quản lý toàn bộ hệ thống và cấu hình các tham số nghiệp vụ
   HR Manager: quản lý nhân sự, phê duyệt lương và đơn nghỉ phép
   HR Staff: thực hiện các nghiệp vụ nhập liệu và hỗ trợ vận hành
   Employee: sử dụng hệ thống để chấm công, xem lương và quản lý thông tin cá nhân
4. Tổng quan chức năng hệ thống
   4.1 User & Authentication Module
   Module này chịu trách nhiệm xác thực và phân quyền người dùng. Hệ thống sử dụng JWT để quản lý phiên đăng nhập, kết hợp với cơ chế Refresh Token nhằm đảm bảo trải nghiệm người dùng liên tục nhưng vẫn giữ mức bảo mật cao.
   Các chức năng chính bao gồm:
   Đăng nhập và đăng xuất
   Quản lý phiên làm việc
   Đổi mật khẩu và quên mật khẩu
   Phân quyền theo mô hình RBAC
   4.2 Employee Module
   Module quản lý toàn bộ vòng đời của nhân viên trong hệ thống. Dữ liệu được lưu trữ có cấu trúc và có thể mở rộng.
   Các chức năng chính:
   Quản lý hồ sơ nhân viên
   Quản lý hợp đồng lao động (lưu trữ trên Amazon S3)
   Quản lý bảo hiểm
   Khai báo người phụ thuộc phục vụ tính thuế
   Cập nhật thông tin cá nhân
   4.3 Attendance Module
   Hệ thống sử dụng QR Code động để ghi nhận chấm công thay vì các phương pháp truyền thống như chấm vân tay hoặc nhận diện khuôn mặt.
   Đặc điểm nổi bật:
   QR Code được tạo theo thời gian thực
   Token có thời hạn ngắn
   Mỗi lần quét tạo một token duy nhất
   Ngăn chặn hành vi chấm công hộ
   Hệ thống tự động phân loại vi phạm dựa trên thời gian check-in và check-out:
   Trễ bậc 1 (không quá 15 phút)
   Trễ bậc 2 (trên 15 phút)
   Về sớm
   Ngoài ra, hệ thống cho phép chấm công thủ công trong các trường hợp đặc biệt, nhưng yêu cầu phải có lý do và được phê duyệt.
   4.4 Payroll Module
   Đây là module trung tâm của hệ thống, thực hiện toàn bộ quá trình tính lương tự động.
   Dữ liệu đầu vào bao gồm:
   Dữ liệu chấm công
   Thông tin hợp đồng
   Các tham số cấu hình từ hệ thống
   Hệ thống thực hiện:
   Tính lương Gross
   Tính các khoản phạt theo vi phạm
   Tính bảo hiểm theo tỷ lệ
   Tính thuế thu nhập cá nhân theo biểu lũy tiến
   Tính lương thực lĩnh (Net)
   Công thức tổng quát:
   Gross = Lương cơ bản + Phụ cấp
   Net = Gross - Tổng phạt - Bảo hiểm - Thuế TNCN 
4.5 Leave Module
Module này quản lý toàn bộ vòng đời của quỹ phép năm.
Các chức năng chính:
Khởi tạo quỹ phép đầu năm
Gửi và phê duyệt đơn nghỉ phép
Theo dõi lịch sử sử dụng phép
Quản lý phép tồn và thời hạn hết hạn
Điểm quan trọng nhất là cơ chế hoàn trả phép. Khi một bản ghi lương bị xóa hoặc tính lại, hệ thống bắt buộc phải hoàn trả số ngày phép đã sử dụng trước đó để đảm bảo tính nhất quán dữ liệu.
4.6 System Configuration Module
Toàn bộ tham số nghiệp vụ của hệ thống được lưu trong cơ sở dữ liệu và có thể cấu hình thông qua giao diện Admin.
Các nhóm cấu hình bao gồm:
Mức phạt vi phạm (trễ, về sớm, nghỉ không phép)
Tỷ lệ bảo hiểm
Tham số giảm trừ thuế
Quỹ phép năm và thời hạn phép tồn
Việc không sử dụng hard-code giúp hệ thống dễ dàng thích ứng với thay đổi chính sách mà không cần chỉnh sửa logic lõi.
4.7 Report & Notification Module
Hệ thống hỗ trợ:
Xuất báo cáo lương, chấm công và nghỉ phép
Gửi thông báo liên quan đến lương và phê duyệt đơn
Các báo cáo có thể xuất dưới dạng Excel hoặc PDF để phục vụ đối chiếu với bộ phận kế toán.
5. Kiến trúc hệ thống
   Hệ thống được thiết kế theo mô hình phân lớp:
   Controller → Service → Repository → Database
   Cách tiếp cận này giúp:
   Tách biệt rõ ràng trách nhiệm giữa các tầng
   Dễ kiểm thử và bảo trì
   Dễ mở rộng khi bổ sung chức năng mới
6. Công nghệ sử dụng
   Backend: Java Spring Boot 3.x
   Security: Spring Security, JWT
   Frontend: React.js, Tailwind CSS
   Database: MySQL 8.0
   Lưu trữ: Cloudinary
   CI/CD: GitHub Actions
   Monitoring: AWS CloudWatch
7. Yêu cầu phi chức năng
   7.1 Bảo mật
   Mật khẩu được mã hóa bằng BCrypt
   Giao tiếp qua HTTPS
   Sử dụng JWT với thời hạn xác định
   Áp dụng phân quyền RBAC
   QR Code có cơ chế chống replay
8. Tiêu chí chấp nhận
   Hệ thống được coi là đạt yêu cầu khi:
   Bảng lương trùng khớp 100% với file kế toán
   Thay đổi cấu hình có hiệu lực ngay lập tức
   Tính lại lương không làm sai lệch quỹ phép
   QR Code hết hạn không thể sử dụng lại
   Giao diện hoạt động tốt trên nhiều thiết bị
9. Hướng dẫn cài đặt
   Clone dự án
   https://github.com/VanHaPtit/QLNV_QR.git
   Chạy backend
   mvn spring-boot:run
   Chạy frontend
   npm install
   npm run dev

