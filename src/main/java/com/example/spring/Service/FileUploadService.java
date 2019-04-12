package com.example.spring.Service;

import com.example.spring.Controller.FileUploadController;
import com.example.spring.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class FileUploadService {
    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    private static final Logger logger = LoggerFactory
            .getLogger(FileUploadController.class);

    public String uploadFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "/home/ttn/Reap/out/production/resources/static/assets/profileImages";
                File dir = new File(rootPath);
                if (!dir.exists())
                    dir.mkdirs();
                String username;
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal instanceof UserDetails) {
                    username = ((UserDetails) principal).getUsername();
                } else {
                    username = principal.toString();
                }
                User user = userService.findByEmail(username);
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + user.getUserId() + ".png");
                user.setPhoto("/assets/profileImages/" + user.getUserId() + ".png");
                userService.saveUser(user);

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();


                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return "redirect:dashboard";
            } catch (Exception e) {
                return "You failed to upload " + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload "
                    + " because the file was empty.";
        }

    }
}
