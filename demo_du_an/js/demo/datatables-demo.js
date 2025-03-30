// Call the dataTables jQuery plugin for Growth Data Table
$(document).ready(function() {
  $('#growthDataTable').DataTable({
    "paging": true,
    "searching": true,
    "ordering": true,
    "info": true,
    "columns": [
      { "title": "Tháng tuổi" },
      { "title": "Cân nặng (kg)" },
      { "title": "Chiều cao (cm)" },
      { "title": "BMI" }
    ]
  });
});
