package com.sparta.newsfeed.file.controller;

import com.sparta.newsfeed.file.dto.UploadFileDTO;
import com.sparta.newsfeed.file.dto.UploadResultDTO;
import com.sparta.newsfeed.file.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.util.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class UploadRestController {
    private final FileService fileService;

    @Operation(summary = "이미지 파일 업로드", description = "Post요청을 통해 이미지를 업로드할 수 있다.")
    @PostMapping(value="/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);

        List<UploadResultDTO> list = fileService.uploadFile(uploadFileDTO);

        return list;
    }
    @Operation(summary = "이미지 파일 조회", description = "GET요청을 통해 이미지를 조회할 수 있다.")
    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {
        Resource resource = fileService.searchFile(fileName);

        HttpHeaders httpHeaders = new HttpHeaders();

        try {
            httpHeaders.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(httpHeaders).body(resource);
    }
    @Operation(summary = "이미지 파일 삭제", description = "DELETE요청을 통해 이미지를 삭제할 수 있다.")
    @DeleteMapping("/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName) {
        boolean removed = fileService.removeFile(fileName);

        Map<String, Boolean> resultMap = new HashMap<>();

        resultMap.put("result", removed);

        return resultMap;
    }
}
