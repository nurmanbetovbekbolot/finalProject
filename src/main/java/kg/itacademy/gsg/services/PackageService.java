package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.models.CategoryModel;
import kg.itacademy.gsg.models.PackageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PackageService {
    List<Package> getAllPackages();

    List<PackageModel> getAll();

    Page<PackageModel> findAll(Pageable pageable);

    Page<CategoryModel> getAllCategoriesByPackageId(Long id, Pageable pageable);

    Package savePackage(Package p);

    Package getPackageById(Long id);

    Package updatePackage(PackageModel packageModel);

    Package savePackage(PackageModel packageModel);

    void deletePackageById(Long id);
}
