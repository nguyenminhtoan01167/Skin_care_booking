package kidsgrowth.child_growth_tracking_system.service;

import kidsgrowth.child_growth_tracking_system.model.Child;
import kidsgrowth.child_growth_tracking_system.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    // Tạo mới trẻ em
    public Child createChild(Child child) {
        return childRepository.save(child);
    }

    // Lấy thông tin trẻ em theo ID
    public Child getChildById(Long id) {
        Optional<Child> child = childRepository.findById(id);
        return child.orElse(null);
    }

    // Lấy tất cả trẻ em
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    // Cập nhật thông tin trẻ em
    public Child updateChild(Long id, Child child) {
        Optional<Child> existingChild = childRepository.findById(id);
        if (existingChild.isPresent()) {
            child.setId(id);
            return childRepository.save(child);
        }
        return null;
    }

    // Xóa trẻ em
    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }
}
