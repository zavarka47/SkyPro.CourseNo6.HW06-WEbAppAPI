package ru.horwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.horwarts.school.DTO.Avatar;
import ru.horwarts.school.model.Student;
import ru.horwarts.school.repository.AvatarRepository;
import ru.horwarts.school.repository.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${avatar.dir.path")
    private String avatarDir;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void uploadAvatar (Long studentId, MultipartFile file) throws IOException {
        Student student = studentRepository.getById(studentId);

        Path avatarPath = Path.of(avatarDir, studentId + "." + getExtension(file.getOriginalFilename()));

        Files.createDirectories(avatarPath.getParent());
        Files.deleteIfExists(avatarPath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(avatarPath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024))
        {
            bis.transferTo(bos);
        }
        Avatar avatar = findByStudentId(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(avatarPath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setDate(generateImagePreview(avatarPath));

        avatarRepository.save(avatar);
    }

    public Avatar findByStudentId (Long studentId){
        return avatarRepository.findByStudent_StudentId(studentId).orElse(new Avatar());
    }
    private String getExtension (String originalFilename){
        return originalFilename.substring(originalFilename.lastIndexOf('.')+1);
    }
    private byte[] generateImagePreview (Path path) throws IOException {
        try (InputStream is = Files.newInputStream(path)){
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage image = ImageIO.read(bis);

            int height =  image.getWidth() / image.getHeight() * 100;
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphic = preview.createGraphics();
            graphic.drawImage(image, 0, 0, 100, height, null);
            graphic.dispose();

            ImageIO.write(preview, getExtension(path.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }
}
