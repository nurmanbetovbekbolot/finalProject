package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.models.PackageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PackageService {
    List<Package> getAllPackages();

    Page<PackageModel> findAll(Pageable pageable);

    Package getPackageById(Long id);

    Package updatePackage(PackageModel packageModel);

    Package savePackage(PackageModel packageModel);

    void deletePackageById(Long id);
}
