package kidsgrowth.child_growth_tracking_system.controller;

import kidsgrowth.child_growth_tracking_system.model.Membership;
import kidsgrowth.child_growth_tracking_system.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping
    public List<Membership> getAllMemberships() {
        return membershipService.getAllMemberships();
    }

    @PostMapping
    public Membership createMembership(@RequestBody Membership membership) {
        return membershipService.createMembership(membership);
    }

    @PutMapping("/{id}")
    public Membership updateMembership(@PathVariable Long id, @RequestBody Membership membership) {
        return membershipService.updateMembership(id, membership);
    }

    @DeleteMapping("/{id}")
    public void deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
    }
}