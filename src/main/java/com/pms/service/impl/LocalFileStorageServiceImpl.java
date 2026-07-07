package com.pms.service.impl;

import com.pms.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalFileStorageServiceImpl implements FileStorageService {

    private final String uploadDIr = "uploads/resumes/" ;

    @Override
    public String storeFile(MultipartFile file) {
        try{
            Path uploadPath = Paths.get(uploadDIr) ;
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath) ;
            }
            String originalName = StringUtils.cleanPath(file.getOriginalFilename()) ;
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalName ;

            Path targetLocation = uploadPath.resolve(uniqueFileName) ;
            Files.copy(file.getInputStream() , targetLocation , StandardCopyOption.REPLACE_EXISTING) ;

            return targetLocation.toString() ;

        } catch (IOException e) {
            throw new RuntimeException("Could not store file . Please try again " + e);
        }
    }
}
