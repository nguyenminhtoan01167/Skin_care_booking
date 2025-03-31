package kidsgrowth.child_growth_tracking_system.service;
import kidsgrowth.child_growth_tracking_system.model.Doctor;
import kidsgrowth.child_growth_tracking_system.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    // Tạo mới bác sĩ
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Lấy thông tin bác sĩ theo ID
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElse(null);
    }

    // Lấy tất cả bác sĩ
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Cập nhật thông tin bác sĩ
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (existingDoctor.isPresent()) {
            doctor.setId(id);
            return doctorRepository.save(doctor);
        }
        return null;
    }

    // Xóa bác sĩ
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
