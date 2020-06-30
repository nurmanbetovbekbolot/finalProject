package kg.itacademy.gsg.controllers;

import kg.itacademy.gsg.entities.MediaFile;
import kg.itacademy.gsg.models.MediaFileModel;
import kg.itacademy.gsg.models.SomeFile;
import kg.itacademy.gsg.services.MediaFileService;
import kg.itacademy.gsg.utils.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/mediaFile")
public class MediaFileController {
    @Autowired
    private MediaFileService mediaFileService;
    @Autowired
    private ServletContext servletContext;

    String username;

    @GetMapping(value = "/{taskId}/list")
    public String getMediaFileList(@PathVariable("taskId") Long taskId, @PageableDefault(5) Pageable pageable, Model model, Authentication authentication) {
        getUserInfo(authentication);
        Page<MediaFileModel> mediaFileList = mediaFileService.findAllMediaFilesByClientTasksId(taskId,pageable);
        model.addAttribute("fileList", mediaFileList);
        model.addAttribute("userName", username);
        return "admin/list_of_mediaFiles";
    }

    @PostMapping(value = "/{taskId}/create")
    public String addMediaFIle(@PathVariable("taskId") Long taskId, @Valid @ModelAttribute("somefile") SomeFile someFile) throws IOException {
        byte[] bytes = someFile.getDocs().getBytes();
        String modified = System.currentTimeMillis() + someFile.getDocs().getOriginalFilename();
        mediaFileService.saveMediaFile(modified, bytes, taskId);
        return "redirect:/mediaFile/" + taskId + "/list";
    }

    @RequestMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") Long id) throws IOException {
        MediaFile mediaFile = mediaFileService.getMediaFileById(id);
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, mediaFile.getTitle());
        File file = MediaTypeUtils.writeByte(mediaFile.getFile(), mediaFile.getTitle());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length()) //
                .body(resource);
    }

    @PostMapping(value = "/{taskId}/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        mediaFileService.deleteMediaFileById(id);
        return "redirect:/mediaFile/list";
    }

    private void getUserInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        username = userPrincipal.getUsername();
    }
}