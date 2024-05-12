package ma.emsi.schoolhubbackend.repositories;

import ma.emsi.schoolhubbackend.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
