package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.MediaFile;
import kg.itacademy.gsg.models.MediaFileModel;
import kg.itacademy.gsg.repositories.MediaFileRepository;
import kg.itacademy.gsg.services.ClientTasksService;
import kg.itacademy.gsg.services.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaFileServiceImpl implements MediaFileService {
    @Autowired
    ClientTasksService clientTasksService;

    @Autowired
    MediaFileRepository mediaFileRepository;

    @Override
    public List<MediaFile> getAllMediaFiles() {
        return mediaFileRepository.findAll();
    }

    @Override
    public Page<MediaFileModel> findAll(Pageable pageable) {
        return mediaFileRepository.findAllMediaFilesWithPagination(pageable);
    }

    @Override
    public MediaFile getMediaFileById(Long id) {
        Optional<MediaFile> m = mediaFileRepository.findById(id);
        return m.orElse(new MediaFile());
    }


    @Override
    public MediaFile saveMediaFile(MediaFileModel mediaFileModel) {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFile(mediaFileModel.getFile());
        mediaFile.setClientTasks(mediaFileModel.getClientTasks());
        return mediaFileRepository.save(mediaFile);
    }

    @Override
    public void deleteMediaFileById(Long id) {
        mediaFileRepository.deleteById(id);
    }

    @Override
    public MediaFile saveMediaFile(String file, byte[] bytes, Long id) {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setTitle(file);
        mediaFile.setFile(bytes);
        mediaFile.setClientTasks(clientTasksService.getById(id));
        return mediaFileRepository.save(mediaFile);
    }

    @Override
    public Page<MediaFileModel> findAllMediaFilesByClientTasksId(Long ctId, Pageable pageable) {
        return mediaFileRepository.findAllMediaFilesByClientTasksId(ctId, pageable);
    }
}
