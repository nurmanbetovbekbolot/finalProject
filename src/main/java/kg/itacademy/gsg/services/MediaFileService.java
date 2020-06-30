package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.MediaFile;
import kg.itacademy.gsg.models.MediaFileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaFileService {
    List<MediaFile> getAllMediaFiles();

    Page<MediaFileModel> findAll(Pageable pageable);

    MediaFile getMediaFileById(Long id);

    MediaFile saveMediaFile(MediaFileModel mediaFileModel);

    void deleteMediaFileById(Long id);

    MediaFile saveMediaFile(String file, byte[] bytes, Long id);

    Page<MediaFileModel> findAllMediaFilesByClientTasksId(Long ctId, Pageable pageable);

}
