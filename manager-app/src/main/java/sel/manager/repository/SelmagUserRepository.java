package sel.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sel.manager.entity.SelmagUser;
import java.util.Optional;

@Repository
public interface SelmagUserRepository extends JpaRepository<SelmagUser, Integer> {
    Optional<SelmagUser> findByUsername(String username);
}
