package com.example.spring.others;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.example.spring.Entities.User;
import com.example.spring.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory
            .getLogger(FileUploadController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
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
