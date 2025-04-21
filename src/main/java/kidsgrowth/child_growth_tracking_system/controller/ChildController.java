package kidsgrowth.child_growth_tracking_system.controller;

import kidsgrowth.child_growth_tracking_system.model.Child;
import kidsgrowth.child_growth_tracking_system.service.ChildService;

//import kidsgrowth.child_growth_tracking_system.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    @Autowired
    private ChildService childService;
    // Thêm mới trẻ em
    @PostMapping
    public Child createChild(@RequestBody Child child) {
        return childService.createChild(child);
    }

    // Lấy thông tin trẻ em theo ID
    @GetMapping("/{id}")
    public Child getChildById(@PathVariable Long id) {
        return childService.getChildById(id);
    }

    // Lấy tất cả trẻ em
    @GetMapping
    public List<Child> getAllChildren() {
        return childService.getAllChildren();
    }

    // Cập nhật thông tin trẻ em
    @PutMapping("/{id}")
    public Child updateChild(@PathVariable Long id, @RequestBody Child child) {
        return childService.updateChild(id, child);
    }

    // Xóa trẻ em
    @DeleteMapping("/{id}")
    public void deleteChild(@PathVariable Long id) {
        childService.deleteChild(id);
    }
}

