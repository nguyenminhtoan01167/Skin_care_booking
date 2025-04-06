package kidsgrowth.child_growth_tracking_system.service;


import kidsgrowth.child_growth_tracking_system.model.GrowthRecord;
import kidsgrowth.child_growth_tracking_system.repository.GrowthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrowthRecordService {
    
    @Autowired
    private GrowthRecordRepository growthRecordRepository;

    // Tạo mới chỉ số tăng trưởng
    public GrowthRecord createGrowthRecord(GrowthRecord growthRecord) {
        return growthRecordRepository.save(growthRecord);
    }

    // Lấy thông tin chỉ số tăng trưởng theo ID
    public GrowthRecord getGrowthRecordById(Long id) {
        Optional<GrowthRecord> growthRecord = growthRecordRepository.findById(id);
        return growthRecord.orElse(null);
    }

    // Lấy tất cả chỉ số tăng trưởng
    public List<GrowthRecord> getAllGrowthRecords() {
        return growthRecordRepository.findAll();
    }

    // Cập nhật chỉ số tăng trưởng
    public GrowthRecord updateGrowthRecord(Long id, GrowthRecord growthRecord) {
        Optional<GrowthRecord> existingGrowthRecord = growthRecordRepository.findById(id);
        if (existingGrowthRecord.isPresent()) {
            growthRecord.setId(id);
            return growthRecordRepository.save(growthRecord);
        }
        return null;
    }

    // Xóa chỉ số tăng trưởng
    public void deleteGrowthRecord(Long id) {
        growthRecordRepository.deleteById(id);
    }
}
