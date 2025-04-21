package kidsgrowth.child_growth_tracking_system.repository;

import kidsgrowth.child_growth_tracking_system.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    public Membership findByMembershipId(String membershipId);
}
