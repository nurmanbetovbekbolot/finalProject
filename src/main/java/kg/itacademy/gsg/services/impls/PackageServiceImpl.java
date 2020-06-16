package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.repositories.PackageRepository;
import kg.itacademy.gsg.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    PackageRepository packageRepository;

    @Override
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public Package getPackageById(Long id) {
        Optional<Package> p = packageRepository.findById(id);
        return p.orElse(new Package());
    }

    @Override
    public void updatePackage(Long id, Package p) {

    }

    @Override
    public void savePackage(Package p) {
        packageRepository.save(p);
    }

    @Override
    public void deletePackageById(Long id) {
        packageRepository.deleteById(id);
    }
}
