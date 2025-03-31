package kidsgrowth.child_growth_tracking_system.controller;

import kidsgrowth.child_growth_tracking_system.model.GrowthRecord;
import kidsgrowth.child_growth_tracking_system.service.GrowthRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/growth-records")
public class GrowthRecordController {
    

     @Autowired
    private GrowthRecordService growthRecordService;

    // Thêm chỉ số tăng trưởng mới
    @PostMapping
    public GrowthRecord createGrowthRecord(@RequestBody GrowthRecord growthRecord) {
        return growthRecordService.createGrowthRecord(growthRecord);
    }

    // Lấy thông tin chỉ số tăng trưởng theo ID
    @GetMapping("/{id}")
    public GrowthRecord getGrowthRecordById(@PathVariable Long id) {
        return growthRecordService.getGrowthRecordById(id);
    }

    // Lấy tất cả chỉ số tăng trưởng
    @GetMapping
    public List<GrowthRecord> getAllGrowthRecords() {
        return growthRecordService.getAllGrowthRecords();
    }

    // Cập nhật chỉ số tăng trưởng
    @PutMapping("/{id}")
    public GrowthRecord updateGrowthRecord(@PathVariable Long id, @RequestBody GrowthRecord growthRecord) {
        return growthRecordService.updateGrowthRecord(id, growthRecord);
    }

    // Xóa chỉ số tăng trưởng
    @DeleteMapping("/{id}")
    public void deleteGrowthRecord(@PathVariable Long id) {
        growthRecordService.deleteGrowthRecord(id);
    }
}
}
