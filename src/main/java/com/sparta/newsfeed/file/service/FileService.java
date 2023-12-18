package com.sparta.newsfeed.file.service;

import com.sparta.newsfeed.file.dto.UploadFileDTO;
import com.sparta.newsfeed.file.dto.UploadResultDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Log4j2
public class FileService {
    @Value("${com.sparta.newfeed.upload.path}")
    private String uploadPath;

    public List<UploadResultDTO> uploadFile(UploadFileDTO uploadFileDTO) {

        if(uploadFileDTO.getFiles() != null) {
            final List<UploadResultDTO> list = new ArrayList<>();
            uploadFileDTO.getFiles().forEach(multipartFile ->  {
                String originalName = multipartFile.getOriginalFilename();
                log.info(multipartFile.getOriginalFilename());
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_"+ originalName);
                boolean image = false;

                try {
                    multipartFile.transferTo(savePath);
                    if(Files.probeContentType(savePath).startsWith("image")) {
                        image = true;
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .img(image).build());
            });
            return list;
        }

        return null;

    }

    public Resource searchFile(String fileName) {
        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);

        String resourceName = resource.getFilename();

        return resource;
    }
    public boolean removeFile(String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        String resourceName = resource.getFilename();

        boolean removed = false;
        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed  = resource.getFile().delete();

            if(contentType.startsWith("image")) {
                File thumbnailFile = new File(uploadPath + File.separator+ "s_" + fileName);
                thumbnailFile.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return removed;
    }


}
