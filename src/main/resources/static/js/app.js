// Ví dụ JavaScript để kiểm tra dữ liệu nhập trong biểu mẫu
document.querySelector('form').addEventListener('submit', function(event) {
    const email = document.querySelector('#email').value;
    const password = document.querySelector('#password').value;
    
    if (!email || !password) {
      alert('Vui lòng điền đầy đủ thông tin!');
      event.preventDefault();
    }
  });
  