package com.example.springprojectgenerator.controllers;

import com.example.springprojectgenerator.models.Entity;
import com.example.springprojectgenerator.models.Field;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController

public class CreateZipController {

    @PostMapping(value = "/download", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<byte[]> download(@Valid @RequestBody List<Entity> entities) throws IOException {
        File folder = new File("models");
        folder.mkdir();
            for (Entity entity : entities) {
                File model = new File(folder, entity.getName() + ".java");
                writeInModel(model, entity);
        }

        File zipFile = new File("generated-directory.zip");
        zipDirectory(folder, zipFile);
        byte [] data = org.apache.commons.io.FileUtils.readFileToByteArray(zipFile);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "generated-directory.zip");
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    private void writeInModel(File model, Entity entity) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("@Data\n@Entity\n@Table\n@NoArgsConstructor\n@AllArgsConstructor\n");
        sb.append("public class ").append(entity.getName());
        sb.append(" {\n");
        for (Field field : entity.getFields()) {
            sb.append("\t").append(field.getModifier()).append(" ")
                    .append(field.getType()).append(" ").append(field.getName())
                    .append(";\n");
        }
        sb.append("\n}");
        FileOutputStream fos1 = new FileOutputStream(model);
        fos1.write(sb.toString().getBytes());
        fos1.close();
    }
    private void zipDirectory(File directory, File zipFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            zip(directory, directory.getName(), zos);
        }
    }

    private void zip(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zos.putNextEntry(new ZipEntry(fileName));
                zos.closeEntry();
            } else {
                zos.putNextEntry(new ZipEntry(fileName + "/"));
                zos.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zip(childFile, fileName + "/" + childFile.getName(), zos);
            }
            return;
        }
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
        }
    }

}
