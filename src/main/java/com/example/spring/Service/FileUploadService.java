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
                if(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".png") || file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".jpg") ||file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".jpeg")){
                    System.out.println(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));


                byte[] bytes = file.getBytes();
                String rootPath = "/home/ttn/Reap/out/production/resources/static/assets/profileImages";
                String rootPath1 = "/home/ttn/Reap/src/main/resources/static/assets/profileImages";
                File dir = new File(rootPath);
                File dirLocal = new File(rootPath1);
                if (!dir.exists())
                    dir.mkdirs();
                if(!dirLocal.exists()) dirLocal.mkdirs();
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
                File localFile = new File(dirLocal.getAbsolutePath()
                        + File.separator + user.getUserId() + ".png");
                user.setPhoto("/assets/profileImages/" + user.getUserId() + ".png");
                userService.saveUser(user);

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                BufferedOutputStream localFileStream = new BufferedOutputStream(
                        new FileOutputStream(localFile));
                stream.write(bytes);
                localFileStream.write(bytes);
                localFileStream.close();
                stream.close();


                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return "redirect:dashboard";
            } }catch (Exception e) {
                return "You failed to upload " + " => " + e.getMessage();
            }
        }
        else {
            return "You failed to upload "
                    + " because the file was empty.";
        }

        return "fail";

}}
