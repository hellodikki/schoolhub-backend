package ma.emsi.schoolhubbackend.repositories;

import ma.emsi.schoolhubbackend.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
