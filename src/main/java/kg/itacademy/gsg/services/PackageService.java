package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Package;

import java.util.List;

public interface PackageService {
    List<Package> getAllPackages();

    Package getPackageById(Long id);

    void updatePackage(Long id, Package p);

    void savePackage(Package p);

    void deletePackageById(Long id);
}
