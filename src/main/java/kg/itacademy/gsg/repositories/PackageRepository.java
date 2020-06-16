package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package,Long> {
}
