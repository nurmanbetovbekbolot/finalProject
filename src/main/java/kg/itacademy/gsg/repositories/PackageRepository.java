package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.models.PackageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package,Long> {

    @Query("select new kg.itacademy.gsg.models.PackageModel(p.id,p.title,p.description) FROM Package p ORDER BY p.title ASC")
    Page<PackageModel> findAllPackagesWithPagination(Pageable pageable);

    @Query("select new kg.itacademy.gsg.models.PackageModel(p.id,p.title,p.description) FROM Package p ORDER BY p.title ASC")
    List<PackageModel> getAll();
}
