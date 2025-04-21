package kidsgrowth.child_growth_tracking_system.service;

import kidsgrowth.child_growth_tracking_system.model.Membership;
import kidsgrowth.child_growth_tracking_system.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    // Lấy tất cả Memberships
    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    // Tạo mới Membership
    public Membership createMembership(Membership membership) {
        validateMembership(membership); // Kiểm tra dữ liệu đầu vào
        return membershipRepository.save(membership);
    }

    // Cập nhật Membership
    public Membership updateMembership(Long id, Membership membership) {
        if (membership == null) {
            throw new IllegalArgumentException("Membership cannot be null");
        }

        Membership existingMembership = membershipRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));

        validateMembership(membership); // Kiểm tra dữ liệu đầu vào
        existingMembership.setName(membership.getName());
        existingMembership.setPrice(membership.getPrice());
        existingMembership.setDurationDays(membership.getDurationDays());
        existingMembership.setDescription(membership.getDescription());
        return membershipRepository.save(existingMembership);
    }

    // Xóa Membership
    public void deleteMembership(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new RuntimeException("Membership not found with id: " + id);
        }
        membershipRepository.deleteById(id);
    }

    // Hàm kiểm tra dữ liệu đầu vào
    private void validateMembership(Membership membership) {
        if (membership.getName() == null || membership.getName().isEmpty()) {
            throw new IllegalArgumentException("Membership name cannot be null or empty");
        }
        if (membership.getPrice() == null || membership.getPrice() <= 0) {
            throw new IllegalArgumentException("Membership price must be greater than 0");
        }
        if (membership.getDurationDays() == null || membership.getDurationDays() <= 0) {
            throw new IllegalArgumentException("Membership duration must be greater than 0");
        }
    }
}