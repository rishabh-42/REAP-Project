package com.example.spring.Controller;



import com.example.spring.Service.FileUploadService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

  @Autowired
  private FileUploadService fileUploadService;

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
        logger.info("Uploading image " + file.getOriginalFilename());
        fileUploadService.uploadFile(file);
       return "redirect:/dashboard";
    }
}
