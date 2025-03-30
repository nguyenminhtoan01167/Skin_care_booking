package kidsgrowth.child_growth_tracking_system.service;

import kidsgrowth.child_growth_tracking_system.model.Child;
import kidsgrowth.child_growth_tracking_system.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    public Optional<Child> getChildById(Integer id) {
        return childRepository.findById(id);
    }

    @Transactional
    public Child createChild(Child child) {
        return childRepository.save(child);
    }

    @Transactional
    public Optional<Child> updateChild(Integer id, Child childDetails) {
        return childRepository.findById(id).map(child -> {
            child.setFullName(childDetails.getFullName());
            child.setGender(childDetails.getGender());
            child.setBirthday(childDetails.getBirthday());
            return childRepository.save(child);
        });
    }

    public boolean deleteChild(Integer id) {
        if (childRepository.existsById(id)) {
            childRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
cc