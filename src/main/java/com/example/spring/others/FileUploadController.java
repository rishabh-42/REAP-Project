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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory
            .getLogger(FileUploadController.class);

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("file") MultipartFile file) {

        System.out.println("aadhfsdnfjkdhasd");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = "/home/ttn/Reap/src/main/resources/static/assets/profileImages";
                File dir = new File(rootPath);
                if (!dir.exists())
                    dir.mkdirs();

                // principal
                String username;

                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                System.out.println("principal " + principal);
                if (principal instanceof UserDetails) {

                    username= ((UserDetails)principal).getUsername();
                    System.out.println(username + "inside username");

                } else {

                    username = principal.toString();
                    System.out.println(username + "inside else username");


                }

                    User user = userService.findByFirstName(username);


                System.out.println(dir.getAbsolutePath() + "  "+ File.separator);

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator+user.getUserId()+".png");

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return "You successfully uploaded file=";
            } catch (Exception e) {
                return "You failed to upload " + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload "
                    + " because the file was empty.";
        }
    }



}
